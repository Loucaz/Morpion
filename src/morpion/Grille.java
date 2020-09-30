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
public final class Grille {

    private final int taille;
    private final Case[][] grille;

    public Grille() {
        this.taille = 3;
        this.grille = new Case[this.taille][this.taille];
        initGrille();
    }

    public void initGrille() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                this.grille[i][j] = new Case();
                this.grille[i][j].setNombre(0);
            }
        }
    }

    public boolean verifvide(int i, int j) {
        return this.grille[i][j].getNombre() == 0;
    }

    public boolean verifWin(int j) {
        for (int x = 0; x < 3; x++) {
            if (grille[x][0].check(j) && grille[x][1].check(j) && grille[x][2].check(j)) {
                return true;
            }
        }
        for (int y = 0; y < 3; y++) {
            if (grille[0][y].check(j) && grille[1][y].check(j) && grille[2][y].check(j)) {
                return true;
            }
        }
        if (grille[0][0].check(j) && grille[1][1].check(j) && grille[2][2].check(j)) {
            return true;
        }
        if (grille[0][2].check(j) && grille[1][1].check(j) && grille[2][0].check(j)) {
            return true;
        }

        return false;
    }

    public boolean coup(int x, int y, int j) {
        if (verifvide(x, y)) {
            grille[x][y].setNombre(j);
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                s += this.grille[i][j].getNombre() + " ";
            }
            s += '\n';
        }
        return s;
    }

    public Case getGrille(int x, int y) {
        return grille[x][y];
    }

}
