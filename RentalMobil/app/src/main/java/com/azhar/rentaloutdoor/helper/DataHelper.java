package com.azhar.rentaloutdoor.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rentaloutdoor.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table barang(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references barang (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into barang values (" +
                "'Sleeping Bag Eiger'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Sleeping Bag Consina'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Deuter'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Fjallraven'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Tenda Arei'," +
                "500000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Tenda Consina'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Treking Pole Eiger'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Treking Pole Naturehike'," +
                "700000" +
                ");" +
                "");
        db.execSQL("insert into barang values (" +
                "'Kompor Kotak'," +
                "1500000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from barang";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}