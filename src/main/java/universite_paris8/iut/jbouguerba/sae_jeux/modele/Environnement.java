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


    private IntegerProperty ressourcesProperty;
    private int numVague = 0;
    private List<List<PoissonAttaque>> vagues;
    private boolean perdu = false;




    public Environnement(int largeur, int hauteur) {
        this.hauteur = map.length;
        this.largeur = map[0].length;

        this.poissonsDeff = new ArrayList<>();
        this.poissonsAtt = new ArrayList<>();
        this.ressourcesProperty = new SimpleIntegerProperty(80);

        initialiserVagues();
        lancerVague();
    }

    private void initialiserVagues() {
        vagues = new ArrayList<>();


        // Vague 1
        List<PoissonAttaque> vague1 = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            vague1.add(new PoissonAttaque("Requin Basic", 20, 50, (6+i)*114, i % 4 * 114, 10, 14.0));
        vague1.add(new PoissonAttaque("Requin Marteau", 10, 30, 11*114, 1*114, 15, 20.0));
        vague1.add(new PoissonAttaque("Requin Baleine", 50, 100, 6*114, 2*114, 30, 10.2));
        vagues.add(vague1);

        // Vague 2
        List<PoissonAttaque> vague2 = new ArrayList<>();
        for (int i = 0; i < 7; i++)
            vague2.add(new PoissonAttaque("Requin Basic", 50, 50, (6+i)*114, i % 4 * 114, 10, 14.0));
        for (int i = 0; i < 4; i++)
            vague2.add(new PoissonAttaque("Requin Marteau", 30, 30, (9+i)*114, i % 4 * 114, 15, 20.0));
        for (int i = 0; i < 2; i++)
            vague2.add(new PoissonAttaque("Requin Baleine", 100, 100, (11+i)*114, i % 4 * 114, 30, 10.2));
        vagues.add(vague2);

        // Vague 3
        List<PoissonAttaque> vague3 = new ArrayList<>();
        for (int i = 0; i < 7; i++)
            vague3.add(new PoissonAttaque("Requin Basic", 50, 50, (6+i)*114, i % 4 * 114, 10, 14.0));
        for (int i = 0; i < 7; i++)
            vague3.add(new PoissonAttaque("Requin Marteau", 30, 30, (9+i)*114, i % 4 * 114, 15, 20.0));
        for (int i = 0; i < 7; i++)
            vague3.add(new PoissonAttaque("Requin Baleine", 100, 100, (12+i)*114, i % 4 * 114, 30, 10.2));
        vagues.add(vague3);
  }

    public void lancerVague() {
        if (numVague < vagues.size()) {
            poissonsAtt.addAll(vagues.get(numVague));
            numVague++;
        }
    }

    public boolean vagueTerminee() {
        return poissonsAtt.isEmpty();
    }

    public boolean aUneProchainerVague() {
        return numVague < vagues.size();
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

    public void ajouterPoissonDeffense(PoissonDeffense p){
        poissonsDeff.add(p);
    }
    public final int getRessources() {
        return ressourcesProperty.getValue();
    }

    public final void setRessources(int n) {
        ressourcesProperty.setValue(n);
    }
    public boolean estPerdu() { return perdu; }

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
    public List<PoissonAttaque> gererCollisions() {
        List<PoissonAttaque> morts = new ArrayList<>();
        for (PoissonDeffense p : poissonsDeff) {
            for (Bulle b : p.getBull()) {
                if(!b.estActive()) continue;
                for (PoissonAttaque requin : poissonsAtt) {
                    double distanceX = Math.abs(b.getX() - requin.getX());
                    int ligneBulle  = (int)(b.getY() / 114);
                    int ligneRequin = (int)(requin.getY() / 114);

                    if (distanceX < 57 && ligneBulle == ligneRequin) {
                        requin.enleveVie(b);

                        if (b.getPouvoir().equals("gele")) {
                            requin.ralentir();
                        }

                        b.desactiver();// la bulle disparaît après impact
                        if(requin.estMort()){
                            morts.add(requin);
                        }
                        break;
                    }
                }
            }

        }
        for (PoissonAttaque mort : morts) {
            setRessources(getRessources() + mort.getRecompense());
        }
        poissonsAtt.removeAll(morts);
        return morts;
    }
    // Avancer tous les requins
    public void avancerRequins() {
        List<PoissonAttaque> sortis = new ArrayList<>();
        for (PoissonAttaque e : poissonsAtt) {
            e.avancer();
            if(e.getX() <= 0){
                sortis.add(e);
                perdu = true ;


            }
        }
        poissonsAtt.removeAll(sortis);
    }
    // Vérifier les collisions requin/case
    public List<int[]> verifierCollisionsRequins() {
        List<int[]> casesDetruites = new ArrayList<>();
        List<PoissonAttaque> morts = new ArrayList<>();

        for (PoissonAttaque e : poissonsAtt) {
            int col = (int)(e.getX() / 114);
            int ligne = (int)(e.getY() / 114);
            if (col >= 0 && col < getLargeur() && ligne >= 0 && ligne < getHauteur()) {
                if (map[ligne][col] == 2) {
                    PoissonDeffense pdef = getPoissonDeffenseAt(col * 114, ligne * 114);
                    if (pdef != null) {
                        e.subirAttaque(pdef.getDegatsContact());
                    }
                    // Le poisson est toujours détruit quand un requin passe dessus
                    supprimerPoissonDeffense(col * 114, ligne * 114);
                    casesDetruites.add(new int[]{ligne, col});

                    if (e.estMort()) {
                        morts.add(e);

                    }
                }
            }
        }
        for (PoissonAttaque mort : morts) {
            setRessources(getRessources() + mort.getRecompense());
        }
        poissonsAtt.removeAll(morts);
        return casesDetruites; // le controller met à jour la vue avec ces cases
    }

    // Fais agir les poissons de défense
    public void agirPoissonsDeffense() {
        for (PoissonDeffense p : poissonsDeff) {
            p.agit();
            if (p.getNom().equals("etoile_mer.png")) {
                int ligneEtoile = (int)(p.getY() / 114);
                for (PoissonAttaque requin : poissonsAtt) {
                    int ligneRequin = (int)(requin.getY() / 114);
                    if (ligneRequin == ligneEtoile) {
                        requin.ralentir();
                    }
                }
            }
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
            map[ligne][col] = mapOriginale[ligne][col];   // ici
            supprimerPoissonDeffense(col * 114, ligne * 114);
            return true;
        }
        return false;
    }
    // Retourne true si le poisson a bien été posé
    public boolean placerPoisson(String nom, int col, int ligne) {
        // Vérifie que la case n'est pas déjà occupée par un poisson
        if (map[ligne][col] == 2) {
            return false;
        }
        int prix = PoissonDeffense.getPrix(nom);
        if (getRessources() >= prix) {
            setRessources(getRessources() - prix);
            map[ligne][col] = 2;   // ici
            ajouterPoissonDeffense(
                    new PoissonDeffense(nom, 100, col * 114, ligne * 114)
            );
            return true;
        }
        return false;
    }

    public PoissonDeffense getPoissonDeffenseAt(double x, double y) {
        for (PoissonDeffense p : poissonsDeff) {
            if (p.getX() == x && p.getY() == y) return p;
        }
        return null;
    }
    public int getNumVague() { return numVague; }


}
