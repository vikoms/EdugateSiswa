package com.example.edugate.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugate.Models.TugasRumah;
import com.example.edugate.R;

import java.util.List;

public class TugasRumahAdapter extends RecyclerView.Adapter<TugasRumahAdapter.ViewHolder> {

    List<TugasRumah> listTugas;

    public TugasRumahAdapter(List<TugasRumah> listTugas) {
        this.listTugas = listTugas;
    }

    Context mContext;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tugas_rumah,parent,false);
        final ViewHolder vHolder = new ViewHolder(view);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_judul.setText(listTugas.get(position).getJudul_tugas());
        holder.tv_ket.setText(listTugas.get(position).getKeterangan_tugas());
        holder.tv_deadline.setText(listTugas.get(position).getDeadline_tugas());


    }

    @Override
    public int getItemCount() {
        return listTugas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_judul, tv_ket, tv_deadline;
        private CardView cv_tugas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_judul = (TextView) itemView.findViewById(R.id.judul_tugas);
            tv_ket = (TextView) itemView.findViewById(R.id.ket_tugas);
            tv_deadline = (TextView) itemView.findViewById(R.id.deadline_tugas);
            cv_tugas = (CardView) itemView.findViewById(R.id.tugas_rumah_cardview);
        }
    }
}
