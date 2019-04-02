package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
        Spinner buttonMucKhac = (Spinner) findViewById(R.id.btnMucKhac);
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
            return true;
        }
        if (id == R.id.menuNgoiSao) {
            Toast.makeText(TrangChuActivity.this, "Bạn đang trang này!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menuAdmin) {
            Intent i = new Intent(this, DangNhapActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            return true;
        }
        if (id == R.id.menuYeuThich) {
            Intent i = new Intent(this, YeuThichActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
