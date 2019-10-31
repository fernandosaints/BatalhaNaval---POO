package gui;

import regras.CtrlRegras;
import regras.Observable;
import regras.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class PNNaval extends JPanel implements MouseListener, Observer {
    private int X,Y;
	double xIni=30.0,yIni=90.0,larg=25.0,alt=25.0,espLinha=5.0;

	Color corDefault = UIManager.getColor ("Panel.background");

	Celula tab1[][]=new Celula[15][15];
	Celula tab2[][]=new Celula[15][15];

	Line2D.Double lines1[]=new Line2D.Double[32];
	Line2D.Double lines2[]=new Line2D.Double[32];
	CtrlRegras ctrl;
	String[] jogadores;

	JTextField nome1 = new JTextField(20);
	JTextField nome2 = new JTextField(20);

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
			lines1[i + 16] = new Line2D.Double(xIni, yIni + (i * (alt + espLinha)), xIni + (15 * (larg + espLinha)), yIni + (i * (alt + espLinha)));
			lines2[i + 16] = new Line2D.Double(xIni + 700, yIni + (i * (alt + espLinha)), xIni + 700 + (15 * (larg + espLinha)), yIni + (i * (alt + espLinha)));
		}



		/*JPanel fundo = new JPanel();
		fundo.setBackground(Color.white);
		fundo.setSize(PNNaval.);
		fundo.setLayout(null);*/

		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setSize(100,100);
		this.add(panel);
		Movement mv = new Movement(this.getComponents());
		/*add(this);
		setVisible(true);*/

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		Armas armas = new Armas();
		int mat[][] = ctrl.getMatriz(1);
		int mat2[][] = ctrl.getMatriz(2);
		int verifica = ctrl.getVerifica();
		jogadores = ctrl.getJogadores();

		remove(iniciar);
		remove(nome1);
		remove(nome2);
		pronto.addActionListener(new ReadyButton());
		pronto.setBounds(500,620,200,40);
		pronto.setEnabled(false);
		add(pronto);

		int posX = 70;
		int posY = 90;

		armas.criaArmas();

		for(int j=0;j<5;j++){
			armas.hidro(g,posX,posY,larg,alt,Color.green);
			posX+=90;
		}

		posX=45;
		posY=190;
		armas.submarino(g,posX,posY,larg,alt,Color.blue);

		posX=45;
		posY=265;
		armas.destroyer(g,posX,posY,larg,alt,Color.yellow);

		posX=45;
		posY=340;
		armas.cruzador(g,posX,posY,larg,alt,Color.orange);

		posX=45;
		posY=415;
		armas.couracado(g,posX,posY,larg,alt,Color.magenta);


		g2d.setStroke(new BasicStroke(2.0f,
				BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f));

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

		if(verifica == 1)
			armas.hidro(g,70,90,larg,alt,Color.green);
		else
			armas.hidro(g,70,90,larg,alt,Color.gray);


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
					armas.hidro(g,70,90,larg,alt,corDefault);
				}
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		System.out.println(x);
		System.out.println(y);

		if((x > 70 && x < 95) && (y > 90 && y < 115)){
			System.out.println("primeira arma de 3");
			repaint();
		}

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
