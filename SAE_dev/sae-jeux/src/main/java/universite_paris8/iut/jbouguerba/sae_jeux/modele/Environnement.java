package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Environnement {
    private int largeur;
    private int hauteur;
    private Tile[][] map ;


    private ArrayList<Poisson> listeTour;
    private ArrayList<Ennemi> listeEnnemi;

    public Environnement(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.map = new Tile[largeur][hauteur];

    }

    public Tile[][] getMap() {
        return map;
    }

    private void initialiserMap(){
        for (int l = 0; l < hauteur; l++) {
            for (int c = 0; c < largeur; c++) {
                // On remplit chaque case de l'environnement avec un OBJET Tile
                // Tu peux décider du type (0 pour eau, 1 pour mur)
                this.map[l][c] = new Tile();
            }
        }
    }
    public int getLargeur() {
        return map[0].length;
    }
    public int getHauteur() {
        return map.length;
    }


}
