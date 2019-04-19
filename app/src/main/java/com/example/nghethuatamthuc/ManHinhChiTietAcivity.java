package com.example.nghethuatamthuc;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ManHinhChiTietAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.man_hinh_chi_tiet_mon_an_layout);

        ImageView imageView = (ImageView) findViewById(R.id.imgChiTiet);

        Bundle bundle = getIntent().getExtras();
        Log.d("TestBundle", bundle+"");
        if (bundle != null) {
            String value = bundle.getString("urlImage","a");
            //String value = extras.getString("uri_image");
            //The key argument here must match that used in the other activity
            Log.d("TestBundle1", bundle+"");

            if(!this.isFinishing()) {
                Glide.with(this)
                        .load(value)
                        .into(imageView);
            }
        }


    }
}