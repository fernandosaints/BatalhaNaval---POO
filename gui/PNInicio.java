package gui;


import regras.Fachada;
import gui.Batalha;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PNInicio extends JPanel implements ActionListener {
	private final int LARG_DEFAULT=1280;
    private final int ALT_DEFAULT=720;
    private Toolkit tk=Toolkit.getDefaultToolkit();
    private Dimension screenSize=tk.getScreenSize();
    private int sl=screenSize.width;
    private int sa=screenSize.height;
    private int x=sl/2-LARG_DEFAULT/2;
    private int y=sa/2-ALT_DEFAULT/2;
    JPanel PNNaval, batalha;

    public PNInicio(Fachada fachada, FRNaval frameInicio){
        JButton novo_jogo = new JButton("Novo Jogo");
        JButton carregar_jogo = new JButton("Carregar Jogo");
        
        this.add(novo_jogo);
        this.add(carregar_jogo);
        novo_jogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField jogador1 = new JTextField("Jogador 1",10);
                JTextField jogador2 = new JTextField("Jogador 2",10);

                JPanel painelInicio = new JPanel();
                painelInicio.setLayout(new GridLayout(2,2));
                painelInicio.add(new JLabel("Jogador 1:"));
                painelInicio.add(jogador1);
                painelInicio.add(new JLabel("Jogador 2:"));
                painelInicio.add(jogador2);

                int resultado = JOptionPane.showConfirmDialog(null,painelInicio,"Digite os nomes dos jogadores:",JOptionPane.OK_OPTION);

                if(resultado == JOptionPane.OK_OPTION){
                    fachada.setJogadores(jogador1.getText(), jogador2.getText());
                    frameInicio.getContentPane().removeAll();
                    PNNaval = new PNNaval(fachada);
                    frameInicio.getContentPane().add(PNNaval);
                    frameInicio.setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
                }

            }
        });
        carregar_jogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frameInicio.getContentPane().removeAll();
                batalha = new Batalha(fachada.carregaTabuleiro());
                frameInicio.getContentPane().add(batalha);
                frameInicio.setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
