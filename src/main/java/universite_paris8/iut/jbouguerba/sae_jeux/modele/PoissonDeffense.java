package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;

public class PoissonDeffense extends Poisson {
    private ArrayList<Bulle> projectiles;
    private int compteur = 10;
    private int intervalle = 10;
    private int degatsContact;
    private ArrayList<PoissonAttaque> ennemis; // liste des ennemis pour détecter les requins

    public PoissonDeffense(String nom, int pv, double x, double y) {
        super(nom, pv, x, y);
        this.projectiles = new ArrayList<>();
        this.degatsContact = getDegatsContact(nom);
    }

    public void setEnnemis(ArrayList<PoissonAttaque> ennemis) {
        this.ennemis = ennemis;
    }

    // Vérifie si un ennemi est sur la même ligne et à gauche du poisson
    public boolean voitUnEnnemi() {
        if (ennemis == null) return false;
        int ligneMoi = (int)(getY() / 114);
        for (PoissonAttaque e : ennemis) {
            int ligneEnnemi = (int)(e.getY() / 114);
            // ✅ requin sur la même ligne ET à droite du poisson
            if (ligneEnnemi == ligneMoi && e.getX() > getX()) {
                return true;
            }
        }
        return false;
    }

    public void etoileDeMer() {
        if (getNom().equals("etoile_mer.png")) {
            setPv(100);
        }
    }

    public void enleveVie(PoissonAttaque r) {
        this.pv -= r.getDegats();
    }

    @Override
    public boolean estMort() {
        return super.estMort();
    }

    public void CreerBulle() {
        if (getNom().equals("crabe.png")) {
            projectiles.add(new Bulle(10, 30, getX()+57, getY()+27, "gele", 0));
        } else if (getNom().equals("poisson_rouge.png")) {
            projectiles.add(new Bulle(10, 30, getX()+57, getY()+57, "aucun", 0));
        } else if (getNom().equals("poulpe.png")) {
            projectiles.add(new Bulle(10, 30, getX()+27, getY()+57, "aucun", 0));
            projectiles.add(new Bulle(10, 30, getX()+87, getY()+57, "aucun", 0));
        } else if (getNom().equals("poissonGlobe2.png")) {
            // Bulle explosive avec rayon de dégâts = 1 case (114px)
            projectiles.add(new Bulle(50, 30, getX()+57, getY()+57, "explose", 114));
        }
    }

    public ArrayList<Bulle> getBull() {
        return this.projectiles;
    }

    public void agit() {
        for (Bulle b : projectiles) {
            b.avancer();
        }

        projectiles.removeIf(b -> !b.estActive());

        System.out.println("voitUnEnnemi : " + voitUnEnnemi() + " ennemis : " + (ennemis == null ? "null" : ennemis.size()));

        if (!voitUnEnnemi()) return;

        compteur++;
        System.out.println("compteur : " + compteur + " nom : " + getNom());

        if (compteur >= intervalle) {
            if (getNom().equals("poissonGlobe2.png")) {
                if (projectiles.isEmpty()) {
                    CreerBulle();
                    compteur = 0;
                }
            } else {
                CreerBulle();
                compteur = 0;
            }
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

    // Dégâts infligés au requin quand il passe sur le poisson
    private int getDegatsContact(String nom) {
        if (nom.equals("etoile_mer.png")) return 0;  // étoile = pas de dégâts contact
        if (nom.equals("poisson_rouge.png")) return 10;
        if (nom.equals("crabe.png")) return 15;
        if (nom.equals("poulpe.png")) return 20;
        if (nom.equals("poissonGlobe2.png")) return 8;
        return 10;
    }

    public String getNom() { return super.getNom(); }
    public double getX() { return super.getX(); }
    public double getY() { return super.getY(); }
    public int getDegatsContact() { return degatsContact; }
}