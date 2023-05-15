import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dado extends JFrame {
    private byte num;
    public Dado() {
        super("Sorteio");
        this.setSize(450,400);
        JButton btSorteio = new JButton("Sortear");
        btSorteio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortear();
            }
        });
        this.add(btSorteio, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sortear();
        this.setVisible(true);
    }
    public void sortear() {
        num = (byte)(Math.random()*6+1);
        this.repaint();
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(20,70,190,190);
        if (num > 1) {
            // Bolinha 1
            g.fillOval(30,80,50,50);
            // Bolinha 7
            g.fillOval(150,200,50,50);
        }
        if (num == 6) {
            // Bolinha 2
            g.fillOval(30,140,50,50);
            // Bolinha 6
            g.fillOval(150,140,50,50);
        }
        if (num > 3) {
            // Bolinha 3
            g.fillOval(30,200,50,50);
            // Bolinha 5
            g.fillOval(150,80,50,50);
        }
        if (num%2 != 0) {
            // Bolinha 4
            g.fillOval(90,140,50,50);
        }
    }
    public static void main(String [] args) { new Dado(); }
}