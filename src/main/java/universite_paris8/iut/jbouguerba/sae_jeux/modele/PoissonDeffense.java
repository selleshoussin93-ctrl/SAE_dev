package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;

public class PoissonDeffense extends Poisson{
    private int coupUtilisation;
    private ArrayList<Bulle> projectiles;
    private int compteur = 10;
    private int intervalle = 10;

    public PoissonDeffense(String nom,int pv, double x, double y, int coupUtilisation){
        super(nom, pv, x, y);
        this.coupUtilisation = coupUtilisation;
        this.projectiles = new ArrayList<>();
    }
    public void etoileDeMer(){
        if(getNom().equals("etoile_mer.png")){
            setPv(100);
        }

    }

    public void CreerBulle() {

        if (getNom().equals("crabe.png")) {
            projectiles.add(new Bulle(10, 30, getX()+57, getY()+27, "gele"));
        } else if (getNom().equals("poisson_rouge.png")) { //  vérifie le nom exact
            projectiles.add(new Bulle(10, 30, getX()+57, getY()+57, "aucun"));
        } else if (getNom().equals("poulpe.png")) {
            projectiles.add(new Bulle(10, 30, getX()+27, getY()+57, "aucun"));
            projectiles.add(new Bulle(10, 30, getX()+87, getY()+57, "aucun"));}
        else if(getNom().equals("poissonGlobe2.png")){
            projectiles.add(new Bulle(50, 30, getX()+57, getY()+57, "explose"));
            //System.out.println("Bulle explose créée !");
        }
    }
    public ArrayList<Bulle> getBull(){
        return this.projectiles;
    }

    @Override
    public boolean estMort() {
        return super.estMort();
    }

    public void agit() {
        compteur++;
        //System.out.println("compteur : " + compteur + " nom : " + getNom());

        for (Bulle b : projectiles) {
            b.avancer();
        }

        if (compteur >= intervalle) {

          // projectiles.clear();
            CreerBulle();
            compteur = 0;
        }
    }
    public static int getPrix(String nom) {
        if (nom.equals("poisson_rouge.png")) return 10;
        if (nom.equals("etoile_mer.png")) return 5;
        if (nom.equals("crabe.png")) return 10;
        if (nom.equals("poulpe.png")) return 20;
        if (nom.equals("poissonGlobe2.png")) return 15;
        return 0;
    }


    public String getNom(){

        return super.getNom();
    }
    public double getX(){

        return super.getX();
    }
    public double getY(){

        return super.getY();
    }
}