import java.util.*;
import java.io.*;

public class Zad7 {
    public static void main(String[] args) {
        // 4 testowe wpisy dla klasy Osoba
        Osoba os1 = new Osoba(6, 790715089);
        os1.dodaj("Kacper", "Piasta", "Kurnos Drugi 174, 97-400 Belchatow");
        Osoba os2 = new Osoba(12, 650692567);
        os2.dodaj("Jolanta", "Matysiak", "Brzozowa 2, 34-700 Lubaczow");
        Osoba os3 = new Osoba(850127407);
        os3.dodaj("Aleksy", "Ziemianski", "Kolejowa 56, 01-345 Milanow");
        Osoba os4 = new Osoba(2, 901012053);
        os4.dodaj("Miroslaw", "Kowalski", "Plac Niepodleglosci 2, 90-002 Wola Wiazowa");

        // 4 testowe wpisy dla klasy Firma
        Firma co1 = new Firma(19, 102346021);
        co1.dodaj("Trevor Phillips Industries", "Sandy Shores 23, 01567 Blaine County");
        Firma co2 = new Firma(7, 490173496);
        co2.dodaj("Clinton Incorporated", "Whispymound Drive 3671, 045713 Vinewood Hills");
        Firma co3 = new Firma(349001245);
        co3.dodaj("De Santa Co", "Long Street 71, 06716 Rockford Hills");
        Firma co4 = new Firma(13, 289056923);
        co4.dodaj("Jakowski Ronland", "Dirty Street 54, 011845 Blaine County");

        // utworzenie kontenera TreeMap dla książki telefonicznej
        TreeMap<NrTelefoniczny, String> phoneBook = new TreeMap<>(NrTelefoniczny::compareTo);
        phoneBook.put(os1.id, os1.opis());
        phoneBook.put(os2.id, os2.opis());
        phoneBook.put(os3.id, os3.opis());
        phoneBook.put(os4.id, os4.opis());
        phoneBook.put(co1.id, co1.opis());
        phoneBook.put(co2.id, co2.opis());
        phoneBook.put(co3.id, co3.opis());
        phoneBook.put(co4.id, co4.opis());

        int i = 0;
        for (Map.Entry<NrTelefoniczny, String> entry : phoneBook.entrySet()) {
            i++;
            System.out.println(Integer.toString(i) + ". " + entry.getValue());
        }
    }
}

class NrTelefoniczny implements Comparable<NrTelefoniczny> {

    public final int nrKierunkowy, nrTelefonu;

    public static final int NULL_ID = -1;

    NrTelefoniczny(int kier, int tel) {
        nrKierunkowy = kier;
        nrTelefonu = tel;
    }

    NrTelefoniczny(int tel) {
        nrKierunkowy = NULL_ID;
        nrTelefonu = tel;
    }

    @Override
    public int compareTo(NrTelefoniczny id) {
        if(nrTelefonu != id.nrTelefonu)
            return nrTelefonu - id.nrTelefonu;
        return nrKierunkowy - id.nrKierunkowy;
    }

    @Override
    public boolean equals(Object r) {
        if (!(r instanceof NrTelefoniczny)) {
            return false;
        }
        return compareTo((NrTelefoniczny)r) == 0;
    }
}

abstract class Wpis {

    public final NrTelefoniczny id;

    public Wpis(NrTelefoniczny id) {
        this.id = id;
    }

    public abstract String opis();
}

class Osoba extends Wpis {
    private static int nrTelefonu = 0;

    private String imie = null;
    private String nazwisko = null;
    private String adres = null;

    public Osoba(int kier, int tel) {
        super(new NrTelefoniczny(kier, tel));
        nrTelefonu = tel;
    }

    public Osoba(int tel) {
        super(new NrTelefoniczny(tel));
        nrTelefonu = tel;
    }

    void dodaj(String imie, String nazwisko, String adres) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = adres;
    }

    @Override
    public String opis() {
        return "Osoba o nr tel.: " + id.nrTelefonu + ", nr kierunkowym: " + ((id.nrKierunkowy != -1) ? id.nrKierunkowy : "(nieznany)") + ", o nastepujacych danych: " + this.imie + " " + this.nazwisko + ", " + this.adres;
    }
}

class Firma extends Wpis {
    private static int nrTelefonu = 0;

    private String nazwa = null;
    private String adres = null;

    public Firma(int kier, int tel) {
        super(new NrTelefoniczny(kier, tel));
        nrTelefonu = tel;
    }

    public Firma(int tel) {
        super(new NrTelefoniczny(tel));
        nrTelefonu = tel;
    }

    void dodaj(String nazwa, String adres) {
        this.nazwa = nazwa;
        this.adres = adres;
    }

    @Override
    public String opis() {
        return "Firma o nr tel.: " + id.nrTelefonu + ", nr kierunkowym: " + ((id.nrKierunkowy != -1) ? id.nrKierunkowy : "(nieznany)") + ", o nastepujacych danych: " + this.nazwa + ", " + this.adres;
    }
}
