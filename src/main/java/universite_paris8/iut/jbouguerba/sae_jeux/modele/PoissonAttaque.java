package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonAttaque extends Poisson {

    private int recompense;
    private double vitesse;

    public PoissonAttaque(String nom, int degats, int pv, String pouvoir, double x, double y, int recompense, double vitesse) {
        super(nom, pv, degats, pouvoir, x, y);
        this.recompense = recompense;
        this.vitesse = vitesse;
    }

    public void avancer(){
        this.setX(this.getX()-2);// si le requin se deplace vers la gauche x diminue
        if(this.getX() <= 0){
            this.setX(7);
            this.setY(this.getY() + 1); // descend d'une ligne
            if (this.getY() >= 4) {
                this.setY(0);
            }
        }
    }
    public int getRecompense() {
        return recompense; }


    public double getVitesse() { return vitesse; }

    public int getPv(){
        return super.getPv();
    }
    



}

