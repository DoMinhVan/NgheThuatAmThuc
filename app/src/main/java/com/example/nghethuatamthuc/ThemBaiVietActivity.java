package com.example.nghethuatamthuc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.DanhGia;
import com.example.nghethuatamthuc.models.DanhGiaBaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class ThemBaiVietActivity extends AppCompatActivity{
    private Toolbar mTopToolbar;
    private BaiViet baiViet;
    private HinhAnh hinhAnh;
    private DanhGiaBaiViet danhGiaBaiViet;
    private Uri filePath;

    private List<String> listSpinnerLoaiMon;
    private List<String> listSpinnerDoiTuong;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private ImageView imageView;
    private Spinner spnLoaiMon;
    private Spinner spnDoiTuong;
    private TextView txtTenMonAn;
    private TextView txtNguyenLieu;
    private TextView txtBuocLam;
    private TextView txtTenDD;
    private TextView txtKhoiLuong;
    private TextView txtPhanTram;
    private TextView txtDiaChi;
    private TextView txtThongTinThem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thembaiviet_layout);

        mTopToolbar = (Toolbar) findViewById(R.id.toolbarThemBaiViet);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        Button btnThem = (Button) findViewById(R.id.btnDangTai);
        imageView = (ImageView) findViewById(R.id.imageView);
        spnLoaiMon = (Spinner) findViewById(R.id.spnLoaiMon);
        spnDoiTuong = (Spinner) findViewById(R.id.spnDoiTuong);
        txtTenMonAn = (TextView) findViewById(R.id.edtMonAn);
        txtNguyenLieu = (TextView) findViewById(R.id.edtNguyenLieu);
        txtBuocLam = (TextView) findViewById(R.id.edtBuocLam);
        txtTenDD = (TextView) findViewById(R.id.edtTenDinhDuong);
        txtKhoiLuong = (TextView) findViewById(R.id.edtKhoiLuong);
        txtPhanTram = (TextView) findViewById(R.id.edtPhanTram);
        txtDiaChi = (TextView) findViewById(R.id.edtDiaChi);
        txtThongTinThem = (TextView) findViewById(R.id.edtThongTinThem);

        listSpinnerLoaiMon = new ArrayList<String>();
        listSpinnerDoiTuong = new ArrayList<String>();

        //ĐỔ DỮ LIỆU TỪ FIREBASE VỀ CHO SPIINER LOAI MON
        myRef.child("LoaiMon").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    String value = dataSnapshot.getValue(String.class);
                    listSpinnerLoaiMon.add(value);
                }
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
        ArrayAdapter<String> adapterSpinnerLoaiMon = new ArrayAdapter<String>(this,R.layout.spinner_item, listSpinnerLoaiMon);
        adapterSpinnerLoaiMon.setDropDownViewResource(R.layout.spinner_dropdown_item);
        listSpinnerLoaiMon.add("Chọn loại món");
        spnLoaiMon.setAdapter(adapterSpinnerLoaiMon);

        //ĐỔ DỮ LIỆU TỪ FIREBASE VỀ CHO SPIINER DOI TUONG
        myRef.child("DoiTuong").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) {
                    String value = dataSnapshot.getValue(String.class);
                    listSpinnerDoiTuong.add(value);
                }
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
        ArrayAdapter<String> adapterSpinnerDoiTuong = new ArrayAdapter<String>(this,R.layout.spinner_item, listSpinnerDoiTuong);
        adapterSpinnerDoiTuong.setDropDownViewResource(R.layout.spinner_dropdown_item);
        listSpinnerDoiTuong.add("Chọn đối tượng người dùng");
        spnDoiTuong.setAdapter(adapterSpinnerDoiTuong);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonAnh();
                /*// Get the data from an ImageView as bytes
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = storageRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                    }
                });*/
                //imageView.setImageURI(filePath);

            }
        });

        //myRef.child("BaiViet").push().setValue(baiViet);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtTenMonAn.getText() == ""){
                    Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa nhập tên món ăn", Toast.LENGTH_SHORT).show();
                }
                    else if(txtNguyenLieu.getText() == ""){
                        Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa nhập nguyên liệu", Toast.LENGTH_SHORT).show();
                    }
                        else if(txtBuocLam.getText() == ""){
                                Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa nhập bước làm", Toast.LENGTH_SHORT).show();
                            }
                                else if(txtTenDD.getText() == ""){
                                    Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa nhập tên dinh dưỡng", Toast.LENGTH_SHORT).show();
                                }
                                    else if(txtKhoiLuong.getText() == ""){
                                        Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa nhập khối lượng", Toast.LENGTH_SHORT).show();
                                    }
                                        else if(txtPhanTram.getText() == ""){
                                            Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa nhập phần trăm", Toast.LENGTH_SHORT).show();
                                        }
                                            else if(txtDiaChi.getText() == ""){
                                                Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                                            }
                                                else{
                                                        taiAnh();
                }

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

    public void PushFireBase(final Long nameImage){
        final Calendar calendar = Calendar.getInstance();

        final String key = myRef.child("BaiViet").push().getKey();
        final String keyDanhGia = myRef.child("DanhGia").push().getKey();

        danhGiaBaiViet = new DanhGiaBaiViet(keyDanhGia,0,0,0,0,0);

        int doiTuong = spnDoiTuong.getSelectedItemPosition();
        int loaiMon = spnLoaiMon.getSelectedItemPosition();

        baiViet = new BaiViet(key,txtTenMonAn.getText()+"","",txtNguyenLieu.getText()+"",txtBuocLam.getText()+"",txtTenDD.getText() + "" + txtKhoiLuong.getText() + "" + txtPhanTram.getText() + "",txtDiaChi.getText()+"",txtThongTinThem.getText()+"", doiTuong, loaiMon,"11/05/2000","17/4/2019",0,0,1,113+"",keyDanhGia);


        myRef.child("BaiViet").child(key).setValue(baiViet, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError == null)
                {
                    hinhAnh = new HinhAnh(nameImage+"",200,200,0,key);
                    myRef.child("HinhAnh").child(nameImage+"").setValue(hinhAnh);
                    myRef.child("DanhGiaBaiViet").child(keyDanhGia).setValue(danhGiaBaiViet);

                    Toast.makeText(ThemBaiVietActivity.this, "Đăng bài viết thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ThemBaiVietActivity.this, "Đăng bài viết thất bại", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }

    private void chonAnh() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), 71);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 71 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap b = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
       /* try
        {   // When an Image is picked
            if (requestCode == 71 && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                filePath = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(filePath,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                // Set the Image in ImageView after decoding the String
                imageView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "Bạn chưa chọn ảnh",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi ảnh", Toast.LENGTH_LONG)
                    .show();
        }*/
    }

    private void taiAnh() {
        if(filePath != null)
        {
            Calendar calendar = Calendar.getInstance();
            final Long NameImage = calendar.getTimeInMillis();
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Tải ảnh lên");
            progressDialog.show();

            StorageReference ref = storageRef.child("images/"+ NameImage.toString());
            ref.putFile(filePath)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();

                    PushFireBase(NameImage);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    /*Toast.makeText(ThemBaiVietActivity.this, "Đăng bài viết thất bại", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);*/
                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                            .getTotalByteCount());
                    progressDialog.setMessage("Đang tải hình ảnh "+(int) progress +"%");
                }
            });
        }
        else {
            Toast.makeText(ThemBaiVietActivity.this, "Bạn chưa chọn ảnh", Toast.LENGTH_SHORT).show();
        }
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
