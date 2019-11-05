package gui;

import com.sun.deploy.util.ArrayUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Armas extends JPanel {

    private double larg=25.0,alt=25.0,espLinha=5.0;
    private int[][] arma;
    private Celula[][] matriz;
    private Color cor;


    public Armas(int[][] arma, Color cor) {
        /*this.x=x;
        this.y=y;
        this.quantidade=quantidade;
        */
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
        //setOpaque(false);
    }

    public Armas(){}

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
        //Armas armas = new Armas();
        //Armas[] armasJogo = armas.criaArmas();
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
                    x  = matriz[i][j].x+(espLinha/2);
                    y = matriz[i][j].y+(espLinha/2);
                    rt=new Rectangle2D.Double(x,y,larg+1,alt+1);
                    g2d.fill(rt);
                }
            }
        }
    }

    /*public void criaArmas(){

        int[][] hidro = new int[][]{{0,1,0},{1,0,1}};
        int[][] submarino = new int[][]{{0,1,0},{1,0,1}};
        int[][] destroyer = new int[][]{{0,1,0},{1,0,1}};
        int[][] cruzador = new int[][]{{0,1,0},{1,0,1}};
        int[][] couracado = new int[][]{{0,1,0},{1,0,1}};

        int i, x = 70,y = 90;
        for(i=0;i<5;i++){
            armas[i] = new Armas(hidro,Color.green);
            armas[i].setBounds(x,y,100,70);
            x+=25*1+65;
        }
        x = 45;
        y = 190;
        for(i=5;i<9;i++){
            armas[i] = new Armas(submarino,Color.blue);
            armas[i].setBounds(x,y,30,30);
            x+=25*1+65;
        }
        x = 45;
        y = 265;
        for(i=9;i<12;i++){
            armas[i] = new Armas(destroyer,Color.yellow);
            armas[i].setBounds(x,y,60,30);
            x+=25*2+65;
        }
        x=45;
        y=340;
        for(i=12;i<14;i++) {
            armas[i] = new Armas(cruzador, Color.orange);
            armas[i].setBounds(x, y, 120, 30);
            x += 25 * 4 + 65;
        }
        x=45;
        y=415;
        armas[14] = new Armas(couracado,Color.magenta);
        armas[i].setBounds(x,y,150,30);

        setArmas();
    }

    private void setArmas() {
        int i, j = 0, x = 70,y = 90;
        for(i=0;i<5;i++){
            armas[i].setLocation(x,y);
            x+=25*1+65;
        }
        x = 45;
        y = 190;
        for(i=5;i<9;i++){
            armas[i].setLocation(x,y);
            x+=25*1+65;
        }
        x = 45;
        y = 265;
        for(i=9;i<12;i++){
            armas[i].setLocation(x,y);
            x+=25*2+65;
        }
        x=45;
        y=340;
        for(i=12;i<14;i++) {
            armas[i].setLocation(x, y);
            x += 25 * 4 + 65;
        }
        x=45;
        y=415;
        armas[i].setLocation(x,y);

        while(j<15){
            panel.add(armas[j],j);
        }
    }*/

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

    public void viraArma(int x,int y) {
        System.out.println("X DIR: "+x);
        System.out.println("Y DIR: "+y);

    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

}
