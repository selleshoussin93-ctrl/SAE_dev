package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


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
    private IntegerProperty ressourcesProperty;


    public Environnement(int largeur, int hauteur) {
        this.hauteur = map.length;
        this.largeur = map[0].length;

        this.poissonsDeff = new ArrayList<>();
        this.poissonsAtt = new ArrayList<>();
        this.ressourcesProperty = new SimpleIntegerProperty(40);

        poissonsAtt.add(new PoissonAttaque("Requin Basic", 50, 50, 6*114, 0*114, 10, 14.0));
        poissonsAtt.add(new PoissonAttaque("Requin Marteau", 30, 30,  6*114, 1*114, 15, 20.0));
        poissonsAtt.add(new PoissonAttaque("Requin Baleine", 100, 100, 6*114, 2*114, 30, 10.2));


    }

    public ArrayList<Bulle> getListeBulles() {
        ArrayList<Bulle> bulles = new ArrayList<>();
        for (PoissonDeffense p : poissonsDeff) {
            bulles.addAll(p.getBull()); // addAll car getBull() retourne une liste
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

    public ArrayList<PoissonDeffense> getListePoissonsDeffense() {
        return poissonsDeff;
    }

    public void ajouterPoissonDeffense(PoissonDeffense p){
        poissonsDeff.add(p);
    }
    public final int getRessources() {
        return ressourcesProperty.getValue();
    }

    public final void setRessources(int n) {
        ressourcesProperty.setValue(n);
    }

    public final IntegerProperty ressourcesProperty() {
        return ressourcesProperty;
    }

    public void supprimerPoissonDeffense(double x, double y) {
        System.out.println("Suppression poisson à x=" + x + " y=" + y);
        System.out.println("Liste avant : " + poissonsDeff.size());
        poissonsDeff.removeIf(p -> {
            System.out.println("Poisson à x=" + p.getX() + " y=" + p.getY());
            return p.getX() == x && p.getY() == y;
        });
        System.out.println("Liste après : " + poissonsDeff.size());
    }

}
