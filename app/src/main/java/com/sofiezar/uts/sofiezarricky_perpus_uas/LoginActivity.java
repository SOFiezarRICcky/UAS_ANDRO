package com.sofiezar.uts.sofiezarricky_perpus_uas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private Button btnRegister;
    private Button btnLogin;
    private DBHelper dbHelper;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//inisiasi database
        dbHelper = new DBHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        //event button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saat tombol diklik
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        //akhir event button

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //read DB untuk login
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                //select id, nama, password from m_users where nama = ? and password = ?

                String[] projection = {
                        BaseColumns._ID,
                        Tables.Users.NAMA,
                        Tables.Users.PASSWORD
                };

                String selection = Tables.Users.NAMA+" = ? and "+
                        Tables.Users.PASSWORD+ " = ?";

                String[] selectionArgs = {
                        etUsername.getText().toString(),
                        etPassword.getText().toString()
                };

                String sortOrder = Tables.Users.NAMA + " DESC";

                //SELECT ID, NAMA, PASSWORD from TABLE where username = ? and pasword = ? order by nama asc;

                Cursor cursor = db.query(
                        Tables.Users.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );

                ArrayList<User> list = new ArrayList<User>();
                if(etUsername.getText().toString().equalsIgnoreCase("")||etPassword.getText().toString().equalsIgnoreCase(""))
                { //untuk memunculkan dialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            LoginActivity.this);
                    // set title dialog
                    alertDialogBuilder.setTitle("Pesan");
                    // set pesan dari dialog
                    alertDialogBuilder
                            .setMessage("Login Gagal")
                            .setCancelable(false) //wajib diklik
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // jika tombol diklik, maka akan menutup activity ini
                                    return;
                                }
                            });
                    // membuat alert dialog dari builder
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // menampilkan alert dialog
                    alertDialog.show();
                    //akhir  memunculkan dialog
                    }
                else
                {


                //while
                while (cursor.moveToNext()){

                    //untuk mengambil data dari query
                    String nama = cursor.getString(
                            cursor.getColumnIndexOrThrow(Tables.Users.NAMA)
                    );
                    String password = cursor.getString(
                            cursor.getColumnIndexOrThrow(Tables.Users.PASSWORD)
                    );

                    User user = new User();
                    user.nama = nama;
                    user.password = password;

                    //add object ke array list
                    list.add(user);

                } //akhir while

                //cek ada tidak record di db
                if(list.size() > 0){
                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }else{


                    //untuk memunculkan dialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            LoginActivity.this);
                    // set title dialog
                    alertDialogBuilder.setTitle("Pesan");
                    // set pesan dari dialog
                    alertDialogBuilder
                            .setMessage("Username dan password salah!")
                            .setCancelable(false) //wajib diklik
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // jika tombol diklik, maka akan menutup activity ini
                                    return;
                                }
                            });
                    // membuat alert dialog dari builder
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // menampilkan alert dialog
                    alertDialog.show();
                    //akhir  memunculkan dialog


                }
                }

            }
        });


    }
}
