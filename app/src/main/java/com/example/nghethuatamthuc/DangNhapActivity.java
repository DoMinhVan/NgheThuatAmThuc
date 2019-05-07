package com.example.nghethuatamthuc;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nghethuatamthuc.models.NguoiDung;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookActivity;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtTenDangNhap, edtMatKhau;
    Button btnDangNhap, btnHuy, btnFacebook, btnEmail;
    RadioGroup radioGroup;
    RadioButton radNoiTro, radAmThuc, radDinhDuong;
    CheckBox chkLuuDangNhap;
    TextView txtDangKy;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private ArrayList<NguoiDung> nguoiDungArrayList = new ArrayList<>();
    private int idNguoiDung = 0;
    private NguoiDung nguoiDung;
    CallbackManager callbackManager;
    String email, name, first_name, id, birthday;
    private static final int RC_SIGN_IN = 123;
    public static int KIEM_TRA_DANG_NHAP = 0;
    private static boolean in = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap_layout);
        //Ánh Xạ
        AnhXa();
        //gạch  dưới và in nghiêng
        txtDangKy.setPaintFlags(txtDangKy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtDangKy.setTypeface(null, Typeface.ITALIC);
        //Bắt sự l=kiện
        btnEmail.setOnClickListener(this);
        btnFacebook.setOnClickListener(this);
        btnHuy.setOnClickListener(this);
        txtDangKy.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        radNoiTro.setOnClickListener(this);
        radAmThuc.setOnClickListener(this);
        radDinhDuong.setOnClickListener(this);
        //Facebook
        callbackManager = CallbackManager.Factory.create();
        //Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        //get đối tượng người dùng
        myRef.child("NguoiDung").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    NguoiDung nguoiDung = dataSnapshot.getValue(NguoiDung.class);
                    nguoiDungArrayList.add(nguoiDung);
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
    }

    private void AnhXa() {
        edtTenDangNhap = (EditText) findViewById(R.id.edtTenDangNhap);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        chkLuuDangNhap = (CheckBox) findViewById(R.id.chkLuuDangNhap);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnEmail = (Button) findViewById(R.id.btnEmail);
        txtDangKy = (TextView) findViewById(R.id.txtDangKy);
        radNoiTro = (RadioButton) findViewById(R.id.radNoiTro);
        radDinhDuong = (RadioButton) findViewById(R.id.radDinhDuong);
        radAmThuc = (RadioButton) findViewById(R.id.radAmThuc);
    }

    //hàm xử lý khi người dùng chọn đăng nhập
    private void DangNhap() {
        if (radAmThuc.isChecked()) {
            idNguoiDung = 3;
        }
        if (radDinhDuong.isChecked()) {
            idNguoiDung = 2;
        }
        if (radNoiTro.isChecked()) {
            idNguoiDung = 1;
        }
        for (int i = 0; i < nguoiDungArrayList.size(); i++) {
            if (idNguoiDung == (int) nguoiDungArrayList.get(i).getLoaiNguoiDung()) {
                nguoiDung = nguoiDungArrayList.get(i);
                if (edtTenDangNhap.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Tên Đăng Nhập", Toast.LENGTH_SHORT).show();
                } else if (edtMatKhau.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
                }
                if (edtTenDangNhap.getText().toString().equals(nguoiDung.getTenDangNhap()) && edtMatKhau.getText().toString().equals(nguoiDung.getMatKhau())) {
                    if (chkLuuDangNhap.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Luu mat khau", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "chuyển sang trang ca nhan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangNhapActivity.this, ManHinhTrangCaNhanActivity.class);
                        intent.putExtra("NguoiDung", nguoiDungArrayList.get(i));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "chuyển sang trang ca nhan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangNhapActivity.this, ManHinhTrangCaNhanActivity.class);
                        intent.putExtra("NguoiDung", nguoiDungArrayList.get(i));
                        startActivity(intent);
                    }
                    in = true;
                    KIEM_TRA_DANG_NHAP = 1;
                }
            }
        }
        if (in == false) {
            Toast.makeText(this, "Sai Tai Khoan Hoac Mat Khau", Toast.LENGTH_SHORT).show();
        }
    }

    //bắt sự kiện khi đăng nhập
    @Override
    public void onClick(View v) {
        if (v == btnDangNhap) {
            DangNhap();

        }
        if (v == btnHuy) {
            finish();
            Toast.makeText(this, "Huy", Toast.LENGTH_SHORT).show();
        }
        if (v == btnFacebook) {
            LoginFacebook();
            Toast.makeText(this, "Facebook", Toast.LENGTH_SHORT).show();
        }
        if (v == btnEmail) {
            LoginEmail();
            Toast.makeText(this, "Email", Toast.LENGTH_SHORT).show();
        }
        if (v == txtDangKy) {
            Intent i = new Intent(DangNhapActivity.this, DangKyActivity.class);
            startActivity(i);
        }
    }

    private void LoginFacebook() {
        //xin quyền đăng nhập từ người dùng
        LoginManager.getInstance().logInWithReadPermissions(DangNhapActivity.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KetQuaTraVe();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void LoginEmail() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    private void KetQuaTraVe() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON", response.getJSONObject().toString());
                try {
                    email = object.getString("email");
                    name = object.getString("name");
                    first_name = object.getString("first_name");
                    id = Profile.getCurrentProfile().getId();
                    KIEM_TRA_DANG_NHAP = 2;//gửi giá trị kiểm tra đăng nhập bên trang cá nhân
                    Intent i = new Intent(DangNhapActivity.this, ManHinhTrangCaNhanActivity.class);
                    i.putExtra("TenFB", name);
                    i.putExtra("ID", id);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        //Lấy kq trả về
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}
