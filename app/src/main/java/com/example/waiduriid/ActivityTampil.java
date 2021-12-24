package com.example.waiduriid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTampil extends AppCompatActivity {
    TextView tampilNama, tampilManfaat, tampilHarga, tampilBahan, tampilSize, tampilStock;
    String nama, manfaat, harga, bahan, size, stock;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        tampilNama = (TextView) findViewById(R.id.tampilNama);
        tampilManfaat = (TextView) findViewById(R.id.tampilManfaat);
        tampilHarga = (TextView) findViewById(R.id.tampilHarga);
        tampilBahan = (TextView) findViewById(R.id.tampilBahan);
        tampilSize = (TextView) findViewById(R.id.tampilSize);
        tampilStock = (TextView) findViewById(R.id.tampilStock);

        if (getIntent().getStringExtra("nama") != "") {
            nama = getIntent().getStringExtra("nama");
            tampilNama.setText(nama);
        }
        if (getIntent().getStringExtra("manfaat") != "") {
            manfaat = getIntent().getStringExtra("manfaat");
            tampilManfaat.setText(manfaat);
        }
        if (getIntent().getStringExtra("harga") != "") {
            harga = getIntent().getStringExtra("harga");
            tampilHarga.setText(harga);
        }
        if (getIntent().getStringExtra("bahan") != "") {
            bahan = getIntent().getStringExtra("bahan");
            tampilBahan.setText(bahan);
        }
        if (getIntent().getStringExtra("size") != "") {
            size = getIntent().getStringExtra("size");
            tampilSize.setText(size);
        }
        if (getIntent().getStringExtra("stock") != "") {
            stock = getIntent().getStringExtra("stock");
            tampilStock.setText(stock);
    }
}
    public void  submit (View view) {
        Intent intent = new Intent(com.example.waiduriid.ActivityTampil.this, MainActivity.class);
        startActivity(intent);
    }
}


