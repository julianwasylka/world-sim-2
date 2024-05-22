public class Main {
    public static void main(String[] args) {

        Swiat swiat = new Swiat();

        Kontroler kontroler = new Kontroler(swiat);
        Czlowiek czlowiek = new Czlowiek(swiat, kontroler);
        swiat.dodajOrganizm(czlowiek);
        swiat.dodajOrganizm(new Trawa(swiat));
        swiat.dodajOrganizm(new Trawa(swiat));
        swiat.dodajOrganizm(new Mlecz(swiat));
        swiat.dodajOrganizm(new Wilk(swiat));
        swiat.dodajOrganizm(new Owca(swiat));
        swiat.dodajOrganizm(new Owca(swiat));
        swiat.dodajOrganizm(new Lis(swiat));
        swiat.dodajOrganizm(new Lis(swiat));
        swiat.dodajOrganizm(new Wilk(swiat));
        swiat.dodajOrganizm(new Zolw(swiat));
        swiat.dodajOrganizm(new Antylopa(swiat));
        swiat.dodajOrganizm(new Barszcz(swiat));
        swiat.dodajOrganizm(new Barszcz(swiat));
        swiat.dodajOrganizm(new Guarana(swiat));
        swiat.dodajOrganizm(new Guarana(swiat));
        swiat.dodajOrganizm(new Jagody(swiat));
        swiat.dodajOrganizm(new Jagody(swiat));

        swiat.wyswietl();

        kontroler.uruchom(czlowiek);
    }
}