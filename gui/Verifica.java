package gui;

public class Verifica {
	static int total = 0;
	public static void removeArmas(Arma arma, int xAnt,  int yAnt, int rotacao, Casa[][] tabuleiro) {
		if(arma.getTipo()==1) {
			if(rotacao == 0){
				tabuleiro[xAnt][yAnt].removeArma();
				tabuleiro[xAnt-1][yAnt+1].removeArma();
				tabuleiro[xAnt][yAnt+2].removeArma();
			}
			else if(rotacao == 1) {
				tabuleiro[xAnt][yAnt].removeArma();
				tabuleiro[xAnt+1][yAnt+1].removeArma();
				tabuleiro[xAnt+2][yAnt].removeArma();
			}
			else if(rotacao == 2) {
				tabuleiro[xAnt][yAnt].removeArma();
                tabuleiro[xAnt+1][yAnt+1].removeArma();
                tabuleiro[xAnt][yAnt+2].removeArma();
			}
			else if(rotacao == 3) {
				tabuleiro[xAnt][yAnt].removeArma();
				tabuleiro[xAnt+1][yAnt-1].removeArma();
				tabuleiro[xAnt+2][yAnt].removeArma();
			}
		}
		else {
			if(rotacao == 0 || rotacao == 2) {
				for(int z=0;z<arma.getVida();z++) {
					tabuleiro[xAnt][yAnt+z].removeArma();
				}
			}
			else {
				for(int z=0;z<arma.getVida();z++) {
					tabuleiro[xAnt+z][yAnt].removeArma();
				}
			}
		}
	}
	
