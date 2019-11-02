package gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Armas {

    int x,y;
    int quantidade;
    Color cor;

    Armas(int x, int y, int quantidade, Color cor) {
        this.x=x;
        this.y=y;
        this.quantidade=quantidade;
        this.cor = cor;
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

    public void submarino(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,larg+1,alt+1);
        g2d.fill(rt);
    }

    public void destroyer(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,2*larg+1,alt+1);
        g2d.fill(rt);
    }

    public void cruzador(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

            g2d.setPaint(cor);
            rt=new Rectangle2D.Double(posX,posY,4*larg+1,alt+1);
            g2d.fill(rt);

    }

    public void couracado(Graphics g, int posX, int posY, double larg, double alt, Paint cor){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,5*larg+1,alt+1);
        g2d.fill(rt);
    }

    public Armas[] criaArmas(){
        Armas[] pecas = new Armas[15];
        int i, x = 70,y = 140;
        //for(i=0;i<5)

        
        x = 45;
        y = 190;
        for(i=0;i<4;i++){
            pecas[i] = new Armas(x,y,1,cor);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x = 45;
        y = 265;
        for(i=4;i<7;i++){
            pecas[i] = new Armas(x,y,2,cor);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x=45;
        y=340;
        for(i=7;i<9;i++){
            pecas[i] = new Armas(x,y,4,cor);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x=45;
        y=415;
        pecas[9] = new Armas(x,y,5,cor);

        return pecas;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Color getCor() {
        return cor;
    }

}
