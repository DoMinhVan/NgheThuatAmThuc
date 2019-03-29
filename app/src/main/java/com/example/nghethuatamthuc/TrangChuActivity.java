package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class TrangChuActivity extends AppCompatActivity {
    Toolbar toolbarTrangChu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu_layout);
        //Toolbar
        toolbarTrangChu = (Toolbar) findViewById(R.id.toolbarTrangChu);
        setSupportActionBar(toolbarTrangChu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trang_chu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
