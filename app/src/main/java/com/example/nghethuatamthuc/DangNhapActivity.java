package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.NguoiDung;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtTenDangNhap, edtMatKhau;
    Button btnDangNhap, btnHuy, btnDanhNhapBangFB, btnDangNhapBangGmail;
    RadioGroup radioGroup;
    CheckBox chkLuuDangNhap;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<NguoiDung> nguoiDungArrayList = new ArrayList<>();
    private int idNguoiDung = 0;
    private String idDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap_layout);
        //Ánh Xạ
        edtTenDangNhap = (EditText) findViewById(R.id.edtTenDangNhap);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDanhNhapBangFB = (Button) findViewById(R.id.btnDangNhapBangFB);
        btnDangNhapBangGmail = (Button) findViewById(R.id.btnDangNhapBangGmail);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        chkLuuDangNhap = (CheckBox) findViewById(R.id.chkLuuDangNhap);
        //Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        //get đối tượng người dùng
        myRef.child("NguoiDung").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    NguoiDung nguoiDung = dataSnapshot.getValue(NguoiDung.class);
                    nguoiDungArrayList.add(nguoiDung);

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Xuat

        //Dat gia tri khoi tao
        final String username = "minhvan";
        final String password = "123";
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.radNoiTro:
                        idNguoiDung = 1;
                        for(int i = 0 ;  i < nguoiDungArrayList.size(); i++){
                            if(idNguoiDung ==  (int)nguoiDungArrayList.get(i).getLoaiNguoiDung()) {
                                idDangNhap = nguoiDungArrayList.get(i).getIDNguoiDung();
                                Login(nguoiDungArrayList.get(i).getTenDangNhap(), nguoiDungArrayList.get(i).getMatKhau());
                            }
                        }
                        break;
                    case R.id.radAmThuc:
                        idNguoiDung = 3;
                        for(int i = 0 ;  i < nguoiDungArrayList.size(); i++){
                            if(idNguoiDung ==  (int)nguoiDungArrayList.get(i).getLoaiNguoiDung()) {
                                idDangNhap = nguoiDungArrayList.get(i).getIDNguoiDung();
                                Login(nguoiDungArrayList.get(i).getTenDangNhap(), nguoiDungArrayList.get(i).getMatKhau());
                            }
                        }
                        break;
                    case R.id.radDinhDuong:
                        idNguoiDung = 2;
                        for(int i = 0 ;  i < nguoiDungArrayList.size(); i++){
                            if(idNguoiDung ==  (int)nguoiDungArrayList.get(i).getLoaiNguoiDung()) {
                                idDangNhap = nguoiDungArrayList.get(i).getIDNguoiDung();
                                Login(nguoiDungArrayList.get(i).getTenDangNhap(), nguoiDungArrayList.get(i).getMatKhau());
                            }
                        }
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Ban Chua Chon Doi Tuong", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                startActivity(i);
            }
        });
        btnDanhNhapBangFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Chuyen Sang Facebook", Toast.LENGTH_SHORT).show();
            }
        });
        btnDangNhapBangGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Chuyen sang gmail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Login(String ten, String matkhau) {
        if (edtTenDangNhap.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Tên Đăng Nhập", Toast.LENGTH_SHORT).show();
        } else if (edtMatKhau.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
        } else {
            if (edtTenDangNhap.getText().toString().equals(ten) && edtMatKhau.getText().toString().equals(matkhau)) {
                if (chkLuuDangNhap.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Luu mat khau", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "chuyển sang trang ca nhan", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DangNhapActivity.this, ManHinhTrangCaNhanActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(DangNhapActivity.this, ManHinhTrangCaNhanActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("idnguoidung",idDangNhap+"");
                    i.putExtras(bundle);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "chuyển sang trang ca nhan", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
