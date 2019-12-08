package gui;

import regras.Fachada;
import regras.Observable;
import regras.Observer;
import gui.Casa;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class PNNaval extends JPanel implements MouseListener, Observer, KeyListener, ActionListener {
	private double xIni=30.0, yIni=90.0, larg=25.0, alt=25.0, espLinha=5.0;
	private int xAnt, yAnt, rotacao, clicado = 0;
	private boolean outroJogador = false, segundoPronto = false, podeColocar = false;

	//Vetor de armas
	private Arma[] armas = new Arma[15];
	
	private Fachada ctrl;
	private Casa[][] tabuleiro = new Casa[15][15];
	
	//Linhas e colunas do tabuleiro
	private Line2D.Double linha[]=new Line2D.Double[16];
	private Line2D.Double coluna[]=new Line2D.Double[16];

	Observable obs;
	Object lob[];
	
	private String[] jogadores;

	private JPanel batalha;
	private JButton pronto = new JButton("Tabuleiro Pronto!");

	public PNNaval(Fachada fachada) {
		setLayout(new BorderLayout());
		ctrl = fachada;
		//tabuleiro = ctrl.getTabuleiro();
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		//jogadores = ctrl.getJogadores();
		pronto.addActionListener(this);
		criaArmas();
		desenhaArmas();

		//CRIA LINHAS E COLUNAS
		for(int i=0; i < 16; i++){
			linha[i] = new Line2D.Double(xIni+700, yIni+(i*(alt+espLinha)), xIni+700 + (15*(larg+espLinha)), yIni+(i*(alt+espLinha)));
			coluna[i] = new Line2D.Double(xIni+700 + (i*(larg+espLinha)), yIni , xIni+700 + (i*(larg+espLinha)), yIni+(15*(alt+espLinha)));
		}

		Fachada.getFachada().register(this);
		inicializa();
	}

	private void inicializa() {
		lob=null;
		tabuleiro=Fachada.getFachada().getTabuleiro();
		jogadores=Fachada.getFachada().getJogadores();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;

		if(!segundoPronto){
			pronto.setBounds(500,620,200,40);
			pronto.setEnabled(false);
			add(pronto);
			addArmas();
			if(ctrl.getArmasNoTabuleiro() >= 1) {
				pronto.setEnabled(true);
			}

			g2d.setStroke(new BasicStroke(2.0f,
					BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f));

			g2d.setPaint(Color.black);

			for(int i=0;i<16;i++) {
				g2d.draw(linha[i]);
				g2d.draw(coluna[i]);
			}

			//Desenha nome dos jogadores
			if(!outroJogador)
				g2d.drawString(jogadores[0]+", selecione uma arma na lista.", 500, 600);
			else
				g2d.drawString(jogadores[1]+", selecione uma arma na lista.", 500, 600);

			//Desenha Letras e numeros
			String letras = "ABCDEFGHIJKLMNO";
			for(int i=0; i < 15 ; i++) {
				g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni+685), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)((xIni + 700) + (i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));
			}

			for(int i=0; i < 15; i++) {
				for(int j=0; j < 15; j++) {
					if(tabuleiro[i][j].getArma()!=null){
						int xint = (int) (tabuleiro[i][j].getX()+(espLinha/2));
						int yint = (int) (tabuleiro[i][j].getY()+(espLinha/2));
						g2d.setPaint(tabuleiro[i][j].getArma().getCor());
						rt=new Rectangle2D.Double(xint,yint,larg+1,alt+1);
						g2d.fill(rt);
					}
				}
			}
		}
		else{
			for(int i = 0; i<armas.length; i++) {
				armas[i].setLocation(10000, 10000);
			}

		}
	}
	
	public void desenhaArmas() {
		int x = 45,y = 90;
		for(int i = 0; i<15;i++) {
			if(!armas[i].getNoTabuleiro()) {
				if(i>=0 && i<5) {
					armas[i].setBounds(x,y,90,90);
					x+=90;
				}
				if(i==5) {
					x = 45;
					y = 190;
				}
				if(i>=5 && i<9) {
					armas[i].setBounds(x,y,30,30);
					x+=50;
				}
				if(i==9) {
					x = 45;
					y = 265;
				}
				if(i>=9 && i<12) {
					armas[i].setBounds(x,y,60,60);
					x+=75;
				}
				if(i==12) {
					x = 45;
					y = 340;
				}
				if(i>=12 && i<14) {
					armas[i].setBounds(x, y, 120, 120);
					x+=125;
				}
				if(i==14) {
					x = 45;
					y = 415;
					armas[14].setBounds(x,y,150,150);
				}
			}
		}
	}
	
	public void addArmas() {
		for(int i = 0; i<armas.length; i++) {
			if(!armas[i].getNoTabuleiro()) {
				this.add(armas[i],i);
			}
			else {
				armas[i].setLocation(10000,10000);
			}
		}
	}
	
	//PINTANDO ARMA SEMPRE NO QUADRADO DA ESQUERDA
	public void mouseClicked(MouseEvent e) {
		double x = e.getX(), y = e.getY();
		int idsArmasTabuleiro;
		int id;
		boolean temCinza = false;
		
		//Coordenadas limites do tabuleiro
//		x = 730 - 1180
//		y = 90 - 540
		if(e.getButton() == MouseEvent.BUTTON1 && (x<730 || x>1180)) {
			for(int i=0; i < 15; i++) {
				for(int j=0; j < 15; j++) {
					if(tabuleiro[i][j].getArma()!=null){
						if(tabuleiro[i][j].getArma().getCor() == Color.GRAY || tabuleiro[i][j].getArma().getCor() == Color.RED) {
							temCinza = true;
							break;
						}
					}
				}
			}
			
			if(!temCinza) {
				//RESETA A COR DE UMA ARMA AO CLICAR EM OUTRA
				for(int i=0;i<armas.length;i++) {
					armas[i].setCor(armas[i].getCorOriginal());
					armas[i].repaintArma();
				}
				//SELECIONA OS HIDRO AVIOES
				for(int i=0;i<5;i++) {
					if(armas[i].getRotacao() == 0) {
						if((x>=45+(i*90) && x<70+(i*90)) && (y>=115 && y<140)) {
							armas[i].setCor(Color.GRAY);
						}
					}
					else if(armas[i].getRotacao() == 1) {
						if((x>=45+i*90 && x<70+i*90) && (y>=90 && y<115)) {
							armas[i].setCor(Color.GRAY);
						}
					}
					else if(armas[i].getRotacao() == 2) {
						if((x>=95+i*90 && x<120+i*90) && (y>=90 && y<115)) {
							armas[i].setCor(Color.GRAY);
						}
					}
					else if(armas[i].getRotacao() == 3) {
						if((x>=70+i*90 && x<95+i*90) && (y>=140 && y<165)) {
							armas[i].setCor(Color.GRAY);
						}
					}
				}
	
				//SELECIONA ARMA DE UMA CASAs
				for(int i = 5;i<9;i++) {
					if(((x>=45+((i-5)*50)) && (x<70+((i-5)*50))) && (y>=190 && y<215)){
						armas[i].setCor(Color.GRAY);
					}
				}
				
				//SELECIONA ARMA DE DUAS CASAs
				for(int i = 9;i<12;i++) {
					if(((x>=45+((i-9)*75)) && (x<70+((i-9)*75))) && (y>=265 && y<290)){
						armas[i].setCor(Color.GRAY);
					}
				}
				
				//SELECIONA ARMA DE TRES CASAs
				for(int i = 12;i<14;i++) {
					if(((x>=45+((i-12)*125)) && (x<70+((i-12)*125))) && (y>=340 && y<365)){
						armas[i].setCor(Color.GRAY);
					}
				}
				
				//SELECIONA ARMA DE QUATRO CASAs
				if((x>=45 && x<70) && (y>=415 && y<440)) {
					armas[14].setCor(Color.GRAY);
				}
			}
			
		}
		
		//ROTACIONA A ARMA SELECIONADA
		if(e.getButton() == MouseEvent.BUTTON3) {
			for(int i = 0;i<armas.length;i++) {
				if(armas[i].getCor() == Color.GRAY) {
					armas[i].setRotacao();
					armas[i].repaintArma();
				}
			}
		}
		
		//CLICAR NO TABULEIRO
		if(e.getButton() == MouseEvent.BUTTON1 && (x>=730 && x<=1180) && (y>=90 && y<=540) ) {
			for(int i = 0;i<armas.length;i++) {
				if(armas[i].getCor() == Color.GRAY || armas[i].getCor() == Color.RED) {
					for(int j=0;j<15;j++) {
						for(int k=0;k<15;k++) {
							//VERIFICA QUAL CASA FOI CLICADA DO TABULEIRO
							if((x>tabuleiro[j][k].getX() && x<tabuleiro[j][k].getX()+25) && (y>tabuleiro[j][k].getY() && y<tabuleiro[j][k].getY()+25)){
								//SE A ARMA SELECIONADA FOI O HIDROAVIAO
								if(armas[i].getTipo() == 1) {
									if(armas[i].getRotacao() == 0) {
										//VERIFICA SE � POSSIVEL COLOCAR A ARMA NO TABULEIRO
										if(j-1<15 && j-1>=0 && k+2<15 && k+2>=0){
											tabuleiro[j][k].setArma(armas[i]);
											id = armas[i].getId();
											idsArmasTabuleiro = tabuleiro[j][k].getArma().getId();
											if(id == idsArmasTabuleiro && !armas[i].getNoTabuleiro()) {
											    xAnt=j;
	                                            yAnt=k;
	                                            rotacao = armas[i].getRotacao();
	                                            podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
	                                            if(!podeColocar) {
	                                            	armas[i].setCor(Color.RED);
	                                            }
	                                            else {
	                                            	armas[i].setCor(Color.GRAY);
	                                            }
	                                            if(tabuleiro[j-1][k+1].getArma()==null && tabuleiro[j][k+2].getArma()==null) {
		                                            tabuleiro[j][k].setArma(armas[i]);
													tabuleiro[j-1][k+1].setArma(armas[i]);
													tabuleiro[j][k+2].setArma(armas[i]);
													armas[i].setNoTabuleiro();
	                                            }
	                                            else {
	                                            	tabuleiro[j][k].removeArma();
	                                            }
												
											}
											else {
												if(id == idsArmasTabuleiro && armas[i].getNoTabuleiro()) {
													if(tabuleiro[j-1][k+1].getArma()==null && tabuleiro[j][k+2].getArma()==null) {
			                                            tabuleiro[j][k].setArma(armas[i]);
														tabuleiro[j-1][k+1].setArma(armas[i]);
														tabuleiro[j][k+2].setArma(armas[i]);
														armas[i].setNoTabuleiro();
													}
													else {
		                                            	tabuleiro[j][k].removeArma();
		                                            }
													
													Verifica.removeArmas(armas[i], xAnt, yAnt, rotacao, tabuleiro);
												    xAnt=j;
		                                            yAnt=k;
		                                            rotacao = armas[i].getRotacao();
		                                            podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
		                                            if(!podeColocar) {
		                                            	armas[i].setCor(Color.RED);
		                                            }
		                                            else {
		                                            	armas[i].setCor(Color.GRAY);
		                                            }
												}
											}
										}
									}
									else if(armas[i].getRotacao() == 1) {
										//VERIFICA SE � POSSIVEL COLOCAR A ARMA NO TABULEIRO
										if(j+2<15 && k+1<15){
											tabuleiro[j][k].setArma(armas[i]);
											id = armas[i].getId();
											idsArmasTabuleiro=tabuleiro[j][k].getArma().getId();
											if(id == idsArmasTabuleiro && armas[i].getNoTabuleiro()) {
												if(tabuleiro[j+1][k+1].getArma()==null && tabuleiro[j+2][k].getArma()==null) {
													tabuleiro[j][k].setArma(armas[i]);
													tabuleiro[j+1][k+1].setArma(armas[i]);
													tabuleiro[j+2][k].setArma(armas[i]);
													armas[i].setNoTabuleiro();
												}
												else {
													tabuleiro[j][k].removeArma();
												}
		                                        Verifica.removeArmas(armas[i], xAnt, yAnt, rotacao, tabuleiro);
		                                        xAnt=j;
		                                        yAnt=k;
		                                        rotacao = armas[i].getRotacao();
		                                        podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
		                                        if(!podeColocar) {
	                                            	armas[i].setCor(Color.RED);
	                                            }
	                                            else {
	                                            	armas[i].setCor(Color.GRAY);
	                                            }
											}
											else {
												if(id == idsArmasTabuleiro  && !armas[i].getNoTabuleiro()) {
													xAnt=j;
			                                        yAnt=k;
			                                        rotacao = armas[i].getRotacao();
			                                        podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
			                                        if(!podeColocar) {
		                                            	armas[i].setCor(Color.RED);
		                                            }
		                                            else {
		                                            	armas[i].setCor(Color.GRAY);
		                                            }
			                                        if(tabuleiro[j+1][k+1].getArma()==null && tabuleiro[j+2][k].getArma()==null) {
														tabuleiro[j][k].setArma(armas[i]);
														tabuleiro[j+1][k+1].setArma(armas[i]);
														tabuleiro[j+2][k].setArma(armas[i]);
														armas[i].setNoTabuleiro();
													}
													else {
														tabuleiro[j][k].removeArma();
													}
												}
											}
											
										}
									}
									else if(armas[i].getRotacao() == 2) {
										//VERIFICA SE � POSSIVEL COLOCAR A ARMA NO TABULEIRO
										if(j+1<15 && k+2<15){
											tabuleiro[j][k].setArma(armas[i]);
											id = armas[i].getId();
											idsArmasTabuleiro=tabuleiro[j][k].getArma().getId();
											if(id == idsArmasTabuleiro && armas[i].getNoTabuleiro()) {
												if(tabuleiro[j+1][k+1].getArma()==null && tabuleiro[j][k+2].getArma()==null) {
			                                        tabuleiro[j][k].setArma(armas[i]);
													tabuleiro[j+1][k+1].setArma(armas[i]);
													tabuleiro[j][k+2].setArma(armas[i]);
												}
												else {
													tabuleiro[j][k].removeArma();
												}
												Verifica.removeArmas(armas[i], xAnt, yAnt, rotacao, tabuleiro);
												xAnt=j;
												yAnt=k;
												rotacao = armas[i].getRotacao();
												podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
												if(!podeColocar) {
	                                            	armas[i].setCor(Color.RED);
	                                            }
	                                            else {
	                                            	armas[i].setCor(Color.GRAY);
	                                            }
											}
											else {
												if(id == idsArmasTabuleiro && !armas[i].getNoTabuleiro()) {
													xAnt=j;
			                                        yAnt=k;
			                                        rotacao = armas[i].getRotacao();
			                                        podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
			                                        if(!podeColocar) {
		                                            	armas[i].setCor(Color.RED);
		                                            }
		                                            else {
		                                            	armas[i].setCor(Color.GRAY);
		                                            }
			                                        
			                                        if(tabuleiro[j+1][k+1].getArma()==null && tabuleiro[j][k+2].getArma()==null) {
														tabuleiro[j][k].setArma(armas[i]);
														tabuleiro[j+1][k+1].setArma(armas[i]);
														tabuleiro[j][k+2].setArma(armas[i]);
														armas[i].setNoTabuleiro();
													}
			                                        else {
			                                        	tabuleiro[j][k].removeArma();
			                                        }
												}
											}
												
										}
									}
									else if(armas[i].getRotacao() == 3) {
										//VERIFICA SE � POSSIVEL COLOCAR A ARMA NO TABULEIRO
										if(j+2<15 && k-1<15){
											tabuleiro[j][k].setArma(armas[i]);
											id = armas[i].getId();
											idsArmasTabuleiro=tabuleiro[j][k].getArma().getId();
											if(id == idsArmasTabuleiro && armas[i].getNoTabuleiro()) {
												if(tabuleiro[j+1][k-1].getArma()==null && tabuleiro[j+2][k].getArma()==null) {
													tabuleiro[j][k].setArma(armas[i]);
													tabuleiro[j+1][k-1].setArma(armas[i]);
													tabuleiro[j+2][k].setArma(armas[i]);
												}
												else {
													tabuleiro[j][k].removeArma();
												}
												Verifica.removeArmas(armas[i], xAnt, yAnt, rotacao, tabuleiro);
												xAnt=j;
												yAnt=k;
												rotacao = armas[i].getRotacao();
												podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
												if(!podeColocar) {
	                                            	armas[i].setCor(Color.RED);
	                                            }
	                                            else {
	                                            	armas[i].setCor(Color.GRAY);
	                                            }
											}
											else {
												if(id == idsArmasTabuleiro && !armas[i].getNoTabuleiro()) {
													armas[i].setNoTabuleiro();
													xAnt=j;
													yAnt=k;
													rotacao = armas[i].getRotacao();
													podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
													if(!podeColocar) {
		                                            	armas[i].setCor(Color.RED);
		                                            }
		                                            else {
		                                            	armas[i].setCor(Color.GRAY);
		                                            }
													if(tabuleiro[j+1][k-1].getArma()==null && tabuleiro[j+2][k].getArma()==null) {
														tabuleiro[j][k].setArma(armas[i]);
														tabuleiro[j+1][k-1].setArma(armas[i]);
														tabuleiro[j+2][k].setArma(armas[i]);
													}
													else {
														tabuleiro[j][k].removeArma();
													}
												}
											}
											
										}
									}
									
								}
								else {
									if(armas[i].getRotacao()==0 || armas[i].getRotacao()==2) {
										//VERIFICA SE � POSSIVEL COLOCAR A ARMA NO TABULEIRO55
										if(k+armas[i].getVida()<=15) {
											tabuleiro[j][k].setArma(armas[i]);
											id = armas[i].getId();
											idsArmasTabuleiro=tabuleiro[j][k].getArma().getId();
											if(id == idsArmasTabuleiro && armas[i].getNoTabuleiro()) {
												for(int z=0;z<armas[i].getVida();z++) {
													tabuleiro[j][k+z].setArma(armas[i]);
												}
												Verifica.removeArmas(armas[i], xAnt, yAnt, rotacao, tabuleiro);
												xAnt=j;
												yAnt=k;
												rotacao = armas[i].getRotacao();
												podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
												if(!podeColocar) {
	                                            	armas[i].setCor(Color.RED);
	                                            }
	                                            else {
	                                            	armas[i].setCor(Color.GRAY);
	                                            }
											}
											else {
												if(id == idsArmasTabuleiro && !armas[i].getNoTabuleiro()) {
													for(int z=0;z<armas[i].getVida();z++) {
														tabuleiro[j][k+z].setArma(armas[i]);
													}
													armas[i].setNoTabuleiro();
													xAnt=j;
													yAnt=k;
													rotacao = armas[i].getRotacao();
													podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
													if(!podeColocar) {
		                                            	armas[i].setCor(Color.RED);
		                                            }
		                                            else {
		                                            	armas[i].setCor(Color.GRAY);
		                                            }
												}
											}
										}
									}
									else {
										//VERIFICA SE � POSSIVEL COLOCAR A ARMA NO TABULEIRO
										if(j+armas[i].getVida()<=15) {
											tabuleiro[j][k].setArma(armas[i]);
											id = armas[i].getId();
											idsArmasTabuleiro=tabuleiro[j][k].getArma().getId();
											if(id == idsArmasTabuleiro && armas[i].getNoTabuleiro()) {
												for(int z=0;z<armas[i].getVida();z++) {
													tabuleiro[j+z][k].setArma(armas[i]);
												}
												Verifica.removeArmas(armas[i], xAnt, yAnt, rotacao, tabuleiro);
												xAnt=j;
												yAnt=k;
												rotacao = armas[i].getRotacao();
												podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
												if(!podeColocar) {
	                                            	armas[i].setCor(Color.RED);
	                                            }
	                                            else {
	                                            	armas[i].setCor(Color.GRAY);
	                                            }
											}
											else {
												if(id == idsArmasTabuleiro && !armas[i].getNoTabuleiro()) {
													armas[i].setNoTabuleiro();
													for(int z=0;z<armas[i].getVida();z++) {
														tabuleiro[j+z][k].setArma(armas[i]);
													}
													armas[i].setNoTabuleiro();
													xAnt=j;
													yAnt=k;
													rotacao = armas[i].getRotacao();
													podeColocar = Verifica.verificaBorda(j, k, armas[i], tabuleiro);
													if(!podeColocar) {
		                                            	armas[i].setCor(Color.RED);
		                                            }
		                                            else {
		                                            	armas[i].setCor(Color.GRAY);
		                                            }
												}
											}
										}
									}
								}
								repaint();
							}
						}
					}
				}	
			}	
		}
	}
		

	//Cria vetor de armas
   	public void criaArmas() {
     	for(int i=0;i<15;i++){
     		if(i<5) {
     			armas[i] = new Arma(1,i);
     		}
     		else if(i>=5 && i<9) {
     			armas[i] = new Arma(2,i);
     		}
     		else if(i>=9 && i<12) {
     			armas[i] = new Arma(3,i);
     		}
     		else if(i>=12 && i<14) {
     			armas[i] = new Arma(4,i);
     		}
     		else {
     			armas[i] = new Arma(5,i);
     		}
     	}
   	}


	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void notify(Observable o) {
		obs=o;
		lob=(Object []) obs.get();
		tabuleiro=(Casa[][]) lob[0];
	}

	
	public void actionPerformed(ActionEvent e) {
		if(clicado == 1) {
			segundoPronto = true;
			this.remove(pronto);
			batalha = new Batalha(ctrl);
			this.add(batalha);
			this.revalidate();
		}
		else {
			clicado++;
			outroJogador = true;
			tabuleiro = ctrl.getTabuleiro2();
			ctrl.resetaArmasNoTabuleiro();
			for(int i=0;i<armas.length;i++) {
				if(i<5) {
					armas[i] = new Arma(1,i+15);
				}
				else if(i>=5 && i<9) {
					armas[i] = new Arma(2,i+15);
				}
				else if(i>=9 && i<12) {
					armas[i] = new Arma(3,i+15);
				}
				else if(i>=12 && i<14) {
					armas[i] = new Arma(4,i+15);
				}
				else {
					armas[i] = new Arma(5,i+15);
				}
			}
			desenhaArmas();
			repaint();
		}	
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			for(int i = 0;i<armas.length;i++) {
				if(armas[i].getCor() == Color.GRAY) {
					armas[i].setCor(armas[i].getCorOriginal());
					if(armas[i].getNoTabuleiro())
						ctrl.setArmasNoTabuleiro();
				}
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		}
		
	}
