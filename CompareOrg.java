import java.util.Comparator;
class CompareOrg implements Comparator<Organizm> {
    @Override
    public int compare(Organizm pierwszy, Organizm drugi) {
        if (pierwszy.getInicjatywa() == drugi.getInicjatywa())
            return Integer.compare(pierwszy.getWiek(), drugi.getWiek());
        return Integer.compare(pierwszy.getInicjatywa(), drugi.getInicjatywa());
    }
}
