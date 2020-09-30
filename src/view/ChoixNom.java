/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import morpion.Partie;

/**
 *
 * @author Loucaz
 */
public class ChoixNom extends JFrame implements ActionListener {

    private final JButton valider;
    private final JTextField j1;
    private final JTextField j2;
    private final JLabel choix;
    private final JLabel joueur1;
    private final JLabel joueur2;

    public ChoixNom() {
        this.setTitle("Morpion");
        this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;

        cont.gridx = 1;
        cont.gridy = 0;
        this.choix = new JLabel("Choix nom");
        this.add(choix, cont);

        cont.gridx = 0;
        cont.gridy = 1;
        this.joueur1 = new JLabel("Joueur 1");
        this.add(joueur1, cont);

        cont.gridx = 2;
        cont.gridy = 1;
        this.joueur2 = new JLabel("Joueur 2");
        this.add(joueur2, cont);

        cont.gridx = 0;
        cont.gridy = 2;
        this.j1 = new JTextField("");
        this.add(j1, cont);

        cont.gridx = 2;
        cont.gridy = 2;
        this.j2 = new JTextField("");
        this.add(j2, cont);

        cont.gridx = 1;
        cont.gridy = 3;
        this.valider = new JButton("Valider");
        this.add(valider, cont);
        this.valider.addActionListener(this);

        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == valider) {
            Partie p = new Partie(j1.getText(), j2.getText());
            MainWindow MainW = new MainWindow(p);
            this.dispose();
        }
    }

}
