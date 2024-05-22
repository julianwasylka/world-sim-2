import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat) {
        super(swiat);
        setSila(9);
        setInicjatywa(5);
    }
    public Wilk(Swiat swiat, Polozenie pos) {
        super(swiat);
        setSila(9);
        setInicjatywa(5);
        setPolozenie(pos);
    }


    public Wilk(Wilk prev) {
        super(prev);
    }

    @Override
    public Organizm narodziny() {
        return new Wilk(this);
    }

    @Override
    public char rysowanie() {
        return 'w';
    }

    @Override
    public Color getKolor() {
        return Color.DARK_GRAY;
    }
    @Override
    public void odbicie(Organizm org) {}
    @Override
    public void koniecGry() {}
    @Override
    public String wypisanie() {
        return "wilk";
    }
}
