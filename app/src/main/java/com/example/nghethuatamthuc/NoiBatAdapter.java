package com.example.nghethuatamthuc;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.nfc.Tag;
import android.os.Debug;
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

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.MonAn_NoiBat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoiBatAdapter extends BaseAdapter {

    private Activity context;
    private int layoutID;
    private ArrayList<BaiViet> listMonAn;

    public NoiBatAdapter(Activity context, int layoutID, ArrayList<BaiViet> listBaiViet) {
        this.context = context;
        this.layoutID = layoutID;
        this.listMonAn = listBaiViet;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layoutID,null);

        TextView tenNguoiDang = (TextView) view.findViewById(R.id.txtTenNguoiDang);
        ImageView hinhMonAn = (ImageView) view.findViewById(R.id.imgMonAn);
        final TextView tenMonAn = (TextView) view.findViewById(R.id.txtTenMonAn);
        TextView luotThich = (TextView) view.findViewById(R.id.txtLuotThich);
        TextView thoiGian = (TextView) view.findViewById(R.id.txtTime);
        final RatingBar soluotdanhgia = (RatingBar) view.findViewById(R.id.simpleRatingBar);
        //final Button btnLike = (Button) view.findViewById(R.id.btnlike);
        //final Button btnLove = (Button) view.findViewById(R.id.btnlove);
        //soluotdanhgia.setEnabled(false);
        soluotdanhgia.setIsIndicator(false);

        BaiViet baiViet = listMonAn.get(position);

        tenNguoiDang.setText(baiViet.getIDNguoiDung()+"ID");
        hinhMonAn.setImageResource(R.drawable.monan);
        tenMonAn.setText(baiViet.getTenBaiViet());
        luotThich.setText(baiViet.getLuotThich() + " Lượt thích");

        SimpleDateFormat postFormater = new SimpleDateFormat("dd/MM/yyyy");
        String newNgayViet = null;
        try {
            newNgayViet = postFormater.format(baiViet.getNgayViet());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        thoiGian.setText(newNgayViet);
        soluotdanhgia.setRating(baiViet.getIDDanhGia());

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
        });

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLove.isSelected()){
                    //listMonAn.get(position).setLove(1);
                    btnLove.setBackgroundResource(R.mipmap.icons8_love_none_48);
                    btnLove.setSelected(false);
                }
                else {
                    //listMonAn.get(position).setLove(0);
                    btnLove.setBackgroundResource(R.mipmap.icons8_love_selected_48);
                    btnLove.setSelected(true);
                }
            }
        });*/

        tenMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Now","Chuyển tới trang chi tiết");
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
        }

        if(monAn.isLove()==1)
        {
            btnLove.setSelected(true);
            btnLove.setBackgroundResource(R.mipmap.icons8_love_selected_48);
        }
        else{
            btnLove.setSelected(false);
            btnLove.setBackgroundResource(R.mipmap.icons8_love_none_48);
        }
        //Click vào ratingbar
        soluotdanhgia.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(context,"Đã Đánh Gía: "+rating+" Sao",Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }
}
