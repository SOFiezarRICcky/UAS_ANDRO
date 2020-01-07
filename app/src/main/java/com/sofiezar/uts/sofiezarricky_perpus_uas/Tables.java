package com.sofiezar.uts.sofiezarricky_perpus_uas;

import android.provider.BaseColumns;

public class Tables {

    public Tables(){
    }

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "m_users";
        public static final String NAMA = "nama";
        public static final String PASSWORD = "password";
    }

    /* Inner class that defines the table contents */
    public static class Books implements BaseColumns {
        public static final String TABLE_NAME = "m_books";
        public static final String KODE_TRANSAKSI = "kode_transaksi";
        public static final String NAMA_BUKU = "nama_buku";
        public static final String LAMA_PINJAM = "lama_pinjam";
    }

}
