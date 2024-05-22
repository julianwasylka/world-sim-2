import java.awt.*;

public class Jagody extends Roslina {
    public Jagody(Swiat swiat) {
        super(swiat);
        setSila(99);
    }
    public Jagody(Swiat swiat, Polozenie pos) {
        super(swiat);
        setSila(99);
        setPolozenie(pos);
    }
    public Jagody(Jagody prev) {
        super(prev);
    }

    @Override
    public Organizm narodziny() {
        return new Jagody(this);
    }

    @Override
    public void kolizja(Organizm org) {
        super.kolizja(org);
        getSwiat().usunOrganizm(org);
        getSwiat().dodajRaport(org.wypisanie() + " umarl przez " + wypisanie());
    }
    @Override
    public void koniecGry() {}
    @Override
    public char rysowanie() {
        return 'j';
    }

    @Override
    public Color getKolor() {
        return new Color(58, 9, 86);
    }

    @Override
    public String wypisanie() {
        return "jagody";
    }
}
