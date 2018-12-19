package myProjects.Redhat.JUnitTest;

import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class Matches {

    private Scanner sc;
    private int pocetSirek = 10;

    public Matches(int pocetSirek, String hra){
        sc = new Scanner(System.in);
        System.out.println("Napis pocet sirek: ");
        this.pocetSirek = pocetSirek;
        System.out.println("Hra pro 2 hrace (a/n): ");

        if (hra.equals("a")) {
            hra();
        } else {
            AIHra();
        }
    }

    private void hra() {
        String hrac = "A";
        while (pocetSirek > 1) {
            System.out.println("Hraje hrac A");
            ukazSirky();
            pocetSirek -= zkontrolujInput();
            if (pocetSirek > 1) {
                System.out.println("Hraje hrac B");
                ukazSirky();
                pocetSirek -= zkontrolujInput();
                hrac = "A";
            } else {
                hrac = "B";
            }
        }
        System.out.println("Prohral hrac " + hrac);
        ukazSirky();
    }

    private void AIHra() {
        String hrac = "A";
        while (pocetSirek > 1) {
            System.out.println("Hraje hrac A");
            ukazSirky();
            pocetSirek -= zkontrolujInput();
            if (pocetSirek > 1) {
                System.out.println("Hraje hrac AI");
                ukazSirky();
                pocetSirek -= AI();
                hrac = "A";
            } else {
                hrac = "B";
            }
        }
        System.out.println("Prohral hrac " + hrac);
        ukazSirky();
    }

    private int AI() {
        if (pocetSirek - 3 > 5) {
            return 3;
        } else if (pocetSirek - 2 > 4) {
            return 2;
        }
        return 1;
    }

    private void ukazSirky() {
        for (int i = 0; i < pocetSirek; i++) {
            System.out.print("I");
        }
        System.out.print("\n");
    }

    private int zkontrolujInput() {
        int vystup = sc.nextInt();
        if (vystup <= 3) {
            return vystup;
        }
        System.out.println("Zadal jsi moc ztracis kolo");
        return 0;
    }
}

