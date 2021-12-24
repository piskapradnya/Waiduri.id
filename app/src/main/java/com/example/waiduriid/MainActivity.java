package com.example.waiduriid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waiduriid.database.AppDatabase;
import com.example.waiduriid.database.entitas.Waiduri;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnTambah, btnAbout;
    private RecyclerView rvIndex;
    private AppDatabase database;
    private com.example.waiduriid.WaiduriAdapter WaiduriAdapter;
    private List<Waiduri> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvIndex = findViewById(R.id.rv_waiduri);

        btnTambah = (Button) findViewById(R.id.btn_Tambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getApplicationContext(), ActivityAdd.class);
                startActivity(intent);
            }
        });

        btnAbout = (Button) findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, about.class);
                startActivity(intent);
            }
        });

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.waiduriDao().getAll());
        WaiduriAdapter = new com.example.waiduriid.WaiduriAdapter(getApplicationContext(),list);
        WaiduriAdapter.setDialog(new com.example.waiduriid.WaiduriAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit Produk", "Hapus Produk"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, ActivityAdd.class);
                                intent.putExtra("id_waiduri", list.get(position).id_waiduri);
                                startActivity(intent);
                                break;
                            case 1:
                                Waiduri Waiduri = list.get(position);
                                database.waiduriDao().delete(Waiduri);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rvIndex.setLayoutManager(layoutManager);
        rvIndex.setAdapter(WaiduriAdapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.waiduriid.MainActivity.this,ActivityAdd.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.waiduriDao().getAll());
        WaiduriAdapter.notifyDataSetChanged();
    }
}