package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.NguoiDung;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManHinhTrangCaNhanActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarTrangCaNhan;
    ImageView imgAnhBia;
    CircleImageView imgAnhDaiDien;
    TextView  txtUser,txtNgayThamGia;
    Button btnQuanLyTaiKhoan,btnQuanLyBaiViet;
    NguoiDung nguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_trang_ca_nhan_layout);

        //Giả lập người dùng
        nguoiDung = new NguoiDung("LctqUfnB9x3d9OVGQow","HuuPhu","Huuphudn2015","123","0123456789","Huuphudn2015@gmail.com","16/04/1998",1,1,"","","20/04/2019",1);

        Bundle bundle = getIntent().getExtras();
        //Log.d("TestBundle", bundle+"");
        if (bundle != null) {
            String idnguoidung = bundle.getString("idnguoidung","0");
            //String value = extras.getString("uri_image");
            //The key argument here must match that used in the other activity
            //Log.d("TestBundle1", bundle+"");
        }

        //Tool bar
        toolbarTrangCaNhan = (Toolbar) findViewById(R.id.toolbarTrangCaNhan);
        setSupportActionBar(toolbarTrangCaNhan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        //Ánh xạ
        AnhXa();
        //Bắt sự  kiện tab
        btnQuanLyTaiKhoan.setOnClickListener(this);
        btnQuanLyBaiViet.setOnClickListener(this);

        //Intent getIntentDangNhap = getIntent();
        //nguoiDung = (NguoiDung) getIntentDangNhap.getSerializableExtra("NguoiDung");

        txtUser.setText(nguoiDung.getHoTen());
        txtNgayThamGia.setText(nguoiDung.getThoiGian());
    }

    private void AnhXa() {
        imgAnhBia = (ImageView) findViewById(R.id.imgAnhBia);
        imgAnhDaiDien = (CircleImageView) findViewById(R.id.imgHinhDaiDien);
        txtUser = (TextView) findViewById(R.id.txtUser);
        txtNgayThamGia = (TextView) findViewById(R.id.txtNgayThamGia);
        btnQuanLyTaiKhoan = (Button) findViewById(R.id.btnQuanLyTaiKhoan);
        btnQuanLyBaiViet = (Button) findViewById(R.id.btnQuanLyBaiViet);
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

    @Override
    public void onClick(View v) {
        if (v == btnQuanLyTaiKhoan){
            Intent intent1 = new Intent(ManHinhTrangCaNhanActivity.this, QuanLyTaiKhoanActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent1);
        }
        if (v == btnQuanLyBaiViet){
            Intent intent1 = new Intent(ManHinhTrangCaNhanActivity.this, QuanLyBaiVietActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent1);
        }
    }
}
