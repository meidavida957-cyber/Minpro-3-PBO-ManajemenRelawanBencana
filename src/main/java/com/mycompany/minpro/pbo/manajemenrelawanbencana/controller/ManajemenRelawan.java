package com.mycompany.minpro.pbo.manajemenrelawanbencana.controller;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Bencana;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Relawan;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Penempatan;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.RelawanUmum;
import java.util.ArrayList;

public class ManajemenRelawan {

    private ArrayList<Relawan> daftarRelawan;
    private ArrayList<Bencana> daftarBencana;
    private ArrayList<Penempatan> daftarPenempatan;

    public ManajemenRelawan() {

        daftarRelawan = new ArrayList<>();
        daftarBencana = new ArrayList<>();
        daftarPenempatan = new ArrayList<>();

        // Dummy data awal relawan
        daftarRelawan.add(new RelawanUmum(
        "R001",
        "Andi",
        "Samarinda",
        "081234567890",
        "Evakuasi"
        ));

        // Dummy data awal bencana
        daftarBencana.add(new Bencana(
                "B001",
                "Banjir Samarinda",
                "Samarinda",
                "Banjir",
                "Aktif"
        ));
    }

    // =========================
    // RELAWAN
    // =========================

    public void tambahRelawan(Relawan relawan) {

        if (cariRelawan(relawan.getId()) != null) {
            System.out.println("ID relawan sudah digunakan.");
            return;
        }

        daftarRelawan.add(relawan);
        System.out.println("Relawan berhasil ditambahkan.");
    }

    public void tampilkanRelawan() {

        if (daftarRelawan.isEmpty()) {
            System.out.println("Belum ada data relawan.");
            return;
        }

        System.out.println("\n===== DATA RELAWAN =====");

        for (Relawan relawan : daftarRelawan) {
            System.out.println("-------------------------");
            relawan.tampilkanInfo();
        }
    }

    public Relawan cariRelawan(String id) {

        for (Relawan relawan : daftarRelawan) {

            if (relawan.getId().equalsIgnoreCase(id)) {
                return relawan;
            }
        }

        return null;
    }

    public void hapusRelawan(String id) {

        Relawan relawan = cariRelawan(id);

        if (relawan == null) {
            System.out.println("Relawan tidak ditemukan.");
            return;
        }

        daftarRelawan.remove(relawan);
        System.out.println("Relawan berhasil dihapus.");
    }

    // =========================
    // BENCANA
    // =========================

    public void tambahBencana(Bencana bencana) {

        if (cariBencana(bencana.getIdBencana()) != null) {
            System.out.println("ID bencana sudah digunakan.");
            return;
        }

        daftarBencana.add(bencana);
        System.out.println("Bencana berhasil ditambahkan.");
    }

    public void tampilkanBencana() {

        if (daftarBencana.isEmpty()) {
            System.out.println("Belum ada data bencana.");
            return;
        }

        System.out.println("\n===== DATA BENCANA =====");

        for (Bencana bencana : daftarBencana) {
            System.out.println("-------------------------");
            bencana.tampilkanInfo();
        }
    }

    public Bencana cariBencana(String id) {

        for (Bencana bencana : daftarBencana) {

            if (bencana.getIdBencana().equalsIgnoreCase(id)) {
                return bencana;
            }
        }

        return null;
    }

    public void hapusBencana(String id) {

        Bencana bencana = cariBencana(id);

        if (bencana == null) {
            System.out.println("Bencana tidak ditemukan.");
            return;
        }

        daftarBencana.remove(bencana);
        System.out.println("Bencana berhasil dihapus.");
    }
    
    public void tambahPenempatan(String idRelawan, String idBencana) {

        Relawan relawan = cariRelawan(idRelawan);
        Bencana bencana = cariBencana(idBencana);

        if (relawan == null) {
            System.out.println("Relawan tidak ditemukan.");
            return;
        }

        if (bencana == null) {
            System.out.println("Bencana tidak ditemukan.");
            return;
        }
        
        for (Penempatan p : daftarPenempatan) {
            if (p.getRelawan().getId().equalsIgnoreCase(idRelawan)
                    && p.getBencana().getIdBencana().equalsIgnoreCase(idBencana)) {
                System.out.println("Relawan tersebut sudah ditugaskan di bencana yang sama.");
                return;
            }
        }

        Penempatan penempatan = new Penempatan(relawan, bencana);
        daftarPenempatan.add(penempatan);

        System.out.println("Relawan berhasil ditempatkan.");
    }
    
    public void tampilkanPenempatan() {

        if (daftarPenempatan.isEmpty()) {
            System.out.println("Belum ada penempatan relawan.");
            return;
        }

        System.out.println("\n===== PENEMPATAN RELAWAN =====");

        for (Penempatan penempatan : daftarPenempatan) {
            System.out.println("-------------------------");
            penempatan.tampilkanInfo();
        }
    }
    
}
