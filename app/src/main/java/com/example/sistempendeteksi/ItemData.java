package com.example.sistempendeteksi;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ItemData {
    private static String[] Titles = {
            "1. No 1",
            "2. No 2",
            "3. No 3",
            "4. No 4",
            "5. No 5",
            "6. No 6",
    };

    private static String[] Descs = {
            "Isi No 1",
            "Isi No 2",
            "Isi No 3",
            "Isi No 4",
            "Isi No 5",
            "Isi No 6"
    };

    static ArrayList<RCList> getListData() {
        ArrayList<RCList> list = new ArrayList<>();
        for (int position = 0; position < Titles.length; position++) {
            RCList data = new RCList();
            data.setTitle(Titles[position]);
            data.setDesc(Descs[position]);
            list.add(data);
        }
        return list;
    }
}
