package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ManHinhTrangCaNhanActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarTrangCaNhan;
    ImageView imgAnhBia;
    ImageView imgAnhDaiDien;
    ProfilePictureView imgFacebook;
    TextView  txtUser,txtNgayThamGia;
    Button btnQuanLyTaiKhoan,btnQuanLyBaiViet;
    NguoiDung nguoiDung;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_trang_ca_nhan_layout);
        //Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
/*
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
*/
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
        //Kiểm  tra trường hợp đăng nhập
        int KiemTra = DangNhapActivity.KIEM_TRA_DANG_NHAP;
        switch (KiemTra){
            case 1 :
                LayThongTinNguoiDung();
                break;
            case 2 :
                LayThongTinTuFacebook();
                break;
            case 3 :
                LayThongTinTuGmail();
                break;
            default:break;
        }
    }

    private void LayThongTinNguoiDung() {
        Intent intent = getIntent();
        final String idNguoiDung =  intent.getStringExtra("NguoiDung");
         myRef.child("NguoiDung").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                   if (dataSnapshot1.getKey().toString().equals(idNguoiDung)){//so sanh voi id nguoidung doc du lieu
                       NguoiDung ngDung = dataSnapshot1.getValue(NguoiDung.class);
                       txtUser.setText(ngDung.getHoTen().toString());
                       txtNgayThamGia.setText(ngDung.getThoiGian().toString());
                   }


               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }

    private void LayThongTinTuGmail() {
        Toast.makeText(ManHinhTrangCaNhanActivity.this,"Ban đang đăng nhập gmail",Toast.LENGTH_SHORT).show();
    }

    private void LayThongTinTuFacebook() {
        //nhận các thông tin  từ facebook
        Intent i = getIntent();
        String id =  i.getStringExtra("ID");
        String ten = i.getStringExtra("TenFB");
        imgAnhDaiDien.setVisibility(View.GONE);
        imgFacebook.setVisibility(View.VISIBLE);
        imgFacebook.setProfileId(id);
        txtUser.setText(ten);
        Toast.makeText(ManHinhTrangCaNhanActivity.this,"Ban đang đăng nhập facebook",Toast.LENGTH_SHORT).show();
    }
    private void AnhXa() {
        imgAnhBia = (ImageView) findViewById(R.id.imgAnhBia);
        imgAnhDaiDien = (ImageView) findViewById(R.id.imgHinhDaiDien);
        txtUser = (TextView) findViewById(R.id.txtUser);
        txtNgayThamGia = (TextView) findViewById(R.id.txtNgayThamGia);
        btnQuanLyTaiKhoan = (Button) findViewById(R.id.btnQuanLyTaiKhoan);
        btnQuanLyBaiViet = (Button) findViewById(R.id.btnQuanLyBaiViet);
        imgFacebook = (ProfilePictureView) findViewById(R.id.imgFacebook);
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
