package com.genadidharma;

import java.util.Scanner;

public class Perjalanan {
    public static void main(String[] args) {
        int jamBerangkat, menitBerangkat, detikBerangkat, jamTiba, menitTiba, detikTiba, jamHasil, menitHasil, detikHasil;

        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan Jam berangkat: ");
        jamBerangkat = input.nextInt();
        System.out.print("Masukkan Menit berangkat: ");
        menitBerangkat = input.nextInt();
        System.out.print("Masukkan Detik berangkat: ");
        detikBerangkat = input.nextInt();

        System.out.print("Masukkan Jam tiba: ");
        jamTiba = input.nextInt();
        System.out.print("Masukkan Menit tiba: ");
        menitTiba = input.nextInt();
        System.out.print("Masukkan Detik tiba: ");
        detikTiba = input.nextInt();

        if(jamBerangkat > 0 && menitBerangkat > 0 && detikBerangkat > 0 && jamTiba > 0 && menitTiba > 0 && detikTiba > 0 && menitBerangkat <= 60 && detikBerangkat <= 60 && menitTiba <= 60 && detikTiba <= 60){
            if(jamBerangkat < jamTiba){
                jamHasil = jamTiba - jamBerangkat;
            }else {
                System.out.println("Masukkan input yang benar");
            }

            if(menitBerangkat < menitTiba){
                menitHasil = menitTiba;
            }

        }else {
            System.out.println("Masukkan input yang benar");
        }

    }
}
