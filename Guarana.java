import java.awt.*;

public class Guarana extends Roslina {
    public Guarana(Swiat swiat) {
        super(swiat);
    }
    public Guarana(Swiat swiat, Polozenie pos) {
        super(swiat);
        setPolozenie(pos);
    }

    public Guarana(Guarana prev) {
        super(prev);
    }

    @Override
    public Organizm narodziny() {
        return new Guarana(this);
    }
    @Override
    public void koniecGry() {}
    @Override
    public void kolizja(Organizm org) {
        super.kolizja(org);
        org.setSila(org.getSila() + 3);
        getSwiat().dodajRaport("sila " + org.wypisanie() + " urosla");
    }

    @Override
    public char rysowanie() {
        return 'g';
    }

    @Override
    public Color getKolor() {
        return Color.red;
    }

    @Override
    public String wypisanie() {
        return "guarana";
    }
}
