package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;

public class Environnement {
    private int largeur;
    private int hauteur;
    private int[][] grille = {
            {0, 0, 0, 0, 1, 0, 0}, //0 = vide
            {0, 0, 0, 0, 0, 0, 0}, //1 = obstacle
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };

    private ArrayList<Poisson> poissons;
    private ArrayList<Ennemi> ennemis;

    public Environnement(int largeur, int hauteur ) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.poissons = new ArrayList<>();
        this.ennemis = new ArrayList<>();

    }

    public int[][] getGrille() { return grille; }
    public int getLargeur() { return grille[0].length; }
    public int getHauteur() { return grille.length; }
}
