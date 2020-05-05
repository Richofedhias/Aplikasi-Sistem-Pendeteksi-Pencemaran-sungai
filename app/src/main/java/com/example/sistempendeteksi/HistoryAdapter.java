package com.example.sistempendeteksi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.myViewholder> {

    Context context;
    ArrayList<HistoryList> lists;

    public HistoryAdapter(Context context, ArrayList<HistoryList> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_history_list, parent,false);
        return new HistoryAdapter.myViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewholder holder, int position) {
        final HistoryList list = lists.get(position);
        holder.hari.setText(list.getHari() + "/");
        holder.bulan.setText(list.getBulan() + "/");
        holder.tahun.setText(list.getTahun());
        holder.suhu.setText(list.getSuhu());
        holder.ph.setText(list.getPh());
        holder.kekeruhan.setText(list.getKekruhan());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class myViewholder extends RecyclerView.ViewHolder {
        TextView hari, bulan, tahun, suhu, ph, kekeruhan;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.hari);
            bulan = itemView.findViewById(R.id.bulan);
            tahun = itemView.findViewById(R.id.tahun);
            suhu = itemView.findViewById(R.id.suhu);
            ph = itemView.findViewById(R.id.ph);
            kekeruhan = itemView.findViewById(R.id.kekeruhan);
        }
    }
}
