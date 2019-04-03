package com.example.nghethuatamthuc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.MonAn_NoiBat;


import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    Toolbar toolbarTrangChu;
    //Button btnLike;
    private ArrayList<MonAn_NoiBat> listMembers = new ArrayList<MonAn_NoiBat>();
    private NoiBatAdapter adapter;
    private ListView listView;
    private Button btnLike;
    private boolean DangNhap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu_layout);
        //Button
        Button btnMonNgonMoiNgay = (Button) findViewById(R.id.btnMonNgonMoiNgay);
        Button btnDanhGiaCao = (Button) findViewById(R.id.btnDanhGiaCao);
        Button btnSoiNoi = (Button) findViewById(R.id.btnSoiNoi);

        //RatingBar
        RatingBar simpleRantingBar= (RatingBar) findViewById(R.id.simpleRatingBar);
        //Spinner
        final Spinner buttonMucKhac = (Spinner) findViewById(R.id.btnMucKhac);
        List<String> list = new ArrayList<String>();
        list.add("MÓN KHÁC");
        list.add("Cơm chay");
        list.add("Món nướng");
        list.add("Món xào");
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.spinner_item, list);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        buttonMucKhac.setAdapter(adapterSpinner);

        //Toolbar
        toolbarTrangChu = (Toolbar) findViewById(R.id.toolbarTrangChu);
        setSupportActionBar(toolbarTrangChu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(null);


        listView = (ListView) findViewById(R.id.listMain);

        listMembers.add(new MonAn_NoiBat("Ngô Hiếu","Hamburger","696","Mới đây",5f,1,1));
        listMembers.add(new MonAn_NoiBat("Nguyễn Văn A","Hamburger","5000","Hôm qua",3.5f,0,1));
        listMembers.add(new MonAn_NoiBat("Nguyễn Văn A","Hamburger","5000","Hôm qua",3.5f,0,1));

        adapter = new NoiBatAdapter(this, R.layout.item_info_monan,listMembers);


        btnMonNgonMoiNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Món ngon mỗi ngày!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        btnDanhGiaCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Đánh giá cao!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
        btnSoiNoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Sôi nổi!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

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
                Intent intent1 = new Intent(TrangChuActivity.this, ManHinhTimKiemActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent1);
                return true;
            case R.id.menuNgoiSao:
                Toast.makeText(TrangChuActivity.this, "Bạn đang trang này!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuAdmin:
                if (DangNhap == true) {
                    Intent intent2 = new Intent(TrangChuActivity.this, ManHinhTrangCaNhanActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent2);
                } else {
                    Intent intent3 = new Intent(TrangChuActivity.this, DangNhapActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent3);
                }
                return true;
            case R.id.menuYeuThich:
                Intent intent4 = new Intent(TrangChuActivity.this, YeuThichActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
