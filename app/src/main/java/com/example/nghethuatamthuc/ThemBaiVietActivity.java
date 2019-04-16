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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.BaiViet;
import com.example.nghethuatamthuc.models.HinhAnh;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

public class ThemBaiVietActivity extends AppCompatActivity{
    private Toolbar mTopToolbar;
    private BaiViet baiViet;
    private HinhAnh hinhAnh;
    private Uri filePath;

    private ImageView imageView;

    FirebaseStorage storage;
    StorageReference storageRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thembaiviet_layout);

        imageView = (ImageView) findViewById(R.id.imageView);
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

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        mTopToolbar = (Toolbar) findViewById(R.id.toolbarThemBaiViet);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        Button btnThem = (Button) findViewById(R.id.btnDangTai);

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
                final Calendar calendar = Calendar.getInstance();
                final Long NameImage = calendar.getTimeInMillis();
                taiAnh(NameImage);
                final String key = myRef.child("BaiViet").push().getKey();

                baiViet = new BaiViet(key,txtTenMonAn.getText()+"","",txtNguyenLieu.getText()+"",txtBuocLam.getText()+"",txtTenDD.getText() + "" + txtKhoiLuong.getText() + "" + txtPhanTram.getText() + "",txtDiaChi.getText()+"",txtThongTinThem.getText()+"",0,0,calendar.getTime().getSeconds()+"","6-4-2019",5,5,1,113,5);


                myRef.child("BaiViet").child(key).setValue(baiViet, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError == null)
                        {
                            hinhAnh = new HinhAnh(NameImage+"",200,200,0,key);
                            myRef.child("HinhAnh").child(NameImage+"").setValue(hinhAnh);

                            /*Toast.makeText(ThemBaiVietActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);*/
                        }
                        else {
                            /*Toast.makeText(ThemBaiVietActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);*/
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

    private void chonAnh() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), 71);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try
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
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void taiAnh(Long NameImage) {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Tải ảnh lên");
            progressDialog.show();

            StorageReference ref = storageRef.child("images/"+ NameImage.toString());
            ref.putFile(filePath)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(ThemBaiVietActivity.this, "Đăng bài viết thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(ThemBaiVietActivity.this, "Đăng bài viết thất bại", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemBaiVietActivity.this, QuanLyBaiVietActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
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
