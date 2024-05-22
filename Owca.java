import java.awt.*;

public class Owca extends Zwierze{
    public Owca(Swiat swiat) {
        super(swiat);
        setSila(4);
        setInicjatywa(4);
    }
    public Owca(Swiat swiat, Polozenie pos) {
        super(swiat);
        setSila(4);
        setInicjatywa(4);
        setPolozenie(pos);
    }
    public Owca(Owca prev) {
        super(prev);
    }
    @Override
    public Organizm narodziny() {
        return new Owca(this);
    }
    @Override
    public void koniecGry() {}

    @Override
    public char rysowanie() {
        return 'o';
    }
    @Override
    public Color getKolor() {
        return Color.lightGray;
    }
    @Override
    public String wypisanie() {
        return "owca";
    }
    @Override
    public void odbicie(Organizm org) {}
}
