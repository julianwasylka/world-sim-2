import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Swiat {
    private final String save_file_name = "swiat.save";
    private MakeGrid makeGridPanel;

    private int n;
    private int m;
    private boolean koniec = false;
    private boolean nowySwiat = false;
    private List<Organizm> organizmy;
    private List<String> raport;
    private Organizm[][] plansza;

    public JFrame getFrame() {
        return frame;
    }

    private JFrame frame;

    public void setKoniec(boolean koniec) {
        this.koniec = koniec;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public boolean getKoniec() {
        return koniec;
    }

    public Organizm[][] getPlansza() {
        return plansza;
    }
    public List<Organizm> getOrganizmy() {
        return organizmy;
    }

    public List<String> getRaport() {
        return raport;
    }

    public Swiat(int n, int m) {
        this.n = n;
        this.m = m;
        frame = new JFrame("Julian Wasylka 193223");
        zrobPlansze();
        organizmy = new ArrayList<Organizm>();
        raport = new ArrayList<String>();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(n*60,m*60);
    }

    public Swiat() {
        this.n = 20;
        this.m = 20;
        frame = new JFrame("Julian Wasylka 193223");
        zrobPlansze();
        organizmy = new ArrayList<Organizm>();
        raport = new ArrayList<String>();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(n*30,m*30);
    }

    public void wyswietl() {
        makeGridPanel = new MakeGrid(10, this);
        frame.add(makeGridPanel, BorderLayout.NORTH);
        frame.requestFocusInWindow();
    }

    public void wykonajTure(JFrame frame, Polozenie pos) {
        int size = organizmy.size();
        Collections.sort(organizmy, new CompareOrg());

        for (int i = 0; i < size; i++) {
            if (nowySwiat) {
                nowySwiat = false;
                break;
            }
            if (i >= organizmy.size()) {
                break;
            }
            organizmy.get(i).akcja(pos);
        }
        aktualizujGrid();
        raport.clear();
    }
    public void aktualizujGrid() {
        frame.remove(makeGridPanel);
        makeGridPanel = new MakeGrid(10, this);
        frame.add(makeGridPanel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public void wyczyscPlansze() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                plansza[i][j] = null;
            }
        }
    }

    public Organizm getPosPlansza(int i, int j) {
        return plansza[i][j];
    }
    public void setPosPlansza(int i, int j, Organizm org) {
        plansza[i][j] = org;
    }


    public boolean dodajOrganizm(Organizm org) {
        if (plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] != null) {
            return false;
        }
        organizmy.add(org);
        plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = org;
        return true;
    }

    public void usunOrganizm(Organizm org) {
        org.koniecGry();
        plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = null;
        organizmy.remove(org);
    }

    public void przesunOrganizm(Organizm org, Polozenie nowe) {
        plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = null;
        plansza[nowe.getX()][nowe.getY()] = org;
        org.setPolozenie(nowe);
    }

    public void dodajRaport(String rap) {
        raport.add(rap);
    }
    public void zrobPlansze() {
        plansza = new Organizm[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                plansza[i][j] = null;
            }
        }
    }

    public void zniszczPlansze() {
        for (Organizm[] cols : plansza) {
            for (int j = 0; j < cols.length; j++) {
                cols[j] = null;
            }
        }
        plansza = null;
    }

    public void wyczyscOrganizmy(boolean bezCzlowieka) {
        if (bezCzlowieka) {
            List<Organizm> tempOrganizmy = new ArrayList<>(organizmy);
            for (Organizm org : tempOrganizmy) {
                if (org.rysowanie() != '#') {
                    organizmy.remove(org);
                }
            }
        } else {
            organizmy.clear();
        }
    }
    public void zapisz(PrintWriter os) {
        os.println(n + " " + m);
    }
    public void czytaj(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String[] values = line.split(" ");
        n = Integer.parseInt(values[0]);
        m = Integer.parseInt(values[1]);
    }

    public void wyczyscOrganizmy() {
        wyczyscOrganizmy(false);
    }

    public void koniecGry() {
        wyczyscOrganizmy();
        raport.clear();
        zniszczPlansze();
    }
    public void save(Kontroler kontroler) {
        try (PrintWriter fp = new PrintWriter(new FileWriter(save_file_name))) {
            fp.println(kontroler.getCooldown());

            zapisz(fp);

            fp.println(organizmy.size());
            for (Organizm org : organizmy) {
                org.zapisz(fp);
                fp.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(Kontroler kontroler, Polozenie pos) {
        try (BufferedReader fp = new BufferedReader(new FileReader(save_file_name))) {
            wyczyscOrganizmy(true);
            zniszczPlansze();

            int tmp = 0;
            tmp = Integer.parseInt(fp.readLine());
            kontroler.setCooldown(tmp);

            czytaj(fp);
            zrobPlansze();

            int typ = ' ';
            tmp = Integer.parseInt(fp.readLine());
            for (int i = 0; i < tmp; i++) {
                Organizm org = null;
                typ = fp.read();
                String line = fp.readLine();

                if (typ == '#') {
                    org = organizmy.get(0);
                }
                else org = generujOrganizm(typ, kontroler);

                org.czytaj(line);
                if (typ == '#') {
                    pos.setXY(org.getPolozenie());
                } else {
                    dodajOrganizm(org);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Organizm generujOrganizm(int typ, Kontroler kontroler)
    {
        Organizm org = null;
        switch (typ)
        {
            case 'a':
                org = new Antylopa(this);
                break;
            case 'b':
                org = new Barszcz(this);
                break;
            case '#':
                org = new Czlowiek(this, kontroler);
                break;
            case 'g':
                org = new Guarana(this);
                break;
            case 'j':
                org = new Jagody(this);
                break;
            case 'l':
                org = new Lis(this);
                break;
            case 'm':
                org = new Mlecz(this);
                break;
            case 'o':
                org = new Owca(this);
                break;
            case 't':
                org = new Trawa(this);
                break;
            case 'w':
                org = new Wilk(this);
                break;
            case 'z':
                org = new Zolw(this);
                break;

            default:
                break;

        }
        return org;
    }
}