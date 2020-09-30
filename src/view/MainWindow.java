package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import morpion.Coup;
import morpion.Partie;
import morpion.Util;

public class MainWindow extends JFrame implements ActionListener {

    private final Panneau pan;
    private final Partie p;

    private final JLabel scorej1;
    private final JLabel scorej2;

    private final JLabel tour;
    private final JButton pause;

    public MainWindow(Partie p) {
        this.p = p;

        this.setTitle("Morpion");
        this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;

        cont.insets = new Insets(10, 20, 10, 20);
        cont.gridx = 0;
        cont.gridy = 2;
        this.scorej1 = new JLabel(p.getJ1().getName() + ":  " + p.getJ1().getScore(), JLabel.CENTER);
        this.add(scorej1, cont);

        cont.gridx = 1;
        cont.gridy = 0;
        p.getM().setJoueurActuel();
        this.tour = new JLabel("C'est à " + this.p.getJ(this.p.getM().getJoueurActuel()).getName() + " de jouer.", JLabel.CENTER);
        p.getM().setJoueurActuel();
        this.add(tour, cont);

        cont.insets = new Insets(0, 0, 0, 0);
        cont.gridy = 1;
        cont.gridheight = 2;
        this.pan = new Panneau(p, this);
        this.add(pan, cont);
        cont.gridheight = 1;

        cont.gridy = 3;
        this.pause = new JButton("Pause");
        this.add(pause, cont);
        this.pause.addActionListener(this);

        cont.insets = new Insets(10, 20, 10, 20);
        cont.gridx = 2;
        cont.gridy = 2;
        this.scorej2 = new JLabel(p.getJ2().getName() + ":  " + p.getJ2().getScore());
        this.add(scorej2, cont);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pause) {
            PauseWindow pauseWindo = new PauseWindow(p, this);
        }

    }

    public void replay(String s) {
        this.p.getM().setG();
        repaint();
        Runnable r = () -> {
            for (int x = 0; x < p.getM().getTabCoup().size(); x++) {
                Util.pause(600);
                Coup c = p.getM().getTabCoup().get(x);
                getP().getM().getG().coup(c.getPosX(), c.getPosY(), c.getTypeJoueur());
                repaint();
            }

            EndWindow f = new EndWindow(p, this, s);
        };

        (new Thread(r)).start();
    }

    public Partie getP() {
        return p;
    }

    public JLabel getJ1() {
        return scorej1;
    }

    public JLabel getJ2() {
        return scorej2;
    }

    public JButton getPause() {
        return pause;
    }

    public JLabel getTour() {
        return tour;
    }

}
