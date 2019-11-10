package gui;

import org.omg.CORBA.CODESET_INCOMPATIBLE;
import regras.CtrlRegras;
import regras.Fachada;
import regras.Observable;
import regras.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class PNNaval extends JPanel implements MouseListener, Observer {
	private int X,Y;
	double xIni=30.0,yIni=90.0,larg=25.0,alt=25.0,espLinha=5.0, var=0;
	int xAtual, yAtual, xPeca, yPeca;

	Color corDefault = UIManager.getColor ("Panel.background");

	Celula tab1[][]=new Celula[15][15];
	Celula tab2[][]=new Celula[15][15];

	Armas arma;
	Armas armas[];

	Line2D.Double lines1[]=new Line2D.Double[32];
	Line2D.Double lines2[]=new Line2D.Double[32];
	Fachada ctrl;
	String[] jogadores;

	JButton pronto = new JButton("Tabuleiro Pronto!");

	public PNNaval(Fachada f) {
		double x,y=yIni;
		ctrl=f;

		/*for(int i=0;i<15;i++) {
			x=xIni;
			for(int j=0;j<15;j++) {
				tab1[i][j]=new Celula(x,y);
				x+=larg+espLinha;
			}
			y+=alt+espLinha;
		}*/

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
			lines1[i + 16] = new Line2D.Double(xIni, yIni + (i * (alt + espLinha)), xIni + (15 * (larg + espLinha)), yIni + (i * (alt + espLinha)));
			lines2[i + 16] = new Line2D.Double(xIni + 700, yIni + (i * (alt + espLinha)), xIni + 700 + (15 * (larg + espLinha)), yIni + (i * (alt + espLinha)));
		}

		armas = new Armas[15];
		armas = criaArmas();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;

		int mat1[][] = ctrl.getMatriz(1);
		int mat2[][] = ctrl.getMatriz(2);
		jogadores = ctrl.getJogadores();

		pronto.addActionListener(new ReadyButton());
		pronto.setBounds(500,620,200,40);
		pronto.setEnabled(false);
		add(pronto);

		g2d.setStroke(new BasicStroke(2.0f,
				BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f));

		g2d.setPaint(Color.black);

		for(int i=0;i<32;i++) {
			//g2d.draw(lines1[i]);
			g2d.draw(lines2[i]);
		}

		//g2d.drawString(jogadores[0], (int)(xIni+(7.5*(larg+espLinha))), 510);
		g2d.drawString(jogadores[0]+", selecione uma arma na lista.", 500, 600);

		String letras = "ABCDEFGHIJKLMNO";

		for(int i=0; i < 15 ; i++) {
				/*g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni-15), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)(xIni +(i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));*/
				g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni+685), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)((xIni + 700) + (i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));
		}

		setArmas();

		for(int i=0; i < 15; i++) {
			for(int j=0; j < 15; j++) {
				/*if(mat1[i][j]!=0) {
					g2d.setPaint(Color.green);
					rt=new Rectangle2D.Double(tab1[i][j].x+(espLinha/2),tab1[i][j].y+(espLinha/2),larg+1,alt+1);
					g2d.fill(rt);
				}*/
				if(mat2[i][j]!=0){
					int xint = (int) (tab2[i][j].x+(espLinha/2));
					int yint = (int) (tab2[i][j].y+(espLinha/2));
					double qt;

					g2d.setPaint(Color.red);
					rt=new Rectangle2D.Double(xint,yint,larg+1,alt+1);
					g2d.fill(rt);

					/*for(int k=0;k<armas.length;k++){
						if(armas[k].getCor() == Color.gray){
							armas[k].setCor(Color.red);
							armas[k].setLocation(xint-2,yint-2);
							*//*qt = armas[c].getQuantidade();
							if(qt == 2.6) {
								setCores();
								armas[c].setLocation(xint,yint);
								//armas = removeArma(armas,i);
								//armas[c].remove(c);
							}*//*
						}
					}*/

					/*for(Armas el : armas){
						if(el.getCor() == Color.gray){
							el.remove(4);
						}
					}*/
				}
			}
		}

	}

	public Armas[] criaArmas(){

		int[][] hidro = new int[][]{{0,1,0},{1,0,1}};
		int[][] submarino = new int[][]{{1}};
		int[][] destroyer = new int[][]{{1,1}};
		int[][] cruzador = new int[][]{{1,1,1,1}};
		int[][] couracado = new int[][]{{1,1,1,1,1}};

		int i, x = 45,y = 90;
		for(i=0;i<5;i++){
			armas[i] = new Armas(hidro,Color.green);
			armas[i].setBounds(x,y,90,70);
			armas[i].setQuantidade(2.6);
			Movimento mv = new Movimento(armas[i]);
			x+=90;
		}
		x = 45;
		y = 190;
		for(i=5;i<9;i++){
			armas[i] = new Armas(submarino,Color.blue);
			armas[i].setBounds(x,y,30,30);
			armas[i].setQuantidade(1);
			Movimento mv = new Movimento(armas[i]);
			x+=50;
		}
		x = 45;
		y = 265;
		for(i=9;i<12;i++){
			armas[i] = new Armas(destroyer,Color.yellow);
			armas[i].setBounds(x,y,60,30);
			armas[i].setQuantidade(2);
			Movimento mv = new Movimento(armas[i]);
			x+=75;
		}
		x=45;
		y=340;
		for(i=12;i<14;i++) {
			armas[i] = new Armas(cruzador, Color.orange);
			armas[i].setBounds(x, y, 120, 30);
			armas[i].setQuantidade(4);
			Movimento mv = new Movimento(armas[i]);
			x+=125;
		}
		x=45;
		y=415;
		armas[14] = new Armas(couracado,Color.magenta);
		armas[14].setBounds(x,y,150,30);
		armas[14].setQuantidade(5);
		Movimento mv = new Movimento(armas[14]);

		return armas;
	}

	private void setArmas() {
		int j = 0;

		while(j<15){
			this.add(armas[j],j);
			j++;
		}
	}

	private void setCores(){
		double qt;
		for(int i=0;i<armas.length;i++){
			qt = armas[i].getQuantidade();
			if(qt == 2.6)
				armas[i].setCor(Color.green);
			else if(qt == 1)
				armas[i].setCor(Color.blue);
			else if(qt == 2)
				armas[i].setCor(Color.yellow);
			else if(qt == 4)
				armas[i].setCor(Color.orange);
			else
				armas[i].setCor(Color.magenta);
		}
	}

	private boolean temCinza(){
		for(Armas el : armas){
			if(el.getCor() == Color.gray)
				return true;
		}
		return false;
	}

	public Armas[] removeArma(Armas[] armas, int index) { //realmente necessario?

		if (armas == null || index < 0 || index >= armas.length)
			return armas;

		Armas[] outroArray = new Armas[armas.length - 1];

		for (int i=0, k=0; i < armas.length; i++) {
			if (i == index)
				continue;
			outroArray[k++] = armas[i];
		}
		return outroArray;
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		int mat2[][] = ctrl.getMatriz(2);

		if(e.getButton() == MouseEvent.BUTTON1){
			System.out.println("X ESQUERDO:"+x);
			System.out.println("Y ESQUERDO:"+y);
		}

		if(e.getButton() == MouseEvent.BUTTON3) {
			System.out.println("X DIREITO:"+x);
			System.out.println("Y DIREITO:"+y);
			arma.viraArma();
            repaint();
			return;
        }

        y-=yIni;

		/*if ((x > xIni && x < xIni + 15*(larg+espLinha)) && (y > 0 && y < 15*(alt+espLinha))) { //Se clicar no tabuleiro 1
			x-=xIni;
			ctrl.setValor((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
			repaint();
		}*/

        if ((x > (xIni + 700) && x < (xIni + 700) + 15*(larg+espLinha)) && (y > 0 && y < 15*(alt+espLinha))) { //Se clicar no tabuleiro 2
        	/*x-=(xIni+700);
            ctrl.setValor2((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
            repaint();*/
			for(int i = 0; i < mat2.length; i++) {
				for(int j = 0; j < mat2[i].length; j++) {
					if(mat2[i][j] != 0) {
						ctrl.setValor2((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
					}
				}
			}
			repaint();
        }
        else{
			y = e.getY();
			for(Armas el : armas){ //Melhorar ponto de pegada para hidro
				if((x > el.getX() && x < 25*el.getQuantidade()+el.getX()) && (y > el.getY() && y < el.getY() + alt) && (el.getCor() != Color.gray)) {
					System.out.println("PEGUEI NÃO CINZA");
					if(temCinza())
						setCores();
					el.setCor(Color.gray);
					xAtual = x;
					yAtual = y;
					xPeca = el.getX();
					yPeca = el.getY();
					repaint();
				}
				else if((x > el.getX() && x < 25*el.getQuantidade()+el.getX()) && (y > el.getY() && y < el.getY() + alt) && (el.getCor() == Color.gray)) {
					setCores();
					repaint();
				}
			}
		}
	}


	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void notify(Observable o) {}

	class ReadyButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {

		}
	}
}
