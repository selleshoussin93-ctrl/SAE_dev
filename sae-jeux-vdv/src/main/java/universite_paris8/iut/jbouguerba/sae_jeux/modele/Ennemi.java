package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class Ennemi extends Poisson {

    private int recompense;
    private double vitesse;

    public Ennemi(String nom, int degats, int pv, String pouvoir, double x, double y, int recompense, double vitesse) {
        super(nom, pv, degats, pouvoir, x, y);
        this.recompense = recompense;
        this.vitesse = vitesse;
    }

    public void avancer(){
        this.setX(this.getX() - 1); // si le requin se deplace vers la gauche x diminue
    }
    public int getRecompense() { return recompense; }
    public double getVitesse() { return vitesse; }

}
