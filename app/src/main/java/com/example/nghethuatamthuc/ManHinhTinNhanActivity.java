package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.nghethuatamthuc.models.TinNhan;

import java.util.ArrayList;

public class ManHinhTinNhanActivity extends AppCompatActivity {
    Toolbar toolbarTinNhan;
    ListView lvTinNhan;
    ArrayList<TinNhan> tinNhans;
    TinNhanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_tin_nhan_layout);
        //Toolbar
        toolbarTinNhan = (Toolbar) findViewById(R.id.toolbarTinNhan);
        setSupportActionBar(toolbarTinNhan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        toolbarTinNhan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish();
                Intent intent = new Intent(ManHinhTinNhanActivity.this,ManHinhThongBaoActivity.class);
                startActivity(intent);
            }
        });
        //Listview thong báo
        lvTinNhan = (ListView) findViewById(R.id.lvTinNhan);
        tinNhans = new ArrayList<>();

        tinNhans.add(new TinNhan(R.drawable.iconuser,"Nguoi Dung 1","Hello"));
        tinNhans.add(new TinNhan(R.drawable.iconuser,"Nguoi Dung 2","Hello"));
        tinNhans.add(new TinNhan(R.drawable.iconuser,"Nguoi Dung 3","Hello"));
        tinNhans.add(new TinNhan(R.drawable.iconuser,"Nguoi Dung 4","Hello"));
        tinNhans.add(new TinNhan(R.drawable.iconuser,"Nguoi Dung 5","Hello"));

        adapter = new TinNhanAdapter(tinNhans,this,R.layout.item_tin_nhan);
        lvTinNhan.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tin_nhan,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
