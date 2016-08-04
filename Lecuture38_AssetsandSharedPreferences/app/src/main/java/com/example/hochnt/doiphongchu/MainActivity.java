package com.example.hochnt.doiphongchu;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
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
        }
        catch (Exception e)
        {
            Log.e("LOI_FONT",e.toString());
        }


    }
}
