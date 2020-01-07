package com.sofiezar.uts.sofiezarricky_perpus_uas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "perpustakaanuas.db";

    private static final int DATABASE_VERSION = 2;

    //m_users
    private static final String SQL_CREATE_M_USERS = "CREATE TABLE "+ Tables.Users.TABLE_NAME+ " (" +
            " "+ Tables.Users._ID +" INTEGER PRIMARY KEY, " +
            " "+ Tables.Users.NAMA + " TEXT," +
            " "+ Tables.Users.PASSWORD +" TEXT ) ";
    private static final String SQL_DELETE_M_USERS = "DROP TABLE IF EXISTS "+
            Tables.Users.TABLE_NAME;

    //m_books
    private static final String SQL_CREATE_M_BOOKS = "CREATE TABLE "+ Tables.Books.TABLE_NAME+ " (" +
            " "+ Tables.Books._ID +" INTEGER PRIMARY KEY, " +
            " "+ Tables.Books.KODE_TRANSAKSI + " TEXT," +
            " "+ Tables.Books.NAMA_BUKU + " TEXT," +
            " "+ Tables.Books.LAMA_PINJAM +" TEXT ) ";
    private static final String SQL_DELETE_M_BOOKS = "DROP TABLE IF EXISTS "+
            Tables.Books.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_M_USERS);
        db.execSQL(SQL_CREATE_M_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_M_USERS);
        db.execSQL(SQL_DELETE_M_BOOKS);

        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDowngrade(db, oldVersion, newVersion);
    }


}
