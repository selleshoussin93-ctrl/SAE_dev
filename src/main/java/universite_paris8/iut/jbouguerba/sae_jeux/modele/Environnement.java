package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;
import java.util.List;

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
    public void gererCollisions() {
        for (PoissonDeffense p : poissonsDeff) {
            for (Bulle b : p.getBull()) {
                for (PoissonAttaque requin : poissonsAtt) {
                    requin.toucher(b, requin);
                }
            }
        }
    }
    // Avancer tous les requins
    public void avancerRequins() {
        for (PoissonAttaque e : poissonsAtt) {
            e.avancer();
        }
    }
    // Vérifier les collisions requin/case
    public List<int[]> verifierCollisionsRequins() {
        List<int[]> casesDetruites = new ArrayList<>();
        for (PoissonAttaque e : poissonsAtt) {
            int col = (int)(e.getX() / 114);
            int ligne = (int)(e.getY() / 114);
            if (col >= 0 && col < getLargeur() && ligne >= 0 && ligne < getHauteur()) {
                if (map[ligne][col] == 2) {
                    e.subirAttaque(10);
                    if (e.estMort()) {
                        supprimerPoissonDeffense(col * 114, ligne * 114);
                        casesDetruites.add(new int[]{ligne, col});
                    }
                }
            }
        }
        return casesDetruites; // le controller met à jour la vue avec ces cases
    }

    // Fais agir les poissons de défense
    public void agirPoissonsDeffense() {
        for (PoissonDeffense p : poissonsDeff) {
            p.agit();
        }
    }
    // Collecte la positions des bulles
    public List<double[]> getPositionsBulles() {
        List<double[]> positions = new ArrayList<>();
        for (PoissonDeffense p : poissonsDeff) {
            for (Bulle b : p.getBull()) {
                positions.add(new double[]{b.getX(), b.getY()});
            }
        }
        return positions;
    }
    // Retourne true si la pelle a bien supprimé un poisson
    public boolean utiliserPelle(int col, int ligne, int[][] mapOriginale) {
        if (map[ligne][col] == 2) {
            map[ligne][col] = mapOriginale[ligne][col];
            supprimerPoissonDeffense(col * 114, ligne * 114);
            return true;
        }
        return false;
    }
    // Retourne true si le poisson a bien été posé
    public boolean placerPoisson(String nom, int col, int ligne, int prix) {
        if (getRessources() >= prix) {
            setRessources(getRessources() - prix);
            map[ligne][col] = 2;
            ajouterPoissonDeffense(
                    new PoissonDeffense(nom, 100, col * 114, ligne * 114, 10)
            );
            return true;
        }
        return false;
    }

}
