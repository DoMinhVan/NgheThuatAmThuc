package com.example.nghethuatamthuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ItemInfoMonAnActivity extends AppCompatActivity {
    Button btnLike;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info_monan);

        btnLike = (Button) findViewById(R.id.btnlike);
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLike.setSelected(!btnLike.isSelected());

                if (btnLike.isSelected()) {
                    btnLike.setBackgroundResource(R.mipmap.icons8_like_selected_48);
                } else {
                    btnLike.setBackgroundResource(R.mipmap.icons8_like_none_48);
                }
            }
        });
    }
}
