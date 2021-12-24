package com.example.waiduriid.database.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Waiduri {
    @PrimaryKey(autoGenerate = true)
    public int id_waiduri;

    public String Nama;
    public String Manfaat;
    public String Harga;
    public String Bahan;
    public String Size;
    public String Stock;

    public int getId_waiduri() {
        return id_waiduri;
    }

    public void setId_waiduri(int id_waiduri) {
        this.id_waiduri = id_waiduri;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        this.Nama = nama;
    }

    public String getbahan() {
        return Bahan;
    }

    public void setbahan(String Bahan) {
        this.Bahan = Bahan;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        this.Harga = harga;
    }

    public String getManfaat() {
        return Manfaat;
    }

    public void setManfaat(String manfaat) {
        this.Manfaat = manfaat;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) { this.Size = size; }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        this.Stock = stock;
    }
}
