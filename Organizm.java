import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Organizm {
    private int sila;
    private int inicjatywa;
    private int wiek;
    private Polozenie polozenie;
    private Swiat swiat;
    public Organizm(Swiat swiat, int inicjatywa, int sila) {
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.swiat = swiat;
        polozenie = new Polozenie(swiat);
        wiek = 0;
    }
    public Organizm(Polozenie pos, Swiat swiat) {
        this.swiat = swiat;
        this.polozenie = pos;
        wiek = 0;
    }
    public int getSila()
    {
        return sila;
    }

    public void setSila(int sila)
    {
        this.sila = sila;
    }

    public Polozenie getPolozenie()
    {
        return polozenie;
    }

    public void setPolozenie(Polozenie pos)
    {
        polozenie = new Polozenie(pos);
    }

    public int getInicjatywa()
    {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa)
    {
        this.inicjatywa = inicjatywa;
    }

    public int getWiek()
    {
        return wiek;
    }

    public Swiat getSwiat()
    {
        return swiat;
    }

    public void dodajWiek() {
        wiek++;
    }

    public void zmniejszWiek() {
        wiek--;
    }

    public abstract void koniecGry();

    public abstract void akcja(Polozenie next);

    public abstract void kolizja(Organizm org);

    public abstract boolean czyOdbilAtak();

    public abstract Organizm narodziny();

    public abstract char rysowanie();

    public abstract Color getKolor();

    public abstract String wypisanie();
    public void czytaj(String line) throws IOException {
        String[] values = line.split(" ");
        this.sila = Integer.parseInt(values[1]);
        this.inicjatywa = Integer.parseInt(values[2]);
        this.wiek = Integer.parseInt(values[3]);
        this.polozenie = new Polozenie(swiat, Integer.parseInt(values[4]), Integer.parseInt(values[5]));
    }

    public void zapisz(PrintWriter os) {
        os.print(rysowanie() + " " + sila + " " + inicjatywa + " " + wiek + " ");
        polozenie.zapisz(os);
    }
}
