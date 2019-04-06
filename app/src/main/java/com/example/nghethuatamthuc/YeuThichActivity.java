package com.example.nghethuatamthuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.MonAn_NoiBat;

import java.util.ArrayList;

public class YeuThichActivity extends AppCompatActivity {
    Toolbar toolbarYeuThich;

    private ArrayList<BaiViet> listMembers = new ArrayList<BaiViet>();
    private NoiBatAdapter adapter;
    private boolean DangNhap = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeuthich_layout);

        //Toolbar
        toolbarYeuThich = (Toolbar) findViewById(R.id.toolbarYeuThich);
        setSupportActionBar(toolbarYeuThich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        ListView listView = (ListView) findViewById(R.id.listYeuThich);

        //listMembers.add(new MonAn_NoiBat("Ngô Hiếu","Hamburger","696","Mới đây",5f,0,0));

        adapter = new NoiBatAdapter(this, R.layout.item_info_monan,listMembers);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trang_chu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuTimKiem:
                Intent intent1 = new Intent(YeuThichActivity.this, ManHinhTimKiemActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent1);
                return true;
            case R.id.menuNgoiSao:
                Intent intent2 = new Intent(YeuThichActivity.this, TrangChuActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                return true;
            case R.id.menuAdmin:
                if (DangNhap == true) {
                    Intent intent3 = new Intent(YeuThichActivity.this, ManHinhTrangCaNhanActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent3);
                } else {
                    Intent intent4 = new Intent(YeuThichActivity.this, DangNhapActivity.class);
                    intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent4);
                }
                return true;
            case R.id.menuYeuThich:
                Toast.makeText(YeuThichActivity.this, "Bạn đang ở trang này", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
