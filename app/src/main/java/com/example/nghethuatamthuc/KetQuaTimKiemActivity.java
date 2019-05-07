package com.example.nghethuatamthuc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;

import java.util.ArrayList;

public class KetQuaTimKiemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ket_qua_tim_kiem_layout);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("KetQua");
        ArrayList<BaiViet> object = (ArrayList<BaiViet>) args.getSerializable("BaiViet");
        Toast.makeText(this, object.get(0).getDinhDuong().toString(), Toast.LENGTH_SHORT).show();
    }
}
