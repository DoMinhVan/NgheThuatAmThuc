package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ManHinhTrangCaNhanActivity extends AppCompatActivity {
    Toolbar toolbarTrangCaNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_trang_ca_nhan_layout);

        //Tool bar
        toolbarTrangCaNhan = (Toolbar) findViewById(R.id.toolbarTrangCaNhan);
        setSupportActionBar(toolbarTrangCaNhan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
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
                Intent intent1 = new Intent(ManHinhTrangCaNhanActivity.this, TrangChuActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();
                return true;
            case R.id.menuThongBao:
                Intent intent2 = new Intent(ManHinhTrangCaNhanActivity.this, ManHinhThongBaoActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                return true;
            case R.id.menuMessenger:
                Intent intent3 = new Intent(ManHinhTrangCaNhanActivity.this, ManHinhTinNhanActivity.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent3);
                return true;
            case R.id.menuAdmin:
                Toast.makeText(ManHinhTrangCaNhanActivity.this, "Bạn đang trang này!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
