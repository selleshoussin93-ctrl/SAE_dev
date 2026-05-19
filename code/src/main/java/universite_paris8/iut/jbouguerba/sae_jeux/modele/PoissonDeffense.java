package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonDeffense extends Poisson{
    private int coupUtilisation;

    public PoissonDeffense(String nom, int degats, int pv, String pouvoir,double x, double y,  int coupUtilisation){
        super(nom, degats, pv, pouvoir, x ,y);
        this.coupUtilisation = coupUtilisation;

    }
    public int getPv(){
        return super.getPv();
    }
    public String getNom(){
        return super.getNom();
    }
    public
}
