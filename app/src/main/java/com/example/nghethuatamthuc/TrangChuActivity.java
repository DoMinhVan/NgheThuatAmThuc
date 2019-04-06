package com.example.nghethuatamthuc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.MonAn_NoiBat;
import com.example.nghethuatamthuc.models.TestBai;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    Toolbar toolbarTrangChu;
    //Button btnLike;
    private static ArrayList<BaiViet> listMembers = new ArrayList<BaiViet>();
    private NoiBatAdapter adapter;
    private ListView listView;
    private Button btnLike;
    private boolean DangNhap = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu_layout);
        //Button
        Button btnMonNgonMoiNgay = (Button) findViewById(R.id.btnMonNgonMoiNgay);
        Button btnDanhGiaCao = (Button) findViewById(R.id.btnDanhGiaCao);
        Button btnSoiNoi = (Button) findViewById(R.id.btnSoiNoi);

        //RatingBar
        RatingBar simpleRantingBar= (RatingBar) findViewById(R.id.simpleRatingBar);
        //Spinner
        final Spinner buttonMucKhac = (Spinner) findViewById(R.id.btnMucKhac);
        List<String> list = new ArrayList<String>();
        list.add("MÓN KHÁC");
        list.add("Cơm chay");
        list.add("Món nướng");
        list.add("Món xào");
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this,R.layout.spinner_item, list);
        adapterSpinner.setDropDownViewResource(R.layout.spinner_dropdown_item);
        buttonMucKhac.setAdapter(adapterSpinner);

        //Toolbar
        toolbarTrangChu = (Toolbar) findViewById(R.id.toolbarTrangChu);
        setSupportActionBar(toolbarTrangChu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(null);

        listView = (ListView) findViewById(R.id.listMain);

        //FireBase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //BaiViet baiViet = new BaiViet("Bánh huế","Bột","ab","Dinh Duong","TD","ABC",0,0,"6-4-2019","6-4-2019",5,5,1,113,5);
        //TestBai test = new TestBai(1,"ABC");
        //Lưu bài viết
        //myRef.child("BaiViet").push();
        /*myRef.child("BaiViet").push().setValue(baiViet, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError !=null)
                {
                    Toast.makeText(TrangChuActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(TrangChuActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });*/
        //myRef.child("BaiViet").child("-LblVJvszkyHbyKR4HzG");

        myRef.child("BaiViet").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BaiViet value = dataSnapshot.getValue(BaiViet.class);
                listMembers.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*myRef.child("BaiViet").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BaiViet value = dataSnapshot.getValue(BaiViet.class);
                listMembers.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("KQ", "Failed to read value.", databaseError.toException());
            }
        });*/

        adapter = new NoiBatAdapter(this, R.layout.item_info_monan,listMembers);


        btnMonNgonMoiNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Món ngon mỗi ngày!", Toast.LENGTH_SHORT).show();
                Collections.sort(listMembers, new Comparator<BaiViet>() {
                    @Override
                    public int compare(BaiViet baiViet1, BaiViet baiViet2) {
                        try {
                            if (baiViet1.getNgayViet().compareTo(baiViet2.getNgayViet()) == 1) {
                                return -1;
                            } else {
                                if (baiViet1.getNgayViet().compareTo(baiViet2.getNgayViet()) == 0) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });

                adapter.notifyDataSetChanged();
            }
        });
        btnDanhGiaCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Đánh giá cao!", Toast.LENGTH_SHORT).show();
                Collections.sort(listMembers, new Comparator<BaiViet>() {
                    @Override
                    public int compare(BaiViet baiViet1, BaiViet baiViet2) {
                        if (baiViet1.getIDDanhGia() < baiViet2.getIDDanhGia()) {
                            return 1;
                        } else {
                            if (baiViet1.getIDDanhGia() == baiViet2.getIDDanhGia()) {
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    }
                });

                adapter.notifyDataSetChanged();
            }
        });
        btnSoiNoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TrangChuActivity.this, "Update list view - Sôi nổi!", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        listView.setAdapter(adapter);
    }

    public int soSanhNgay(Date date1, Date date2){
        if(date1.compareTo(date2)>0){
            return 1;
        }
        else if(date1.compareTo(date2)<0){
            return -1;
        }
        else return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_trang_chu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuTimKiem:
                Intent intent1 = new Intent(TrangChuActivity.this, ManHinhTimKiemActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent1);
                return true;
            case R.id.menuNgoiSao:
                Toast.makeText(TrangChuActivity.this, "Bạn đang trang này!", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuAdmin:
                if (DangNhap == true) {
                    Intent intent2 = new Intent(TrangChuActivity.this, ManHinhTrangCaNhanActivity.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent2);
                } else {
                    Intent intent3 = new Intent(TrangChuActivity.this, DangNhapActivity.class);
                    intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent3);
                }
                return true;
            case R.id.menuYeuThich:
                Intent intent4 = new Intent(TrangChuActivity.this, YeuThichActivity.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
