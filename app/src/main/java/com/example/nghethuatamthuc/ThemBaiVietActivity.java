package com.example.nghethuatamthuc;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ThemBaiVietActivity extends AppCompatActivity{
    private Toolbar mTopToolbar;
    BaiViet baiViet;
    HinhAnh hinhAnh;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thembaiviet_layout);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final TextView txtTenMonAn = (TextView) findViewById(R.id.txtMonAn);
        final TextView txtNguyenLieu = (TextView) findViewById(R.id.txtNguyenLieu);
        final TextView txtBuocLam = (TextView) findViewById(R.id.txtBuocLam);
        final TextView txtTenDD = (TextView) findViewById(R.id.edtTenDinhDuong);
        final TextView txtKhoiLuong = (TextView) findViewById(R.id.edtKhoiLuong);
        final TextView txtPhanTram = (TextView) findViewById(R.id.edtPhanTram);
        final TextView txtDiaChi = (TextView) findViewById(R.id.txtDiaChi);
        final TextView txtThongTinThem = (TextView) findViewById(R.id.txtThongTinThem);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        mTopToolbar = (Toolbar) findViewById(R.id.toolbarThemBaiViet);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        Button btnThem = (Button) findViewById(R.id.btnDangTai);



        //myRef.child("BaiViet").push().setValue(baiViet);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                final String key = myRef.child("BaiViet").push().getKey();

                baiViet = new BaiViet(key,txtTenMonAn.getText()+"","",txtNguyenLieu.getText()+"",txtBuocLam.getText()+"",txtTenDD.getText() + "" + txtKhoiLuong.getText() + "" + txtPhanTram.getText() + "",txtDiaChi.getText()+"",txtThongTinThem.getText()+"",0,0,calendar.getTime().getTimezoneOffset()+"","6-4-2019",5,5,1,113,5);


                myRef.child("BaiViet").child(key).setValue(baiViet, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError == null)
                        {
                            Calendar calendar = Calendar.getInstance();
                            Long NameImage = calendar.getTimeInMillis();

                            hinhAnh = new HinhAnh(NameImage+"",200,200,0,key);
                            myRef.child("HinhAnh").child(NameImage+"").setValue(hinhAnh);

                            Toast.makeText(ThemBaiVietActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ThemBaiVietActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    }
                });
            }
        });


        //Calendar calendar = Calendar.getInstance();

        //HinhAnh hinhAnh = new HinhAnh("00001",200,200,0,"-LblVJvszkyHbyKR4HzG");


        //TestBai test = new TestBai(1,"ABC");
        //Lưu bài viết
        //myRef.child("HinhAnh").push().setValue(hinhAnh);

        /*myRef.child("HinhAnh").push().setValue(hinhAnh, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError !=null)
                {
                    Toast.makeText(ThemBaiVietActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ThemBaiVietActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them_bai_viet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home) {
            Intent intent1 = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
            return true;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
