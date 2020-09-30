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
public class Coup {

    private final int numCoup;
    private final int typeJoueur;
    private final int posX;
    private final int posY;

    public Coup(int numCoup, int typeJoueur, int posX, int posY) {
        this.numCoup = numCoup;
        this.typeJoueur = typeJoueur;
        this.posX = posX;
        this.posY = posY;
    }

    public int getNumCoup() {
        return numCoup;
    }

    public int getTypeJoueur() {
        return typeJoueur;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}
