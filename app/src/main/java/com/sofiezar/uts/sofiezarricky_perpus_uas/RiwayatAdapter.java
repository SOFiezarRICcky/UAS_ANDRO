package com.sofiezar.uts.sofiezarricky_perpus_uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.MyViewHolder> {

    public ArrayList<String> listRiwayat = new ArrayList<String>();

    //Constructor
    public RiwayatAdapter(ArrayList<String>listRiwayat){
        this.listRiwayat = listRiwayat;
    }

    @NonNull
    @Override
    public RiwayatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_riwayat, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatAdapter.MyViewHolder myViewHolder, final int i) {
        String riwayat = listRiwayat.get(i);
        myViewHolder.tvRiwayat.setText( riwayat );

        myViewHolder.btnHapus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listRiwayat.remove(i);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRiwayat.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRiwayat;
        public Button btnHapus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRiwayat = itemView.findViewById(R.id.tvRiwayat);
            btnHapus = itemView.findViewById(R.id.btnHapus);
        }
    }
}
