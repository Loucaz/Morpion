/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import morpion.Partie;

/**
 *
 * @author Loucaz
 */
public class Panneau extends JPanel implements MouseListener {

    private final int tailleX = 600;
    private final int tailleY = 600;

    private final Partie p;
    private final MainWindow m;

    public Panneau(Partie p, MainWindow m) {
        this.p = p;
        this.m = m;
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Reload");
        ImageIcon img = new ImageIcon(getClass().getResource("/image/grille.png"));
        img.paintIcon(this, g, 0, 0);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (p.getM().getG().verifvide(i, j) == false) {
                    if (p.getM().getG().getGrille(i, j).getNombre() == 1) {
                        img = new ImageIcon(getClass().getResource("/image/croix.png"));
                    } else {
                        img = new ImageIcon(getClass().getResource("/image/rond.png"));
                    }
                    img.paintIcon(this, g, 50 + 200 * i, 50 + 200 * j);
                }

            }

        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tailleX, tailleY);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {

            if (p.getM().getEtatManche() == 0) {
                switch (p.getM().game(e.getPoint().x / 200, e.getPoint().y / 200)) {
                    case 0:
                        //System.out.println(p.getM().getG());
                        repaint();

                        p.getM().setJoueurActuel();
                        m.getTour().setText("C'est à " + this.p.getJ(this.p.getM().getJoueurActuel()).getName() + " de jouer.");
                        p.getM().setJoueurActuel();
                        System.out.println("Continue");
                        break;
                    case 1:
                        repaint();
                        System.out.println("GAGNER");
                        this.p.score(this.p.getJ(this.p.getM().getJoueurActuel()));
                        p.getM().setEtatManche(1);
                        m.getTour().setText(this.p.getJ(this.p.getM().getJoueurActuel()).getName() + " a gagner !");
                        EndWindow f = new EndWindow(p, this.m, this.p.getJ(this.p.getM().getJoueurActuel()).getName() + " a gagner !");
                        break;
                    case 2:
                        repaint();
                        System.out.println("NULLE");
                        p.getM().setEtatManche(1);
                        m.getTour().setText("Partie nulle");
                        EndWindow f2 = new EndWindow(p, this.m, "Partie nulle");
                        break;
                    default:
                        repaint();
                        break;
                }
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
