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
public class Case {

    private int nombre;

    public Case() {
        this.nombre = 0;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public boolean check(int j) {
        return this.nombre == j;
    }
}
