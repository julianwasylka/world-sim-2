import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;

public class Polozenie {
    private int x;
    private int y;
    private Swiat swiat;

    public Polozenie(Swiat swiat) {
        this.swiat = swiat;
        if (swiat == null) {
            x = 0;
            y = 0;
            return;
        }
        Random rand = new Random();
        int a = rand.nextInt(swiat.getN());
        int b = rand.nextInt(swiat.getM());

        while (a == (swiat.getN() / 2)) a = rand.nextInt(swiat.getN());
        while (b == (swiat.getM() / 2)) b = rand.nextInt(swiat.getM());

        x = a;
        y = b;
    }

    public Polozenie(Swiat swiat, int x, int y) {
        this.x = x;
        this.y = y;
        this.swiat = swiat;
    }

    public Polozenie(Polozenie prev) {
        this.x = prev.x;
        this.y = prev.y;
        this.swiat = prev.swiat;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setXY(Polozenie pos) {
        x = pos.x;
        y = pos.y;
    }

    public boolean przesun(int x, int y) {
        if (this.x + x < swiat.getN() && this.x + x >= 0
                && this.y + y < swiat.getM() && this.y + y >= 0) {
            this.x += x;
            this.y += y;
            return true;
        }
        return false;
    }

    public boolean losowyKrok(int krok) {
        int gdzie = new Random().nextInt(8);

        // 0 1 2
        // 3 X 4
        // 5 6 7

        switch (gdzie) {
            case 0:
                return przesun(-1 * krok, -1 * krok);
            case 1:
                return przesun(0, -1 * krok);
            case 2:
                return przesun(krok, -1 * krok);
            case 3:
                return przesun(-1 * krok, 0);
            case 4:
                return przesun(krok, 0);
            case 5:
                return przesun(-1 * krok, krok);
            case 6:
                return przesun(0, krok);
            case 7:
                return przesun(krok, krok);
        }
        return false;
    }

    public boolean equals(Polozenie other) {
        return other.getX() == x && other.getY() == y;
    }

    public void zapisz(PrintWriter writer) {
        writer.print(x + " " + y);
    }
}