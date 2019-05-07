package com.example.nghethuatamthuc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nghethuatamthuc.models.BinhLuan;
import com.example.nghethuatamthuc.models.NguoiDung;

import java.util.ArrayList;
import java.util.Calendar;

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
            if(nguoiDung.getIDNguoiDung().equals(binhLuan.getiDNguoiDung())){
                txtTenNguoiDung.setText(nguoiDung.getHoTen());
                //SET THỜI GIAN
                Calendar calendar = Calendar.getInstance();

                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(binhLuan.getGioBL());

                if(calendar.get(Calendar.YEAR) - 1900 - calendar1.get(Calendar.YEAR) == 0)
                {
                    txtThoiGian.setText(binhLuan.getThoiGianBL());
                }
                else if(calendar.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH) == 0){
                    txtThoiGian.setText(binhLuan.getGioBL()+"");
                }
                else if(calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH) >= 1){
                    if((24 - Math.abs(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY))) >= 24){
                        txtThoiGian.setText("Hôm qua");
                    }
                    if(calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH) >= 2){
                        if((24 - Math.abs(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY))) >= 24){
                            txtThoiGian.setText("Hôm kia");
                        }
                        else{
                            txtThoiGian.setText(binhLuan.getGioBL()+"");
                        }
                    }
                    if(calendar.get(Calendar.HOUR_OF_DAY) < calendar1.get(Calendar.HOUR_OF_DAY)) {
                        if ((24 - Math.abs(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY))) <= 24) {
                            txtThoiGian.setText((24 - Math.abs(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY))) + " Giờ trước");
                        }
                    }

                }
                else if(calendar.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH)  < 1)
                {
                    if(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY) >= 1 && calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY) < 2)
                    {
                        if(calendar.get(Calendar.MINUTE) < calendar1.get(Calendar.MINUTE)){
                            if ((60 - Math.abs(calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE))) >= 1 && Math.abs(calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE)) < 60) {
                                txtThoiGian.setText(60 - Math.abs(calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE)) + " Phút trước");
                            }
                        }
                        else {
                            txtThoiGian.setText(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY) + " Giờ trước");
                        }
                    }
                    if(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY) >= 2){
                        if((calendar.get(Calendar.MINUTE) >= calendar1.get(Calendar.MINUTE))) {
                            txtThoiGian.setText(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY) + " Giờ trước");
                        }
                        else if((calendar.get(Calendar.MINUTE) < calendar1.get(Calendar.MINUTE))){
                            txtThoiGian.setText(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY) - 1 + " Giờ trước");
                        }
                    }
                    else if(calendar.get(Calendar.HOUR_OF_DAY) - calendar1.get(Calendar.HOUR_OF_DAY) == 0){
                        if (calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE) >= 1 && calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE) < 60) {
                            txtThoiGian.setText(calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE) + " Phút trước");
                        }
                        else if(calendar.get(Calendar.MINUTE) - calendar1.get(Calendar.MINUTE)  == 0) {
                            if (calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) > 15 && calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) < 60) {
                                txtThoiGian.setText(calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) + " Giây trước");
                                if (calendar.get(Calendar.SECOND) - calendar1.get(Calendar.SECOND) <= 15) {
                                    txtThoiGian.setText("Mới đây");
                                }
                            }
                        }
                    }
                }
                //imgHinhDaiDien.setImageResource(nguoiDung.getHinhDaiDien());
            }
        }

        txtBinhLuan.setText(binhLuan.getBinhLuan());
        return view;
    }
}
