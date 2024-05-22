import java.awt.*;

public class Mlecz extends Roslina {
    public Mlecz(Swiat swiat) {
        super(swiat);
    }
    public Mlecz(Swiat swiat, Polozenie pos) {
        super(swiat);
        setPolozenie(pos);
    }
    public Mlecz(Mlecz prev) {
        super(prev);
    }

    @Override
    public Organizm narodziny() {
        return new Mlecz(this);
    }

    @Override
    public void akcja(Polozenie next) {
        dodajWiek();
        int counter = 0;
        while (counter < 3) {
            super.akcja(next);
            counter++;
            zmniejszWiek();
        }
    }
    @Override
    public void koniecGry() {}
    @Override
    public char rysowanie() {
        return 'm';
    }

    @Override
    public Color getKolor() {
        return Color.yellow;
    }

    @Override
    public String wypisanie() {
        return "mlecz";
    }
}
