package com.example.edugate.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        mContext = parent.getContext();

        final Dialog dialog_tugas = new Dialog(mContext);
        dialog_tugas.setContentView(R.layout.dialog_tugas_rumah);
        dialog_tugas.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.cv_tugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv_judul_tugas = (TextView) dialog_tugas.findViewById(R.id.tv_dialog_judul_tugas);
                TextView tv_ket_tugas = (TextView) dialog_tugas.findViewById(R.id.tv_dialog_ket_tugas);
                TextView tv_subjudul_tugas = (TextView) dialog_tugas.findViewById(R.id.tv_dialog_subjudul_tugas);
                TextView tv_deadline_tugas = (TextView) dialog_tugas.findViewById(R.id.tv_dialog_deadline_tugas);
                Button btn_ok_tugas = (Button) dialog_tugas.findViewById(R.id.btn_ok_dialog_tugas);

                tv_judul_tugas.setText(listTugas.get(vHolder.getAdapterPosition()).getJudul_tugas());
                tv_ket_tugas.setText(listTugas.get(vHolder.getAdapterPosition()).getKeterangan_tugas());
                tv_subjudul_tugas.setText(listTugas.get(vHolder.getAdapterPosition()).getJudul_tugas());
                tv_deadline_tugas.setText(listTugas.get(vHolder.getAdapterPosition()).getDeadline_tugas());

                dialog_tugas.show();

                btn_ok_tugas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog_tugas.dismiss();
                    }
                });
            }
        });


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_judul.setText(listTugas.get(position).getJudul_tugas());
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
            tv_deadline = (TextView) itemView.findViewById(R.id.deadline_tugas);
            cv_tugas = (CardView) itemView.findViewById(R.id.tugas_rumah_cardview);
        }
    }
}
