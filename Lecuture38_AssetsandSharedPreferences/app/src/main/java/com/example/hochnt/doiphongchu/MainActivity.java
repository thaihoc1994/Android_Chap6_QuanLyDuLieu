package com.example.hochnt.doiphongchu;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView txtFont;
    ListView lvFont;
    ArrayList<String> dsFont;
    ArrayAdapter<String> fontAdapter;

    String tenLuuTru = "TrangThaiFont";//luu tru đặt tên cho file XML
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
        lvFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                xuLyDoiFontChu(postion);
            }
        });
    }

    private void xuLyDoiFontChu(int postion) {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"Font/"+ dsFont.get(postion));//đổi font chữ từ access
        txtFont.setTypeface(typeface);

        SharedPreferences preferences = getSharedPreferences(tenLuuTru,MODE_PRIVATE);//(Ten tap tin muon dat, che do luu tru)
        //Luu tru xuong
        SharedPreferences.Editor editor = preferences.edit();//tra về Editor ==> cho phép lưu dữ liêu xuống file
        editor.putString("FONTCHU","Font/"+ dsFont.get(postion));//đánh dấu lưu trữ
        //=> xác nhận lưu trữ xuống file xml
        editor.commit();
    }

    private void addControl() {
        txtFont = (TextView) findViewById(R.id.txtFont);
        lvFont = (ListView) findViewById(R.id.lvFont);

        dsFont = new ArrayList<>();
        fontAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,dsFont);
        lvFont.setAdapter(fontAdapter);

        AssetManager assetManager = getAssets();//lay het tai nguyen co trong asset
        //lay tap tin trong font
        try{
            String[] arrayFont = assetManager.list("Font");//thư mục nằm trong asset
            //dua vao font chu
            dsFont.addAll(Arrays.asList(arrayFont));
            //truyen vao font chữ lúc load lên
            SharedPreferences preferences = getSharedPreferences(tenLuuTru,MODE_PRIVATE);//(Ten tap tin muon dat, che do luu tru)
            String font = preferences.getString("FONTCHU","");//Set chuoi chua font da luu tru => đường dẫn chứa font
            if (font.length()>0)
            {
                Typeface typeface = Typeface.createFromAsset(getAssets(),font);//đổi font chữ từ access
                txtFont.setTypeface(typeface);
            }
        }
        catch (Exception e)
        {
            Log.e("LOI_FONT",e.toString());
        }


    }
}
