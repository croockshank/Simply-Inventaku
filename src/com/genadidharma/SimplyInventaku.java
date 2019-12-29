package com.genadidharma;

import org.apache.commons.lang3.ArrayUtils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SimplyInventaku {
    public static void main(String[] args) {
        String[][] barangHabisPakai = {
                {"Kertas HVS", "100"},
                {"Isi Stepless", "1500"},
                {"Klip Kertas", "2500"},
                {"Pensil 2B", "2000"},
                {"Buku Tulis", "3000"}
        };
        String[][] barangInventaris = {
                {"Obeng Min", "10"},
                {"Obeng Plus", "20"},
                {"Kabel Roll 3m", "8"},
                {"Kunci Inggris", "3"},
                {"Tang", "7"}
        };

        String[][] barangBarangHabisPakaiTerpilih = new String[100][3];
        String[][] barangBarangInventarisTerpilih = new String[100][2];

        int jumlahTransaksi = 0, jenisBarang, noBarang, jumlahBarangTersedia, jumlahBarangInput, hargaBarang, lamaPeminjaman;
        float diskonBarang, totalBayar = 0, uang, kembalian;
        String barangTerpilih, besarDiskon;
        boolean isLanjutTransaksi;

        Locale localeId = new Locale("id", "ID");
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", localeId);

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

            do {

                noBarang = getIntInput("Pilih barang yang akan dibeli(1-" + barangHabisPakai.length + "): ", input, barangHabisPakai.length);

                barangTerpilih = barangHabisPakai[noBarang - 1][0];
                hargaBarang = Integer.parseInt(barangHabisPakai[noBarang - 1][1]);

                jumlahBarangInput = getIntInput("Masukkan jumlah yang akan dibeli: ", input, 0);

                jumlahTransaksi += 1;

                //Memasukkan data ke array barang-barang sekali pakai terpilih
                for (int i = 0; i <= jumlahTransaksi; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if (i == jumlahTransaksi - 1 && j == 0) {
                            barangBarangHabisPakaiTerpilih[i][j] = barangTerpilih;
                        } else if (i == jumlahTransaksi - 1 && j == 1) {
                            barangBarangHabisPakaiTerpilih[i][j] = String.valueOf(jumlahBarangInput);
                        } else if (i == jumlahTransaksi - 1) {
                            barangBarangHabisPakaiTerpilih[i][j] = String.valueOf(hargaBarang);
                        }
                    }
                }

                totalBayar += jumlahBarangInput * hargaBarang;

                isLanjutTransaksi = getCharInput("Ingin melanjutkan pembelian (y/t): ", input);

            } while (isLanjutTransaksi);

            // Menghitung Diskon
            if (totalBayar >= 50000) {
                besarDiskon = "30%";
                diskonBarang = 0.3F;
            } else if (totalBayar >= 25000) {
                besarDiskon = "20%";
                diskonBarang = 0.2F;
            } else if (totalBayar >= 20000) {
                besarDiskon = "10%";
                diskonBarang = 0.1F;
            } else {
                besarDiskon = "0";
                diskonBarang = 0F;
            }

            // Menampilkan Struk Pembelian
            System.out.println("Info Pembelian");
            System.out.println(dash + dash);
            System.out.println("Nama Barang" + "\t\t\t" + "Jumlah" + "\t\t" + "Harga Satuan (Rp)");
            printStrukBarang(barangBarangHabisPakaiTerpilih, jumlahTransaksi, 3);
            System.out.println(dash + dash);
            System.out.println("Sub Total" + "\t\t\t" + convertRupiah(totalBayar));
            System.out.println("Diskon" + "\t\t\t\t" + besarDiskon);
            totalBayar = totalBayar - (totalBayar * diskonBarang);
            System.out.println("Total" + "\t\t\t\t" + convertRupiah(totalBayar));

            //Meminta uang
            do {
                System.out.print("Masukkan uang: ");
                uang = input.nextFloat();
                if(uang < totalBayar){
                    System.out.println("Uang yang anda masukkan kurang");
                }
            } while (uang < totalBayar);

            //Menampilkan struk pembelain
            System.out.println("Struk Pembelian");
            System.out.println(dash + dash);
            System.out.println("Nama Barang" + "\t\t\t" + "Jumlah" + "\t\t" + "Harga Satuan (Rp)");
            printStrukBarang(barangBarangHabisPakaiTerpilih, jumlahTransaksi, 3);
            System.out.println(dash + dash);
            System.out.println("Sub Total" + "\t\t\t" + convertRupiah(totalBayar));
            System.out.println("Diskon" + "\t\t\t\t" + besarDiskon);
            System.out.println(dash + dash);
            System.out.println("Uang" + "\t\t\t\t" + convertRupiah(uang));
            kembalian = uang - totalBayar;
            System.out.println("Kembalian" + "\t\t\t" + convertRupiah(kembalian));

        } else {
            do {
                System.out.println("Daftar Barang:");
                System.out.println(dash);
                System.out.println("Nama \t\t\t\t Jumlah");
                printBarang(barangInventaris);
                System.out.println(dash);

                noBarang = getIntInput("Pilih barang yang akan dipinjam(1-" + barangInventaris.length + "): ", input, barangInventaris.length);

                barangTerpilih = barangInventaris[noBarang - 1][0];
                jumlahBarangTersedia = Integer.parseInt(barangInventaris[noBarang - 1][1]);

                jumlahBarangInput = getIntInput("Masukkan jumlah yang akan dipinjam " + "(makismal " + jumlahBarangTersedia + "): ", input, jumlahBarangTersedia);

                jumlahTransaksi += 1;

                //Memasukkan data ke array barang-barang inventaris
                for (int i = 0; i <= jumlahTransaksi; i++) {
                    for (int j = 0; j <= 1; j++) {
                        if (i == jumlahTransaksi - 1 && j == 0) {
                            barangBarangInventarisTerpilih[i][j] = barangTerpilih;
                        } else if (i == jumlahTransaksi - 1 && j == 1) {
                            barangBarangInventarisTerpilih[i][j] = String.valueOf(jumlahBarangInput);
                        }
                    }
                }

                //Mengurangi jumlah barang tersedia
                barangInventaris[noBarang - 1][1] = String.valueOf(Integer.parseInt(barangInventaris[noBarang - 1][1]) - jumlahBarangInput);

                //Menghapus daftar barang yang telah dipinjam
                barangInventaris = ArrayUtils.remove(barangInventaris, noBarang - 1);

                //Menanyakan apakah akan melanjutkan peminjaman
                if (jumlahTransaksi < 2 && barangInventaris.length > 0) {
                    isLanjutTransaksi = getCharInput("Ingin melanjutkan peminjaman(y/t): ", input);
                } else {
                    isLanjutTransaksi = false;
                }

            } while (isLanjutTransaksi);

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
            System.out.println("Nama Barang" + "\t\t\t" + "Jumlah");
            printStrukBarang(barangBarangInventarisTerpilih, jumlahTransaksi, 2);
            System.out.println(dash + dash);
            System.out.println("Tanggal Pinjam" + "\t\t" + dateFormat.format(tanggalSekarang));
            System.out.println("Lama Peminjaman" + "\t\t" + lamaPeminjaman + " hari");
            System.out.println("Tanggal Kembali" + "\t\t" + dateFormat.format(tanggalKembali));
        }
    }

    // Fungsi untuk menampilkan barang tersedia
    private static void printBarang(String[][] barang) {
        for (int i = 0; i < barang.length; i++) {
            for (int j = 0; j < barang[i].length; j++) {
                if (j % 2 == 1) {
                    System.out.println(barang[i][j]);
                } else {
                    System.out.print(i + 1 + ". " + barang[i][j] + "\t\t");
                }
            }
        }
    }

    //Fungsi untuk menampilkan struk barang
    private static void printStrukBarang(String[][] barang, int jumlahBarang, int jumlahKolom) {
        for (int i = 0; i < jumlahBarang; i++) {
            for (int j = 0; j < jumlahKolom; j++) {
                System.out.print(barang[i][j] + "\t\t\t");
                if (j == jumlahKolom - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    //Fungsi untuk mengambil input berupa angka
    private static int getIntInput(String perintah, Scanner input, int maksimal) {
        int hasil;
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

            if (hasil <= 0 || hasilMaksimal) {
                System.out.println("Masukkan angka yang sesuai");
            }

        } while (hasil <= 0 || hasilMaksimal);
        return hasil;
    }

    //Fungsi untuk mengambil input berupa karakter
    private static boolean getCharInput(String perintah, Scanner input) {
        char hasil;
        do {
            System.out.print(perintah);
            hasil = Character.toLowerCase(input.next().charAt(0));
            if (hasil != 'y' && hasil != 't') {
                System.out.println("Masukan karakter yang sesuai");
            }
        } while (hasil != 'y' && hasil != 't');
        return hasil == 'y';
    }

    //Convert uang menjadi rupiah
    private static String convertRupiah(float uang) {
        Locale localeId = new Locale("id", "ID");
        NumberFormat currFormat = NumberFormat.getCurrencyInstance(localeId);
        return currFormat.format(uang);
    }

}
