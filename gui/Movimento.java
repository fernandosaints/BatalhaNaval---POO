package gui;

import regras.Observable;
import regras.Observer;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Movimento implements MouseListener, Observable {

    Armas arma;

    public Movimento(JPanel arma){
        arma.addMouseListener(this);
        this.arma = (Armas) arma;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("X DIREITO:"+e.getX());
            System.out.println("Y DIREITO:"+e.getY());
            arma.viraArma();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public Object get() {
        return null;
    }
}
