import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JogoDaForca extends JFrame {
	
    private int errei = 0;
    private String [] palavras = {
        "ENFORCADO", "REPROVADO", "REARRUMADO", "FIGUEIRA",
        "EMPOSSADO", "MUDANÇA", "REFORMA", "GIROSCÓPIO"
    };
    private String palavraSorteada;
    private String palavraEscondida;
    private JLabel lbEscondida;
    private JTextField textField = new JTextField("",3);
    private JButton buttonOK = new JButton("OK");

    public JogoDaForca() {
        this.setTitle("Enforcado");
        this.setSize(600,400);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        palavraSorteada = palavras[(int)(Math.random()*palavras.length)];
        esconderPalavra();
        JPanel pn = new JPanel();
        pn.setLayout(new GridLayout(1,2)); // 1 linha, 2 colunas.
        lbEscondida = new JLabel(palavraEscondida);
        pn.add(new JLabel());
        pn.add(lbEscondida);
        this.add(pn);
        JPanel pn2 = new JPanel();
        pn2.add(textField);
        pn2.add(buttonOK);
 
        buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char letra = textField.getText().toUpperCase().charAt(0);
                boolean acertou = verificarLetra(letra);
                if(acertou) {
                    substituirTraco(letra);
                    lbEscondida.setText(palavraEscondida);
                    if(!palavraEscondida.contains("_")){
                        JOptionPane.showMessageDialog(JogoDaForca.this, "Parabéns!! Você ganhou.");
                    }
                }else {
                    errei ++;
                    repaint();
                    if (errei >=6) {
                    	JOptionPane.showMessageDialog(JogoDaForca.this, "Uma pena!! Você perdeu =(");
                    	limparDesenho();
                    	repaint();
                    }
                }
                textField.setText("");
                textField.requestFocus();
			}
		});
        this.add(pn,BorderLayout.CENTER);
        this.add(pn2,BorderLayout.SOUTH);
        this.setVisible(true);
    }
    private void esconderPalavra() {
        palavraEscondida = "";
        for (int i = 0; i < palavraSorteada.length(); i += 1) {
            palavraEscondida += "_ ";
        }
    }
    private boolean verificarLetra(char c) {
        for (int i = 0; i < palavraSorteada.length(); i += 1) {
            if (c == palavraSorteada.charAt(i)) {
                return true;
            }
        }
        return false;
    }
    private void substituirTraco(char c) {
        String mnt = "";
        for (int i = 0; i < palavraSorteada.length(); i += 1) {
            if (c == palavraSorteada.charAt(i)) {
                mnt += "" + c + " ";
            } else {
                int x = i * 2;
                mnt += "" + palavraEscondida.charAt(x) + " ";
            }    
        }
        palavraEscondida = mnt;                
    }
    private void limparDesenho() {
        errei = 0;
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(110,100,110,300);
        g.drawLine(110,100,210,100);
        g.drawLine(210,100,210,120);
        if (errei > 0) {
            // Cabeça
            g.drawOval(200,120,20,30);
        }
        if (errei > 1) {
            // Tronco
            g.drawLine(210,150,210,200);
        }
        if (errei > 2) {
            // Pernas
            g.drawLine(210,200,190,240);
        }
        if (errei > 3) {
            g.drawLine(210,200,230,240);
        }
        if (errei > 4) {
            // Braços
            g.drawLine(210,160,180,180);
        }
        if (errei > 5) {
            g.drawLine(210,160,240,180);
            g.setColor(new Color(255,0,0));
            g.fillOval(200,120,20,30);
        }
    }
    public static void main(String [] args) {
        new JogoDaForca();
    }
}