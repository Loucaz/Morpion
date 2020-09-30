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
public class Util {

    public static void pause(int _temps) {
        try {
            Thread.sleep(_temps);
        } catch (InterruptedException e) {
        }
    }
}
