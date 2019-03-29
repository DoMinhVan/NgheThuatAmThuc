package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.MonAn_NoiBat;

import java.util.ArrayList;

public class TrangChuActivity extends AppCompatActivity {
    Toolbar toolbarTrangChu;

    private ArrayList<MonAn_NoiBat> listMembers = new ArrayList<MonAn_NoiBat>();
    private NoiBatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu_layout);
        Button buttonMucKhac = (Button) findViewById(R.id.btnMucKhac);
        //buttonMucKhac.getResources();

        //Toolbar
        toolbarTrangChu = (Toolbar) findViewById(R.id.toolbarTrangChu);
        setSupportActionBar(toolbarTrangChu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(null);

        ListView listView = (ListView) findViewById(R.id.listMain);

        listMembers.add(new MonAn_NoiBat("Ngô Hiếu","Hamburger","696","Mới đây","5 Sao"));

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuTimKiem) {
            Toast.makeText(TrangChuActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.menuNgoiSao) {
            Toast.makeText(TrangChuActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.menuAdmin) {
            Toast.makeText(TrangChuActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, DangNhapActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuYeuThich) {
            Toast.makeText(TrangChuActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
