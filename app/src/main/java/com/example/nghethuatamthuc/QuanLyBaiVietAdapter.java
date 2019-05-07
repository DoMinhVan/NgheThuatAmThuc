package com.example.nghethuatamthuc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.DanhGiaBaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.example.nghethuatamthuc.models.MonAn_NoiBat;
import com.example.nghethuatamthuc.models.NguoiDung;
import com.example.nghethuatamthuc.models.Thich;
import com.example.nghethuatamthuc.models.YeuThich;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class QuanLyBaiVietAdapter extends BaseAdapter {

    private Activity context;
    private int layoutID;
    private NguoiDung nguoiDung;

    private ArrayList<BaiViet> listBaiViet;
    private ArrayList<HinhAnh> listHinhAnh;
    private ArrayList<NguoiDung> listNguoiDung;
    private ArrayList<DanhGiaBaiViet> listDanhGiaBaiViet;
    private ArrayList<YeuThich> listYeuThich;
    private ArrayList<Thich> listThich;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    //SAVE DATE SEND DETAIL
    private String uriSend = null;
    private final static int REQUEST_CODE_URL_IMAGE = 1;

    public QuanLyBaiVietAdapter(Activity context, int layoutID, ArrayList<BaiViet> listBaiViet, ArrayList<HinhAnh> listHinhAnh, ArrayList<DanhGiaBaiViet> listDanhGiaBaiViet, ArrayList<NguoiDung> listNguoiDung, NguoiDung nguoiDung, ArrayList<YeuThich> listYeuThich, ArrayList<Thich> listThich){
        this.context = context;
        this.layoutID = layoutID;
        this.listBaiViet = listBaiViet;
        this.listHinhAnh = listHinhAnh;
        this.listDanhGiaBaiViet = listDanhGiaBaiViet;
        this.listNguoiDung = listNguoiDung;
        this.nguoiDung = nguoiDung;
        this.listYeuThich = listYeuThich;
        this.listThich = listThich;
    }

    @Override
    public int getCount() {
        return listBaiViet.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layoutID,null);

        TextView tenNguoiDang = (TextView) view.findViewById(R.id.txtTenNguoiDangn_QuanLyBaiViet);
        final ImageView hinhMonAn = (ImageView) view.findViewById(R.id.imgMonAn_QuanLyBaiViet);
        TextView tenMonAn = (TextView) view.findViewById(R.id.txtTenMonAnn_QuanLyBaiViet);
        TextView luotThich = (TextView) view.findViewById(R.id.txtLuotThichn_QuanLyBaiViet);
        TextView thoiGian = (TextView) view.findViewById(R.id.txtThoiGian_QuanLyBaiViet);
        RatingBar soluotdanhgia = (RatingBar) view.findViewById(R.id.simpleRatingBar_QuanLyBaiViet);
        final CheckBox danhDau = (CheckBox) view.findViewById(R.id.chkChon_QuanLyBaiViet);
        final Button btnLike = (Button) view.findViewById(R.id.btnlike_QuanLyBaiViet);
        final Button btnLove = (Button) view.findViewById(R.id.btnlove_QuanLyBaiViet);
        //soluotdanhgia.setEnabled(false);
        soluotdanhgia.setIsIndicator(true);

        final BaiViet baiViet = listBaiViet.get(position);

        luotThich.setText(baiViet.getLuotThich() + " Lượt thích");

        //final HinhAnh hinhAnh = listHinhAnh.get(position);

        for (DanhGiaBaiViet danhGiaBaiViet : listDanhGiaBaiViet) {
            if (baiViet.getiDDanhGia().equals(danhGiaBaiViet.getiDDanhGia())) {
                float soDanhGiaTrungBinh = 0f;
                float soLuotDanhGia = danhGiaBaiViet.getDanhGia1() + danhGiaBaiViet.getDanhGia2() + danhGiaBaiViet.getDanhGia3() + danhGiaBaiViet.getDanhGia4() + danhGiaBaiViet.getDanhGia5();
                //Log.d("soLuotDanhGia", soLuotDanhGia + "");
                if (soLuotDanhGia != 0) {
                    soDanhGiaTrungBinh = (((danhGiaBaiViet.getDanhGia1()) + (danhGiaBaiViet.getDanhGia2() * 2) + (danhGiaBaiViet.getDanhGia3() * 3) + (danhGiaBaiViet.getDanhGia4() * 4) + (danhGiaBaiViet.getDanhGia5() * 5)) / soLuotDanhGia);
                    //Log.d("soDanhGiaTrungBinh", soDanhGiaTrungBinh + "");
                    //DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    //baiViet.DanhGiaTrungBinh(Float.valueOf(decimalFormat.format(soDanhGiaTrungBinh)));
                    baiViet.DanhGiaTrungBinh(soDanhGiaTrungBinh);
                    soluotdanhgia.setRating(soDanhGiaTrungBinh);
                }
            }
        }

        for (HinhAnh hinhAnh : listHinhAnh) {
            if (baiViet.getiD().equals(hinhAnh.getiDLoai())) {
                //Log.d("ListHinhAnh", baiViet.getID() + "  " + hinhAnh.getIDLoai() + "");
                storageRef.child("images/" + hinhAnh.getDuongDan()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Log.d("TestDuongDan", uri+"");
                        //MyAppGlideModule Glide = new MyAppGlideModule();
                        if (!context.isFinishing()) {
                            Glide.with(context)
                                    .load(uri)
                                    .into(hinhMonAn);
                        }
                    }
                });
            }
        }


        for (NguoiDung nguoiDung : listNguoiDung) {
            if (baiViet.getiDNguoiDung().equals(nguoiDung.getIDNguoiDung())) {
                tenNguoiDang.setText(nguoiDung.getHoTen());
                break;
            }
        }


        /*final long ONE_MEGABYTE = 1024 * 1024;
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                hinhMonAn.setImageResource(bytes[0]);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });*/

        tenMonAn.setText(baiViet.getTenBaiViet());

        hinhMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        luotThich.setText(baiViet.getLuotThich() + " Lượt thích");

        //SET THỜI GIAN
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(baiViet.getGioViet());

        Log.d("KiemTraThoiGian", calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH)+"");

        if(calendar.get(Calendar.YEAR) - 1900 - calendar1.get(Calendar.YEAR) > 0)
        {
            thoiGian.setText(baiViet.getNgayViet());
        }
        else if(calendar.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH) > 0){
            thoiGian.setText(baiViet.getNgayViet());
        }
        else if(calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH) >= 1){
            if(calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH) <= 2){
                thoiGian.setText("Hôm qua");
            }
            else if(calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH) <= 3){
                thoiGian.setText("Hôm kia");
            }
            else{
                thoiGian.setText(baiViet.getNgayViet());
            }
        }
        else if(calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH)  < 1)
        {
            if(calendar.get(Calendar.HOUR) - calendar1.get(Calendar.HOUR) >= 1)
            {
                thoiGian.setText(calendar.get(Calendar.HOUR) - calendar1.get(Calendar.HOUR) + " Giờ trước");
            }
            else if (calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE) >= 1 && calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE) < 60) {
                thoiGian.setText(calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE) + " Phút trước");
            }
            else if(calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE)  < 1) {
                if (calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) > 30 && calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) < 60) {
                    thoiGian.setText(calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) + " Giây trước");
                    if (calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) <= 30) {
                        thoiGian.setText("Mới đây");
                    }
                }

            }
        }

        /*SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy");
        String newNgayViet = null;
        try {
            newNgayViet = postFormater.format(baiViet.LayNgayVietTheoNgayThang());
            thoiGian.setText(newNgayViet);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLike.isSelected()){
                    for (final Thich thich : listThich) {
                        if (baiViet.getiD().equals(thich.getiDLoai()))
                        {
                            myRef.child("Thich").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    if(dataSnapshot.exists() && dataSnapshot.getKey().equals(thich.getiDThich())) {
                                        dataSnapshot.getRef().removeValue();
                                        btnLike.setBackgroundResource(R.mipmap.icons8_like_none_48);
                                        btnLike.setSelected(false);
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
                else {
                    String key = myRef.child("Thich").push().getKey();
                    Thich thich = new Thich(key + "", 0, baiViet.getiD(),"", nguoiDung.getIDNguoiDung());
                    myRef.child("Thich").child(key).setValue(thich, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                btnLike.setBackgroundResource(R.mipmap.icons8_like_selected_48);
                                btnLike.setSelected(true);
                            } else {
                                Toast.makeText(context, "Có lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnLove.isSelected()) {
                    for (final YeuThich yeuThich : listYeuThich) {
                        if (baiViet.getiD().equals(yeuThich.getiDBaiVietYeuThich()))
                        {
                            myRef.child("YeuThich").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    if(dataSnapshot.exists() && dataSnapshot.getKey().equals(yeuThich.getiDYeuThich())) {
                                        dataSnapshot.getRef().removeValue();
                                        btnLove.setBackgroundResource(R.mipmap.icons8_love_none_48);
                                        btnLove.setSelected(false);
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

                } else {
                    String key = myRef.child("YeuThich").push().getKey();
                    YeuThich yeuThich = new YeuThich(key + "", baiViet.getiD(), nguoiDung.getIDNguoiDung());
                    myRef.child("YeuThich").child(key).setValue(yeuThich, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                //listMonAn.get(position).setLove(0);
                                btnLove.setBackgroundResource(R.mipmap.icons8_love_selected_48);
                                btnLove.setSelected(true);
                            } else {
                                Toast.makeText(context, "Có lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        tenMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ManHinhChiTietAcivity.class);
                Bundle bundle = new Bundle();
                //BaiViet baiViet = listMonAn.get(position);
                for (HinhAnh hinhAnh : listHinhAnh) {
                    if (baiViet.getiD().equals(hinhAnh.getiDLoai())) {
                        uriSend = hinhAnh.getDuongDan();
                    }
                }

                bundle.putString("urlImage", uriSend + "");
                //intent.putExtra("urlImage",hinhMonAn.getDrawingCache());
                intent.putExtras(bundle);

                for (DanhGiaBaiViet danhGia : listDanhGiaBaiViet) {
                    if (baiViet.getiDDanhGia().equals(danhGia.getiDDanhGia())) {
                        intent.putExtra("DanhGia", danhGia);
                    }
                }
                intent.putExtra("NguoiDung", nguoiDung);
                intent.putExtra("BaiViet", baiViet);
                //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

                context.startActivity(intent);
                //context.startActivityForResult(intent,REQUEST_CODE_URL_IMAGE);

            }
        });

        /*if(monAn.isLike()==1)
        {
            btnLike.setSelected(true);
            btnLike.setBackgroundResource(R.mipmap.icons8_like_selected_48);
        }
        else {
            btnLike.setSelected(false);
            btnLike.setBackgroundResource(R.mipmap.icons8_like_none_48);
        }*/
        for (YeuThich yeuThich : listYeuThich) {
            if (baiViet.getiD().equals(yeuThich.getiDBaiVietYeuThich())) {
                btnLove.setBackgroundResource(R.mipmap.icons8_love_selected_48);
                btnLove.setSelected(true);
                break;
            }
            else{
                btnLove.setBackgroundResource(R.mipmap.icons8_love_none_48);
                btnLove.setSelected(false);
            }

        }

        for (Thich thich : listThich) {
            if (baiViet.getiD().equals(thich.getiDLoai())) {
                btnLike.setSelected(true);
                btnLike.setBackgroundResource(R.mipmap.icons8_like_selected_48);
                break;
            }
            else{
                btnLike.setSelected(false);
                btnLike.setBackgroundResource(R.mipmap.icons8_like_none_48);
            }

        }


        danhDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(danhDau.isChecked()) {
                    listBaiViet.get(position).suaDanhDau(true);
                    Log.d("Kiemtrađanhau", listBaiViet.get(position).layDanhDau()+"");
                }
                else
                {
                    listBaiViet.get(position).suaDanhDau(false);
                }
            }
        });

        return view;
    }


}
