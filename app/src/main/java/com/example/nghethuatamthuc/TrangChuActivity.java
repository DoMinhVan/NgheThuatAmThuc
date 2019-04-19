package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.DanhGiaBaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.example.nghethuatamthuc.models.NguoiDung;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    Toolbar toolbarTrangChu;
    //Button btnLike;
    private static ArrayList<BaiViet> listBaiViet = new ArrayList<BaiViet>();
    private static ArrayList<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
    private static ArrayList<HinhAnh> listHinhAnh = new ArrayList<>();
    private static ArrayList<DanhGiaBaiViet> listDanhGiaBaiViet = new ArrayList<>();
    private NoiBatAdapter adapter;
    private ListView listView;
    private Button btnLike;
    private boolean DangNhap = false;
    private List<String> listSpinnerLoaiMon;
    private HinhAnh hinhAnhBaiViet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu_layout);
        //FireBase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //Button
        Button btnMonNgonMoiNgay = (Button) findViewById(R.id.btnMonNgonMoiNgay);
        Button btnDanhGiaCao = (Button) findViewById(R.id.btnDanhGiaCao);
        Button btnSoiNoi = (Button) findViewById(R.id.btnSoiNoi);

        //RatingBar
        RatingBar simpleRantingBar= (RatingBar) findViewById(R.id.simpleRatingBar);
        //Spinner
        final Spinner buttonMucKhac = (Spinner) findViewById(R.id.btnMucKhac);
        listSpinnerLoaiMon = new ArrayList<String>();
        listSpinnerLoaiMon.add("MÓN KHÁC");

        //ĐỔ DỮ LIỆU TỪ FIREBASE VỀ CHO SPIINER
        myRef.child("LoaiMon").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    String value = dataSnapshot.getValue(String.class);
                    listSpinnerLoaiMon.add(value);
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
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.spinner_item, listSpinnerLoaiMon);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        buttonMucKhac.setAdapter(adapterSpinner);

        //Toolbar
        toolbarTrangChu = (Toolbar) findViewById(R.id.toolbarTrangChu);
        setSupportActionBar(toolbarTrangChu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(null);

        listView = (ListView) findViewById(R.id.listMain);


        //ĐỌC TẤT CẢ CÁC BÀI VIẾT VÀ ĐỔ VÀO ARRAYLIST
        myRef.child("BaiViet").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(dataSnapshot.exists()) {
                    BaiViet value = dataSnapshot.getValue(BaiViet.class);
                    listBaiViet.add(value);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //ĐỌC TẤT CẢ CÁC NGUOI DUNG VÀ ĐỔ VÀO ARRAYLIST
        myRef.child("NguoiDung").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    NguoiDung nguoiDung = dataSnapshot.getValue(NguoiDung.class);
                    listNguoiDung.add(nguoiDung);
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

        //ĐỌC TẤT CẢ HÌNH ẢNH ĐỔ VÀO LISTDANHGIA
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


        /*myRef.child("BaiViet").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BaiViet value = dataSnapshot.getValue(BaiViet.class);
                listMembers.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("KQ", "Failed to read value.", databaseError.toException());
            }
        });*/

        adapter = new NoiBatAdapter(this, R.layout.item_info_monan,listBaiViet,listHinhAnh,listDanhGiaBaiViet,listNguoiDung);


        btnMonNgonMoiNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Món ngon mỗi ngày!", Toast.LENGTH_SHORT).show();
                Collections.sort(listBaiViet, new Comparator<BaiViet>() {
                    @Override
                    public int compare(BaiViet baiViet1, BaiViet baiViet2) {
                        try {
                            if (baiViet1.LayNgaySuaTheoNgayThang().compareTo(baiViet2.LayNgaySuaTheoNgayThang()) == 1) {
                                return -1;
                            } else {
                                if (baiViet1.LayNgaySuaTheoNgayThang().compareTo(baiViet2.LayNgaySuaTheoNgayThang()) == 0) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });

                adapter.notifyDataSetChanged();
            }
        });
        btnDanhGiaCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Đánh giá cao!", Toast.LENGTH_SHORT).show();
                Collections.sort(listBaiViet, new Comparator<BaiViet>() {
                    @Override
                    public int compare(BaiViet baiViet1, BaiViet baiViet2) {
                        if (baiViet1.LayDanhGiaTrungBinh() > (baiViet2.LayDanhGiaTrungBinh())) {
                            return -1;
                        } else {
                            if (baiViet1.getIDDanhGia() == baiViet2.getIDDanhGia()) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    }
                });

                adapter.notifyDataSetChanged();
            }
        });
        btnSoiNoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Sôi nổi!", Toast.LENGTH_SHORT).show();
                Collections.sort(listBaiViet, new Comparator<BaiViet>() {
                    @Override
                    public int compare(BaiViet baiViet1, BaiViet baiViet2) {
                        if (baiViet1.getLuotBinhLuan() > (baiViet2.getLuotBinhLuan())) {
                            return -1;
                        } else {
                            if (baiViet1.getLuotBinhLuan() == baiViet2.getLuotBinhLuan()) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        listView.setAdapter(adapter);
    }

    public int soSanhNgay(Date date1, Date date2){
        if(date1.compareTo(date2)>0){
            return 1;
        }
        else if(date1.compareTo(date2)<0){
            return -1;
        }
        else return 0;
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
                Toast.makeText(TrangChuActivity.this, "Bạn đang ở trang này!", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onResume() {
        listBaiViet.clear();
        listHinhAnh.clear();
        listDanhGiaBaiViet.clear();
        super.onResume();
    }
}
