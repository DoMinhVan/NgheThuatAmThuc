package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.ThongBao;

import java.util.ArrayList;

public class ManHinhThongBaoActivity extends AppCompatActivity {
    Toolbar toolbarThongBao;
    ListView lvThongBao;
    ArrayList<ThongBao> thongBaos;
    ThongBaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_thong_bao_layout);
        //Toolbar
        toolbarThongBao = (Toolbar) findViewById(R.id.toolbarThongBao);
        setSupportActionBar(toolbarThongBao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        //Listview thong báo
        lvThongBao = (ListView) findViewById(R.id.lvThongBao);
        thongBaos = new ArrayList<>();
        thongBaos.add(new ThongBao(R.drawable.iconuser,"Nguoi Dung 1","3 phút trước"));
        thongBaos.add(new ThongBao(R.drawable.iconuser,"Nguoi Dung 2","10 phút trước"));
        thongBaos.add(new ThongBao(R.drawable.iconuser,"Nguoi Dung 3","25 phút trước"));
        thongBaos.add(new ThongBao(R.drawable.iconuser,"Nguoi Dung 4","30 phút trước"));
        thongBaos.add(new ThongBao(R.drawable.iconuser,"Nguoi Dung 5","40 phút trước"));

        adapter = new ThongBaoAdapter(thongBaos,this,R.layout.item_thong_bao);
        lvThongBao.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tin_nhan,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                Intent intent1 = new Intent(ManHinhThongBaoActivity.this, TrangChuActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();
                return true;
            case R.id.menuThongBao:
                Toast.makeText(ManHinhThongBaoActivity.this, "Bạn đang trang này!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuMessenger:
                Intent intent3 = new Intent(ManHinhThongBaoActivity.this, ManHinhTinNhanActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent3);
                return true;
            case R.id.menuAdmin:
                Intent intent2 = new Intent(ManHinhThongBaoActivity.this, ManHinhTrangCaNhanActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
