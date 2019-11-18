package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Armas extends JPanel {

    private double larg=25.0,alt=25.0,espLinha=5.0, quantidade;
    private int[][] arma;
    private Celula[][] matriz;
    private Color cor;
    private int tipo, virada = 0;

    public Armas(int[][] arma, Color cor) {
        double largSum, altSum = 0;
        int i, j;
        this.arma = arma;
        matriz = new Celula[arma.length][arma[0].length];
        this.cor = cor;
        for(i=0;i<arma.length;i++) {
            largSum = 0;
            for (j = 0; j < arma[0].length; j++) {
                matriz[i][j] = new Celula(largSum, altSum);
                largSum += larg;
            }
            altSum+=alt;
        }
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;
        double x,y;

        g2d.setPaint(cor);
        g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));


        for(int i=0;i<arma.length;i++) {
            for(int j=0;j<arma[0].length;j++) {
                if(arma[i][j]!=0) {
                    x = matriz[i][j].x + (espLinha/2);
                    y = matriz[i][j].y + (espLinha/2);
                    rt=new Rectangle2D.Double(x,y,larg+1,alt+1);
                    g2d.fill(rt);
                }
            }
        }
    }

    public void viraArma() {
        int [][] newPeca;
        newPeca = new int[this.arma[0].length][this.arma.length];
        for(int i=0; i < this.arma.length; i++) {
            for(int j=0; j < this.arma[0].length; j++) {
                newPeca[j][i] = this.arma[i][j];
                virada++;
            }
        }

        if(virada %2 == 0) {
            int[][] newPecaVirada = new int[newPeca.length][newPeca[0].length];
            for(int i = 0; i < newPeca.length ;i++) {
                newPecaVirada[i] = newPeca[newPeca.length-1-i];
            }
            newPeca = newPecaVirada;
        }

        this.arma = newPeca;

        double x, y=0;
        matriz = new Celula[arma.length][arma[0].length];
        for(int i=0;i<arma.length;i++) {
            x = 0;
            for(int j=0;j<arma[0].length;j++) {
                matriz[i][j]=new Celula(x,y);
                x+=larg;
            }
            y+=alt;
        }
        this.setBounds(this.getX(), this.getY(), this.getHeight(), this.getWidth() );
        repaint();
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
