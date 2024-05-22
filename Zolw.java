import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(Swiat swiat) {
        super(swiat);
        setSila(2);
        setInicjatywa(1);
    }
    public Zolw(Swiat swiat, Polozenie pos) {
        super(swiat);
        setSila(2);
        setInicjatywa(1);
        setPolozenie(pos);
    }
    public Zolw(Zolw prev) {
        super(prev);
    }

    @Override
    public void akcja(Polozenie pos) {
        Random random = new Random();
        int ruch = random.nextInt(4);
        if (ruch == 0) {
            super.akcja(pos);
        }
    }
    @Override
    public void odbicie(Organizm org) {}
    @Override
    public void koniecGry() {}
    @Override
    public void kolizja(Organizm org) {
        if (org.getSila() < 5 && !getClass().equals(org.getClass())) {
            if (org instanceof Zwierze) {
                Zwierze zw = (Zwierze) org;
                zw.setRuch(false);
            }
        } else {
            super.kolizja(org);
        }
    }

    @Override
    public Organizm narodziny() {
        return new Zolw(this);
    }

    @Override
    public boolean czyOdbilAtak() {
        return false;
    }

    @Override
    public char rysowanie() {
        return 'z';
    }

    @Override
    public Color getKolor() {
        return Color.green.darker().darker().darker();
    }

    @Override
    public String wypisanie() {
        return "zolw";
    }
}
