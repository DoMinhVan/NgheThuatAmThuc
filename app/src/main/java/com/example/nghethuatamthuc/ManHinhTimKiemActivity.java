package com.example.nghethuatamthuc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ManHinhTimKiemActivity extends AppCompatActivity {
    Toolbar toolbarTimKiem;
    Spinner spDanhMuc,spDoiTuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_tim_kiem_layout);
        Toolbar
        toolbarTimKiem = (Toolbar) findViewById(R.id.toolbarTimKiem);
        setSupportActionBar(toolbarTimKiem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        //Spinner
        //danh muc
        spDanhMuc = (Spinner) findViewById(R.id.spDanhMuc);
        List<String> list = new ArrayList<String>();
        list.add("Món Lẩu");
        list.add("Món Nướng");
        list.add("Món Khai vị");
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, list);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spDanhMuc.setAdapter(adapterSpinner);
        //doi tuong
        spDoiTuong = (Spinner) findViewById(R.id.spDoiTuong);
        List<String> list2 = new ArrayList<String>();
        list2.add("Bình Thường");
        list2.add("Ăn Kiêng");
        list2.add("Người Bệnh Tiểu Đường");
        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, list2);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spDoiTuong.setAdapter(adapterSpinner2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trang_chu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
