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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.edugate.Models.IzinPiket;
import com.example.edugate.R;

import java.util.List;

public class ListIzinPiketAdapter extends RecyclerView.Adapter<ListIzinPiketAdapter.MyViewHolder> {

    List<IzinPiket> listIzinPiket;
    Dialog myDialog;
    Context mContext;

    public ListIzinPiketAdapter(List<IzinPiket> listIzinPiket, Context mContext) {
        this.listIzinPiket = listIzinPiket;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_izin_piket,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final IzinPiket izinPiket = listIzinPiket.get(position);
        holder.txtTitle.setText(izinPiket.getAlasanIzin());
        holder.txtTime.setText(izinPiket.getWaktuIzin());

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_izin_piket);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog(izinPiket);
                myDialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listIzinPiket.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle,txtTime;
        CardView rvConteiner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.time_izin_piket);
            txtTitle = itemView.findViewById(R.id.titleIzinPiket);
            rvConteiner = itemView.findViewById(R.id.cv_container_list_izin_piket);
        }
    }

    private void initDialog(IzinPiket izinPiket) {

        ImageView imgDialogPiket = myDialog.findViewById(R.id.img_dialog_piket);
        TextView txtTitle = myDialog.findViewById(R.id.tv_alasan);
        TextView txtTime = myDialog.findViewById(R.id.tv_time);
        TextView txtStatus = myDialog.findViewById(R.id.tv_status_izin_piket);
        Button btnClose = myDialog.findViewById(R.id.btn_close_dialog);

        String statusIzinPiket = izinPiket.getStatus();
        if (statusIzinPiket.equals("0")) {
            imgDialogPiket.setImageResource(R.drawable.ic_textsms_black_24dp);
            txtStatus.setText("Izin piket anda belum di proses");
            txtTitle.setText("Alasan : " + izinPiket.getAlasanIzin());
            txtTime.setText("Waktu : " + izinPiket.getWaktuIzin());
        } else if(statusIzinPiket.equals("1")) {
            imgDialogPiket.setImageResource(R.drawable.ic_check_black_24dp);
            txtStatus.setText("Izin piket anda diterima");
            txtTitle.setText("Alasan : " + izinPiket.getAlasanIzin());
            txtTime.setText("Waktu : " + izinPiket.getWaktuIzin());
        } else if(statusIzinPiket.equals("2")) {
            imgDialogPiket.setImageResource(R.drawable.ic_close_black_24dp);
            txtStatus.setText("Izin piket anda diTolak");
            txtTitle.setText("Alasan : " + izinPiket.getAlasanIzin());
            txtTime.setText("Waktu : " + izinPiket.getWaktuIzin());
        }

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
    }

}
