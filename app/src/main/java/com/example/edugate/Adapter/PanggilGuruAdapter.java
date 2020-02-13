package com.example.edugate.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edugate.Models.Guru;
import com.example.edugate.Models.PanggilGuru;
import com.example.edugate.PanggilGuruActivity;
import com.example.edugate.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PanggilGuruAdapter extends RecyclerView.Adapter<PanggilGuruAdapter.ViewHolder> implements Filterable {
    private List<Guru> mList;
    private List<Guru> mListFiltered;
    private Context mContext;
    private Dialog myDialog;
    String namaMurid;
    String keyKelasMurid;
    public PanggilGuruAdapter(List<Guru> listGuru) {
        this.mList = listGuru;
        this.mListFiltered = listGuru;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.item_guru, parent, false);
        final ViewHolder vHolder = new ViewHolder(v);
        mContext = parent.getContext();


        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_panggil_guru);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDataMurid();
        vHolder.panggil_guru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TextView nama =myDialog.findViewById(R.id.dialog_name_id);
                TextView pelajaran =myDialog.findViewById(R.id.dialog_pelajaran_id);
                final Button panggil_button = myDialog.findViewById(R.id.panggil_button);
                final Button panggil_batal = myDialog.findViewById(R.id.batal_button);
                final ProgressBar pgPanggilGuru = myDialog.findViewById(R.id.pg_panggil_guru);
                nama.setText(mList.get(vHolder.getAdapterPosition()).getNama());
                pelajaran.setText(mList.get(vHolder.getAdapterPosition()).getPelajaran());

                myDialog.show();

                panggil_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pgPanggilGuru.setVisibility(View.VISIBLE);
                        panggil_button.setVisibility(View.INVISIBLE);
                        panggil_batal.setVisibility(View.INVISIBLE);
                        String namaGuru = mListFiltered.get(vHolder.getAdapterPosition()).getNama();
                        String uidGuru = mListFiltered.get(vHolder.getAdapterPosition()).getUid();
                        String pelajaranGuru = mListFiltered.get(vHolder.getAdapterPosition()).getPelajaran();
                        DatabaseReference refPanggilGuru = FirebaseDatabase.getInstance().getReference("PanggilGuru");
                        String id = refPanggilGuru.push().getKey();
                        PanggilGuru panggilGuru = new PanggilGuru(namaGuru,pelajaranGuru,uidGuru,namaMurid,keyKelasMurid);
                        refPanggilGuru.child(id).setValue(panggilGuru).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                pgPanggilGuru.setVisibility(View.INVISIBLE);
                                panggil_button.setVisibility(View.VISIBLE);
                                panggil_batal.setVisibility(View.VISIBLE);
                                myDialog.dismiss();
                                Toast.makeText(mContext, "Panggil Guru Berhasil", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pgPanggilGuru.setVisibility(View.INVISIBLE);
                                panggil_button.setVisibility(View.VISIBLE);
                                panggil_batal.setVisibility(View.VISIBLE);

                                Toast.makeText(mContext, "Panggil Guru Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });

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

    private void getDataMurid() {

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference refMurid = FirebaseDatabase.getInstance().getReference("Users").child("Murid").child(currentUser.getUid());

        refMurid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namaMurid= dataSnapshot.child("name").getValue(String.class);
                keyKelasMurid = dataSnapshot.child("kelas").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nama.setText(mListFiltered.get(position).getNama());
        holder.pelajaran.setText(mListFiltered.get(position).getPelajaran());
    }

    @Override
    public int getItemCount() {
        return mListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if(key.isEmpty()) {
                    mListFiltered = mList;
                } else {
                    List<Guru> listFiltered =  new ArrayList<>();
                    for(Guru row : mList) {
                        if(row.getNama().toLowerCase().contains(key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }

                    mListFiltered = listFiltered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListFiltered = (List<Guru>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
