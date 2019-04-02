package com.example.nghethuatamthuc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

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
}
