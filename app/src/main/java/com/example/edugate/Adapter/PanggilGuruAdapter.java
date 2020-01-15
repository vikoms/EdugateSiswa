package com.example.edugate.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugate.Models.PanggilGuru;
import com.example.edugate.PanggilGuruActivity;
import com.example.edugate.R;

import java.util.List;

public class PanggilGuruAdapter extends RecyclerView.Adapter<PanggilGuruAdapter.ViewHolder> {
    private List<PanggilGuru> mList;
    Context mContext;
    Dialog myDialog;

    public PanggilGuruAdapter(List<PanggilGuru> listGuru) {
        this.mList = listGuru;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.item_guru, parent, false);
        final ViewHolder vHolder = new ViewHolder(v);
        mContext = parent.getContext();

//Inisialisasi Dialog

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_panggil_guru);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.panggil_guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nama =(TextView)myDialog.findViewById(R.id.dialog_name_id);
                TextView pelajaran =(TextView)myDialog.findViewById(R.id.dialog_pelajaran_id);
                ImageView gambar_guru =(ImageView) myDialog.findViewById(R.id.dialog_img);
                Button panggil_button = (Button) myDialog.findViewById(R.id.panggil_button);
                Button panggil_batal = (Button) myDialog.findViewById(R.id.batal_button);

                nama.setText(mList.get(vHolder.getAdapterPosition()).getNama());
                pelajaran.setText(mList.get(vHolder.getAdapterPosition()).getPelajaran());
//                gambar_guru.setImageResource(mList.get(vHolder.getAdapterPosition()).getGambar());

                myDialog.show();

                panggil_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                        Toast.makeText(mContext, "Guru berhasil Dipanggil",Toast.LENGTH_SHORT).show();
                    }
                });

                panggil_batal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });

            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nama.setText(mList.get(position).getNama());
        holder.pelajaran.setText(mList.get(position).getPelajaran());
//        holder.gambar_guru.setImageResource(mList.get(position).getGambar());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, pelajaran;
        private ImageView gambar_guru;
        private LinearLayout panggil_guru;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = (TextView) itemView.findViewById(R.id.nama_guru);
            pelajaran = (TextView) itemView.findViewById(R.id.pelajaran_guru);
            gambar_guru = (ImageView) itemView.findViewById(R.id.img_guru);
            panggil_guru = (LinearLayout) itemView.findViewById(R.id.panggil_guru);
        }
    }
}
