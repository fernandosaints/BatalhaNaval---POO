package gui;

import gui.Arma;
import java.awt.*;
import java.io.Serializable;

public class Casa implements Serializable{
	private static final long serialVersionUID = 1L;
	private int _x,_y;
	private Arma _arma;
	private Color _cor;
	private boolean _clicado;
	
	public Casa(int x, int y) {
		this._x=x;
		this._y=y;
		this._arma = null;
		this._cor = null;
		this._clicado = false;
	}
	
	public int getX() {
		return this._x;
	}
	
	public int getY() {
		return this._y;
	}
	
	public void setArma(Arma arma){
		if(this._arma == null) {
			this._arma = arma;
		}
	}
	
	public void removeArma() {
		this._arma = null;
	}
	
	public Arma getArma() {
		if(this._arma == null) {
			return null;
		}
		return this._arma;
	}
	
	public Color getCor() {
		if(this._cor!=null) {
			return this._cor;
		}
		return null;
	}
	
	public void setCor(Color cor) {
		this._cor = cor;
	}
	
	public boolean getClicado() {
		return _clicado;
	}

	public void setClicado() {
		this._clicado = true;
	}
	
}

