import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import java.util.Vector;

public class MakeGrid extends JPanel {
    private int cellSize;
    private JTextArea textArea;
    private Swiat swiat;

    public MakeGrid(int cellSize, Swiat swiat) {
        this.cellSize = cellSize;
        this.swiat = swiat;

        int numRows = swiat.getN();
        int numCols = swiat.getM();

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (swiat.getPosPlansza(row, col) == null) {
                    JButton button = new JButton();
                    button.setBackground(Color.black);
                    button.setBorder(new LineBorder(Color.BLACK));
                    button.setPreferredSize(new Dimension(cellSize, cellSize));

                    gbc.gridx = col;
                    gbc.gridy = row;

                    gbc.ipadx = cellSize;
                    gbc.ipady = cellSize;
                    add(button, gbc);

                    int finalRow = row;
                    int finalCol = col;
                    Vector<String> options = new Vector<>(10);
                    options.add("Antylopa");
                    options.add("Barszcz");
                    options.add("Guarana");
                    options.add("Jagody");
                    options.add("Lis");
                    options.add("Mlecz");
                    options.add("Owca");
                    options.add("Trawa");
                    options.add("Wilk");
                    options.add("Zolw");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selectedOption = (String) JOptionPane.showInputDialog(
                                    null,
                                    "Wybierz organizm:",
                                    "Dodanie organizmu",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    options.toArray(),
                                    options.get(0)
                            );
                            if (selectedOption != null) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Dodano: " + selectedOption,
                                        "Dodanie organizmu",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                Polozenie pos = new Polozenie(swiat, finalRow, finalCol);
                                switch (selectedOption) {
                                    case "Antylopa": swiat.dodajOrganizm(new Antylopa(swiat, pos));
                                    case "Barszcz": swiat.dodajOrganizm(new Barszcz(swiat, pos));
                                    case "Guarana": swiat.dodajOrganizm(new Guarana(swiat, pos));
                                    case "Jagody": swiat.dodajOrganizm(new Jagody(swiat, pos));
                                    case "Lis": swiat.dodajOrganizm(new Lis(swiat, pos));
                                    case "Mlecz": swiat.dodajOrganizm(new Mlecz(swiat, pos));
                                    case "Owca": swiat.dodajOrganizm(new Owca(swiat, pos));
                                    case "Trawa": swiat.dodajOrganizm(new Trawa(swiat, pos));
                                    case "Wilk": swiat.dodajOrganizm(new Wilk(swiat, pos));
                                    case "Zolw": swiat.dodajOrganizm(new Zolw(swiat, pos));
                                }
                                swiat.aktualizujGrid();
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Anulowano wybór",
                                        "Dodanie organizmu",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        }
                    });
                } else {
                    JPanel panel = new JPanel();
                    panel.setBackground(swiat.getPosPlansza(row, col).getKolor());
                    panel.setBorder(new LineBorder(Color.BLACK));
                    gbc.gridx = col;
                    gbc.gridy = row;

                    gbc.ipadx = cellSize;
                    gbc.ipady = cellSize;
                    add(panel, gbc);
                }
            }
        }

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridx = 0;
        gbc.gridy = numRows;
        gbc.gridwidth = numCols + 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, gbc);

        updateTextArea();
    }

    private void updateTextArea() {
        textArea.setText("");
        for (String report : swiat.getRaport()) {
            textArea.append(report + "\n");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int numRows = swiat.getN();
        int numCols = swiat.getM();

        int width = numCols * cellSize;
        int height = numRows * cellSize;

        g.setColor(Color.BLACK);
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int x = col * cellSize;
                int y = row * cellSize;

                g.setColor(Color.BLACK);
                g.fillRect(x, y, cellSize, cellSize);

                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
}