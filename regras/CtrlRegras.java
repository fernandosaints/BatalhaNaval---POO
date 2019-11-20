package regras;

import gui.Armas;

import java.util.ArrayList;
import java.util.List;

public class CtrlRegras implements Observable{

	private int tabuleiro[][];
	int vez=5;
    private static CtrlRegras control = null; //SINGLETON?
	String jogador1,jogador2;
    List<Observer> lob=new ArrayList<Observer>();
	
	public CtrlRegras() {
        this.tabuleiro = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.tabuleiro[i][j] = 0;
            }
        }
    }

    public Object get(){
	    /*if(control == null)
	        control = new CtrlRegras();
	    return control;*/
		Object data[] = new Object[4];
		String jogs[] = new String[2];
		jogs[0] = jogador1;
		jogs[1] = jogador2;

		data[0] = "regras";
		data[1] = tabuleiro;
		data[2] = vez;
		data[3] = jogs;
		return data;
	}

	public int[][] getMatriz() {
		return tabuleiro;
	}
	
	public void setValor(int i, int j){
		if (this.tabuleiro[j][i] == 0) {
		    //if(arma.getTipo == 1)
            this.tabuleiro[j][i] = vez;
            return;
        }

		getVez();
	}

	public int getVez() {
		if(vez == 5)
			vez = -1;
		else
			vez = 5;
		return vez;
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

    public void addObserver(Observer o) {
	    lob.add(o);
    }

    public void removeObserver(Observer o) {
	    lob.remove(o);
    }
}
