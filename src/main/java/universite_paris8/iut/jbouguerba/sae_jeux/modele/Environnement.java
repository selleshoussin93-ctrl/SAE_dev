package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;

public class Environnement {
    private int largeur;
    private int hauteur;
    private int[][] map = {
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0}
    };


    private ArrayList<PoissonDeffense> poissonsDeff;
    private ArrayList<PoissonAttaque> poissonsAtt;
    private int ressources;

    public Environnement(int largeur, int hauteur) {
        this.hauteur = map.length;
        this.largeur = map[0].length;

        this.poissonsDeff = new ArrayList<>();
        this.poissonsAtt = new ArrayList<>();
        this.ressources = 40;

        poissonsAtt.add(new PoissonAttaque("Requin Basic", 50, 50, "aucun", 6 * 114, 0, 10, 3.0));
        poissonsAtt.add(new PoissonAttaque("Requin Marteau", 30, 30, "rapide", 6 * 114, 114, 15, 5.0));
        poissonsAtt.add(new PoissonAttaque("Requin Baleine", 100, 100, "resistant", 6 * 114, 228, 30, 1.5));
        poissonsAtt.add(new PoissonAttaque("Requin Basic", 50, 50, "aucun", 6 * 114, 342, 10, 3.0));

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
    public ArrayList<PoissonAttaque> getListeEnnemi() {
        return poissonsAtt;
    }


}
