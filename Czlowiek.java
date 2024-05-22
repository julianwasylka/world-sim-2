import java.awt.*;

public class Czlowiek extends Zwierze {
    private Kontroler kontroler;

    public Czlowiek(Swiat swiat, Kontroler kontroler) {
        super(swiat);
        setPolozenie(new Polozenie(swiat, swiat.getN() / 2, swiat.getM() / 2));
        setSila(5);
        setInicjatywa(4);
        setRuch(true);
        this.kontroler = kontroler;
    }

    @Override
    public void akcja(Polozenie next) {
        dodajWiek();

        Polozenie old = new Polozenie(getPolozenie());
        old = next;
        Organizm pos = getSwiat().getPosPlansza(old.getX(), old.getY());

        if (pos != null)
            pos.kolizja(this);

        if (!getRuch()) {
            setRuch(true);
            return;
        }

        getSwiat().przesunOrganizm(this, old);
    }

    @Override
    public void odbicie(Organizm organizm) {
        Polozenie nowe = new Polozenie(getPolozenie());

        if (nowe.losowyKrok(1)) {
            Organizm prev = getSwiat().getPosPlansza(nowe.getX(), nowe.getY());
            if (prev == null) {
                getSwiat().przesunOrganizm(organizm, nowe);
                Zwierze zw = (Zwierze) organizm;
                zw.setRuch(false);
                return;
            }
        }

        nowe = new Polozenie(getPolozenie());
        for (int i = 0; i <= 8; i++) {
            if (i == 8) {
                getSwiat().usunOrganizm(organizm);
                return;
            }
            if (nowe.losowyKrok(i)) {
                Organizm prev = getSwiat().getPosPlansza(nowe.getX(), nowe.getY());
                if (prev != null) {
                    nowe = new Polozenie(getPolozenie());
                    continue;
                }
                getSwiat().przesunOrganizm(organizm, nowe);
                Zwierze zw = (Zwierze) organizm;
                zw.setRuch(false);
                return;
            }
            nowe = new Polozenie(getPolozenie());
        }
    }

    @Override
    public char rysowanie() {
        return '#';
    }

    @Override
    public Color getKolor() {
        return Color.pink;
    }

    @Override
    public String wypisanie() {
        return "czlowiek";
    }

    @Override
    public boolean czyOdbilAtak() {
        if (kontroler.getCooldown() > 5) {
            return true;
        }
        return false;
    }

    @Override
    public Organizm narodziny() {
        return null;
    }

    @Override
    public void koniecGry() {
        getSwiat().setKoniec(true);
    }
}
