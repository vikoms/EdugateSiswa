package com.example.edugate.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugate.Models.PanggilGuru;
import com.example.edugate.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IzinPiketAdapter extends RecyclerView.Adapter<IzinPiketAdapter.IzinPiketHolder> {

    List<PanggilGuru> mList;
    OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public IzinPiketAdapter(List<PanggilGuru> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public IzinPiketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guru_izin_piket,parent,false);

        return new IzinPiketHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IzinPiketHolder holder, int position) {
        PanggilGuru guru = mList.get(position);
        holder.txtNama.setText(guru.getNama());
        holder.txtPelajaran.setText(guru.getPelajaran());

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(mList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class IzinPiketHolder extends RecyclerView.ViewHolder{

        CardView cvContainer;
        CircleImageView imgPhoto;
        TextView txtNama;
        TextView txtPelajaran;


        public IzinPiketHolder(@NonNull View itemView) {
            super(itemView);

            cvContainer = itemView.findViewById(R.id.cv_guru_izin_piket);
            imgPhoto = itemView.findViewById(R.id.img_photo_guru_izin_piket);
            txtNama = itemView.findViewById(R.id.txt_nama_guru);
            txtPelajaran = itemView.findViewById(R.id.txt_pelajaran_guru);

        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(PanggilGuru data);
    }
}
