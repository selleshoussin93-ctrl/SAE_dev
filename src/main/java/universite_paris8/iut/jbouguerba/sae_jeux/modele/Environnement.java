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


    private ArrayList<PoissonDeffense> poissonsDe;
    private ArrayList<PoissonAttaque> poissonsAtt;
    private int ressources;

    public Environnement(int largeur, int hauteur) {
        this.hauteur = map.length;
        this.largeur = map[0].length;

        this.poissonsDe = new ArrayList<>();
        this.poissonsAtt = new ArrayList<>();
        this.ressources = 40;

        poissonsAtt.add(new PoissonAttaque("Requin Basic", 50, 50, 6, 0, 10, 1.0));
        poissonsAtt.add(new PoissonAttaque("Requin Marteau", 30, 30,  6, 1, 15, 2.0));
        poissonsAtt.add(new PoissonAttaque("Requin Baleine", 100, 100, 6, 2, 30, 0.5));

        poissonsDe.add(new PoissonDeffense("poisson rouge",20,0,0,10));
        poissonsDe.add(new PoissonDeffense("poisson rouge",20,0,1,10));
        poissonsDe.add(new PoissonDeffense("poisson rouge",20,0,2,10));

    }

    public void tirer(){


    }

    public void ajouterPoissonDeffense(){





    }

    public void ajouterPoissonAttaque(){



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
        return poissonsAtt;
    }


}
