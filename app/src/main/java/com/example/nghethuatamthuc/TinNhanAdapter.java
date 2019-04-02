package com.example.nghethuatamthuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghethuatamthuc.R;
import com.example.nghethuatamthuc.models.TinNhan;

import java.util.ArrayList;

public class TinNhanAdapter extends BaseAdapter {
    private ArrayList<TinNhan> tinNhans;
    private Context context;
    private int layout;

    public TinNhanAdapter(ArrayList<TinNhan> tinNhans, Context context, int layout) {
        this.tinNhans = tinNhans;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return tinNhans.size();
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

        ImageView imgHinhDaiDien;
        TextView txtTenNguoiDung, txtNoiDung;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView   = layoutInflater.inflate(layout,null);

        imgHinhDaiDien = (ImageView) convertView.findViewById(R.id.imgHinhDaiDien);
        txtTenNguoiDung =(TextView) convertView.findViewById(R.id.txtTenNguoiDung);
        txtNoiDung = (TextView) convertView.findViewById(R.id.txtNoiDung);

        TinNhan tinNhan = tinNhans.get(position);

        imgHinhDaiDien.setImageResource(tinNhan.getImgHinhThongBao());
        txtTenNguoiDung.setText(tinNhan.getTxtTenNguoiDung());
        txtNoiDung.setText(tinNhan.getTxtNoiDung());

        return convertView;
    }
}
