/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import morpion.Manche;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Loucaz
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   //Verifie que la grille est gagnante
    @Test
    public void testGagnant() {
        Manche m1 = new Manche();
        m1.game(0, 0);
        m1.game(1, 2);
        m1.game(0, 1);
        m1.game(1, 1);
        assertEquals(1, m1.game(0, 2));
    }

    //Verifie que la grille est nulle
    @Test
    public void testNul() {
        Manche m1 = new Manche();
        m1.game(0, 0);
        m1.game(0, 2);
        m1.game(0, 1);
        m1.game(1, 0);
        m1.game(1, 2);
        m1.game(1, 1);
        m1.game(2, 0);
        m1.game(2, 2);
        assertEquals(2, m1.game(2, 1));
    }

    //Verifie que le coup n'est pas joué deux fois sur la meme case
    @Test
    public void verifCoup() {
        Manche m1 = new Manche();
        m1.game(0, 0);
        m1.game(1, 2);
        assertEquals(false, m1.getG().coup(1, 2, 1));
    }
    
    //Verifie que le coup n'est pas joué deux fois sur la meme case
    @Test
    public void verifCoupOK() {
        Manche m1 = new Manche();
        m1.game(0, 0);
        m1.game(1, 2);
        assertEquals(true, m1.getG().coup(1, 0, 1));
    }

}

