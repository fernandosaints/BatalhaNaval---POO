package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Armas extends JPanel {

    private double larg=25.0,alt=25.0,espLinha=5.0, quantidade;
    private int[][] arma;
    private Celula[][] matriz;
    private Color cor;
    private int flipped = 0;


    public Armas(int[][] arma, Color cor) {
        int a,b;
        this.arma = arma;
        matriz = new Celula[arma.length][arma[0].length];
        this.cor = cor;
        int i, j;
        b = 0;
        for(i=0;i<arma.length;i++) {
            a = 0;
            for (j = 0; j < arma[0].length; j++) {
                matriz[i][j] = new Celula(a, b);
                a += larg;
            }
            b+=alt;
        }
        setOpaque(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;
        double x,y;

        g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));

        g2d.setPaint(cor);


        for(int i=0;i<arma.length;i++) {
            for(int j=0;j<arma[0].length;j++) {
                if(arma[i][j]!=0) {
                    x = matriz[i][j].x+(espLinha/2);
                    y = matriz[i][j].y+(espLinha/2);
                    rt=new Rectangle2D.Double(x,y,larg+1,alt+1);
                    g2d.fill(rt);
                }
            }
        }
    }

    public void viraArma() {
        /*boolean virada = false;
        int [][] novaArma;
        novaArma = new int[arma[0].length][arma.length];
        for(int i=0; i < arma.length; i++) {
            for(int j=0; j < arma[0].length; j++) {
                novaArma[j][i] = arma[i][j];
                flipped++;
            }
        }
        this.arma = novaArma;

        if(flipped%2 == 0) {
            int[][] flippedNewPeca = new int[novaArma.length][novaArma[0].length];
            for(int i = 0; i < novaArma.length ;i++) {
                flippedNewPeca[i] = novaArma[novaArma.length-1-i];
            }
            novaArma = flippedNewPeca;
        }

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

        this.setBounds(this.getX(), this.getY(), this.getHeight(), this.getWidth());
        repaint();
        */
        int [][] newPeca;
        newPeca = new int[this.arma[0].length][this.arma.length];
        for(int i=0; i < this.arma.length; i++) {
            for(int j=0; j < this.arma[0].length; j++) {
                newPeca[j][i] = this.arma[i][j];
                flipped++;
            }
        }

        if(flipped%2 == 0) {
            int[][] flippedNewPeca = new int[newPeca.length][newPeca[0].length];
            for(int i = 0; i < newPeca.length ;i++) {
                flippedNewPeca[i] = newPeca[newPeca.length-1-i];
            }
            newPeca = flippedNewPeca;
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
}
