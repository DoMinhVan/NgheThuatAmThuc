package com.example.nghethuatamthuc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.BinhLuan;
import com.example.nghethuatamthuc.models.DanhGia;
import com.example.nghethuatamthuc.models.DanhGiaBaiViet;
import com.example.nghethuatamthuc.models.NguoiDung;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ManHinhChiTietAcivity extends AppCompatActivity {

    ImageView imageView;

    Toolbar toolbarChiTiet;
    BaiViet baiViet;
    DanhGiaBaiViet danhGia;
    private static ArrayList<BinhLuan> listBinhLuan = new ArrayList<BinhLuan>();
    private static ArrayList<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
    private static NguoiDung nguoiDung;
    private RatingBar ratingBarNguoiDung;
    private BinhLuanNguoiDungAdapter adapterBinhLuanNguoiDung;
    ListView listViewBinhLuanNguoiDung;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_chi_tiet_mon_an_layout);

        toolbarChiTiet = (Toolbar) findViewById(R.id.toolbarChiTiet);
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        ratingBarNguoiDung = (RatingBar) findViewById(R.id.ratingBarNguoiDung_ChiTiet);


        RatingBar ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1_ChiTiet);
        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2_ChiTiet);
        RatingBar ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3_ChiTiet);
        RatingBar ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4_ChiTiet);
        RatingBar ratingBar5 = (RatingBar) findViewById(R.id.ratingBar5_ChiTiet);

        ratingBar1.setIsIndicator(true);
        ratingBar2.setIsIndicator(true);
        ratingBar3.setIsIndicator(true);
        ratingBar4.setIsIndicator(true);
        ratingBar5.setIsIndicator(true);

        ratingBar1.setRating(1);
        ratingBar2.setRating(2);
        ratingBar3.setRating(3);
        ratingBar4.setRating(4);
        ratingBar5.setRating(5);

        TextView danhGiaTrungBinh = (TextView) findViewById(R.id.txtDanhGiaTrunginh_ChiTiet);

        TextView danhGia1 = (TextView) findViewById(R.id.txt1_ChiTiet);
        TextView danhGia2 = (TextView) findViewById(R.id.txt2_ChiTiet);
        TextView danhGia3 = (TextView) findViewById(R.id.txt3_ChiTiet);
        TextView danhGia4 = (TextView) findViewById(R.id.txt4_ChiTiet);
        TextView danhGia5 = (TextView) findViewById(R.id.txt5_ChiTiet);

        imageView = (ImageView) findViewById(R.id.img_ChiTiet);
        TextView tenMonAn = (TextView) findViewById(R.id.txtTenMonAn_ChiTiet);
        final TextView nguyenLieu = (TextView) findViewById(R.id.txtNguyenLieu_ChiTiet);
        TextView buocLam = (TextView) findViewById(R.id.txtBuocLam_ChiTiet);
        TextView dinhDuong = (TextView) findViewById(R.id.txtDinhDuong_ChiTiet);
        TextView diaChi = (TextView) findViewById(R.id.txtDiaChi_ChiTiet);
        TextView thongTinChiTiet = (TextView) findViewById(R.id.txtThongTinChiTiet_ChiTiet);
        Button btnGui = (Button) findViewById(R.id.btnDanhGia_ChiTiet);
        final EditText edtbinhLuan = (EditText) findViewById(R.id.edtBinhLuanNguoiDung_ChiTiet);
        listViewBinhLuanNguoiDung = (ListView) findViewById(R.id.listBinhLuanNguoiDung_ChiTiet);
        TextView txtMoRong = (TextView) findViewById(R.id.txtMoRongBinhLuan);




        //ĐỌC TẤT CẢ NGUOIDUNG VÀ ĐỔ VÀO LISTNGUOIDUNG
        myRef.child("NguoiDung").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                NguoiDung value = dataSnapshot.getValue(NguoiDung.class);
                listNguoiDung.add(value);

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



        Intent intent = getIntent();

        if (intent != null) {
            Bundle bundle = getIntent().getExtras();
            String value = bundle.getString("urlImage","a");
            //The key argument here must match that used in the other activity
            //Log.d("TestBundle1", bundle+"");

            //Bitmap b =
            //imageView.setImageBitmap(b);
            storageRef.child("images/" + value).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .into(imageView);

                }
            });
            nguoiDung = (NguoiDung) intent.getSerializableExtra("NguoiDung");
            baiViet = (BaiViet) intent.getSerializableExtra("BaiViet");
            danhGia = (DanhGiaBaiViet) intent.getSerializableExtra("DanhGia");
            if (baiViet != null) {
                danhGia1.setText(danhGia.getDanhGia1()+"");
                danhGia2.setText(danhGia.getDanhGia2()+"");
                danhGia3.setText(danhGia.getDanhGia3()+"");
                danhGia4.setText(danhGia.getDanhGia4()+"");
                danhGia5.setText(danhGia.getDanhGia5()+"");
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                float danhGiaTrungBinhNew = Float.valueOf(decimalFormat.format(baiViet.LayDanhGiaTrungBinh()));
                danhGiaTrungBinh.setText(danhGiaTrungBinhNew+"");
                tenMonAn.setText(baiViet.getTenBaiViet());
                nguyenLieu.setText("Nguyên liệu: " + baiViet.getNguyenLieu());
                buocLam.setText("Các bước làm: " + baiViet.getBuocLam());
                dinhDuong.setText("Dinh dưỡng: " + baiViet.getDinhDuong());
                diaChi.setText("Địa chỉ: " + baiViet.getDiaChi());
                thongTinChiTiet.setText("Thông tin chi tiết: " + baiViet.getThongTinChiTiet());
            }
        }

        //ĐỌC TẤT CẢ BINHLUAN VÀ ĐỔ VÀO LISTBINHLUAN
        myRef.child("BinhLuan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BinhLuan value = dataSnapshot.getValue(BinhLuan.class);
                if(value.getIDBaiViet().equals(baiViet.getID())) {
                    listBinhLuan.add(value);
                    Log.d("KiemtraBinhLuan",listBinhLuan.size()+"");
                    adapterBinhLuanNguoiDung.notifyDataSetChanged();
                }
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
                adapterBinhLuanNguoiDung.notifyDataSetChanged();
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

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DanhGia
                String key = myRef.child("DanhGia").push().getKey();
                final DanhGia danhGiaNguoiDung = new DanhGia(key+"",Math.round(ratingBarNguoiDung.getRating()),nguoiDung.getIDNguoiDung()+"",baiViet.getID()+"");

                myRef.child("DanhGia").child(key).setValue(danhGiaNguoiDung, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError !=null)
                        {
                            Toast.makeText(ManHinhChiTietAcivity.this, "Có lỗi", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(ManHinhChiTietAcivity.this, "Cảm ơn sự đánh giá của bạn", Toast.LENGTH_SHORT).show();
                            switch (Math.round(ratingBarNguoiDung.getRating())) {
                                case 1:
                                    danhGia.setDanhGia1(danhGia.getDanhGia1() + 1);
                                    break;
                                case 2:
                                    danhGia.setDanhGia2(danhGia.getDanhGia2() + 1);
                                    break;
                                case 3:
                                    danhGia.setDanhGia3(danhGia.getDanhGia3() + 1);
                                    break;
                                case 4:
                                    danhGia.setDanhGia4(danhGia.getDanhGia4() + 1);
                                    break;
                                case 5:
                                    danhGia.setDanhGia5(danhGia.getDanhGia5() + 1);
                                    break;
                                default:
                                    break;
                            }
                            //Danh gia nguoi dung xong thi cap nhat danh gia cua bai viet do
                            myRef.child("DanhGiaBaiViet").child(danhGia.getIDDanhGia()).setValue(danhGia, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                    if(databaseError !=null)
                                    {
                                        //Toast.makeText(ManHinhChiTietAcivity.this, "Có lỗi", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        //Toast.makeText(ManHinhChiTietAcivity.this, "Cảm ơn sự đánh giá của bạn", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                        }

                    }
                });

                //BinhLuan
                if(edtbinhLuan!=null) {
                    String keyBinhLuan = myRef.child("BinhLuan").push().getKey();
                    final BinhLuan binhLuan = new BinhLuan(keyBinhLuan + "", edtbinhLuan.getText() + "", nguoiDung.getLoaiNguoiDung(), "", "", 1, nguoiDung.getIDNguoiDung() + "", baiViet.getID());

                    myRef.child("BinhLuan").child(keyBinhLuan).setValue(binhLuan, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError != null) {
                                Toast.makeText(ManHinhChiTietAcivity.this, "Có lỗi", Toast.LENGTH_SHORT).show();
                            } else {
                                //Toast.makeText(ManHinhChiTietAcivity.this, "Có lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });

        txtMoRong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView layout = findViewById(R.id.listBinhLuanNguoiDung_ChiTiet);
                // Gets the layout params that will allow you to resize the layout
                ViewGroup.LayoutParams params = layout.getLayoutParams();
                // Changes the height and width to the specified *pixels*
                params.height += 500;
                layout.setLayoutParams(params);
            }
        });



        adapterBinhLuanNguoiDung = new BinhLuanNguoiDungAdapter(listBinhLuan,this,R.layout.item_binhluan_chitiet_nguoidung,listNguoiDung);
        listViewBinhLuanNguoiDung.setAdapter(adapterBinhLuanNguoiDung);
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_bai_viet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home) {
            Intent intent1 = new Intent(ManHinhChiTietAcivity.this, TrangChuActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
            return true;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listBinhLuan.clear();
        adapterBinhLuanNguoiDung.notifyDataSetChanged();
    }
}