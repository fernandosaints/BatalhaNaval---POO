package gui;

import javax.swing.*;

import regras.Fachada;
import gui.Casa;
import regras.Observable;
import regras.Observer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class Batalha extends JPanel implements ActionListener, MouseListener, Observer{
    Color corDefault = UIManager.getColor ("Panel.background");
    
    //Variaveis do Fachada
    String [] jogadores;
    Casa[][] tabuleiro1, tabuleiro2;
	Observable obs;
    Object lob[];
    Fachada ctrl;
    
    //Linhas e colunas do tabuleiro
    Line2D.Double linhaTab1[]=new Line2D.Double[16];
  	Line2D.Double colunaTab1[]=new Line2D.Double[16];
  	Line2D.Double linhaTab2[]=new Line2D.Double[16];
  	Line2D.Double colunaTab2[]=new Line2D.Double[16];
  	
  	JButton passaAVez = new JButton("Passa a Vez");
  	JButton salva = new JButton("Salvar");
  	Object options[] = {"Reiniciar", "Fechar"};
  	String letras = "ABCDEFGHIJKLMNO";
  	
  	private int xIni = 30, yIni = 90, larg=25, alt=25, espLinha=5, ataques, vez, idArma;
  	List <Integer> idArmas = new ArrayList<Integer>();
  	
	public Batalha(Fachada fachada) {
		setLayout(new BorderLayout());
		this.setFocusable(true);
		ctrl = fachada;
		//jogadores = ctrl.getJogadores();
		//tabuleiro1 = ctrl.getTabuleiro();
		//tabuleiro2 = ctrl.getTabuleiro2();
		//ataques = ctrl.getAtaques();
		//vez = ctrl.getVez();
		passaAVez.addActionListener(this);
		addMouseListener(this);
		salva.addActionListener(this);
		
		//CRIA LINHAS E COLUNAS
			for(int i=0; i < 16; i++){
				linhaTab1[i] = new Line2D.Double(xIni+50, yIni+(i*(alt+espLinha)), xIni + 50 + (15*(larg+espLinha)), yIni+(i*(alt+espLinha)));
				colunaTab1[i] = new Line2D.Double(xIni + 50 + (i*(larg+espLinha)), yIni , xIni + 50 + (i*(larg+espLinha)), yIni+(15*(alt+espLinha)));
				linhaTab2[i] = new Line2D.Double(xIni+700, yIni+(i*(alt+espLinha)), xIni+700 + (15*(larg+espLinha)), yIni+(i*(alt+espLinha)));
				colunaTab2[i] = new Line2D.Double(xIni+700 + (i*(larg+espLinha)), yIni , xIni+700 + (i*(larg+espLinha)), yIni+(15*(alt+espLinha)));
			}
		Fachada.getFachada().register(this);
        inicializa();
	}

	private void inicializa() {
        lob=null;
        jogadores=Fachada.getFachada().getJogadores();
		tabuleiro1 = Fachada.getFachada().getTabuleiro();
		tabuleiro2 = Fachada.getFachada().getTabuleiro2();
        ataques=Fachada.getFachada().getAtaques();
        vez=Fachada.getFachada().getVez();
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		g2d.setStroke(new BasicStroke(2.0f,
				BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f));

		g2d.setPaint(Color.black);
		
		if(ctrl.getArmasTab1() == 0 || ctrl.getArmasTab2() == 0) {
			if(ctrl.getArmasTab1() == 0) {
				int resposta = JOptionPane.showOptionDialog(null, "O " + jogadores[1]+" venceu!", "VENCEDOR", JOptionPane.YES_NO_OPTION, 1, null, options, options[1]);
				if(resposta == 0) {
					ctrl.resetaFachada();
					this.remove(passaAVez);
					this.remove(salva);
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
					frame.dispose();
					new FRNaval(ctrl).setVisible(true);
				}
				else {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
					frame.dispose();
				}
					
			}
			else {
				int resposta = JOptionPane.showOptionDialog(null, "O " + jogadores[0]+" venceu!", "VENCEDOR", JOptionPane.YES_NO_OPTION, 1, null, options, options[1]);
				if(resposta == 0) {
					ctrl.resetaFachada();
					this.remove(passaAVez);
					this.remove(salva);
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
					frame.dispose();
					new FRNaval(ctrl).setVisible(true);
				}
				else {
					JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
					frame.dispose();
				}			
			}
			
		}
		salva.setBounds(900,620,200,40);
		salva.setEnabled(true);
		passaAVez.setBounds(525,620,200,40);
		passaAVez.setEnabled(false);
		ataques = ctrl.getAtaques();
		if(ataques == 0) {
			passaAVez.setEnabled(true);
		}
		add(salva);
		add(passaAVez);
		
		//Desenha linhas e colunas do tabuleiro
		if(vez == 0){
			for(int i=0;i<16;i++) {
				g2d.draw(linhaTab2[i]);
				g2d.draw(colunaTab2[i]);
			}
			for(int i=0; i < 15 ; i++) {
				g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni+685), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)((xIni + 700) + (i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));
			}
			//Frase de cima do tabuleiro
			g2d.drawString("Tabuleiro do jogador: " + jogadores[1], 890, 65);
			//Frase de baixo do tabuleiro
			g2d.drawString(jogadores[0] + ", voce tem " + ataques + " ataques disponiveis.", 525, 600);
		}
		else{
			for(int i=0;i<16;i++) {
				g2d.draw(linhaTab1[i]);
				g2d.draw(colunaTab1[i]);
				
			}
			for(int i=0; i < 15 ; i++) {
				g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni+35), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)((xIni + 50) + (i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));
			}
			//Frase de cima do tabuleiro
			g2d.drawString("Tabuleiro do jogador: " + jogadores[0], 230, 65);
			//Frase de baixo do tabuleiro
			g2d.drawString(jogadores[1] + ", voce tem " + ataques + " ataques disponiveis.", 525, 600);
		}
		
		if(vez == 0 && lob != null) {
		//Printa tabuleiro
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					if(tabuleiro2[i][j].getCor()!=null) {
						int xint = (int) (tabuleiro2[i][j].getX()+(espLinha/2));
						int yint = (int) (tabuleiro2[i][j].getY()+(espLinha/2));
						g2d.setPaint(tabuleiro2[i][j].getCor());
						rt=new Rectangle2D.Double(xint,yint,larg+1,alt+1);
						g2d.fill(rt);
					}
				}
			}
		}
		else if(vez != 0 && lob != null){
			for(int i=0;i<15;i++) {
				for(int j=0;j<15;j++) {
					if(tabuleiro1[i][j].getCor()!=null) {
						int xint = (int) (tabuleiro1[i][j].getX()-650+(espLinha/2));
						int yint = (int) (tabuleiro1[i][j].getY()+(espLinha/2));
						g2d.setPaint(tabuleiro1[i][j].getCor());
						rt=new Rectangle2D.Double(xint,yint,larg+1,alt+1);
						g2d.fill(rt);
					}
				}
			}
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salva) {
			ctrl.salvaTabuleiros();
		}
		else {
			ctrl.setVez();
			vez = ctrl.getVez();
			ctrl.setAtaques();
			ataques = ctrl.getAtaques();
			repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		
		//Pega posicoes do tabuleiro da esquerda
		if(e.getButton() == MouseEvent.BUTTON1 && (x>=80 && x<=530) && (y>=90 && y<=540) && vez==1 && ataques != 0) {
			ataques = ctrl.getAtaques();
			//Tabuleiro 1
			for(int j=0;j<15;j++) {
				for(int k=0;k<15;k++) {
					if((x>tabuleiro1[j][k].getX()-650 && x<tabuleiro1[j][k].getX()-625) && (y>tabuleiro1[j][k].getY() && y<tabuleiro1[j][k].getY()+25) && !tabuleiro1[j][k].getClicado()){
						ctrl.setAtaques();
						tabuleiro1[j][k].setClicado();
						if(tabuleiro1[j][k].getArma()!=null) {
							idArma = tabuleiro1[j][k].getArma().getId();
							tabuleiro1[j][k].getArma().dano();
							tabuleiro1[j][k].setCor(Color.RED);
						}
						else {
							tabuleiro1[j][k].setCor(Color.BLUE);
							break;
						}
					}
				}
			}
			
			for(int linha=0;linha<15;linha++) {
				for(int coluna=0;coluna<15;coluna++) {
					if(tabuleiro1[linha][coluna].getArma()!=null) {
							if(tabuleiro1[linha][coluna].getArma().getVida() == 0) {
								if(!idArmas.contains(tabuleiro1[linha][coluna].getArma().getId())){
									ctrl.setArmasTab1();
									idArmas.add(tabuleiro1[linha][coluna].getArma().getId());
								}
								tabuleiro1[linha][coluna].removeArma();
							}
					}
				}
			}
		}
		
		//Pega posicoes do tabuleiro da direita
		else if(e.getButton() == MouseEvent.BUTTON1 && (x>=730 && x<=1180) && (y>=90 && y<=540) && vez==0 && ataques != 0) {
			ataques = ctrl.getAtaques();
			//Tabuleiro 2
			for(int j=0;j<15;j++) {
				for(int k=0;k<15;k++) {
					if((x>tabuleiro2[j][k].getX() && x<tabuleiro2[j][k].getX()+25) && (y>tabuleiro2[j][k].getY() && y<tabuleiro2[j][k].getY()+25) && !tabuleiro2[j][k].getClicado()){
						ctrl.setAtaques();
						tabuleiro2[j][k].setClicado();
						if(tabuleiro2[j][k].getArma()!=null) {
							idArma = tabuleiro2[j][k].getArma().getId();
							tabuleiro2[j][k].getArma().dano();
							tabuleiro2[j][k].setCor(Color.RED);
						}
						else {
							tabuleiro2[j][k].setCor(Color.BLUE);
							break;
						}
					}
				}
			}
			
			for(int linha=0;linha<15;linha++) {
				for(int coluna=0;coluna<15;coluna++) {
					if(tabuleiro2[linha][coluna].getArma()!=null) {
						if(tabuleiro2[linha][coluna].getArma().getVida() == 0) {
							if(!idArmas.contains(tabuleiro2[linha][coluna].getArma().getId())){
								ctrl.setArmasTab2();
								idArmas.add(tabuleiro2[linha][coluna].getArma().getId());
							}
							tabuleiro2[linha][coluna].removeArma();
						}
					}
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void notify(Observable o) {
        obs=o;
        lob=(Object []) obs.get();
        tabuleiro1=(Casa[][]) lob[0];
        tabuleiro2=(Casa[][]) lob[1];
        ataques=(int) lob[2];
        vez=(int) lob[3];
        repaint();
    }

}
