package com.example.sistempendeteksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PenanggulanganActivity extends AppCompatActivity {
    private RecyclerView rv_list;
    private ArrayList<RCList> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penanggulangan);
        rv_list = findViewById(R.id.rv_list);
        rv_list.setHasFixedSize(true);

        list.addAll(ItemData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        RCAdapter listAdapter = new RCAdapter(list);
        rv_list.setAdapter(listAdapter);
    }
}
