import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class Antylopa extends Zwierze {
    private boolean czyUciekla;

    public Antylopa(Swiat swiat) {
        super(swiat);
        setSila(4);
        setInicjatywa(4);
        czyUciekla = false;
    }
    public Antylopa(Swiat swiat, Polozenie pos) {
        super(swiat);
        setSila(4);
        setInicjatywa(4);
        setPolozenie(pos);
    }

    public Antylopa(Antylopa prev) {
        super(prev);
        czyUciekla = prev.czyUciekla;
    }
    @Override
    public void koniecGry() {}
    @Override
    public void akcja(Polozenie next) {
        dodajWiek();

        if (!getRuch()) {
            setRuch(true);
            return;
        }

        Polozenie ruch = new Polozenie(getPolozenie());
        ruch.losowyKrok(2);
        Organizm pos = getSwiat().getPosPlansza(ruch.getX(), ruch.getY());

        if (pos != null && pos != this) {
            pos.kolizja(this);
        }
        if (getRuch()) {
            getSwiat().przesunOrganizm(this, ruch);
        } else {
            setRuch(true);
        }
    }

    @Override
    public void kolizja(Organizm org) {
        Random random = new Random();
        int ucieczka = random.nextInt(2);

        if (ucieczka == 0) {
            czyUciekla = true;
        } else {
            czyUciekla = false;
        }

        super.kolizja(org);
    }

    @Override
    public void odbicie(Organizm organizm) {
        Polozenie nowe = new Polozenie(getPolozenie());

        if (nowe.losowyKrok(2)) {
            Organizm prev = getSwiat().getPosPlansza(nowe.getX(), nowe.getY());
            if (prev == null) {
                getSwiat().przesunOrganizm(this, nowe);
                setRuch(false);
                return;
            }
        }
        nowe = new Polozenie(getPolozenie());

        for (int i = 0; i <= 8; i++) {
            if (i == 8) {
                getSwiat().usunOrganizm(this);
                return;
            }
            if (nowe.losowyKrok(i)) {
                Organizm prev = getSwiat().getPosPlansza(nowe.getX(), nowe.getY());
                if (prev != null) {
                    nowe = new Polozenie(getPolozenie());
                    continue;
                }
                getSwiat().przesunOrganizm(this, nowe);
                setRuch(false);
                return;
            }
            nowe = new Polozenie(getPolozenie());
        }
    }

    @Override
    public Organizm narodziny() {
        return new Antylopa(this);
    }

    @Override
    public boolean czyOdbilAtak() {
        return czyUciekla;
    }

    @Override
    public char rysowanie() {
        return 'a';
    }

    @Override
    public Color getKolor() {
        return new Color(80, 50, 32);
    }

    @Override
    public String wypisanie() {
        return "antylopa";
    }
}
