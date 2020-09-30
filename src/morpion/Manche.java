/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import java.util.ArrayList;

/**
 *
 * @author Loucaz
 */
public final class Manche {

    private Grille g;
    private ArrayList<Coup> tabCoup;
    private int numManche;

    private int etatManche;

    private int joueurActuel;

    public Manche() {
        reset();
    }

    public void replay() {
        Grille replay = new Grille();
        for (Coup c : tabCoup) {
            replay.coup(c.getPosX(), c.getPosY(), c.getTypeJoueur());
            System.out.println(replay);

        }
    }

    public Grille getG() {
        return g;
    }

    public int game(int xpos, int ypos) {
        if (g.verifWin(this.joueurActuel) == false) {
            setJoueurActuel();
            if (this.g.coup(xpos, ypos, this.joueurActuel)) {
                tabCoup.add(new Coup(tabCoup.size(), this.joueurActuel, xpos, ypos));
            } else {
                setJoueurActuel();
            }

        }
        if (g.verifWin(this.joueurActuel)) {
            System.out.println("Joueur " + this.joueurActuel + " a gagner !");
            return 1;
        }
        if (tabCoup.size() == 9) {
            return 2;
        }
        return 0;
    }

    public int getEtatManche() {
        return etatManche;
    }

    public void setEtatManche(int etatManche) {
        this.etatManche = etatManche;
    }

    public void reset() {
        this.g = new Grille();
        this.numManche = 1;

        this.tabCoup = new ArrayList();

        this.etatManche = 0;

        this.joueurActuel = 2;
        if ((int) (Math.random() * 2) == 0) {
            setJoueurActuel();
        }
    }

    public void setJoueurActuel() {
        if (this.joueurActuel == 1) {
            this.joueurActuel = 2;
        } else {
            this.joueurActuel = 1;
        }
    }

    public void setJoueurActuel(int x) {

        this.joueurActuel = x;
    }

    public int getJoueurActuel() {
        return joueurActuel;
    }

    public ArrayList<Coup> getTabCoup() {
        return tabCoup;
    }

    public void setG() {
        this.g = new Grille();
    }

    public void setTabCoup(ArrayList<Coup> tabCoup) {
        this.tabCoup = tabCoup;
    }

    @Override
    public String toString() {
        return "Manche{" + "g=" + g + ", tabCoup=" + tabCoup + ", numManche=" + numManche + ", etatManche=" + etatManche + ", joueurActuel=" + joueurActuel + '}';
    }

}
