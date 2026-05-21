package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonDeffense extends Poisson{
    private int coupUtilisation;
    private Bulle projectile;

    public PoissonDeffense(String nom,int pv, String pouvoir, double x, double y, int coupUtilisation){
        super(nom, pv, x, y);
        this.coupUtilisation = coupUtilisation;
        this.projectile = null;

    }

    public void tirer(){

        this.projectile = new Bulle(30,10,getX(),getY(),"gele");
    }


    public void agit(){

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