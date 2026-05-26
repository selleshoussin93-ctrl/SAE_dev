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
    private ArrayList<Bulle> bulles;

    private int ressources;

    public Environnement(int largeur, int hauteur) {
        this.hauteur = map.length;
        this.largeur = map[0].length;

        this.poissonsDeff = new ArrayList<>();
        this.poissonsAtt = new ArrayList<>();
        this.ressources = 40;

        poissonsAtt.add(new PoissonAttaque("Requin Basic", 50, 50, 6*114, 0*114, 10, 4.0));
        poissonsAtt.add(new PoissonAttaque("Requin Marteau", 30, 30,  6*114, 1*114, 15, 10.0));
        poissonsAtt.add(new PoissonAttaque("Requin Baleine", 100, 100, 6*114, 2*114, 30, 0.2));




    }


    public ArrayList<Bulle> getListeBulles() {
        ArrayList<Bulle> bulles = new ArrayList<>();
        for (PoissonDeffense p : poissonsDeff) {
            if (p.getBull() != null) {
                bulles.add(p.getBull());
            }
        }
        return bulles;
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
