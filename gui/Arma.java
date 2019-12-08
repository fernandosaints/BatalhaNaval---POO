package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


@SuppressWarnings("serial")
public class Arma extends JPanel {
	private int _id;
    private int _tipo; //tipo de arma
    private int _vida; //vida da arma
    private Color _mudaCor; //Cor da arma
    private Color _cor;
    private int _rotacao = 0; //Orientacao da arma
    private double _x, _y;
    private boolean _noTabuleiro;
    
    //Inicializa armas de todos os tipos
    public Arma(int tipo, int id) {    
    	if(tipo == 1) {
    		this._vida = 3;
    		this._cor = Color.GREEN;
    		this._mudaCor = Color.GREEN;
    		this._tipo = 1;
    		this._x = 0;
    		this._y = 25;
    	}
    	else if(tipo == 2) {
    		this._vida = 1;
    		this._cor = Color.BLUE;
    		this._mudaCor = Color.BLUE;
    		this._tipo = 2;
    		this._x = 0;
    		this._y = 0;
    		
    	}
    	else if(tipo == 3) {
    		this._vida = 2;
    		this._cor = Color.YELLOW;
    		this._mudaCor = Color.YELLOW;
    		this._tipo = 3;
    		this._x = 0;
    		this._y = 0;
    	}
    	else if(tipo == 4) {
    		this._vida = 3;
    		this._cor = Color.ORANGE;
    		this._mudaCor = Color.ORANGE;
    		this._tipo = 4;
    		this._x = 0;
    		this._y = 0;
    	}
    	else if(tipo == 5) {
    		this._vida = 4;
    		this._cor = Color.MAGENTA;
    		this._mudaCor = Color.MAGENTA;
    		this._tipo = 5;
    		this._x = 0;
    		this._y = 0;
    	}
    	this._id = id;
    	this._rotacao = 0;
    	this._noTabuleiro = false;

    	setOpaque(false);
    }
   	
    //Desenha as armas no frame
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g;
        Rectangle2D rt;
        double largura=25.0,altura=25.0;
        
        g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
        
        g2d.setPaint(this.getCor());
        
        if(_tipo == 1) {
        	if(_rotacao == 0) {
	        	rt = new Rectangle2D.Double(_x,_y,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x+25,_y-25,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x+50,_y,largura+1,altura+1);
	        	g2d.fill(rt);
        	}
        	else if(_rotacao == 1) {
        		rt = new Rectangle2D.Double(_x,_y-25,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x+25,_y,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x,_y+25,largura+1,altura+1);
	        	g2d.fill(rt);
        	}
        	else if(_rotacao == 2) {
        		rt = new Rectangle2D.Double(_x,_y-25,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x+25,_y,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x+50,_y-25,largura+1,altura+1);
	        	g2d.fill(rt);
        	}
        	else {
        		rt = new Rectangle2D.Double(_x+25,_y-25,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x,_y,largura+1,altura+1);
	        	g2d.fill(rt);
	        	rt = new Rectangle2D.Double(_x+25,_y+25,largura+1,altura+1);
	        	g2d.fill(rt);
        	}
        }
        else {
        	if(_rotacao == 0 || _rotacao == 2) {
	        	for(int i = 0;i<_vida;i++) {
	        		rt = new Rectangle2D.Double(_x+25*i,_y,largura+1,altura+1);
	            	g2d.fill(rt);
	        	}
        	}
        	else {
        		for(int i = 0;i<_vida;i++) {
	        		rt = new Rectangle2D.Double(_x,_y+25*i,largura+1,altura+1);
	            	g2d.fill(rt);
	        	}
        	}
        }
    }

  	public void repaintArma() {repaint();}
   
  	public int getTipo() {return this._tipo;}
   
  	public int getRotacao() {return this._rotacao;}
  	
  	public void setRotacao() {
  		if(this._rotacao == 3) {
  			this._rotacao = 0;
  		}
  		else {
  			this._rotacao++;
  		}
  	}

  	public Color getCor() {return this._mudaCor;}

  	public void setCor(Color cor) {this._mudaCor = cor;}

    public int getVida() {return this._vida;}

    public void dano() {this._vida--;}
    
    public Color getCorOriginal() {return this._cor;}
    
    public void setX(double x) { this._x=x;}
    
    public void setY(double y) { this._y=y;}
    
    public double get_X() {return this._x;}
    
    public double get_Y() {return this._y;}
    
    public int getId() { return this._id;}
    
    public boolean getNoTabuleiro() { return this._noTabuleiro; }
    
    public void setNoTabuleiro() { this._noTabuleiro = true; }
    
 }
