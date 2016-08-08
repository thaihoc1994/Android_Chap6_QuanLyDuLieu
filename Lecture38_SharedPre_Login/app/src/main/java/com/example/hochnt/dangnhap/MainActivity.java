package com.example.hochnt.dangnhap;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtUserName;
    EditText txtPassWord;
    Button btnDangNhap, btnThoat;
    CheckBox chkLuu;

    //Luu trang thai
    String tenThongTinDangNhap = "login"; //=> tao ra file login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addEvent() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);

            }
        });
    }

    private void addControl() {
        txtUserName = (EditText) findViewById(R.id.txtUsername);
        txtPassWord = (EditText) findViewById(R.id.txtPass);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        chkLuu = (CheckBox) findViewById(R.id.chkLuu);

        //Luu trang thai
    }

    @Override
    protected void onPause() {//Luu trang thai
        super.onPause();
        //tu dong luu thong tn
        SharedPreferences preferences = getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserName",txtUserName.getText().toString());
        editor.putString("PassWord",txtPassWord.getText().toString());
        editor.putBoolean("SAVE",chkLuu.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {//Phuc hoi trang thai
        super.onResume();

        SharedPreferences preferences = getSharedPreferences(tenThongTinDangNhap,MODE_PRIVATE);
        String userName = preferences.getString("UserName","");
        String passWord = preferences.getString("PassWord","");
        Boolean save = preferences.getBoolean("SAVE",false);
        if (save)
        {
            txtUserName.setText(userName);
            txtPassWord.setText(passWord);
        }
    }
}
