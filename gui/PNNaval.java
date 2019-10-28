package gui;

import javafx.stage.PopupWindow;
import regras.CtrlRegras;
import regras.Observable;
import regras.Observer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class PNNaval extends JPanel implements MouseListener, Observer {
	double xIni=30.0,yIni=90.0,larg=25.0,alt=25.0,espLinha=5.0;
	int iClick,jClick;
	Celula tab1[][]=new Celula[15][15];
	Celula tab2[][]=new Celula[15][15];

	Line2D.Double lines1[]=new Line2D.Double[32];
	Line2D.Double lines2[]=new Line2D.Double[32];
	CtrlRegras ctrl;
	String[] jogadores;

	JTextField nome1 = new JTextField(20);
	JTextField nome2 = new JTextField(20);

	JButton novo_jogo = new JButton("Novo Jogo");
	JButton iniciar = new JButton("Iniciar");
	JButton pronto = new JButton("Tabuleiro Pronto!");

	public PNNaval(CtrlRegras c) {
		double x=xIni,y=yIni;
		ctrl=c;

		for(int i=0;i<15;i++) {
			x=xIni;
			for(int j=0;j<15;j++) {
				tab1[i][j]=new Celula(x,y);
				x+=larg+espLinha;
			}
			y+=alt+espLinha;
		}

		y = yIni;
		for(int i=0;i<15;i++) {
			x = (xIni+700);
			for (int j = 0; j < 15; j++) {
				tab2[i][j] = new Celula(x, y);
				x += larg + espLinha;
			}
			y += alt + espLinha;
		}

		addMouseListener(this);

		for(int i=0; i < 16; i++){
			lines1[i] = new Line2D.Double(xIni + (i*(larg+espLinha)), yIni , xIni + (i*(larg+espLinha)), yIni+(15*(alt+espLinha)));
			lines2[i] = new Line2D.Double(xIni+700 + (i*(larg+espLinha)), yIni , xIni+700 + (i*(larg+espLinha)), yIni+(15*(alt+espLinha)));
		}
		for(int i=0; i < 16; i++) {
			lines1[i+16]= new Line2D.Double(xIni,yIni +(i*(alt+espLinha)), xIni+(15*(larg+espLinha)), yIni + (i*(alt+espLinha)));
			lines2[i+16]= new Line2D.Double(xIni+700,yIni +(i*(alt+espLinha)), xIni+700 + (15*(larg+espLinha)), yIni + (i*(alt+espLinha)));
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		int mat[][] = ctrl.getMatriz(1);
		int mat2[][] = ctrl.getMatriz(2);
		int vez = ctrl.getVez();
		jogadores = ctrl.getJogadores();

		if(jogadores[0].compareTo("") == 0 || jogadores[1].compareTo("") == 0){
			g2d.drawString("Jogador 1:", 500, 250);
			g2d.drawString("Jogador 2:", 500, 300);
			nome1.setBounds(550,260,100,25);
			nome1.setFont(nome1.getFont().deriveFont(15f));

			nome2.setBounds(550,310,100,25);
			nome2.setFont(nome1.getFont().deriveFont(15f));

			iniciar.addActionListener(new StartButton());
			iniciar.setBounds(525,360,100,40);
			add(nome1);
			add(nome2);
			add(iniciar);

		}
		else{
			remove(iniciar);
			remove(nome1);
			remove(nome2);
			pronto.addActionListener(new ReadyButton());
			pronto.setBounds(500,620,200,40);
			pronto.setEnabled(false);
			add(pronto);

			int count = 0;
			int posX = 70;
			int posY = 90;
			for(int j=0;j<5;j++){
				for(int i=0;i<3;i++){
					g2d.setPaint(Color.green);
					if(i == 2)
						rt=new Rectangle2D.Double(posX+count,posY+count,larg+1,alt+1);
					else
						rt=new Rectangle2D.Double(posX-count,posY+count,larg+1,alt+1);
					count=25;
					g2d.fill(rt);
				}
				posX+=90;
				count=0;
			}

			posX=45;
			posY=190;
			for(int j=0;j<4;j++){
				g2d.setPaint(Color.blue);
				rt=new Rectangle2D.Double(posX,posY,larg+1,alt+1);
				g2d.fill(rt);
				posX+=50;
			}

			posX=45;
			posY=265;
			for(int j=0;j<3;j++){
				g2d.setPaint(Color.yellow);
				rt=new Rectangle2D.Double(posX,posY,2*larg+1,alt+1);
				g2d.fill(rt);
				posX+=100;
			}

			posX=45;
			posY=340;
			for(int j=0;j<2;j++){
				g2d.setPaint(Color.orange);
				rt=new Rectangle2D.Double(posX,posY,4*larg+1,alt+1);
				g2d.fill(rt);
				posX+=200;
			}

			posX=45;
			posY=415;
			g2d.setPaint(Color.magenta);
			rt=new Rectangle2D.Double(posX,posY,5*larg+1,alt+1);
			g2d.fill(rt);


			g2d.setStroke(new BasicStroke(2.0f,
					BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER,
					10.0f));

			g2d.setPaint(Color.black);

			for(int i=0;i<32;i++) {
				//g2d.draw(lines1[i]);
				g2d.draw(lines2[i]);
			}

			//g2d.drawString(jogadores[0], (int)(xIni+(7.5*(larg+espLinha))), 510);
			//g2d.drawString(jogadores[1]+", selecione uma arma na lista.", (int)((xIni+700)+(7.5*(larg+espLinha))), 510);
			g2d.drawString(jogadores[0]+", selecione uma arma na lista.", 500, 600);

			String letras = "ABCDEFGHIJKLMNO";

			for(int i=0; i < 15 ; i++) {
				//g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni-15), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				//g2d.drawString(String.valueOf(i),(int)(xIni +(i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));
				g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni+685), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)((xIni + 700) + (i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));
			}

			for(int i=0; i < 15; i++) {
				for(int j=0; j < 15; j++) {
					/*if(mat[i][j]!=0) {
						g2d.setPaint(Color.green);
						rt=new Rectangle2D.Double(tab1[i][j].x+(espLinha/2),tab1[i][j].y+(espLinha/2),larg+1,alt+1);
						g2d.fill(rt);
					}*/
					if(mat2[i][j]!=0){
						g2d.setPaint(Color.red);
						rt=new Rectangle2D.Double(tab2[i][j].x+(espLinha/2),tab2[i][j].y+(espLinha/2),larg+1,alt+1);
						g2d.fill(rt);
					}
				}
			}
		}

	}
	
	public void mouseClicked(MouseEvent e) {
		Rectangle2D rt;
		int x = e.getX(), y = e.getY();

		y-=yIni;
		/*if ((x > xIni && x < xIni + 15*(larg+espLinha)) && (y > 0 && y < 15*(alt+espLinha))) {
			x-=xIni;
			ctrl.setValor((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
			repaint();
		}*/
		if ((x > (xIni + 700) && x < (xIni + 700) + 15*(larg+espLinha)) && (y > 0 && y < 15*(alt+espLinha))) {
			x-=(xIni+700);
			ctrl.setValor2((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
			repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	@Override
	public void notify(Observable o) {

	}

	class StartButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ctrl.setJogadores(nome1.getText(), nome2.getText());
			jogadores = ctrl.getJogadores();
			repaint();
		}
	}

	class ReadyButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}
}
