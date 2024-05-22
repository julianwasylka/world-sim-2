import java.awt.*;

public class Barszcz extends Roslina {
    public Barszcz(Swiat swiat) {
        super(swiat);
        setSila(10);
    }
    public Barszcz(Swiat swiat, Polozenie pos) {
        super(swiat);
        setSila(10);
        setPolozenie(pos);
    }
    @Override
    public void koniecGry() {}
    public Barszcz(Barszcz prev) {
        super(prev);
    }

    @Override
    public Organizm narodziny() {
        return new Barszcz(this);
    }

    private void usunZwierze(int x, int y) {
        Swiat swiat = getSwiat();
        if (x < 0 || x >= swiat.getN() || y < 0 || y >= swiat.getM()) {
            return;
        }

        Organizm org = swiat.getPosPlansza(x, y);
        if (org instanceof Zwierze) {
            swiat.usunOrganizm(org);
            swiat.dodajRaport(org.wypisanie() + " umarl przez " + wypisanie());
        }
    }

    @Override
    public void akcja(Polozenie next) {
        super.akcja(next);

        int x = getPolozenie().getX();
        int y = getPolozenie().getY();

        usunZwierze(x - 1, y - 1);
        usunZwierze(x, y - 1);
        usunZwierze(x + 1, y - 1);
        usunZwierze(x - 1, y);
        usunZwierze(x + 1, y);
        usunZwierze(x - 1, y + 1);
        usunZwierze(x, y + 1);
        usunZwierze(x + 1, y + 1);
    }

    @Override
    public void kolizja(Organizm org) {
        super.kolizja(org);
        getSwiat().usunOrganizm(org);
        getSwiat().dodajRaport(org.wypisanie() + " zjadl " + wypisanie());
    }

    @Override
    public char rysowanie() {
        return 'b';
    }

    @Override
    public Color getKolor() {
        return Color.MAGENTA;
    }

    @Override
    public String wypisanie() {
        return "barszcz";
    }
}
