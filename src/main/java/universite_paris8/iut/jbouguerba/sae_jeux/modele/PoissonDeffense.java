package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;

public class PoissonDeffense extends Poisson {
    private ArrayList<Bulle> projectiles;
    private int compteur = 10;
    private int intervalle = 10;
    private int degatsContact;

    public PoissonDeffense(String nom, int pv, double x, double y) {
        super(nom, pv, x, y);
        this.projectiles = new ArrayList<>();
        this.degatsContact = getDegatsContact(nom);
    }

    public void CreerBulle() {
        // verifie le nom pour savoir quelle bulle tirer
        if (getNom().equals("crabe.png")) {
            projectiles.add(new Bulle(10, 30, getX()+57, getY()+27, "gele"));
        } else if (getNom().equals("poisson_rouge.png")) {
            projectiles.add(new Bulle(10, 30, getX()+57, getY()+57, "aucun"));
        } else if (getNom().equals("poulpe.png")) {
            // le poulpe tire deux bulles d'un coup
            projectiles.add(new Bulle(10, 30, getX()+27, getY()+57, "aucun"));
            projectiles.add(new Bulle(10, 30, getX()+87, getY()+57, "aucun"));
        }
        // pas de bloc pour le globe ici (il explose direct dans environnement)
    }

    public ArrayList<Bulle> getBull() {
        return this.projectiles;
    }

    public void agit() {
        compteur++;

        // fait avancer les projectiles de ce poisson
        for (Bulle b : projectiles) {
            b.avancer();
        }

        // vire les bulles qui ont touche un requin
        projectiles.removeIf(b -> !b.estActive());

        // reset du compteur quand on arrive a l'intervalle
        if (compteur >= intervalle) {
            CreerBulle();
            compteur = 0;
        }
    }

    public static int getPrix(String nom) {
        if (nom.equals("poisson_rouge.png")) return 10;
        if (nom.equals("etoile_mer.png")) return 10;
        if (nom.equals("crabe.png")) return 20;
        if (nom.equals("poulpe.png")) return 100;
        if (nom.equals("poissonGlobe2.png")) return 50;
        return 0;
    }

    private int getDegatsContact(String nom) {
        if (nom.equals("etoile_mer.png")) return 0; // l'etoile ralenti juste
        if (nom.equals("poisson_rouge.png")) return 10;
        if (nom.equals("crabe.png")) return 15;
        if (nom.equals("poulpe.png")) return 20;
        if (nom.equals("poissonGlobe2.png")) return 0; // degats geres par l'explosion
        return 10;
    }

    public int getDegatsContact() {
        return degatsContact;
    }
}