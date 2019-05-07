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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManHinhTimKiemActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbarTimKiem;
    boolean DangNhap = false;
    Spinner spDanhMuc,spDoiTuong;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btnTim, btnHuy;
    EditText edtTimKiem;
    ArrayList<BaiViet> listKetQua = new ArrayList<>();
    private static boolean trangThaiTimKiem = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_tim_kiem_layout);
        //Ánh xạ
        AnhXa();
        //Toolbar
        setSupportActionBar(toolbarTimKiem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        //Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        //Spinner
        //danh muc
        final List<String> listLoaiMon = new ArrayList<String>();
        listLoaiMon.add("Chọn Loại Món");
        myRef.child("LoaiMon").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    String value = dataSnapshot.getValue(String.class);
                    listLoaiMon.add(value);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, listLoaiMon);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spDanhMuc.setAdapter(adapterSpinner);
        //doi tuong
        final List<String> listDoiTuong = new ArrayList<String>();
        listDoiTuong.add("Chọn Đối Tượng");
        myRef.child("DoiTuong").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               if(dataSnapshot.exists()) {
                   String value = dataSnapshot.getValue(String.class);
                   listDoiTuong.add(value);
               }
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, listDoiTuong);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spDoiTuong.setAdapter(adapterSpinner2);
        //Bắt sự kien click
        btnTim.setOnClickListener(this);
    }

    private void AnhXa() {
        toolbarTimKiem = (Toolbar) findViewById(R.id.toolbarTimKiem);
        spDanhMuc = (Spinner) findViewById(R.id.spDanhMuc);
        spDoiTuong = (Spinner) findViewById(R.id.spDoiTuong);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnTim = (Button) findViewById(R.id.btnTim);
        edtTimKiem = (EditText) findViewById(R.id.edtTimKiem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trang_chu,menu);
        menu.getItem(3).setIcon(R.mipmap.icons8_search_selected_48);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuTimKiem:
                Toast.makeText(ManHinhTimKiemActivity.this, "Bạn đang trang này!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuNgoiSao:
                Intent intent2 = new Intent(ManHinhTimKiemActivity.this, TrangChuActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                return true;
            case R.id.menuAdmin:
                if (DangNhap == true) {
                    Intent intent3 = new Intent(ManHinhTimKiemActivity.this, ManHinhTrangCaNhanActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent3);
                } else {
                    Intent intent4 = new Intent(ManHinhTimKiemActivity.this, DangNhapActivity.class);
                    intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent4);
                }
                return true;
            case R.id.menuYeuThich:
                Intent intent5 = new Intent(ManHinhTimKiemActivity.this, YeuThichActivity.class);
                intent5.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnTim){
            TimKiem();
            Toast.makeText(this, spDoiTuong.getSelectedItemPosition()+"", Toast.LENGTH_SHORT).show();
        }
    }

    private void TimKiem() {
        final String tuKhoa  = edtTimKiem.getText().toString().toString();
        final String doiTuong = spDoiTuong.getSelectedItemPosition()+"";
       final  String danhMuc  = spDanhMuc.getSelectedItemPosition()+"";
        //lấy dữ liệu từ firebase
        myRef.child("BaiViet").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (doiTuong.equals(dataSnapshot1.child("doiTuong").getValue().toString()) &&
                            danhMuc.equals(dataSnapshot1.child("loaiMon").getValue().toString())){
                        Toast.makeText(ManHinhTimKiemActivity.this, "Tìm theo tất cả yêu cầu", Toast.LENGTH_SHORT).show();
                        Query query  = FirebaseDatabase.getInstance().getReference("BaiViet").orderByChild("tenBaiViet").startAt("%"+tuKhoa+"%");
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSot) {
                                listKetQua.clear();
                                if(dataSot.exists()){
                                    for (DataSnapshot dataS : dataSot.getChildren()){
                                        BaiViet viet = dataS.getValue(BaiViet.class);
                                        listKetQua.add(viet);
                                    }
                                }
                                for (int i = 0 ; i < listKetQua.size() ; i++){
                                    Log.d("ketqua",listKetQua.get(i).getTenBaiViet().toString());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                       // BaiViet baiViet = dataSnapshot1.getValue(BaiViet.class);
                        //listKetQua.add(baiViet);
                       //// Intent i = new Intent(ManHinhTimKiemActivity.this,KetQuaTimKiemActivity.class);
                       // Bundle bundle = new Bundle();
                       // bundle.putSerializable("BaiViet",(Serializable) listKetQua);
                       // i.putExtra("KetQua",bundle);
                     //   startActivity(i);
                       // Toast.makeText(ManHinhTimKiemActivity.this,listKetQua.get(0).getTenBaiViet().toString(), Toast.LENGTH_SHORT).show();
                        trangThaiTimKiem = true;
                    }else if (tuKhoa.equals(dataSnapshot1.child("tenBaiViet").getValue().toString()) && spDanhMuc.getSelectedItemPosition() == 0 && spDoiTuong.getSelectedItemPosition() == 0){
                        Toast.makeText(ManHinhTimKiemActivity.this, "tim theo ten", Toast.LENGTH_SHORT).show();
                        BaiViet baiViet = dataSnapshot1.getValue(BaiViet.class);
                        listKetQua.add(baiViet);
                        Toast.makeText(ManHinhTimKiemActivity.this, listKetQua.get(0).getTenBaiViet().toString(), Toast.LENGTH_SHORT).show();
                        trangThaiTimKiem = true;
                    }else if(tuKhoa.equals(dataSnapshot1.child("tenBaiViet").getValue().toString()) && danhMuc.equals(dataSnapshot1.child("loaiMon").getValue().toString())){
                        BaiViet baiViet = dataSnapshot1.getValue(BaiViet.class);
                        listKetQua.add(baiViet);
                        Toast.makeText(ManHinhTimKiemActivity.this, listKetQua.get(0).getTenBaiViet().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(ManHinhTimKiemActivity.this, "tim theo ten và danh  mục", Toast.LENGTH_SHORT).show();
                        trangThaiTimKiem = true;
                    }else if(tuKhoa.equals(dataSnapshot1.child("tenBaiViet").getValue().toString()) && doiTuong.equals(dataSnapshot1.child("doiTuong").getValue().toString())){
                        BaiViet baiViet = dataSnapshot1.getValue(BaiViet.class);
                        listKetQua.add(baiViet);
                        Toast.makeText(ManHinhTimKiemActivity.this, listKetQua.get(0).getTenBaiViet().toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(ManHinhTimKiemActivity.this, "tim theo ten va doi tuong", Toast.LENGTH_SHORT).show();
                        trangThaiTimKiem = true;
                    }
                }
                if(trangThaiTimKiem == false){
                    Toast.makeText(ManHinhTimKiemActivity.this, "Khong tim thay", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
