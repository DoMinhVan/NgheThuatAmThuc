package com.example.nghethuatamthuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.NguoiDung;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spNguoiDung,spGioiTinh;
    EditText edtHoTen,edtEmail,edtSDT,edtNgaySinh,edtMatKhau,edtNhapLaiMatKhau,edtTenDangNhap;
    Button  btnDangKy,btnHuy;
    NguoiDung nguoiDung;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky_layout);
        //Firebase
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference();
        //Ánh xạ
        AnhXa();
        //Bat su kien
        btnDangKy.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        //Spinner
        //Gioi tinh
        List<String> list = new ArrayList<String>();
        list.add("Nam");
        list.add("Nữ");
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, list);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spGioiTinh.setAdapter(adapterSpinner);
        //Nguoi dung
        List<String> list1 = new ArrayList<String>();
        list1.add("Nội Trợ");
        list1.add("Chuyên Gia Ẩm Thực");
        list1.add("Chuyên Gia Dinh Dưỡng");
        ArrayAdapter<String> adapterSpinner1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, list1);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spNguoiDung.setAdapter(adapterSpinner1);
        //bat su kien cac editext
        edtHoTen.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(edtHoTen.getText().toString().isEmpty()){

                }else {
                    if(kiemTraHoten(edtHoTen.getText().toString())){
                        Toast.makeText(DangKyActivity.this, "Họp lệ" ,Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DangKyActivity.this, "Họ ten không đúng định dạng" ,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        edtTenDangNhap.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(kiemTraHoten(edtHoTen.getText().toString())){
                    Toast.makeText(DangKyActivity.this, "Họp lệ" ,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DangKyActivity.this, "Họ ten không đúng định dạng" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(kiemTraEmail(edtEmail.getText().toString())){
                    Toast.makeText(DangKyActivity.this, "Họp lệ" ,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DangKyActivity.this, "Email không đúng định dạng" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        edtSDT.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(kiemTraSDT(edtSDT.getText().toString())){
                    Toast.makeText(DangKyActivity.this, "Họp lệ" ,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DangKyActivity.this, "SDT không đúng định dạng" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        edtNgaySinh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(kiemTraNgay(edtNgaySinh.getText().toString())){
                    Toast.makeText(DangKyActivity.this, "Họp lệ" ,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DangKyActivity.this, "Ngay Sinh không đúng định dạng" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        spNguoiDung = (Spinner) findViewById(R.id.spnNguoiDung);
        spGioiTinh=(Spinner)findViewById(R.id.spnGioiTinh);
        edtHoTen =  (EditText) findViewById(R.id.edtHoTen);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        edtNgaySinh =(EditText) findViewById(R.id.edtNgaySinh);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        edtNhapLaiMatKhau=(EditText)findViewById(R.id.edtNhapLaiMatKhau);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnHuy=(Button) findViewById(R.id.btnHuy);
        edtTenDangNhap = (EditText)  findViewById(R.id.edtHoTen2);
    }

    @Override
    public void onClick(View v) {
        if(v == btnDangKy){
            if(kiemTraHoten(edtHoTen.getText().toString()) && kiemTraNgay(edtNgaySinh.getText().toString())
                    && kiemTraSDT(edtSDT.getText().toString()) && kiemTraEmail(edtEmail.getText().toString())){
                Calendar calendar = Calendar.getInstance();
                final String key = myRef.child("NguoiDung").push().getKey();
                nguoiDung = new NguoiDung(key,edtHoTen.getText().toString(),edtTenDangNhap.getText().toString()
                        ,edtMatKhau.getText().toString(),edtSDT.getText().toString(),edtEmail.getText().toString(),
                        edtNgaySinh.getText().toString(), spGioiTinh.getSelectedItemPosition(),spNguoiDung.getSelectedItemPosition(),
                        "","", calendar.getTime()
                        +"",0);
                myRef.child("NguoiDung").child(key).setValue(nguoiDung);
            }else {
                Toast.makeText(this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
            }

        }
        if(v == btnHuy){
            finish();
        }
    }
    private boolean KiemTra(){
        if(edtHoTen.getText().toString().equals("")){
            Toast.makeText(this,"Ban Chua Nhap Ho Ten",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtEmail.getText().toString().equals("")){
            Toast.makeText(this,"Ban Chua Nhap Email",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtSDT.getText().toString().equals("")){
            Toast.makeText(this,"Ban Chua Nhap So Dien Thoai",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtNgaySinh.getText().toString().equals("")){
            Toast.makeText(this,"Ban Chua Nhap Ngay Sinh",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtMatKhau.getText().toString().equals("")){
            Toast.makeText(this,"Ban Chua Nhap Mat Khau",Toast.LENGTH_SHORT).show();
            return false;
        }else if(edtNhapLaiMatKhau.getText().toString().equals("")){
            Toast.makeText(this,"Ban Chua Nhap Lại Mat Khau",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private boolean kiemTraHoten(String strHoTen){
        String chuoi = "^\\D+[^~!@#$%^&*()]";
        Pattern pattern = Pattern.compile(chuoi);
        Matcher matcher = pattern.matcher(strHoTen);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }
    }
    private boolean kiemTraEmail(String strEmail){
        String chuoi = "^\\w[a-z0-9A-Z]+@{1}\\w+mail.com$";
        Pattern pattern = Pattern.compile(chuoi);
        Matcher matcher = pattern.matcher(strEmail);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }
    }
    private boolean kiemTraNgay(String strNgay){
        String chuoi = "^\\d{2}+/{1}+\\d{2}+/{1}+\\d{4}$";
        Pattern pattern = Pattern.compile(chuoi);
        Matcher matcher = pattern.matcher(strNgay);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }
    }
    private boolean kiemTraSDT(String strSDT){
        String chuoi = "^0[937]{1}+\\d{8}$";
        Pattern pattern = Pattern.compile(chuoi);
        Matcher matcher = pattern.matcher(strSDT);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }
    }
    private boolean kiemTraMatKhau(String strMatKhau){
        String chuoi = "^\\w[a-z0-9A-Z]+\\w[0-9]+\\w[A-Z]$";
        Pattern pattern = Pattern.compile(chuoi);
        Matcher matcher = pattern.matcher(strMatKhau);
        if(matcher.find()){
            return true;
        }else{
            return false;
        }
    }
}
