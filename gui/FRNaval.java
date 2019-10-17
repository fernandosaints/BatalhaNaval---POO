package gui;

import regras.CtrlRegras;

import javax.swing.*;
import java.awt.*;

public class FRNaval extends JFrame {
	final int LARG_DEFAULT=1280;
	final int ALT_DEFAULT=720;

	public FRNaval(CtrlRegras c) {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new PNNaval(c));
		setTitle("Batalha Naval");
	}
	
	public static void main(String args[]) {
		(new FRNaval(new CtrlRegras())).setVisible(true);
	}
}
