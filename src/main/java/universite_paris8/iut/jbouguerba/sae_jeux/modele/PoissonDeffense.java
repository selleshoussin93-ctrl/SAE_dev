package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonDeffense extends Poisson{
    private int coupUtilisation;
    private Bulle projectile;
    private String Pouvoir;
    public PoissonDeffense(String nom,int pv, String pouvoir, double x, double y, int coupUtilisation, Bulle projectile){
        super(nom, pv, x, y);
        this.coupUtilisation = coupUtilisation;
        this.projectile = projectile;
        this.Pouvoir = pouvoir;

    }

    public void agit(){




    }
}
