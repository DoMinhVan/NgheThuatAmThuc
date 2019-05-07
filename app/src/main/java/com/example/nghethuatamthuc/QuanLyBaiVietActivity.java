package com.example.nghethuatamthuc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.BinhLuan;
import com.example.nghethuatamthuc.models.DanhGia;
import com.example.nghethuatamthuc.models.DanhGiaBaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.example.nghethuatamthuc.models.MonAn_NoiBat;
import com.example.nghethuatamthuc.models.NguoiDung;
import com.example.nghethuatamthuc.models.Thich;
import com.example.nghethuatamthuc.models.YeuThich;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QuanLyBaiVietActivity extends AppCompatActivity {

    Toolbar toolbarQuanLyBaiViet;

    private static ArrayList<BaiViet> listBaiViet = new ArrayList<BaiViet>();
    private static ArrayList<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
    private static ArrayList<HinhAnh> listHinhAnh = new ArrayList<>();
    private static ArrayList<DanhGiaBaiViet> listDanhGiaBaiViet = new ArrayList<>();
    private ArrayList<YeuThich> listYeuThich = new ArrayList<YeuThich>();
    private ArrayList<Thich> listThich = new ArrayList<Thich>();
    private NguoiDung nguoiDung = new NguoiDung();
    private QuanLyBaiVietAdapter adapter;

    //FireBase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    ListView listView;

    private int thuTu = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlybaiviet_layout);

        listNguoiDung.clear();
        listBaiViet.clear();
        listHinhAnh.clear();
        listDanhGiaBaiViet.clear();
        listYeuThich.clear();
        listThich.clear();

        //Giả lập người dùng
        //nguoiDung = new NguoiDung("-LctqUfnB9x3d9OVGQow","HuuPhu","Huuphudn2015","123","0123456789","Huuphudn2015@gmail.com","16/04/1998",1,1,"","","20/04/2019",1);

        //GET NGUOI DUNG
        Intent intent = getIntent();
        if (intent != null) {
            nguoiDung = (NguoiDung) intent.getSerializableExtra("NguoiDung");
        }

        //Toolbar
        toolbarQuanLyBaiViet = (Toolbar) findViewById(R.id.toolbarQuanLyBaiViet);
        setSupportActionBar(toolbarQuanLyBaiViet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        listView = (ListView) findViewById(R.id.listQuanLyBaiViet);

        //ĐỌC TẤT CẢ CÁC BÀI VIẾT VÀ ĐỔ VÀO ARRAYLIST
        myRef.child("BaiViet").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    BaiViet value = dataSnapshot.getValue(BaiViet.class);
                    if(value.getiDNguoiDung().equals(nguoiDung.getIDNguoiDung())){
                        listBaiViet.add(value);
                        adapter.notifyDataSetChanged();
                    }

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
        //Đọc YEUTHICH
        myRef.child("YeuThich").addChildEventListener(new ChildEventListener() {
            public void reset(){
                listYeuThich.clear();
            }
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    final YeuThich yeuThich = dataSnapshot.getValue(YeuThich.class);
                    if(yeuThich.getiDNGuoiDungYeuThich().equals(nguoiDung.getIDNguoiDung())) {
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

        adapter = new QuanLyBaiVietAdapter(this, R.layout.item_info_quanlybaiviet, listBaiViet, listHinhAnh, listDanhGiaBaiViet, listNguoiDung, nguoiDung, listYeuThich, listThich);

        listView.setAdapter(adapter);
    }

    public void removeMember() {
        new AlertDialog.Builder(this)
                .setTitle("Thông báo!")
                .setMessage("Bạn có chắc chắn muốn xóa bài viết này?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Xóa ở firebase
                        for (final BaiViet baiViet : listBaiViet) {
                            if (baiViet.layDanhDau()) {
                                myRef.child("BaiViet").addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        if (dataSnapshot.exists() && dataSnapshot.getKey().equals(baiViet.getiD())) {
                                            dataSnapshot.getRef().removeValue();
                                            //Xoa danhgia theo bai viet
                                            myRef.child("DanhGiaBaiViet").addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                    if (dataSnapshot.exists() && dataSnapshot.getKey().equals(baiViet.getiDDanhGia())) {
                                                        dataSnapshot.getRef().removeValue();
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

                                            //Xóa binhluan theo bai viet
                                            myRef.child("BinhLuan").addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                    BinhLuan binhLuan = dataSnapshot.getValue(BinhLuan.class);
                                                    if (dataSnapshot.exists() && binhLuan.getiDBaiViet().equals(baiViet.getiD())) {
                                                        dataSnapshot.getRef().removeValue();
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

                                            //xoa hinhanh theo bai viet
                                            myRef.child("HinhAnh").addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                    HinhAnh hinhAnh = dataSnapshot.getValue(HinhAnh.class);
                                                    if (dataSnapshot.exists() && hinhAnh.getiDLoai().equals(baiViet.getiD())) {
                                                        dataSnapshot.getRef().removeValue();
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

                                            //Xoa danhgia theo vai viet
                                            myRef.child("DanhGia").addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                    DanhGia danhGia = dataSnapshot.getValue(DanhGia.class);
                                                    if (dataSnapshot.exists() && danhGia.getiDBaiViet().equals(baiViet.getiD())) {
                                                        dataSnapshot.getRef().removeValue();
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

                                            //Xoa yeuthich theo bai viet
                                            myRef.child("YeuThich").addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                    YeuThich yeuThich = dataSnapshot.getValue(YeuThich.class);
                                                    if (dataSnapshot.exists() && yeuThich.getiDBaiVietYeuThich().equals(baiViet.getiD())) {
                                                        dataSnapshot.getRef().removeValue();
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

                                            //Xoa thich theo bai viet
                                            myRef.child("Thich").addChildEventListener(new ChildEventListener() {
                                                @Override
                                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                    Thich thich = dataSnapshot.getValue(Thich.class);
                                                    if (dataSnapshot.exists() && thich.getiDLoai().equals(baiViet.getiD())) {
                                                        dataSnapshot.getRef().removeValue();
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
                                //Xóa ở listview
                                listBaiViet.remove(baiViet);
                            }
                        }
                        //Cập nhật list, thông báo cho người dùng đã thành công
                        adapter.notifyDataSetChanged();
                        Toast.makeText(QuanLyBaiVietActivity.this, "Đã xóa thành công", Toast.LENGTH_LONG).show();
                    }
                })

                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quan_ly_bai_viet,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            Intent intent = new Intent(QuanLyBaiVietActivity.this, ManHinhTrangCaNhanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuXoa) {
            removeMember();
            return true;
        }
        if (id == R.id.menuThem) {
            Intent intent1 = new Intent(QuanLyBaiVietActivity.this, ThemBaiVietActivity.class);
            intent1.putExtra("NguoiDung", nguoiDung);
            intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
