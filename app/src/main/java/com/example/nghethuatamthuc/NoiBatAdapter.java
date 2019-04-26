package com.example.nghethuatamthuc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.DanhGia;
import com.example.nghethuatamthuc.models.DanhGiaBaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.example.nghethuatamthuc.models.MonAn_NoiBat;
import com.example.nghethuatamthuc.models.NguoiDung;
import com.example.nghethuatamthuc.models.YeuThich;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoiBatAdapter extends BaseAdapter {

    private Activity context;
    private int layoutID;
    private NguoiDung nguoiDung;
    private ArrayList<BaiViet> listMonAn;
    private ArrayList<HinhAnh> listHinhAnh;
    private ArrayList<NguoiDung> listNguoiDung;
    private ArrayList<DanhGiaBaiViet> listDanhGiaBaiViet;

    //SAVE DATE SEND DETAIL
    private String uriSend = null;
    private final static int REQUEST_CODE_URL_IMAGE = 1;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public NoiBatAdapter(Activity context, int layoutID, ArrayList<BaiViet> listBaiViet, ArrayList<HinhAnh> listHinhAnh, ArrayList<DanhGiaBaiViet> listDanhGiaBaiViet, ArrayList<NguoiDung> listNguoiDung, NguoiDung nguoiDung) {
        this.context = context;
        this.layoutID = layoutID;
        this.listMonAn = listBaiViet;
        this.listHinhAnh = listHinhAnh;
        this.listDanhGiaBaiViet = listDanhGiaBaiViet;
        this.listNguoiDung = listNguoiDung;
        this.nguoiDung = nguoiDung;
    }

    @Override
    public int getCount() {
        return listMonAn.size();
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
        View view = convertView;
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layoutID,null);

        final TextView tenNguoiDang = (TextView) view.findViewById(R.id.txtTenNguoiDang);
        final ImageView hinhMonAn = (ImageView) view.findViewById(R.id.imgMonAn);
        final TextView tenMonAn = (TextView) view.findViewById(R.id.txtTenMonAn);
        TextView luotThich = (TextView) view.findViewById(R.id.txtLuotThich);
        TextView thoiGian = (TextView) view.findViewById(R.id.txtTime);
        final RatingBar soluotdanhgia = (RatingBar) view.findViewById(R.id.simpleRatingBar);
        //final Button btnLike = (Button) view.findViewById(R.id.btnlike);
        final Button btnLove = (Button) view.findViewById(R.id.btnlove);
        //soluotdanhgia.setEnabled(false);
        soluotdanhgia.setIsIndicator(true);




        final BaiViet baiViet = listMonAn.get(position);
        //final HinhAnh hinhAnh = listHinhAnh.get(position);

        for(DanhGiaBaiViet danhGiaBaiViet : listDanhGiaBaiViet)
        {
            if (baiViet.getIDDanhGia().equals(danhGiaBaiViet.getIDDanhGia())) {
                float soDanhGiaTrungBinh = 0f;
                float soLuotDanhGia = danhGiaBaiViet.getDanhGia1()+danhGiaBaiViet.getDanhGia2()+danhGiaBaiViet.getDanhGia3()+danhGiaBaiViet.getDanhGia4()+danhGiaBaiViet.getDanhGia5();
                //Log.d("soLuotDanhGia", soLuotDanhGia + "");
                if(soLuotDanhGia!=0) {
                    soDanhGiaTrungBinh = (((danhGiaBaiViet.getDanhGia1()) + (danhGiaBaiViet.getDanhGia2() * 2) + (danhGiaBaiViet.getDanhGia3() * 3) + (danhGiaBaiViet.getDanhGia4() * 4) + (danhGiaBaiViet.getDanhGia5() * 5))/soLuotDanhGia);
                    //Log.d("soDanhGiaTrungBinh", soDanhGiaTrungBinh + "");
                    //DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    //baiViet.DanhGiaTrungBinh(Float.valueOf(decimalFormat.format(soDanhGiaTrungBinh)));
                    baiViet.DanhGiaTrungBinh(soDanhGiaTrungBinh);
                    soluotdanhgia.setRating(soDanhGiaTrungBinh);
                }
            }
        }

        for(HinhAnh hinhAnh : listHinhAnh)
        {
            if (baiViet.getID().equals(hinhAnh.getIDLoai())) {
                //Log.d("ListHinhAnh", baiViet.getID() + "  " + hinhAnh.getIDLoai() + "");
                storageRef.child("images/" + hinhAnh.getDuongDan()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Log.d("TestDuongDan", uri+"");
                        //MyAppGlideModule Glide = new MyAppGlideModule();
                        if(!context.isFinishing()) {
                            Glide.with(context)
                                    .load(uri)
                                    .into(hinhMonAn);
                        }
                    }
                });
            }
        }


        for(NguoiDung nguoiDung : listNguoiDung) {
            Log.d("anguoidung", baiViet.getIDNguoiDung());
            Log.d("bnguoidung", nguoiDung.getIDNguoiDung());
            if (baiViet.getIDNguoiDung().equals(nguoiDung.getIDNguoiDung())) {
                tenNguoiDang.setText(nguoiDung.getHoTen());
            }

            //tenNguoiDang.setText("kkkk");
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
        //thoiGian.setText(baiViet.LayNgayVietTheoNgayThang()+"");

        SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy");
        String newNgayViet = null;
        try {
            newNgayViet = postFormater.format(baiViet.LayNgayVietTheoNgayThang());
            thoiGian.setText(newNgayViet);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLike.isSelected()){
                    //listMonAn.get(position).setLike(1);
                    //Log.d("Now",listMonAn.get(position).isLike()+"");
                    btnLike.setBackgroundResource(R.mipmap.icons8_like_none_48);
                    btnLike.setSelected(false);
                }
                else {
                    //listMonAn.get(position).setLike(0);
                    btnLike.setBackgroundResource(R.mipmap.icons8_like_selected_48);
                    btnLike.setSelected(true);
                }
            }
        })*/

        /*btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLove.isSelected()){

                    //listMonAn.get(position).setLove(1);
                    btnLove.setBackgroundResource(R.mipmap.icons8_love_none_48);
                    btnLove.setSelected(false);
                }
                else {
                    String key = myRef.child("YeuThich").push().getKey();
                    YeuThich yeuThich = new YeuThich(key+"",baiViet.getID()+"",nguoiDung.getIDNguoiDung()+"");
                    myRef.child("YeuThich").child(key).setValue(yeuThich, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if(databaseError == null)
                            {
                                //listMonAn.get(position).setLove(0);
                                btnLove.setBackgroundResource(R.mipmap.icons8_love_selected_48);
                                btnLove.setSelected(true);
                            }
                            else {
                                Toast.makeText(context,"Có lỗi",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });*/

        tenMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ManHinhChiTietAcivity.class);
                Bundle bundle = new Bundle();
                //BaiViet baiViet = listMonAn.get(position);
                for(HinhAnh hinhAnh : listHinhAnh)
                {
                    if (baiViet.getID().equals(hinhAnh.getIDLoai())) {
                        uriSend = hinhAnh.getDuongDan();
                    }
                }

                bundle.putString("urlImage",uriSend+"");
                //intent.putExtra("urlImage",hinhMonAn.getDrawingCache());
                intent.putExtras(bundle);

                for(DanhGiaBaiViet danhGia : listDanhGiaBaiViet)
                {
                    if (baiViet.getIDDanhGia().equals(danhGia.getIDDanhGia())) {
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

        /*if(monAn.isLove()==1)
        {
            btnLove.setSelected(true);
            btnLove.setBackgroundResource(R.mipmap.icons8_love_selected_48);
        }
        else{
            btnLove.setSelected(false);
            btnLove.setBackgroundResource(R.mipmap.icons8_love_none_48);
        }*/
        //Click vào ratingbar
        /*soluotdanhgia.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(context,"Đã Đánh Gía: "+rating+" Sao",Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }
}
