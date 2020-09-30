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
import BD.Connexion;
import morpion.Coup;
import morpion.Partie;

/**
 *
 * @author Loucaz
 */
public class EndWindow extends JFrame implements ActionListener {

    private final Partie p;

    private final JLabel result;
    private final JButton start;
    private final JButton replay;
    private final JButton exit;

    private final MainWindow m;

    public EndWindow(Partie p, MainWindow m, String resultS) {

        this.m = m;
        m.getJ1().setText(p.getJ1().getName() + ":  " + p.getJ1().getScore());
        m.getJ2().setText(p.getJ2().getName() + ":  " + p.getJ2().getScore());
        m.getPause().setEnabled(false);
        m.pack();
        this.p = p;

        this.setTitle("Result");
        this.setSize(320, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;

        cont.gridx = 0;
        cont.gridy = 0;
        this.result = new JLabel(resultS);
        this.add(result, cont);
        cont.gridy = 1;

        this.replay = new JButton("Replay");
        this.add(replay, cont);
        this.replay.addActionListener(this);

        cont.gridy = 2;
        this.start = new JButton("Recomencer");
        this.add(start, cont);
        this.start.addActionListener(this);

        cont.gridy = 3;
        this.exit = new JButton("Exit");
        this.add(exit, cont);
        this.exit.addActionListener(this);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start) {
            this.dispose();
            System.out.println("Start");
            p.getM().reset();
            p.getM().setJoueurActuel();
            m.getTour().setText("C'est à " + this.p.getJ(this.p.getM().getJoueurActuel()).getName() + " de jouer.");
            p.getM().setJoueurActuel();
            this.m.repaint();

            m.getPause().setEnabled(true);
        }

        if (e.getSource() == replay) {
            this.dispose();
            m.replay(this.result.getText());
        }

        if (e.getSource() == exit) {
            this.dispose();
            this.m.dispose();
            p.getM().reset();
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
