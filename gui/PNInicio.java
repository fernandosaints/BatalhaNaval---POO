package gui;

import regras.CtrlRegras;
import regras.Fachada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PNInicio extends JPanel implements ActionListener {
    private final int LARG_DEFAULT=1280;
    private final int ALT_DEFAULT=720;
    Toolkit tk=Toolkit.getDefaultToolkit();
    Dimension screenSize=tk.getScreenSize();
    private int sl=screenSize.width;
    private int sa=screenSize.height;
    private int x=sl/2-LARG_DEFAULT/2;
    private int y=sa/2-ALT_DEFAULT/2;

    public PNInicio(Fachada f, FRNaval frameInicio){
        JButton novo_jogo = new JButton("Novo Jogo"); //Cria botão "Novo Jogo"
        JButton carregar_jogo = new JButton("Carregar Jogo"); //Cria botão "Carregar Jogo"
        this.add(novo_jogo); //Adiciona botão "Novo Jogo" no painel
        this.add(carregar_jogo); //Adiciona botão "Carregar Jogo" no painel

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
                    f.setJogadores(jogador1.getText(), jogador2.getText());
                    frameInicio.getContentPane().removeAll();
                    frameInicio.getContentPane().add(new PNNaval(f));
                    frameInicio.setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
                    frameInicio.revalidate();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
