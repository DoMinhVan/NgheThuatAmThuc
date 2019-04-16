package com.example.nghethuatamthuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.MonAn_NoiBat;

import java.util.ArrayList;

public class QuanLyBaiVietActivity extends AppCompatActivity {
    Toolbar toolbarQuanLyBaiViet;

    private ArrayList<MonAn_NoiBat> listMembers = new ArrayList<MonAn_NoiBat>();
    private QuanLyBaiVietAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlybaiviet_layout);

        //Toolbar
        toolbarQuanLyBaiViet = (Toolbar) findViewById(R.id.toolbarQuanLyBaiViet);
        setSupportActionBar(toolbarQuanLyBaiViet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        ListView listView = (ListView) findViewById(R.id.listQuanLyBaiViet);

        listMembers.add(new MonAn_NoiBat("Ngô Hiếu","Hamburger","696","Mới đây",5,1,0));

        adapter = new QuanLyBaiVietAdapter(this, R.layout.item_info_quanlybaiviet,listMembers);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quan_ly_bai_viet,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            Intent intent = new Intent(QuanLyBaiVietActivity.this, ManHinhTrangCaNhanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuThem) {
            Intent intent1 = new Intent(QuanLyBaiVietActivity.this, ThemBaiVietActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
