import java.awt.*;

public class Lis extends Zwierze {
    public Lis(Swiat swiat) {
        super(swiat);
        setSila(3);
        setInicjatywa(7);
    }
    public Lis(Swiat swiat, Polozenie pos) {
        super(swiat);
        setSila(3);
        setInicjatywa(7);
        setPolozenie(pos);
    }

    public Lis(Lis prev) {
        super(prev);
    }

    @Override
    public void akcja(Polozenie next) {
        dodajWiek();

        if (!getRuch()) {
            setRuch(true);
            return;
        }

        Polozenie ruch = new Polozenie(getPolozenie());
        ruch.losowyKrok(-1);
        Organizm pos = getSwiat().getPosPlansza(ruch.getX(), ruch.getY());

        if (pos != null && pos != this) {
            if (pos.getSila() > getSila()) {
                getSwiat().dodajRaport(wypisanie() + " wyczul " + pos.wypisanie());
                return;
            } else {
                pos.kolizja(this);
            }
        }
        if (getRuch()) {
            getSwiat().przesunOrganizm(this, ruch);
        } else {
            setRuch(true);
        }
    }

    @Override
    public Organizm narodziny() {
        return new Lis(this);
    }

    @Override
    public char rysowanie() {
        return 'l';
    }
    @Override
    public void odbicie(Organizm org) {}
    @Override
    public void koniecGry() {}
    @Override
    public Color getKolor() {
        return Color.orange;
    }

    @Override
    public String wypisanie() {
        return "lis";
    }
}
