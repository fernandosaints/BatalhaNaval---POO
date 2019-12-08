package regras;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import gui.Casa;

class CtrlRegras implements Observable, Serializable{

	private static final long serialVersionUID = 1L;
	private int xIni=30,yIni=90,larg=25,alt=25, espLinha=5, _ataques = 3, _vez = 0;
	private Casa _tabuleiro[][];
	private Casa _tabuleiro2[][];
	private int _armasNoTabuleiro = 0, _qtdArmasTab1 = 1, _qtdArmasTab2 = 1;
	private String jogador1 = "",jogador2 = "";
	
	List<Observer> lob=new ArrayList<Observer>();
	
	public CtrlRegras() {
		_tabuleiro = new Casa[15][15];
		_tabuleiro2 = new Casa[15][15];
		int x,y = yIni;
		for(int i = 0; i < 15; i++) {
			x = (xIni+700);
			for (int j = 0; j < 15; j++) {
				_tabuleiro[i][j] = new Casa(x, y);
				_tabuleiro2[i][j] = new Casa(x, y);
				x += larg + espLinha;
			}
			y += alt + espLinha;
		}
    }
	
	public Casa[][] getTabuleiro() {
		return _tabuleiro;
	}
	
	public Casa[][] getTabuleiro2(){
		return _tabuleiro2;
	}

	public String[] getJogadores(){
		String[] jogadores = new String[2];
		jogadores[0] = jogador1;
		jogadores[1] = jogador2;
		return jogadores;
	}

	public void setJogadores(String jogador1, String jogador2){
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
	}

	public void setArmasNoTabuleiro() {
		_armasNoTabuleiro++;
	}
	
	public int getArmasNoTabuleiro() {
		return _armasNoTabuleiro;
	}
	
	public void resetaArmasNoTabuleiro() {
		_armasNoTabuleiro = 0;
	}
	
	public int getAtaques() {
		return _ataques;
	}
	
	public void setAtaques() {
		if(_ataques == 0) {
			_ataques = 3;
		}
		else {
			_ataques--;
		}
		for(Observer o:lob)
            o.notify(this);
	}
	
	public int getVez() {
		return _vez;
	}
	
	public void setVez() {
		if(_vez==0){
			_vez = 1;
		}
		else {
			_vez=0;
		}
		for(Observer o:lob)
	        o.notify(this);
	}
	
	public void setArmasTab1() {
		this._qtdArmasTab1--;
	}
	
	public void setArmasTab2() {
		this._qtdArmasTab2--;
	}
	
	public int getArmasTab1() {
		return this._qtdArmasTab1;
	}
	
	public int getArmasTab2() {
		return this._qtdArmasTab2;
	}
	
	public void salvaTabuleiros() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(new JFrame());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				PrintStream ps = new PrintStream(fc.getSelectedFile()+ ".dat");
				ObjectOutputStream object = new ObjectOutputStream(ps);
				object.writeObject(this);
				object.flush();
				object.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	
	@SuppressWarnings("resource")
	public CtrlRegras carregaTabuleiros() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
		File fileSelected = fc.getSelectedFile();
		FileInputStream fileIn = null;
			try {
				fileIn = new FileInputStream(fileSelected);
				ObjectInputStream is = new ObjectInputStream(fileIn);
				CtrlRegras tabsCarregados = (CtrlRegras) is.readObject();
				return tabsCarregados;
			} catch (IOException | ClassNotFoundException e){
					System.out.println(e);
			}
		}
		return null;
	}
		
    public void addObserver(Observer o) {
	    lob.add(o);
    }

    public void removeObserver(Observer o) {
	    lob.remove(o);
    }

	@Override
	public Object get() {

		Object dados[]=new Object[4];

		dados[0]=_tabuleiro;
		dados[1]=_tabuleiro2;
		dados[2]=new Integer(_ataques);
		dados[3]=new Integer(_vez);
		return dados;
	}

}
