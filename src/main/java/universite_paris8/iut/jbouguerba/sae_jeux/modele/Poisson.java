package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public abstract class Poisson {
    private String nom;
    protected int pv;
    private double x , y ;



    public Poisson(String nom, int pv, double x, double y) {
        this.nom = nom;
        this.pv = pv;
        this.x = x;
        this.y = y;
    }


    public String getNom() { return nom; }
    public int getPv() { return pv; }
    public double getX() {
        return this.x;
    }
    public void setX(double x) { this.x = x; }
    public double getY() {
        return this.y;
    }
    public void setY(double y) { this.y = y; }
    public void setPv(int pv){
        this.pv = pv;

    }


    public boolean estMort() {
        return getPv() <= 0;
    }





}
