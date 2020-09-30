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
public class PauseWindow extends JFrame implements ActionListener {

    private final Partie p;

    private final JButton start;
    private final JButton exit;
    private final MainWindow m;

    public PauseWindow(Partie p, MainWindow m) {

        this.p = p;
        this.p.getM().setEtatManche(1);
        this.m = m;
        m.getPause().setEnabled(false);

        this.setTitle("Pause");
        this.setSize(320, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;

        cont.gridx = 0;
        cont.gridy = 0;

        this.start = new JButton("Reprendre");
        this.add(start, cont);
        this.start.addActionListener(this);

        cont.gridy = 1;
        this.exit = new JButton("Exit");
        this.add(exit, cont);
        this.exit.addActionListener(this);

        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start) {

            this.p.getM().setEtatManche(0);

            m.getPause().setEnabled(true);
            this.dispose();

        }

        if (e.getSource() == exit) {
            //javax.swing.JOptionPane.showMessageDialog(this, "Joueur " + p.getM().getJoueurActuel() + " a gagner !");
            this.dispose();
            this.m.dispose();
            Connexion c = new Connexion("Morpion.sqlite");
            c.connect();
            c.deleteCoup();
            c.deletePartie();
            c.insertPartie(p.getJ1().getName(), p.getJ2().getName(), p.getJ1().getScore(), p.getJ2().getScore(), p.getM().getJoueurActuel(), p.getM().getEtatManche());
            for (int x = 0; x < p.getM().getTabCoup().size(); x++) {

                Coup co = p.getM().getTabCoup().get(x);
                c.insertCoup(x, co.getPosX(), co.getPosY(), co.getTypeJoueur());
            }
            c.close();
        }
    }

}
