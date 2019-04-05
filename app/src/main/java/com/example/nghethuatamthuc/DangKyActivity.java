package com.example.nghethuatamthuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DangKyActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner spNguoiDung,spGioiTinh;
    EditText edtHoTen,edtEmail,edtSDT,edtNgaySinh,edtMatKhau,edtNhapLaiMatKhau;
    Button  btnDangKy,btnHuy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky_layout);
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
    }

    @Override
    public void onClick(View v) {
        if(v == btnDangKy){
            if(KiemTra()) {
                Toast.makeText(getApplicationContext(), "Dang Ky", Toast.LENGTH_SHORT).show();
            }
        }
        if(v == btnHuy){
           Toast.makeText(getApplicationContext(), "Huy", Toast.LENGTH_SHORT).show();
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
}
