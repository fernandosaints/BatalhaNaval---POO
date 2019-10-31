package gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Armas {

    double x,y;
    int quantidade;

    Armas(double x, double y, int quantidade) {
        this.x=x;
        this.y=y;
        this.quantidade=quantidade;
    }

    Armas(){}

    public void hidro(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        int count = 0;

        for(int i=0;i<3;i++){
            g2d.setPaint(cor);
            if(i == 2)
                rt=new Rectangle2D.Double(posX+count,posY+count,larg+1,alt+1);
            else
                rt=new Rectangle2D.Double(posX-count,posY+count,larg+1,alt+1);
            count=25;
            g2d.fill(rt);
        }
    }

    public void destroyer(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        for(int j=0;j<3;j++){
            g2d.setPaint(cor);
            rt=new Rectangle2D.Double(posX,posY,2*larg+1,alt+1);
            g2d.fill(rt);
            posX+=100;
        }
    }

    public void submarino(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        for(int j=0;j<4;j++){
            g2d.setPaint(cor);
            rt=new Rectangle2D.Double(posX,posY,larg+1,alt+1);
            g2d.fill(rt);
            posX+=50;
        }
    }

    public void cruzador(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

        for(int j=0;j<2;j++){
            g2d.setPaint(cor);
            rt=new Rectangle2D.Double(posX,posY,4*larg+1,alt+1);
            g2d.fill(rt);
            posX+=150;
        }
    }

    public void couracado(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,5*larg+1,alt+1);
        g2d.fill(rt);
    }

    public Armas[] criaArmas(){
        Armas[] pecas = new Armas[10];
        int i, x = 45, y = 190;
        for(i=0;i<4;i++){
            pecas[i] = new Armas(x,y,1);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x = 45;
        y = 265;
        for(i=4;i<7;i++){
            pecas[i] = new Armas(x,y,2);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x=45;
        y=340;
        for(i=7;i<9;i++){
            pecas[i] = new Armas(x,y,4);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x=45;
        y=415;
        pecas[9] = new Armas(x,y,5);

        return pecas;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
