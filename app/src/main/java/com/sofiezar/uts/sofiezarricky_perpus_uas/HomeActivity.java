package com.sofiezar.uts.sofiezarricky_perpus_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button btnSimpan, btnTambah;
    private EditText etNamaBuku;
    private EditText etLamaPinjam;
    private EditText etKodeTransaksi;
    private DBHelper dbHelper;

    //Untuk Adapter
    private RecyclerView recyclerView;
    private RiwayatAdapter adapter;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        dbHelper = new DBHelper(this);


        btnSimpan = findViewById(R.id.btnSimpan);
        btnTambah = findViewById(R.id.btnTambah);
        etNamaBuku = findViewById(R.id.etNamaBuku);
        etLamaPinjam = findViewById(R.id.etLamaPinjam);
        etKodeTransaksi = findViewById(R.id.etKodeTransaksi);

        //inisiasi Recycle
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RiwayatAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NamaBuku = etNamaBuku.getText().toString();
                String LamaPinjam = etLamaPinjam.getText().toString();
                String KodeTransaksi = etKodeTransaksi.getText().toString();

                list.add(KodeTransaksi + "\n" + NamaBuku + "\n" + LamaPinjam);
                adapter.notifyDataSetChanged();

            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int i = 0; i < list.size(); i++){
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put(Tables.Books.KODE_TRANSAKSI,etKodeTransaksi.getText().toString());
                    values.put(Tables.Books.NAMA_BUKU,etNamaBuku.getText().toString());
                    values.put(Tables.Books.LAMA_PINJAM,etLamaPinjam.getText().toString());

                    long newRowId = db.insert(Tables.Books.TABLE_NAME,null,values);
                    if(newRowId > 0){
                        Toast.makeText(HomeActivity.this,"Berhasil Simpan",Toast.LENGTH_SHORT).show();
                    }else{
                        dialog("Gagal Simpan");
                    }
                }

//                //mode
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//                //isi nilai
//                ContentValues values = new ContentValues();
//                values.put(Tables.Books.NAMA_BUKU, etNamaBuku.getText().toString());
//                values.put(Tables.Books.LAMA_PINJAM, etLamaPinjam.getText().toString());
//                values.put(Tables.Books.KODE_TRANSAKSI, etKodeTransaksi.getText().toString());
//
//
//                //simpan data
//                long newRowId = db.insert(Tables.Books.TABLE_NAME, null, values);
//
//                //jika data berhasil tersimpan newRowId akan 1
//                if(newRowId > 0){
//
//                    //untuk menutup activity
//                    dialog("Simpan Berhasil");
//                }else{
//                    dialog("Simpan Gagal");
//                }

            }
        });

    }


    private void dialog(String msg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        // set title dialog
        alertDialogBuilder.setTitle("Pesan");
        // set pesan dari dialog
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // jika tombol diklik, maka akan menutup activity ini
                    }
                });
        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();
        // menampilkan alert dialog
        alertDialog.show();
    }


}
