package com.example.waiduriid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.waiduriid.database.AppDatabase;
import com.example.waiduriid.database.entitas.Waiduri;

public class ActivityAdd extends AppCompatActivity implements View.OnClickListener {
    EditText editNama, editManfaat, editHarga;
    SeekBar seekBarStock;
    TextView stockSeekbar;
    RadioButton radioMini, radioMedium, radioBesar, rb;
    RadioGroup rgSize;
    Button btnTampil;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    String textNama, textManfaat, textHarga, textStock;

    private AppDatabase database;
    private boolean isEdit = false;
    private int id_waiduri = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = AppDatabase.getInstance(getApplicationContext());

        editNama = (EditText) findViewById(R.id.editNama);
        editManfaat = (EditText) findViewById(R.id.editManfaat);
        editHarga = (EditText) findViewById(R.id.editHarga);

        radioMini = (RadioButton) findViewById(R.id.radioMini);
        radioMedium = (RadioButton) findViewById(R.id.radioMedium);
        radioBesar = (RadioButton) findViewById(R.id.radioBesar);
        rgSize = (RadioGroup) findViewById(R.id.rGroup_size);

        cb1 = (CheckBox) findViewById(R.id.bahan1);
        cb2 = (CheckBox) findViewById(R.id.bahan2);
        cb3 = (CheckBox) findViewById(R.id.bahan3);
        cb4 = (CheckBox) findViewById(R.id.bahan4);
        cb5 = (CheckBox) findViewById(R.id.bahan5);
        cb6 = (CheckBox) findViewById(R.id.bahan6);


        btnTampil = (Button) findViewById(R.id.btnTampilkan);
        btnTampil.setOnClickListener(this);

        seekBarStock = (SeekBar) findViewById(R.id.seekbarStock); // ini untuk tombol
        stockSeekbar = (TextView) findViewById(R.id.stockSeekbar); // ini untuk text

        Intent intent = getIntent();
        id_waiduri = intent.getIntExtra("id_waiduri", 0);
        if (id_waiduri>0) {
            isEdit = true;
            Waiduri Waiduri = database.waiduriDao().get(id_waiduri);
            editNama.setText(Waiduri.Nama);
            editManfaat.setText(Waiduri.Manfaat);
            editHarga.setText(Waiduri.Harga);
            stockSeekbar.setText(Waiduri.Stock);

            if (Waiduri.Size.toString().equals("Mini")) {
                radioMini.setChecked(true);
            } if (Waiduri.Size.toString().equals("Medium")) {
                radioMedium.setChecked(true);
            } else if (Waiduri.Size.toString().equals("Besar")) {
                radioBesar.setChecked(true);
            }

        }else{
            isEdit = false;
        }

        seekBarStock.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stockSeekbar.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        int radio = rgSize.getCheckedRadioButtonId();
        rb = findViewById(radio);

        String Nama = editNama.getText().toString();
        String Manfaat = editManfaat.getText().toString();
        String Harga = editHarga.getText().toString();
        String Bahan = "";
        String stock = stockSeekbar.getText().toString();
        String size = rb.getText().toString();


        //Check Box
        if (cb1.isChecked()) {
            Bahan += "Kopi";
        }
        if (cb2.isChecked()) {
            Bahan += "Susu";
        }
        if (cb3.isChecked()) {
            Bahan += "Green Tea";
        }
        if (cb4.isChecked()) {
            Bahan += "Beras";
        }
        if (cb5.isChecked()) {
            Bahan += "Safron";
        }
        if (cb6.isChecked()) {
            Bahan += "Rose";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Toast.makeText(this, "Mohon Cek Kembali", Toast.LENGTH_SHORT).show();
        builder.setTitle("Pengecekan Ulang");
        String finalBahan = Bahan;
        builder.setMessage(
                "Barang : " + String.valueOf(Nama) + "\n" +
                        "Harga : " + "Rp." + String.valueOf(Harga) + "\n" +
                        "Manfaat : " + String.valueOf(Manfaat) + "\n" +
                        "Size : " + String.valueOf(size) + "\n" +
                        "Bahan : " + String.valueOf(Bahan) + "\n" +
                        "Stock : " + String.valueOf(stock) + "." + "\n" + "\n" + "Apakah anda yakin ingin menyimpan produk ini ?")
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), com.example.waiduriid.ActivityTampil.class);

                        if (isEdit){
                            database.waiduriDao().update(id_waiduri, Nama, Manfaat, Harga, finalBahan, size, stock);
                            intent.putExtra("nama", Nama);
                            intent.putExtra("manfaat", Manfaat.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("bahan", finalBahan);
                            intent.putExtra("size", size);
                            intent.putExtra("stock", stock);
                        }else{
                            database.waiduriDao().insertWaiduri(Nama, Manfaat, Harga, finalBahan, size, stock);
                            intent.putExtra("nama", Nama);
                            intent.putExtra("manfaat", Manfaat.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("bahan", finalBahan);
                            intent.putExtra("size", size);
                            intent.putExtra("stock", stock);
                        }

                        startActivity(intent);
                        finish();
                    }

                });
        builder.setNegativeButton(
                "Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(com.example.waiduriid.ActivityAdd.this, "Mohon input dengan benar", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialoghasil = builder.create();
        dialoghasil.show();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, com.example.waiduriid.MainActivity.class);
        startActivity(intent);
    };


    //Untuk menampilkan hasil input data diatas di Activity Tampil
    public void openActivityTampil() {
        Intent intent = new Intent(this, ActivityTampil.class);

        String Nama = editNama.getText().toString();
        String Manfaat = editManfaat.getText().toString();
        String Harga = editHarga.getText().toString();
        String Bahan = "";
        String stock = stockSeekbar.getText().toString();
        String size = rb.getText().toString();


        //Radio Button Size
        if (radioMini.isChecked()) {
            size += "Mini";
        }
        if (radioMedium.isChecked()) {
            size += "Medium";
        }


        ///Check Box
        if (cb1.isChecked()) {
            Bahan += "Kopi";
        }
        if (cb2.isChecked()) {
            Bahan += "Susu";
        }
        if (cb3.isChecked()) {
            Bahan += "Green Tea";
        }
        if (cb4.isChecked()) {
            Bahan += "Beras";
        }
        if (cb5.isChecked()) {
            Bahan += "Safron";
        }
        if (cb6.isChecked()) {
            Bahan += "Rose";
        }

        intent.putExtra("nama", Nama);
        intent.putExtra("bahan", Bahan);
        intent.putExtra("harga", Harga);
        intent.putExtra("bahan", Bahan);
        intent.putExtra("ukuran", size);
        intent.putExtra("stock", stock);

        startActivity(intent);

    }

    //Lifecycle
    @Override //saat pencet tombol tambah
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Silahkan Masukan Data Produk", Toast.LENGTH_SHORT).show();
    }

    @Override //saat input data
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Input Data Sedang Berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override //loading
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Mohon Menunggu",Toast.LENGTH_SHORT).show();
    }

    @Override //selesai menginput data
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Produk Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
    }

    @Override //keluar aplikasi
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup, Selamat Tinggal",Toast.LENGTH_SHORT).show();
    }

}
