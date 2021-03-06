package com.azhar.rentaloutdoor.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.rentaloutdoor.R;
import com.azhar.rentaloutdoor.helper.DataHelper;

public class DetailMobilActivity extends AppCompatActivity {

    protected Cursor cursor;
    String sMerk, sHarga, sGambar;
    DataHelper dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from barang where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("Sleeping Bag Eiger")) {
            sGambar = "sb_eiger";
        } else if (sMerk.equals("Sleeping Bag Consina")) {
            sGambar = "sb_consina";
        } else if (sMerk.equals("Deuter")) {
            sGambar = "deuter";
        } else if (sMerk.equals("Fjallraven")) {
            sGambar = "fjallraven";
        } else if (sMerk.equals("Tenda Arei")) {
            sGambar = "tenda_arei";
        } else if (sMerk.equals("Tenda Consina")) {
            sGambar = "tenda_consina";
        } else if (sMerk.equals("Treking Pole Eiger")) {
            sGambar = "trekingpole_eiger";
        } else if (sMerk.equals("Treking Pole Naturehike")) {
            sGambar = "trekingpole_naturhike";
        } else if (sMerk.equals("Kompor Kotak")) {
            sGambar = "kompor2";
        }

        ImageView ivGambar = findViewById(R.id.ivMobil);
        TextView tvMerk = findViewById(R.id.JMobil);
        TextView tvHarga = findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbDetailMbl);
        toolbar.setTitle("Detail Barang");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
