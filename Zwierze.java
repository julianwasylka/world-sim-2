import java.util.Random;

public abstract class Zwierze extends Organizm{

    private boolean czyRuch;
    private Random random;
    public Zwierze(Swiat swiat) {
        super(swiat, 0, 0);
        setInicjatywa(0);
        czyRuch = true;
        random = new Random();
    }
    public Zwierze(Zwierze poprzednie) {
        super(poprzednie.getSwiat(), poprzednie.getInicjatywa(), poprzednie.getSila());
        setPolozenie(poprzednie.getPolozenie());
        czyRuch = false;
        random = new Random();
    }
    public boolean getRuch() {
        return czyRuch;
    }

    public void setRuch(boolean czyRuch) {
        this.czyRuch = czyRuch;
    }
    @Override
    public void akcja(Polozenie next) {
        dodajWiek();

        if (!czyRuch) {
            czyRuch = true;
            return;
        }

        Polozenie ruch = new Polozenie(getPolozenie());
        ruch.losowyKrok(1);
        Organizm pos = getSwiat().getPosPlansza(ruch.getX(), ruch.getY());

        if (pos != null && pos != this) {
            pos.kolizja(this);
        }
        if (czyRuch) {
            getSwiat().przesunOrganizm(this, ruch);
        } else {
            czyRuch = true;
        }
    }
    public abstract void odbicie(Organizm organizm);
    public void rozmnazanie(Zwierze other) {
        czyRuch = false;
        other.czyRuch = false;

        int counter = 0;
        while (counter < 8) {
            Polozenie parent = new Polozenie(getPolozenie());
            parent.losowyKrok(1);
            Organizm pos = getSwiat().getPosPlansza(parent.getX(), parent.getY());

            if (pos == null) {
                Organizm nowe = narodziny();
                if (nowe == null) {
                    return;
                }
                nowe.setPolozenie(parent);
                getSwiat().dodajOrganizm(nowe);
                getSwiat().dodajRaport("narodzil sie " + wypisanie());
                return;
            } else {
                counter++;
            }
        }
    }
    @Override
    public void kolizja(Organizm organizm) {
        if (organizm == null) {
            return;
        }
        if (organizm instanceof Zwierze) {
            Zwierze org = (Zwierze) organizm;
            if (getClass().equals(org.getClass())) {
                rozmnazanie(org);
                return;
            } else if (org.czyOdbilAtak()) {
                org.odbicie(this);
                getSwiat().dodajRaport(org.wypisanie() + " uniknal ataku " + wypisanie());
                return;
            } else if (czyOdbilAtak()) {
                odbicie(org);
                getSwiat().dodajRaport(wypisanie() + " uniknal ataku " + org.wypisanie());
                return;
            }
        }

        if (getSila() < organizm.getSila()) {
            getSwiat().dodajRaport(organizm.wypisanie() + " pokonal " + wypisanie());
            getSwiat().usunOrganizm(this);
        } else {
            getSwiat().dodajRaport(wypisanie() + " pokonal " + organizm.wypisanie());
            getSwiat().usunOrganizm(organizm);
            czyRuch = false;
        }
    }
    @Override
    public boolean czyOdbilAtak() {
        return false;
    }
}
