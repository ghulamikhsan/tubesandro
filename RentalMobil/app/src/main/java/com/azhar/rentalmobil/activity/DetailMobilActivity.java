package com.azhar.rentalmobil.activity;

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

import com.azhar.rentalmobil.R;
import com.azhar.rentalmobil.helper.DataHelper;

public class DetailMobilActivity extends AppCompatActivity {

    protected Cursor cursor;
    String sMerk, sHarga, sGambar;
    DataHelper dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);

        Bundle terima = getIntent().getExtras();

        dbHelper = new DataHelper(this);
        Intent intent = getIntent();

        String merk = terima.getString("merk");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from mobil where merk = '" + merk + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            sMerk = cursor.getString(0);
            sHarga = cursor.getString(1);
        }

        if (sMerk.equals("sb_eiger")) {
            sGambar = "sb_eiger";
        } else if (sMerk.equals("sb_consina")) {
            sGambar = "sb_consina";
        } else if (sMerk.equals("deuter")) {
            sGambar = "deuter";
        } else if (sMerk.equals("fjallraven")) {
            sGambar = "fjallraven";
        } else if (sMerk.equals("tenda_arei")) {
            sGambar = "tenda_arei";
        } else if (sMerk.equals("tenda_consina")) {
            sGambar = "tenda_consina";
        } else if (sMerk.equals("trekingpole_eiger")) {
            sGambar = "trekingpole_eiger";
        } else if (sMerk.equals("trekingpole_naturehike")) {
            sGambar = "trekingpole_naturehike";
        } else if (sMerk.equals("kompor2")) {
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
