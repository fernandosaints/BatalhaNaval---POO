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
	final int LARG_DEFAULT=1280;
	final int ALT_DEFAULT=720;
    private int X,Y;
	double xIni=30.0,yIni=90.0,larg=25.0,alt=25.0,espLinha=5.0, var=0;
	int xAtual, yAtual, xPeca, yPeca, verifica, pos;

	Color corDefault = UIManager.getColor ("Panel.background");

	Celula tab1[][]=new Celula[15][15];
	Celula tab2[][]=new Celula[15][15];

	Armas armas[];
	JPanel panel;

	Line2D.Double lines1[]=new Line2D.Double[32];
	Line2D.Double lines2[]=new Line2D.Double[32];
	Fachada ctrl;
	String[] jogadores;

	JButton pronto = new JButton("Tabuleiro Pronto!");

	public PNNaval(Fachada f) {
		double x,y=yIni;
		ctrl=f;
		panel = new JPanel(new GridLayout(2,1));
		panel.setSize(LARG_DEFAULT/2, ALT_DEFAULT-200);

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
		criaArmas();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;

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

//		g2d.drawString(jogadores[0], (int)(xIni+(7.5*(larg+espLinha))), 510);
		g2d.drawString(jogadores[0]+", selecione uma arma na lista.", 500, 600);

		String letras = "ABCDEFGHIJKLMNO";

		for(int i=0; i < 15 ; i++) {
				/*g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni-15), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)(xIni +(i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));*/
				g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni+685), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
				g2d.drawString(String.valueOf(i),(int)((xIni + 700) + (i*(larg+espLinha))+(larg*0.35)),(int)(yIni-7));
		}

		add(panel);

		/*if(verifica == 0){
			if(yAtual > 90 && yAtual < 120)
				armas.hidro(g,xPeca,yPeca,larg,alt,var,Color.green);
			if(yAtual > 190 && yAtual < 215)
				armas.submarino(g,xPeca,yPeca,larg,alt,Color.blue);
			if(yAtual > 265 && yAtual < 290)
				armas.destroyer(g,xPeca,yPeca,larg,alt,Color.yellow);
			if(yAtual > 340 && yAtual < 365)
				armas.cruzador(g,xPeca,yPeca,larg,alt,Color.orange);
			if(yAtual > 415 && yAtual < 440)
				armas.couracado(g,xPeca,yPeca,larg,alt,Color.magenta);
		}
		else {
			if(yAtual > 90 && yAtual < 120)
				armas.hidro(g,xPeca,yPeca,larg,alt,var,Color.gray);
			if(yAtual > 190 && yAtual < 215)
				armas.submarino(g,xPeca,yPeca,larg,alt,Color.gray);
			if(yAtual > 265 && yAtual < 290)
				armas.destroyer(g,xPeca,yPeca,larg,alt,Color.gray);
			if(yAtual > 340 && yAtual < 365)
				armas.cruzador(g,xPeca,yPeca,larg,alt,Color.gray);
			if(yAtual > 415 && yAtual < 440)
				armas.couracado(g,xPeca,yPeca,larg,alt,Color.gray);
		}*/

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

					/*g2d.setPaint(Color.red);
					rt=new Rectangle2D.Double(tab2[i][j].x+(espLinha/2),tab2[i][j].y+(espLinha/2),larg+1,alt+1);
					g2d.fill(rt);*/


					/*for(Armas peca : armasJogo) {
						if(peca.getCor() == Color.gray){
							if(pos < 5) {
								armas.hidro(g, xint, yint, larg, alt, espLinha, Color.red);
								armas.hidro(g,xPeca,yPeca,larg,alt,var,corDefault);
							}
							else if(pos < 9) {
								armas.submarino(g, xint, yint, larg, alt, Color.red);
								armas.submarino(g,xPeca,yPeca,larg,alt,corDefault);
							}
							else if(pos < 12) {
								armas.destroyer(g, xint, yint, larg, alt, Color.red);
								armas.destroyer(g,xPeca,yPeca,larg,alt,corDefault);
							}
							else if(pos < 14) {
								armas.cruzador(g, xint, yint, larg, alt, Color.red);
								armas.cruzador(g,xPeca,yPeca,larg,alt,corDefault);
							}
							else {
								armas.couracado(g,xint,yint,larg,alt,Color.red);
								armas.couracado(g,xPeca,yPeca,larg,alt,corDefault);
							}

							armasJogo = peca.removeArma(armasJogo,pos);
							//peca.setCor(corDefault);
						}
					}*/
				}
			}
		}

	}

	public void criaArmas(){

		int[][] hidro = new int[][]{{0,1,0},{1,0,1}};
		int[][] submarino = new int[][]{{1}};
		int[][] destroyer = new int[][]{{1,1}};
		int[][] cruzador = new int[][]{{1,1,1,1}};
		int[][] couracado = new int[][]{{1,1,1,1,1}};

		int i, x = 45,y = 90;
		for(i=0;i<5;i++){
			armas[i] = new Armas(hidro,Color.green);
			armas[i].setBounds(x,y,90,70);
			x+=90;
		}
		x = 45;
		y = 190;
		for(i=5;i<9;i++){
			armas[i] = new Armas(submarino,Color.blue);
			armas[i].setBounds(x,y,30,30);
			x+=50;
		}
		x = 45;
		y = 265;
		for(i=9;i<12;i++){
			armas[i] = new Armas(destroyer,Color.yellow);
			armas[i].setBounds(x,y,60,30);
			x+=75;
		}
		x=45;
		y=340;
		for(i=12;i<14;i++) {
			armas[i] = new Armas(cruzador, Color.orange);
			armas[i].setBounds(x, y, 120, 30);
			x+=125;
		}
		x=45;
		y=415;
		armas[14] = new Armas(couracado,Color.magenta);
		armas[14].setBounds(x,y,150,30);

		setArmas();
	}

	private void setArmas() {
		int i, j = 0, x = 45,y = 90;
		for(i=0;i<5;i++){
			armas[i].setLocation(x,y);
			x+=90;
		}
		x = 45;
		y = 190;
		for(i=5;i<9;i++){
			armas[i].setLocation(x,y);
			x+=50;
		}
		x = 45;
		y = 265;
		for(i=9;i<12;i++){
			armas[i].setLocation(x,y);
			x+=75;
		}
		x=45;
		y=340;
		for(i=12;i<14;i++) {
			armas[i].setLocation(x, y);
			x+=125;
		}
		x=45;
		y=415;
		armas[14].setLocation(x,y);

		while(j<15){
			panel.add(armas[j],j);
			j++;
		}
	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		System.out.println(x);
		System.out.println(y);

		if(e.getButton() == MouseEvent.BUTTON3) {
            //armas.viraArma(x,y);
            //repaint();
            return;
        }

        y-=yIni;

        if ((x > (xIni + 700) && x < (xIni + 700) + 15*(larg+espLinha)) && (y > 0 && y < 15*(alt+espLinha))) { //se clicar no tabuleiro 2
            x-=(xIni+700);
            ctrl.setValor2((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
            repaint();
        }
        /*else{
            y = e.getY();
            for(int i=0;i<armasJogo.length;i++){
                if((x > armasJogo[i].getX() && x < 25*armasJogo[i].getQuantidade()+armasJogo[i].getX()) && (y > armasJogo[i].getY() && y < armasJogo[i].getY() + alt)){
                    System.out.println("PEGUEI");
                    verifica = ctrl.getVerifica();
                    xAtual = x;
                    yAtual = y;
                    xPeca = armasJogo[i].getX();
                    yPeca = armasJogo[i].getY();
                    pos = i;
                    if(armasJogo[i].getCor() != Color.gray)
                        armasJogo[i].setCor(Color.gray);
                    repaint();
                }
            }
        }*/

		/*for(Armas peca : armasJogo){
			if((x > peca[i].getX() && x < 25*peca[i].getQuantidade()+peca[i].getX()) && (y > peca[i].getY() && y < peca[i].getY() + alt)){
				System.out.println("PEGUEI");
				verifica = ctrl.getVerifica();
				xAtual = x;
				yAtual = y;
				xPeca = peca[i].getX();
				yPeca = peca[i].getY();
				if(peca[i].getCor() != Color.gray)
					peca[i].setCor(Color.gray);
				repaint();
			}

		}*/

		/*if ((x > xIni && x < xIni + 15*(larg+espLinha)) && (y > 0 && y < 15*(alt+espLinha))) {
			x-=xIni;
			ctrl.setValor((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
			repaint();
		}*/


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
