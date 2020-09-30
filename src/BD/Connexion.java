/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import morpion.Coup;
import morpion.Joueur;

/**
 *
 * @author p1701678
 */
public class Connexion {

    String DBPath;
    ResultSet rs;
    Connection connection = null;
    Statement statement = null;
    ArrayList<Coup> list_c;
    Joueur j1;
    Joueur j2;

    public Connexion(String dBPath) {
        DBPath = dBPath;
    }

    public ArrayList<Coup> getList_c() {
        this.readCoup();
        return list_c;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connecxion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connecxion");
        }
    }

    public void insertPartie(String pseudo1, String pseudo2, int scoreJ1, int scoreJ2, int numManche, int etatManche) {
        try {
            deletePartie();
            String requete = "INSERT INTO Partie VALUES ('" + pseudo1 + "', '" + pseudo2 + "', '" + scoreJ1 + "', '" + scoreJ2 + "', '" + numManche + "', '" + etatManche + "')";
            System.out.println(requete);
            statement.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println("Echec insertion dans Partie");
        }
    }

    public void insertCoup(int numCoup, int posX, int posY, int typeJoueur) {
        try {
            String requete2 = "INSERT INTO Coup VALUES ('" + numCoup + "', '" + posX + "', '" + posY + "', '" + typeJoueur + "')";
            System.out.println(requete2);
            statement.executeUpdate(requete2);
        } catch (SQLException ex) {
            System.out.println("Echec insertion dans Coup");
        }

    }

    public Joueur readj1() {
        try {
            rs = statement.executeQuery("SELECT * FROM Partie");
            j1 = new Joueur(rs.getString("pseudoJoueur1"), 1);
            j1.setScore(rs.getInt("scoreJoueur1"));
        } catch (SQLException ex) {
            //ignorer
        }
        return j1;
    }

    public int joueur() {
        int j = 0;
        try {
            rs = statement.executeQuery("SELECT * FROM Partie");
            j = rs.getInt("numManche");
        } catch (SQLException ex) {
            System.out.println("ereur");
        }
        return j;
    }

    public Joueur readj2() {
        try {
            rs = statement.executeQuery("SELECT * FROM Partie");
            j2 = new Joueur(rs.getString("pseudoJoueur2"), 2);
            j2.setScore(rs.getInt("scoreJoueur2"));
        } catch (SQLException ex) {
            //ignorer
        }
        return j2;
    }

    public void readCoup() {
        list_c = new ArrayList<>();
        try {
            rs = statement.executeQuery("SELECT * FROM Coup");
            while (rs.next()) {
                Coup c = new Coup(rs.getInt("numCoup"), rs.getInt("typeJoueur"), rs.getInt("posX"), rs.getInt("posY"));
                list_c.add(c);
            }
            //deleteCoup();
        } catch (SQLException ex) {
            //ignorer
        }
    }

    public void deletePartie() {
        try {
            statement.executeUpdate("DELETE FROM Partie");
        } catch (SQLException ex) {
            // ignore
        }
    }

    public void deleteCoup() {
        try {
            statement.executeUpdate("DELETE FROM Coup");
        } catch (SQLException ex) {
            // ignore
        }
    }

    public void close() {
        try {
            connection.close();
            System.out.println("Déconnexion réussie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
