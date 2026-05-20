package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonDeffense extends Poisson{
    private int coupUtilisation;

    public PoissonDeffense(String nom, int degats, int pv, String pouvoir, double x, double y, int coupUtilisation){
        super(nom, pv, degats, pouvoir, x, y);
        this.coupUtilisation = coupUtilisation;
        

    }
}
