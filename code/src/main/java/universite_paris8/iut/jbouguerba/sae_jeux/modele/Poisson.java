package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public abstract class Poisson {
    private String nom;
    private int degats;
    private int pv;
    private String pouvoir;
    private double x , y ;



    public Poisson(String nom, int degats, int pv, String pouvoir, double x, double y) {
        this.nom = nom;
        this.degats = degats;
        this.pv = pv;
        this.pouvoir = pouvoir;
        this.x = x;
        this.y = y;
    }
    public String getNom() { return this.nom; }

    public int getPv() { return this.pv; }

    public int getDegats() { return this.degats; }

    public String getPouvoir() { return this.pouvoir; }

    public double getX() {
        return this.x;
    }

    public void setX(double x) { this.x = x; }

    public double getY() {
        return this.y;
    }

    public void setY(double y) { this.y = y; }


    public boolean Estmort(){
        if(getPv()==0){
            return true;
        }
        return false;
    }
}