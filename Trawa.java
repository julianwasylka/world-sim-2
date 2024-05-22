import java.awt.*;

public class Trawa extends Roslina{
    public Trawa(Swiat swiat) {
        super(swiat);
    }
    public Trawa(Swiat swiat, Polozenie pos) {
        super(swiat);
        setPolozenie(pos);
    }
    public Trawa(Trawa prev) {
        super(prev);
    }
    @Override
    public Organizm narodziny() {
        return new Trawa(this);
    }
    @Override
    public void koniecGry() {}

    @Override
    public char rysowanie() {
        return 't';
    }
    @Override
    public Color getKolor() {
        return Color.GREEN;
    }
    @Override
    public String wypisanie() {
        return "trawa";
    }

}
