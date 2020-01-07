package com.sofiezar.uts.sofiezarricky_perpus_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RegisterActivity extends AppCompatActivity {


    private ImageView btnBack;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etKonfirmasiPassword;
    private Button btnRegister;

    //database
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //inisiasi database
        dbHelper = new DBHelper(this);


        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etKonfirmasiPassword = findViewById(R.id.etKonfirmasiPassword);
        btnRegister = findViewById(R.id.btnRegister);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getText().toString().equalsIgnoreCase("")||etKonfirmasiPassword.getText().toString().equalsIgnoreCase("")||etUsername.getText().toString().equalsIgnoreCase("")){
                    dialog("Daftar Gagal");
                }
                else{


                if(etPassword.getText().toString().equalsIgnoreCase(etKonfirmasiPassword.getText().toString())){

                    //mode
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

                    //isi nilai
                    ContentValues values = new ContentValues();
                    values.put(Tables.Users.NAMA, etUsername.getText().toString());
                    values.put(Tables.Users.PASSWORD, etPassword.getText().toString());

                    //simpan data
                    long newRowId = db.insert(Tables.Users.TABLE_NAME, null, values);

                    //jika data berhasil tersimpan newRowId akan 1
                    if(newRowId > 0){
                        //untuk menutup activity
                        finish();
                        //dialogError();
                    }else{
                        dialog("Simpan berhasil");
                    }
                }else{
                    dialog("Password Salah");
                }
                }


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
                .setMessage( msg )
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

