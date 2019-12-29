package com.genadidharma;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[][] barangHabisPakai = {
                {"Kertas HVS", "Isi Stepless", "Klip Kertas"},
                {"250", "1500", "2500"}
        };
        String[][] barangInventaris = {
                {"Obeng Min", "Obeng Plus", "Sound System"},
                {"10", "20", "5"}
        };
        int jenisBarang, noBarang = 0, jumlahBarangTersedia = 0, jumlahBarangInput = 0, hargaBarang, lamaPeminjaman;
        float diskonBarang, totalBayar;
        String barangTerpilih = "", besarDiskon = "";

        Locale localeId = new Locale("id", "ID");
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", localeId);
        NumberFormat currFormat = NumberFormat.getCurrencyInstance(localeId);

        Scanner input = new Scanner(System.in);

        String dash = "===========================";
        System.out.println(dash + " Simply Inventaku " + dash);

        System.out.println("Jenis Barang: ");
        System.out.println("1. Barang Habis Pakai");
        System.out.println("2. Barang Inventaris");

        jenisBarang = getIntInput("Pilih jenis barang: ", input, 2);

        if (jenisBarang == 1) {

            System.out.println("Daftar Barang:");
            System.out.println(dash);
            System.out.println("Nama\t\t\t\tHarga(Rp)");
            printBarang(barangHabisPakai);
            System.out.println(dash);

            noBarang = getIntInput("Pilih barang yang akan dibeli(1-" + barangHabisPakai[0].length + "): ", input, barangHabisPakai[0].length);

            barangTerpilih = barangHabisPakai[0][noBarang - 1];
            hargaBarang = Integer.parseInt(barangHabisPakai[1][noBarang - 1]);

            jumlahBarangInput = getIntInput("Masukkan jumlah yang akan dibeli: ", input, 0);

            // Menghitung Diskon
            if (jumlahBarangInput >= 100) {
                besarDiskon = "50%";
                diskonBarang = 0.5F;
            } else if (jumlahBarangInput >= 50) {
                besarDiskon = "30%";
                diskonBarang = 0.3F;
            } else if (jumlahBarangInput >= 25) {
                besarDiskon = "20%";
                diskonBarang = 0.2F;
            } else {
                besarDiskon = "0";
                diskonBarang = 0F;
            }

            totalBayar = (hargaBarang * jumlahBarangInput) - (hargaBarang * jumlahBarangInput * diskonBarang);

            // Menampilkan Struk Pembelian
            System.out.println("Struk Pembelian");
            System.out.println(dash + dash);
            System.out.println("Nama Barang" + "\t\t\t" + "Jumlah" + "\t\t\t" + "Harga Satuan");
            System.out.println(barangTerpilih + "\t\t\t" + jumlahBarangInput + "\t\t\t\t" + currFormat.format(hargaBarang));
            System.out.println(dash + dash);
            System.out.println("Diskon" + "\t\t\t\t" + besarDiskon);
            System.out.println("Total" + "\t\t\t\t" + currFormat.format(totalBayar));

        } else {
            System.out.println("Daftar Barang:");
            System.out.println(dash);
            System.out.println("Nama \t\t\t\t Jumlah");
            printBarang(barangInventaris);
            System.out.println(dash);

            noBarang = getIntInput("Pilih barang yang akan dipinjam(1-" + barangInventaris[0].length + "): ", input, barangInventaris[0].length);

            barangTerpilih = barangInventaris[0][noBarang - 1];
            jumlahBarangTersedia = Integer.parseInt(barangInventaris[1][noBarang - 1]);

            jumlahBarangInput = getIntInput("Masukkan jumlah yang akan dipinjam " + "(makismal " + jumlahBarangTersedia + "): ", input, jumlahBarangTersedia);
            lamaPeminjaman = getIntInput("Masukkan lama peminjaman (hari): ", input, 0);

            Date tanggalSekarang = new Date();

            //Menghitung kapan harus dikembalikan
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tanggalSekarang);
            calendar.add(Calendar.DATE, lamaPeminjaman);

            //Hari sabtu & minggu tutup jadi dikembalikan senin
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SATURDAY:
                    calendar.add(Calendar.DATE, 2);
                    break;
                case Calendar.SUNDAY:
                    calendar.add(Calendar.DATE, 1);
                    break;
            }

            Date tanggalKembali = calendar.getTime();

            // Menampilkan Struk Peminjaman
            System.out.println("Struk Peminjaman");
            System.out.println(dash + dash);
            System.out.println("Nama Barang" + "\t\t\t" + "Lama Peminjaman (hari)");
            System.out.println(barangTerpilih + "\t\t" + jumlahBarangInput);
            System.out.println(dash + dash);
            System.out.println("Tanggal Kembali " + "\t" + dateFormat.format(tanggalKembali));

        }
    }

    // Fungsi untuk menampilkan barang
    private static void printBarang(String[][] barang) {
        for (int i = 0; i < barang[0].length; i++) {
            for (int j = 0; j < barang.length; j++) {
                if (j % 2 == 1) {
                    System.out.println(barang[j][i]);
                } else {
                    System.out.print(i + 1 + ". " + barang[j][i] + "\t\t");
                }
            }
        }
    }

    //Fungsi untuk mengambil input berupa angka
    private static int getIntInput(String perintah, Scanner input, int maksimal) {
        int hasil = 0;
        boolean hasilMaksimal;
        do {
            System.out.print(perintah);
            while (!input.hasNextInt()) {
                String inputted = input.next();
                System.out.printf("\"%s\" bukan merupakan angka. \n", inputted);
                System.out.print(perintah);
            }
            hasil = input.nextInt();
            hasilMaksimal = (maksimal) != 0 && hasil > maksimal;
        } while (hasil <= 0 || hasilMaksimal);
        return hasil;
    }

}
