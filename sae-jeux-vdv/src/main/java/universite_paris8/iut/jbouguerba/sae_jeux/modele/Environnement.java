package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Environnement {
    private int largeur;
    private int hauteur;
    private int[][] map = {
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0}
    };


    private ArrayList<Tour> listeTour;
    private ArrayList<Ennemi> listeEnnemi;
    private int ressources;

    public Environnement(int largeur, int hauteur) {
        this.hauteur = map.length;
        this.largeur = map[0].length;

        this.listeTour = new ArrayList<>();
        this.listeEnnemi = new ArrayList<>();
        this.ressources = 40;

        listeEnnemi.add(new Ennemi("Requin Basic", 50, 50, "aucun", 6, 0, 10, 1.0));
        listeEnnemi.add(new Ennemi("Requin Marteau", 30, 30, "rapide", 6, 1, 15, 2.0));
        listeEnnemi.add(new Ennemi("Requin Baleine", 100, 100, "resistant", 6, 2, 30, 0.5));


    }


    public int[][] getMap() {
        return map;
    }
    public int getLargeur() {
        return map[0].length;
    }
    public int getHauteur() {
        return map.length;
    }
    public ArrayList<Ennemi> getListeEnnemi() {
        return listeEnnemi;
    }


}
