package regras;

public class CtrlRegras {
	// 0: indica uma casa n�o preenchida
	// -1: indica uma casa preenchida com um ret�ngulo verde
	// 5:  indica uma casa preenchida com um ret�ngulo vermelho
	
	int tabuleiro [][]= {{0,0,0},{0,0,0},{0,0,0}};
	int tabuleiro2 [][]= {{0,0,0},{0,0,0},{0,0,0}};
	int vez=5;
	String jogador1,jogador2;
	
	public CtrlRegras() {
		this.tabuleiro = new int[15][15];
		this.tabuleiro2 = new int[15][15];
		for(int i=0; i < 15; i++){
			for(int j=0; j < 15; j++){
				this.tabuleiro[i][j] = 0;
				this.tabuleiro2[i][j] = 0;
			}
		}
		this.jogador1 = "";
		this.jogador2 = "";
	}

	public int[][] getMatriz(int n) {
		if(n==1) {
			return tabuleiro;
		}
		return tabuleiro2;
	}
	
	public void setValor(int i, int j){
		if (this.tabuleiro[j][i] == 0) {
			this.tabuleiro[j][i] = vez;
			return;
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
		if(vez == 5) {
			vez = -1;
		}
		else {
			vez = 5;
		}
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
}
