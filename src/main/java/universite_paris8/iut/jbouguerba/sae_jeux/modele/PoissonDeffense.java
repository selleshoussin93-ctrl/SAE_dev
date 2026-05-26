package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonDeffense extends Poisson{
    private int coupUtilisation;
    private Bulle projectile;

    public PoissonDeffense(String nom,int pv, double x, double y, int coupUtilisation){
        super(nom, pv, x, y);
        this.coupUtilisation = coupUtilisation;
        this.projectile = null;

    }

    public void CreerBulle(){//Cette methode creer une bulle a l'endroit du PoissonDeffense

        if(getNom()=="crabe"){
            this.projectile = new Bulle(10,10,getX(),getY(),"gele");
        } else if (getNom() == "poisson rouge") {
            this.projectile = new Bulle(10,10,getX(),getY(),"aucun");
        }else if(getNom() =="poulpe") {
            this.projectile = new Bulle(10,10,getX(),getY(),"aucun");

            this.projectile = new Bulle(10,10,getX(),getY(),"aucun");
        }else {
            this.projectile = new Bulle(50,10,getX(),getY(),"explose");
        }

    }

    @Override
    public boolean estMort() {
        return super.estMort();
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