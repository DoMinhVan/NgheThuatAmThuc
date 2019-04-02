package com.example.nghethuatamthuc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nghethuatamthuc.models.MonAn_NoiBat;

import java.util.ArrayList;

public class QuanLyBaiVietAdapter extends BaseAdapter {

    private Activity context;
    private int layoutID;
    private ArrayList<MonAn_NoiBat> listMonAn;

    public QuanLyBaiVietAdapter(Activity context, int layoutID, ArrayList<MonAn_NoiBat> listMonAn) {
        this.context = context;
        this.layoutID = layoutID;
        this.listMonAn = listMonAn;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(layoutID,null);

        TextView tenNguoiDang = (TextView) view.findViewById(R.id.txtTenNguoiDang);
        ImageView hinhMonAn = (ImageView) view.findViewById(R.id.imgMonAn);
        TextView tenMonAn = (TextView) view.findViewById(R.id.txtTenMonAn);
        TextView luotThich = (TextView) view.findViewById(R.id.txtLuotThich);
        TextView thoiGian = (TextView) view.findViewById(R.id.txtTime);
        RatingBar soluotdanhgia = (RatingBar) view.findViewById(R.id.simpleRatingBar);
        //soluotdanhgia.setEnabled(false);
        soluotdanhgia.setIsIndicator(true);

        MonAn_NoiBat monAn = listMonAn.get(position);

        tenNguoiDang.setText(monAn.getTenNguoiDang());
        hinhMonAn.setImageResource(R.drawable.monan);
        tenMonAn.setText(monAn.getTenMonAn());
        luotThich.setText(monAn.getLuotThich() + " Lượt thích");
        thoiGian.setText(monAn.getThoiGian());
        soluotdanhgia.setRating(monAn.getSodanhgia());

        return view;
    }


}
