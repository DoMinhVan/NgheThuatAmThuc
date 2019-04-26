package com.example.nghethuatamthuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghethuatamthuc.models.BinhLuan;
import com.example.nghethuatamthuc.models.NguoiDung;

import java.util.ArrayList;

public class BinhLuanNguoiDungAdapter extends BaseAdapter {
    private ArrayList<BinhLuan> BinhLuan;
    private ArrayList<NguoiDung> listNguoiDung;
    private Context context;
    private int layout;

    public BinhLuanNguoiDungAdapter(ArrayList<BinhLuan> BinhLuan, Context context, int layout,ArrayList<NguoiDung> listNguoiDung) {
        this.BinhLuan = BinhLuan;
        this.context = context;
        this.layout = layout;
        this.listNguoiDung = listNguoiDung;
    }

    @Override
    public int getCount() {
        return BinhLuan.size();
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

        ImageView imgHinhDaiDien;
        TextView txtTenNguoiDung, txtThoiGian, txtBinhLuan;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view   = layoutInflater.inflate(layout,null);

        imgHinhDaiDien = (ImageView) view.findViewById(R.id.imgNguoiDung);
        txtTenNguoiDung =(TextView) view.findViewById(R.id.txtTenNguoiDung);
        txtBinhLuan =(TextView) view.findViewById(R.id.txtBinhLuanNguoiDung);
        txtThoiGian =(TextView) view.findViewById(R.id.txtThoiGianNguoiDungBL);

        BinhLuan binhLuan = BinhLuan.get(position);

        for(NguoiDung nguoiDung : listNguoiDung){
            if(nguoiDung.getIDNguoiDung().equals(binhLuan.getIDNguoiDung())){
                txtTenNguoiDung.setText(nguoiDung.getHoTen());
                //imgHinhDaiDien.setImageResource(nguoiDung.getHinhDaiDien());
            }
        }

        txtBinhLuan.setText(binhLuan.getBinhLuan());
        txtThoiGian.setText(binhLuan.getThoiGianBL());
        return view;
    }
}
