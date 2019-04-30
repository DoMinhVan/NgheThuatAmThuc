package com.example.nghethuatamthuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.DanhGiaBaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.example.nghethuatamthuc.models.NguoiDung;
import com.example.nghethuatamthuc.models.Thich;
import com.example.nghethuatamthuc.models.YeuThich;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class YeuThichActivity extends AppCompatActivity {
    Toolbar toolbarYeuThich;

    private ArrayList<BaiViet> listBaiViet = new ArrayList<BaiViet>();
    private ArrayList<HinhAnh> listHinhAnh = new ArrayList<HinhAnh>();
    private ArrayList<DanhGiaBaiViet> listDanhGiaBaiViet = new ArrayList<DanhGiaBaiViet>();
    private ArrayList<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
    private ArrayList<YeuThich> listYeuThich = new ArrayList<YeuThich>();
    private ArrayList<Thich> listThich = new ArrayList<Thich>();
    NguoiDung nguoiDung;
    private NoiBatAdapter adapter;
    private boolean DangNhap = false;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yeuthich_layout);

        listNguoiDung.clear();
        listBaiViet.clear();
        listHinhAnh.clear();
        listDanhGiaBaiViet.clear();
        listYeuThich.clear();
        listThich.clear();

        //Toolbar
        toolbarYeuThich = (Toolbar) findViewById(R.id.toolbarYeuThich);
        setSupportActionBar(toolbarYeuThich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        ListView listView = (ListView) findViewById(R.id.listYeuThich);

        //listMembers.add(new MonAn_NoiBat("Ngô Hiếu","Hamburger","696","Mới đây",5f,0,0));

        nguoiDung = new NguoiDung("-LctqUfnB9x3d9OVGQow","HuuPhu","Huuphudn2015","123","0123456789","Huuphudn2015@gmail.com","16/04/1998",1,1,"","","20/04/2019",1);

        myRef.child("YeuThich").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    final YeuThich yeuThich = dataSnapshot.getValue(YeuThich.class);
                    if(yeuThich.getiDNGuoiDungYeuThich().equals(nguoiDung.getIDNguoiDung())) {

                        myRef.child("BaiViet").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                if(dataSnapshot.exists()) {
                                    BaiViet baiViet = dataSnapshot.getValue(BaiViet.class);
                                    if(yeuThich.getiDBaiVietYeuThich().equals(baiViet.getiD())) {
                                        listBaiViet.add(baiViet);
                                        listYeuThich.add(yeuThich);
                                        adapter.notifyDataSetChanged();
                                    }
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
                    }
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

        //ĐỌC TẤT CẢ HÌNH ẢNH ĐỔ VÀO LISTHINHANH
        myRef.child("HinhAnh").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HinhAnh value = dataSnapshot.getValue(HinhAnh.class);
                listHinhAnh.add(value);
                /*if(dataSnapshot==null) Toast.makeText(context, "Null", Toast.LENGTH_LONG);
                else {
                    final HinhAnh value = dataSnapshot.getValue(HinhAnh.class);
                    //listHinhAnh.add(value);
                    if (baiViet.getID() == value.getIDLoai()) {

                        Toast.makeText(context, baiViet.getID() + "  " + value.getIDLoai() + "", Toast.LENGTH_LONG);

                        StorageReference islandRef = storageRef.child(value.getDuongDan());

                        Log.d("TestDuongDan", islandRef + "");

                        //MyAppGlideModule Glide = new MyAppGlideModule();
                        Glide.with(context)
                                .load(islandRef)
                                .into(hinhMonAn);
                    }
                }*/
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

        //ĐỌC TẤT CẢ NGƯỜI DÙNG VÀ ĐỔ VÀO LISTNGUOIDUNG
        myRef.child("NguoiDung").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                NguoiDung nguoiDung = dataSnapshot.getValue(NguoiDung.class);
                listNguoiDung.add(nguoiDung);
                /*if(dataSnapshot==null) Toast.makeText(context, "Null", Toast.LENGTH_LONG);
                else {
                    final HinhAnh value = dataSnapshot.getValue(HinhAnh.class);
                    //listHinhAnh.add(value);
                    if (baiViet.getID() == value.getIDLoai()) {

                        Toast.makeText(context, baiViet.getID() + "  " + value.getIDLoai() + "", Toast.LENGTH_LONG);

                        StorageReference islandRef = storageRef.child(value.getDuongDan());

                        Log.d("TestDuongDan", islandRef + "");

                        //MyAppGlideModule Glide = new MyAppGlideModule();
                        Glide.with(context)
                                .load(islandRef)
                                .into(hinhMonAn);
                    }
                }*/
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


        //ĐỌC TẤT CẢ DANHGIABAIVIET ĐỔ VÀO LISTDANHGIA
        myRef.child("DanhGiaBaiViet").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DanhGiaBaiViet value = dataSnapshot.getValue(DanhGiaBaiViet.class);
                listDanhGiaBaiViet.add(value);
                /*if(dataSnapshot==null) Toast.makeText(context, "Null", Toast.LENGTH_LONG);
                else {
                    final HinhAnh value = dataSnapshot.getValue(HinhAnh.class);
                    //listHinhAnh.add(value);
                    if (baiViet.getID() == value.getIDLoai()) {

                        Toast.makeText(context, baiViet.getID() + "  " + value.getIDLoai() + "", Toast.LENGTH_LONG);

                        StorageReference islandRef = storageRef.child(value.getDuongDan());

                        Log.d("TestDuongDan", islandRef + "");

                        //MyAppGlideModule Glide = new MyAppGlideModule();
                        Glide.with(context)
                                .load(islandRef)
                                .into(hinhMonAn);
                    }
                }*/
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

        //Đọc THICH
        myRef.child("Thich").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    final Thich thich = dataSnapshot.getValue(Thich.class);
                    if(thich.getiDNguoiDung().equals(nguoiDung.getIDNguoiDung())) {
                        listThich.add(thich);
                        adapter.notifyDataSetChanged();
                    }
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



        adapter = new NoiBatAdapter(this, R.layout.item_info_monan, listBaiViet, listHinhAnh, listDanhGiaBaiViet,listNguoiDung,nguoiDung,listYeuThich,listThich);
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
            case android.R.id.home:
                Intent intent = new Intent(YeuThichActivity.this, TrangChuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            case R.id.menuTimKiem:
                Intent intent1 = new Intent(YeuThichActivity.this, ManHinhTimKiemActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent1);
                return true;
            case R.id.menuNgoiSao:
                Intent intent2 = new Intent(YeuThichActivity.this, TrangChuActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent2);
                return true;
            case R.id.menuAdmin:
                if (DangNhap == true) {
                    Intent intent3 = new Intent(YeuThichActivity.this, ManHinhTrangCaNhanActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent3);
                } else {
                    Intent intent4 = new Intent(YeuThichActivity.this, DangNhapActivity.class);
                    intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent4);
                }
                return true;
            case R.id.menuYeuThich:
                Toast.makeText(YeuThichActivity.this, "Bạn đang ở trang này", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
