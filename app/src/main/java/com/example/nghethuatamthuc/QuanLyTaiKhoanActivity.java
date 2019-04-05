package com.example.nghethuatamthuc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class QuanLyTaiKhoanActivity extends AppCompatActivity {
    Spinner spGioiTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_tai_khoan_layout);
        //Spinner giới tính
        spGioiTinh =  (Spinner) findViewById(R.id.spGioiTinh);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Nam");
        arrayList.add("Nữ");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        spGioiTinh.setAdapter(arrayAdapter);
    }
}
