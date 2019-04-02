package com.example.nghethuatamthuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghethuatamthuc.R;
import com.example.nghethuatamthuc.models.ThongBao;

import java.util.ArrayList;

public class ThongBaoAdapter extends BaseAdapter {
    private ArrayList<ThongBao> thongBaos;
    private Context context;
    private int layout;

    public ThongBaoAdapter(ArrayList<ThongBao> thongBaos, Context context, int layout) {
        this.thongBaos = thongBaos;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return thongBaos.size();
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
        TextView txtTenNguoiDung, txtThoiGian;

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view   = layoutInflater.inflate(layout,null);

        imgHinhDaiDien = (ImageView) view.findViewById(R.id.imgHinhDaiDien);
        txtTenNguoiDung =(TextView) view.findViewById(R.id.txtTenNguoiDung);
        txtThoiGian =(TextView) view.findViewById(R.id.txtThoiGian);

        ThongBao thongBao = thongBaos.get(position);

        imgHinhDaiDien.setImageResource(thongBao.getImgHinhThongBao());
        txtTenNguoiDung.setText(thongBao.getTxtTenNguoiDung());
        txtThoiGian.setText(thongBao.getTxtThoiGian());
        return view;
    }
}
