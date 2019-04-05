package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtTenDangNhap, edtMatKhau;
    Button btnDangNhap, btnHuy, btnDanhNhapBangFB, btnDangNhapBangGmail;
    RadioGroup radioGroup;
    CheckBox chkLuuDangNhap;

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
        //Dat gia tri khoi tao
        final String username = "minhvan";
        final String password = "123";
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.radNoiTro:
                        Login(username, password);
                        break;
                    case R.id.radAmThuc:
                        Login(username, password);
                        break;
                    case R.id.radDinhDuong:
                        Login(username, password);
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
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "chuyển sang trang ca nhan", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Sai Tài Khoản Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
