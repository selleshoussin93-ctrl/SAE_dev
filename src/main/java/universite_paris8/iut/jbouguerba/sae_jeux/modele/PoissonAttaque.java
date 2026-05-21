package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonAttaque extends Poisson {

    private int recompense;
    private double vitesse;
    private int degats;
    public PoissonAttaque(String nom, int pv,int degats, double x, double y, int recompense, double vitesse) {
        super(nom, pv, x, y);
        this.recompense = recompense;
        this.vitesse = vitesse;
        this.degats = degats;
    }

    public void avancer(){

        this.setX(this.getX() - this.getVitesse());
        if(this.getX() <= 0){
            this.setX(6 * 114);
        }
        /*this.setX(this.getX() - 5);// si le requin se deplace vers la gauche x diminue
        if(this.getX() <= 0){
            this.setX(6*114);
            this.setY(this.getY() + 114); // descend d'une ligne
            if (this.getY() >= 4 * 114) {
                this.setY(0);
            }
        }*/
    }
    public int getRecompense() {
        return recompense; }


    public double getVitesse() { return vitesse; }

    public int getPv(){
        return super.getPv();
    }

    public void agit(){




    }



}

