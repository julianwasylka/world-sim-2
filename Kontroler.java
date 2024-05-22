import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Scanner;

public class Kontroler {
    private JFrame frame;

    public boolean getRuch() {
        return ruch;
    }

    public void setRuch(boolean ruch) {
        this.ruch = ruch;
    }

    private boolean ruch = false;

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    private int cooldown;
    private Swiat swiat;

    public Kontroler(Swiat swiat) {
        cooldown = 0;
        this.swiat = swiat;
        this.frame = swiat.getFrame();
    }

    public void uruchom(Czlowiek czlowiek) {
        Polozenie pos = czlowiek.getPolozenie();
        swiat.setPosPlansza(czlowiek.getPolozenie().getX(), czlowiek.getPolozenie().getY(), null);

        JPanel panel = new JPanel(new FlowLayout());

        JButton zapiszButton = new JButton("Zapisz");
        zapiszButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swiat.save(Kontroler.this);
                swiat.dodajRaport("Zapisano swiat do pliku");
                swiat.aktualizujGrid();
            }
        });
        JButton wczytajButton = new JButton("Wczytaj");
        wczytajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swiat.load(Kontroler.this, pos);
                swiat.wykonajTure(frame, pos);
                swiat.dodajRaport("Wczytano swiat z pliku");
                swiat.aktualizujGrid();
            }
        });

        panel.add(zapiszButton);
        panel.add(wczytajButton);

        frame.add(panel);
        frame.setVisible(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                int pX = pos.getX();
                int pY = pos.getY();
                int key = e.getKeyCode();
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (key) {
                        case KeyEvent.VK_ESCAPE:
                            frame.dispose();
                            System.exit(0);
                            return true;
                        case KeyEvent.VK_T:
                            if (cooldown == 0) {
                                cooldown = 10;
                                swiat.dodajRaport("uruchomiono tarcze");
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if (pY - 1 >= 0) {
                                pY -= 1;
                            }
                            pos.setX(pX);
                            pos.setY(pY);
                            swiat.wykonajTure(frame, pos);
                            ruch = true;
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (pY + 1 < swiat.getM()) {
                                pY += 1;
                            }
                            pos.setX(pX);
                            pos.setY(pY);
                            swiat.wykonajTure(frame, pos);
                            ruch = true;
                            break;
                        case KeyEvent.VK_DOWN:
                            if (pX + 1 < swiat.getN()) {
                                pX += 1;
                            }
                            pos.setX(pX);
                            pos.setY(pY);
                            swiat.wykonajTure(frame, pos);
                            ruch = true;
                            break;
                        case KeyEvent.VK_UP:
                            if (pX - 1 >= 0) {
                                pX -= 1;
                            }
                            pos.setX(pX);
                            pos.setY(pY);
                            swiat.wykonajTure(frame, pos);
                            ruch = true;
                            break;
                        default:
                            break;
                    }

                    if (cooldown > 0) {
                        cooldown--;
                        if (cooldown == 0)
                            swiat.dodajRaport("tarcza gotowa");
                        else if (cooldown == 5)
                            swiat.dodajRaport("tarcza wyladowana");
                    }
                }
                if (swiat.getKoniec()) {
                    System.out.println("Przegrana!");
                    frame.dispose();
                    System.exit(0);
                }
                return false;
            }
        });
    }
}