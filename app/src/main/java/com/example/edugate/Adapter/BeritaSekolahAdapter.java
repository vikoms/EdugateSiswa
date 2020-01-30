package com.example.edugate.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.edugate.Models.BeritaSekolah;
import com.example.edugate.R;
import com.example.edugate.detail_berita;

import org.w3c.dom.Text;

import java.util.List;

public class BeritaSekolahAdapter extends RecyclerView.Adapter<BeritaSekolahAdapter.ViewHolder> {

    private List<BeritaSekolah> mList;
    private Context mContext;

    public BeritaSekolahAdapter(List<BeritaSekolah> listBerita) {
        this.mList = listBerita;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita_sekolah, parent, false);
        final ViewHolder vHolder = new ViewHolder(view);

        mContext = parent.getContext();

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BeritaSekolah berita = mList.get(position);
        holder.titleBeritaSekolah.setText(berita.getJudulBerita());
        holder.descBeritaSekolah.setText(berita.getIsiBerita());

        Glide.with(mContext)
                .load(berita.getGambarBerita())
                .into(holder.image);

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BeritaSekolah beritaIntent = new BeritaSekolah();
                beritaIntent.setGambarBerita(berita.getGambarBerita());
                beritaIntent.setIsiBerita(berita.getIsiBerita());
                beritaIntent.setJudulBerita(berita.getJudulBerita());
                beritaIntent.setTanggalBerita(berita.getTanggalBerita());
                Intent moveToDetail = new Intent(mContext, detail_berita.class);
                moveToDetail.putExtra(detail_berita.EXTRA_BERITA,beritaIntent);
                mContext.startActivity(moveToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView titleBeritaSekolah, descBeritaSekolah;
        CardView cvContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.gambar_berita);
            titleBeritaSekolah = (TextView) itemView.findViewById(R.id.title_beritaSekolah);
            descBeritaSekolah = (TextView) itemView.findViewById(R.id.desc_beritaSekolah);
            cvContainer = itemView.findViewById(R.id.item_beritaSekolah);
        }
    }
}
