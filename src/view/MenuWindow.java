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
import BD.Connexion;
import morpion.Coup;
import morpion.Partie;

/**
 *
 * @author Loucaz
 */
public class MenuWindow extends JFrame implements ActionListener {

    private final JButton resume;
    private final JButton newgame;
    private final JButton exit;

    public MenuWindow() {
        this.setTitle("Menu Morpion");
        this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;

        cont.gridx = 0;
        cont.gridy = 0;
        this.resume = new JButton("Reprendre la partie");
        this.add(resume, cont);
        this.resume.addActionListener(this);

        cont.gridy = 1;
        this.newgame = new JButton("Nouvelle partie");
        this.add(newgame, cont);
        this.newgame.addActionListener(this);
        cont.gridy = 2;
        this.exit = new JButton("Exit");
        this.add(exit, cont);
        this.exit.addActionListener(this);

        //this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resume) {
            Connexion c = new Connexion("Morpion.sqlite");
            c.connect();
            Partie p = new Partie(c.readj1(), c.readj2());
            p.getM().setTabCoup(c.getList_c());
            p.getM().setG();
            for (int x = 0; x < p.getM().getTabCoup().size(); x++) {

                Coup co = p.getM().getTabCoup().get(x);
                p.getM().getG().coup(co.getPosX(), co.getPosY(), co.getTypeJoueur());

            }
            p.getM().setJoueurActuel(c.joueur());

            c.close();
            MainWindow MainW = new MainWindow(p);

            this.dispose();
        }

        if (e.getSource() == newgame) {
            ChoixNom ChoixW = new ChoixNom();
            this.dispose();
        }
        if (e.getSource() == exit) {
            this.dispose();
        }
    }

}
