package regras;

import java.util.ArrayList;
import java.util.List;

public class CtrlRegras implements Observable{

	int tabuleiro1[][];
	int tabuleiro2 [][];
	int vez=5;
	int verifica = 0;
    private static CtrlRegras control = null; //SINGLETON
	String jogador1,jogador2;
    List<Observer> lob=new ArrayList<Observer>();
	
	public CtrlRegras() {
        this.tabuleiro1 = new int[15][15];
        this.tabuleiro2 = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.tabuleiro1[i][j] = 0;
                this.tabuleiro2[i][j] = 0;
            }
        }
        this.jogador1 = "";
        this.jogador2 = "";
    }

    public CtrlRegras get(){
	    if(control == null)
	        control = new CtrlRegras();
	    return control;
    }

	public int[][] getMatriz(int n) {
		if(n==1) {
			return tabuleiro1;
		}
		return tabuleiro2;
	}
	
	public void setValor(int i, int j, int numTab){
		if(numTab == 1) {
			if (this.tabuleiro1[j][i] == 0) {
				this.tabuleiro1[j][i] = vez;
				return;
			}
		}
		else {
			if (this.tabuleiro2[j][i] == 0) {
				this.tabuleiro2[j][i] = vez;
				return;
			}
		}
		getVez();
	}

	public void setValor2(int i, int j){
		if (this.tabuleiro2[j][i] == 0) {
			this.tabuleiro2[j][i] = vez;
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

	public int getVerifica() {
		if(verifica == 0)
			verifica = 1;
		else
			verifica = 0;
		return verifica;
	}

    public void addObserver(Observer o) {
	    lob.add(o);
    }

    public void removeObserver(Observer o) {
	    lob.remove(o);
    }
}
