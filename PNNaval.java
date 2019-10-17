package gui;

import regras.CtrlRegras;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class PNNaval extends JPanel implements MouseListener {
	double xIni=30.0,yIni=30.0,larg=30.0,alt=30.0,espLinha=5.0;
	int iClick,jClick;
	Celula tab[][]=new Celula[15][15];
	Line2D.Double ln[]=new Line2D.Double[32];
	CtrlRegras ctrl;
	String[] jogadores;

	JTextField nameTextField = new JTextField(20);
	JButton button = new JButton("Start");
	
	public PNNaval(CtrlRegras c) {
		double x=xIni,y=yIni;
		ctrl=c;

		
		for(int i=0;i<15;i++) {
			x=xIni;
			for(int j=0;j<15;j++) {
				tab[i][j]=new Celula(x,y);
				x+=larg+espLinha;
			}
			y+=alt+espLinha;
		}
		
		addMouseListener(this);

		for(int i=0;i<15;i++){
			ln[i] = new Line2D.Double((xIni + (i*(larg+espLinha))),yIni , (xIni + (i*(larg+espLinha))), yIni+(15*(alt+espLinha)));
		}
		for(int i = 0; i < 15; i++ ) {
			ln[i+15]= new Line2D.Double(xIni,(yIni +(i*(alt+espLinha))), xIni+(15*(larg+espLinha)), (yIni + (i*(alt+espLinha))));
		}
		ln[30] = new Line2D.Double(xIni+(15*(larg+espLinha)),yIni, xIni+(15*(larg+espLinha)), (yIni + (15*(alt+espLinha))));
		ln[31] = new Line2D.Double(xIni,yIni+(15*(alt+espLinha)), xIni+(15*(larg+espLinha)), (yIni + (15*(alt+espLinha))));


		/*ln[0]=new Line2D.Double(155.0,75.0,155.0,325.0);
		ln[1]=new Line2D.Double(240.0,75.0,240.0,325.0);
		ln[2]=new Line2D.Double(75.0,155.0,325.0,155.0);
		ln[3]=new Line2D.Double(75.0,240.0,325.0,240.0);*/
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		int mat[][]=ctrl.getMatriz();
		int vez=ctrl.getVez();
		jogadores = ctrl.getJogadores();
		
		g2d.setStroke(new BasicStroke(5.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f));
		
		g2d.setPaint(Color.black);
		
		for(int i=0;i<32;i++)
			g2d.draw(ln[i]);

		g2d.drawString(jogadores[0], (int)(xIni+(7.5*(larg+espLinha))), (int)(yIni/2));

		String letras = "ABCDEFGHIJKLMNO";

		for(int i=0;i<15;i++) {
			g2d.drawString(String.valueOf(letras.charAt(i)), (int)(xIni-15), (int)(yIni + (i*(alt+espLinha)+alt*0.7)));
			g2d.drawString(String.valueOf(i),(int)(xIni +(i*(larg+espLinha))+(larg*0.5)),(int)(yIni-7));
		}

		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(mat[i][j]!=0) {
					if(mat[i][j]!=0) {
						g2d.setPaint(Color.green);
						rt=new Rectangle2D.Double(tab[i][j].x+25.0,tab[i][j].y+25.0,30.0,30.0);
						g2d.fill(rt);
					}
				}
			}
		}
		
		
	}
	
	public void mouseClicked(MouseEvent e) {
		Rectangle2D rt;
		int x = e.getX(), y = e.getY();

		if ((x > xIni && x < xIni + 15*(larg+espLinha)) && (y > 0 && y < 15*(alt+espLinha))) {
			ctrl.setValor((int)(x/(larg+espLinha)),(int)(y/(alt+espLinha)));
			repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
