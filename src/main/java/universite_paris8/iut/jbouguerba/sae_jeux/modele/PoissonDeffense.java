package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import java.util.ArrayList;

public class PoissonDeffense extends Poisson{
    private int coupUtilisation;
    private Bulle projectile;

    public PoissonDeffense(String nom,int pv, double x, double y, int coupUtilisation){
        super(nom, pv, x, y);
        this.coupUtilisation = coupUtilisation;
        this.projectile = null;

    }

    public void CreerBulle(){   //Cette methode creer une bulle a l'endroit du PoissonDeffense

        if(getNom().equals("crabe.png")){
            this.projectile = new Bulle(10,30,getX()+57,getY()+57,"gele");
            System.out.println("Bulle crabe créée, pouvoir : " + this.projectile.getPouvoir());
        } else if (getNom().equals("poisson rouge")) {
            this.projectile = new Bulle(10,30,getX()+57,getY()+57,"aucun");
        }else if(getNom().equals("poulpe")) {
            this.projectile = new Bulle(10,30,getX()+57,getY()+57,"aucun");

            this.projectile = new Bulle(10,30,getX()+57,getY()+57,"aucun");
        }else {
            this.projectile = new Bulle(50,30,getX()+57,getY()+57,"explose");
        }




    }
    public Bulle getBull(){
        return this.projectile;
    }

    @Override
    public boolean estMort() {
        return super.estMort();
    }

    public void agit() {

        if (this.projectile == null) {
            CreerBulle();
        } else {
            this.projectile.avancer();
        }
        return ;
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