	public static boolean verificaBorda(int linha, int coluna, Arma arma, Casa[][] tabuleiro) {
		int tamanho = arma.getVida();
		int rotacao = arma.getRotacao();
		total = 0;
		//RESOLVER COORDENADAS E TRATAR BORDA
		if(arma.getTipo() == 1) {
			if(rotacao == 0) {
				verifica(tabuleiro,linha-1,coluna);
				verifica(tabuleiro,linha+1,coluna);
				verifica(tabuleiro,linha,coluna-1);
				verifica(tabuleiro,linha,coluna+1);
				verifica(tabuleiro,linha-1,coluna+2);
				verifica(tabuleiro,linha-2,coluna+1);
				verifica(tabuleiro,linha+1,coluna+2);
				verifica(tabuleiro,linha,coluna+3);
				//Diagonais primeiro quadrado
				verifica(tabuleiro,linha-1,coluna-1);
				verifica(tabuleiro,linha+1,coluna-1);
				verifica(tabuleiro,linha+1,coluna+1);
				//Diagonais segundo quadrado
				verifica(tabuleiro,linha-2,coluna);
				verifica(tabuleiro,linha-2,coluna+2);
				//Diagonais terceiro quadrado
				verifica(tabuleiro,linha-1,coluna+3);
				verifica(tabuleiro,linha+1,coluna+3);
			}
			if(rotacao == 1) {
				verifica(tabuleiro,linha-1,coluna);
				verifica(tabuleiro,linha+1,coluna);
				verifica(tabuleiro,linha,coluna-1);
				verifica(tabuleiro,linha,coluna+1);
				verifica(tabuleiro,linha+1,coluna+2);
				verifica(tabuleiro,linha+2,coluna+1);
				verifica(tabuleiro,linha+2,coluna-1);
				verifica(tabuleiro,linha+3,coluna);
				//Diagonais primeiro quadrado
				verifica(tabuleiro,linha-1,coluna-1);
				verifica(tabuleiro,linha-1,coluna+1);
				verifica(tabuleiro,linha+1,coluna-1);
				//Diagonais segundo quadrado
				verifica(tabuleiro,linha,coluna+2);
				verifica(tabuleiro,linha+2,coluna+2);
				//Diagonais terceiro quadrado
				verifica(tabuleiro,linha+3,coluna-1);
				verifica(tabuleiro,linha+3,coluna+1);
			}
			if(rotacao == 2) {
				verifica(tabuleiro,linha-1,coluna);
				verifica(tabuleiro,linha+1,coluna);
				verifica(tabuleiro,linha,coluna-1);
				verifica(tabuleiro,linha,coluna+1);
				verifica(tabuleiro,linha+2,coluna+1);
				verifica(tabuleiro,linha+1,coluna+2);
				verifica(tabuleiro,linha-1,coluna+2);
				verifica(tabuleiro,linha,coluna+3);
				//Diagonais primeiro quadrado
				verifica(tabuleiro,linha-1,coluna-1);
				verifica(tabuleiro,linha-1,coluna+1);
				verifica(tabuleiro,linha+1,coluna-1);
				//Diagonais segundo quadrado
				verifica(tabuleiro,linha+2,coluna);
				verifica(tabuleiro,linha+2,coluna+2);
				//Diagonais terceiro quadrado
				verifica(tabuleiro,linha-1,coluna+3);
				verifica(tabuleiro,linha+1,coluna+3);

			}
			if(rotacao == 3) {
				//Superior e inferior
				verifica(tabuleiro,linha-1,coluna);
				verifica(tabuleiro,linha+1,coluna);
				//Esquerda e direita
				verifica(tabuleiro,linha,coluna-1);
				verifica(tabuleiro,linha,coluna+1);
				//Esquerda segundo quadrado
				verifica(tabuleiro,linha+1,coluna-2);
				//Esquerda e direita terceiro quadrado
				verifica(tabuleiro,linha+2,coluna-1);
				verifica(tabuleiro,linha+2,coluna+1);
				//Abaixo terceiro quadrado
				verifica(tabuleiro,linha+3,coluna);
				//Diagonais primeiro quadrado
				verifica(tabuleiro,linha-1,coluna-1);
				verifica(tabuleiro,linha-1,coluna+1);
				verifica(tabuleiro,linha+1,coluna+1);
				//Diagonais segundo quadrado
				verifica(tabuleiro,linha+2,coluna-2);
				verifica(tabuleiro,linha,coluna-2);
				//Diagonais terceiro quadrado
				verifica(tabuleiro,linha+3,coluna-1);
				verifica(tabuleiro,linha+3,coluna+1);
			}
		}
		else {
			if(rotacao == 0 || rotacao == 2) {
				for(int i=0;i<tamanho;i++) {
					verifica(tabuleiro,linha-1,coluna+i);
					verifica(tabuleiro,linha+1,coluna+i);
				}
				verifica(tabuleiro,linha,coluna-1);
				verifica(tabuleiro,linha+1,coluna-1);
				verifica(tabuleiro,linha-1,coluna-1);
				verifica(tabuleiro,linha,coluna+tamanho);
				verifica(tabuleiro,linha+1,coluna+tamanho);
				verifica(tabuleiro,linha-1,coluna+tamanho);
			}
			else {
				for(int i=0;i<tamanho;i++) {
					verifica(tabuleiro,linha+i,coluna+1);
					verifica(tabuleiro,linha+i,coluna-1);
				}
				verifica(tabuleiro,linha-1,coluna);
				verifica(tabuleiro,linha-1,coluna+1);
				verifica(tabuleiro,linha-1,coluna-1);
				verifica(tabuleiro,linha+tamanho,coluna);
				verifica(tabuleiro,linha+tamanho,coluna-1);
				verifica(tabuleiro,linha+tamanho,coluna+1);
			}
		}
		if(arma.getTipo()==1) {
			if(total == 15) {return true;}
		}
		if(arma.getTipo()==2) {
			if(total == 8) {return true;}
		}
		if(arma.getTipo()==3) {
			if(total == 10) {return true;}
		}
		if(arma.getTipo()==4) {
			if(total == 12) {return true;}
		}
		if(arma.getTipo()==5) {
			if(total == 14) {return true;}
		}
		return false;
	}
	
	public static void verifica(Casa[][] tabuleiro, int linha, int coluna) {
		try {
			if(tabuleiro[linha][coluna].getArma()==null) {total++;}
		}
		catch (Exception e) {
			total++;
		}
	}
}
