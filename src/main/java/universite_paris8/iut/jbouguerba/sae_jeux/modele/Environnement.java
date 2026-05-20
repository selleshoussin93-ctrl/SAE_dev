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


    private ArrayList<PoissonDeffense> PoissonsDe;
    private ArrayList<PoissonAttaque> PoissonsAtt;
    private int ressources;

    public Environnement(int largeur, int hauteur) {
        this.hauteur = map.length;
        this.largeur = map[0].length;

        this.PoissonsDe = new ArrayList<>();
        this.PoissonsAtt = new ArrayList<>();
        this.ressources = 40;

        PoissonsAtt.add(new PoissonAttaque("Requin Basic", 50, 50, "aucun", 6, 0, 10, 1.0));
        PoissonsAtt.add(new PoissonAttaque("Requin Marteau", 30, 30, "rapide", 6, 1, 15, 2.0));
        PoissonsAtt.add(new PoissonAttaque("Requin Baleine", 100, 100, "resistant", 6, 2, 30, 0.5));


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
    public ArrayList<PoissonAttaque> getListePoissonsAttaque() {
        return PoissonsAtt;
    }


}
