import java.util.Random;

public abstract class Roslina extends Organizm{
    private final int FREQ = 10;
    private Random random;
    public Roslina(Swiat swiat) {
        super(swiat, 0, 0);
        setInicjatywa(0);
        random = new Random();
    }
    public Roslina(Roslina poprzednia) {
        super(poprzednia.getSwiat(), poprzednia.getInicjatywa(), poprzednia.getSila());
        setPolozenie(poprzednia.getPolozenie());
        random = new Random();
    }
    @Override
    public void akcja(Polozenie next) {
        dodajWiek();

        int czyRozsiac = random.nextInt(FREQ);

        if (czyRozsiac == 0) {
            Polozenie nowePolozenie = new Polozenie(getPolozenie());
            nowePolozenie.losowyKrok(1);

            if (getSwiat().getPosPlansza(nowePolozenie.getX(), nowePolozenie.getY()) != null) {
                return;
            }

            Organizm nowa = narodziny();

            if (nowa == null) {
                return;
            }

            nowa.setPolozenie(nowePolozenie);
            getSwiat().dodajOrganizm(nowa);
        }
    }
    @Override
    public void kolizja(Organizm org) {
        if (getSila() <= org.getSila()) {
            getSwiat().usunOrganizm(this);
            getSwiat().dodajRaport(org.wypisanie() + " zjadł " + wypisanie());
        }
    }
    @Override
    public boolean czyOdbilAtak() {
        return false;
    }
}
