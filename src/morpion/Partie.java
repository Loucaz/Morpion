/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

/**
 *
 * @author Loucaz
 */
public class Partie {

    private final Joueur j1;
    private final Joueur j2;
    private final Manche m;

    public Partie(String j1, String j2) {
        this.j1 = new Joueur(j1, 1);
        this.j2 = new Joueur(j2, 2);
        this.m = new Manche();
    }

    public Partie(Joueur j1, Joueur j2) {
        this.j1 = j1;
        this.j2 = j2;
        this.m = new Manche();
    }

    public Manche getM() {
        return m;
    }

    public Joueur getJ1() {
        return j1;
    }

    public Joueur getJ2() {
        return j2;
    }

    public Joueur getJ(int i) {
        if (i == 1) {
            return j1;
        } else if (i == 2) {
            return j2;
        }
        return null;
    }

    public void score(Joueur j) {
        j.setScore(j.getScore() + 1);
    }

}
