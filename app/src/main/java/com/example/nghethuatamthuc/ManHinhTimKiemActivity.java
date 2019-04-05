package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ManHinhTimKiemActivity extends AppCompatActivity {
    Toolbar toolbarTimKiem;
    boolean DangNhap = false;
    Spinner spDanhMuc,spDoiTuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_tim_kiem_layout);
        //Toolbar
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuTimKiem:
                Toast.makeText(ManHinhTimKiemActivity.this, "Bạn đang trang này!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuNgoiSao:
                Intent intent2 = new Intent(ManHinhTimKiemActivity.this, TrangChuActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                return true;
            case R.id.menuAdmin:
                if (DangNhap == true) {
                    Intent intent3 = new Intent(ManHinhTimKiemActivity.this, ManHinhTrangCaNhanActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent3);
                } else {
                    Intent intent4 = new Intent(ManHinhTimKiemActivity.this, DangNhapActivity.class);
                    intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent4);
                }
                return true;
            case R.id.menuYeuThich:
                Intent intent5 = new Intent(ManHinhTimKiemActivity.this, YeuThichActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
