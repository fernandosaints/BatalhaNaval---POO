package gui;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Armas {

    public void trio(Graphics g, int posX, int posY, double larg, double alt){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        int count = 0;

        for(int i=0;i<3;i++){
            g2d.setPaint(Color.green);
            if(i == 2)
                rt=new Rectangle2D.Double(posX+count,posY+count,larg+1,alt+1);
            else
                rt=new Rectangle2D.Double(posX-count,posY+count,larg+1,alt+1);
            count=25;
            g2d.fill(rt);
        }
    }

    public void duo(Graphics g, int posX, int posY, double larg, double alt){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        for(int j=0;j<3;j++){
            g2d.setPaint(Color.yellow);
            rt=new Rectangle2D.Double(posX,posY,2*larg+1,alt+1);
            g2d.fill(rt);
            posX+=100;
        }
    }

    public void solo(Graphics g, int posX, int posY, double larg, double alt){
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;

        for(int j=0;j<4;j++){
            g2d.setPaint(Color.blue);
            rt=new Rectangle2D.Double(posX,posY,larg+1,alt+1);
            g2d.fill(rt);
            posX+=50;
        }
    }

    public void squad(Graphics g, int posX, int posY, double larg, double alt){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

        for(int j=0;j<2;j++){
            g2d.setPaint(Color.orange);
            rt=new Rectangle2D.Double(posX,posY,4*larg+1,alt+1);
            g2d.fill(rt);
            posX+=150;
        }
    }

    public void penta(Graphics g, int posX, int posY, double larg, double alt){
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rt;

        g2d.setPaint(Color.magenta);
        rt=new Rectangle2D.Double(posX,posY,5*larg+1,alt+1);
        g2d.fill(rt);
    }
}
