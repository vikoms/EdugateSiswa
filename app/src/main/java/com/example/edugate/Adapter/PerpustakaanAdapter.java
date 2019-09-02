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

import com.example.edugate.Models.Book;
import com.example.edugate.R;

import java.util.List;


public class PerpustakaanAdapter extends RecyclerView.Adapter<PerpustakaanAdapter.ViewHolder> {

    private List<Book> BookList;
    private Context mContext;

    public PerpustakaanAdapter(List<Book> bookList, Context mContext) {
        BookList = bookList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_perpustakaan, parent, false);
        final ViewHolder vHolder = new ViewHolder(view);

        final Dialog dialogPerpus = new Dialog(mContext);
        dialogPerpus.setContentView(R.layout.dialog_perpustakaan);
        dialogPerpus.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.perpus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) dialogPerpus.findViewById(R.id.tv_perpus);
                ImageView ivPerpus = (ImageView) dialogPerpus.findViewById(R.id.img_dialog_perpus);
                Button btn_ya = (Button) dialogPerpus.findViewById(R.id.btn_ya_perpus);
                Button btn_no = (Button) dialogPerpus.findViewById(R.id.btn_tidak_perpus);

                tv.setText("Anda akan mendownload " + BookList.get(vHolder.getAdapterPosition()).getJudul_buku());
                ivPerpus.setImageResource(BookList.get(vHolder.getAdapterPosition()).getImage());

                dialogPerpus.show();

                btn_ya.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPerpus.dismiss();
                        Toast.makeText(mContext,"Buku sedang di download",Toast.LENGTH_SHORT).show();
                    }
                });
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogPerpus.dismiss();
                    }
                });
            }
        });




        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.judul.setText(BookList.get(position).getJudul_buku());
        holder.kelas.setText(BookList.get(position).getKelas());
        holder.penerbit.setText(BookList.get(position).getPenerbit());
        holder.gambar_buku.setImageResource(BookList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView judul,kelas,penerbit;
        private ImageView gambar_buku;
        private CardView perpus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = (TextView) itemView.findViewById(R.id.judul_buku);
            kelas = (TextView) itemView.findViewById(R.id.kelas_buku);
            penerbit = (TextView) itemView.findViewById(R.id.penerbit_buku);
            gambar_buku = (ImageView) itemView.findViewById(R.id.gambar_buku);
            perpus = (CardView) itemView.findViewById(R.id.perpustakaan_id);

        }
    }

}
