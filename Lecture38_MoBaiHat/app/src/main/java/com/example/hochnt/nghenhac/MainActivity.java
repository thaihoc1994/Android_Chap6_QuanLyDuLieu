package com.example.hochnt.nghenhac;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhSach;

    ArrayList<String> dsBaiHat;
    ArrayAdapter<String> baiHatAdapter;

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

        lvDanhSach = (ListView) findViewById(R.id.lvDanhSach);

        dsBaiHat = new ArrayList<String>();
        baiHatAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_expandable_list_item_1,dsBaiHat);
        lvDanhSach.setAdapter(baiHatAdapter);

        //lay tai nguyen trong asset
        try {
            AssetManager assetManager = getAssets();
            //lay tap tin, dua vao
            String[] arrMusic = assetManager.list("Music");
            dsBaiHat.addAll(Arrays.asList(arrMusic));
        } catch (IOException e) {
            Log.e("LOI_MUSIC",e.toString());
        }

    }
}
