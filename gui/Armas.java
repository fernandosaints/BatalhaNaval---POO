package gui;

import com.sun.deploy.util.ArrayUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Armas extends JPanel {

    private int x, y, quantidade;
    Color cor;



    Armas(int x, int y, int quantidade, Color cor) {
        this.x=x;
        this.y=y;
        this.quantidade=quantidade;
        this.cor = cor;
    }

    Armas(){}

    public void hidro(Graphics g, int posX, int posY, double larg, double alt, double var, Color cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        double count = 0;

        for(int i=0;i<3;i++){
            g2d.setPaint(cor);
            if(i == 2)
                rt=new Rectangle2D.Double(posX+count,posY+count,larg+1,alt+1);
            else
                rt=new Rectangle2D.Double(posX-count,posY+count,larg+1,alt+1);
            count=25+var;
            g2d.fill(rt);
        }
    }

    public void submarino(Graphics g, int posX, int posY, double larg, double alt, Color cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,larg+1,alt+1);
        g2d.fill(rt);
    }

    public void destroyer(Graphics g, int posX, int posY, double larg, double alt, Color cor){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,2*larg+1,alt+1);
        g2d.fill(rt);
    }

    public void cruzador(Graphics g, int posX, int posY, double larg, double alt, Color cor){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,4*larg+1,alt+1);
        g2d.fill(rt);

    }

    public void couracado(Graphics g, int posX, int posY, double larg, double alt, Color cor){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(cor);
        rt=new Rectangle2D.Double(posX,posY,5*larg+1,alt+1);
        g2d.fill(rt);
    }

    public void paintComponent(Graphics g) {
        Armas armas = new Armas();
        Armas[] armasJogo = armas.criaArmas();
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;


        g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));

        g2d.setPaint(cor);


        /*for(int i=0;i<armasJogo.length;i++) {
            for(int j=0;j<armasJogo[0].length;j++) {
                if(peca[i][j]!=0) {
                    rt=new Rectangle2D.Double(matriz[i][j].x+(espLinha/2),matriz[i][j].y+(espLinha/2),larg+1,alt+1);
                    g2d.fill(rt);
                }
            }
        }*/


    }

    public Armas[] criaArmas(){
        Armas[] pecas = new Armas[15];
        int i, x = 70,y = 90;
        for(i=0;i<5;i++){
            pecas[i] = new Armas(x,y,1,Color.green);
            x+=25*pecas[i].getQuantidade()+65;
        }

        x = 45;
        y = 190;
        for(i=5;i<9;i++){
            pecas[i] = new Armas(x,y,1,Color.blue);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x = 45;
        y = 265;
        for(i=9;i<12;i++){
            pecas[i] = new Armas(x,y,2,Color.yellow);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x=45;
        y=340;
        for(i=12;i<14;i++){
            pecas[i] = new Armas(x,y,4,Color.orange);
            x+=25*pecas[i].getQuantidade()+25;
        }
        x=45;
        y=415;
        pecas[14] = new Armas(x,y,5,Color.magenta);

        return pecas;
    }

    public Armas[] removeArma(Armas[] armas,int index) { //realmente necessario?

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (armas == null || index < 0 || index >= armas.length) {
            return armas;
        }

        // Create another array of size one less
        Armas[] anotherArray = new Armas[armas.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < armas.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = armas[i];
        }

        // return the resultant array
        return anotherArray;
    }

    public void viraArma() {
        System.out.println("X DIR: "+this.getX());
        System.out.println("Y DIR: "+this.getY());
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

    public void setCor(Color cor) {
        this.cor = cor;
    }

}
