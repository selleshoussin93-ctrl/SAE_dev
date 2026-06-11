package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class PoissonAttaque extends Poisson {

    private int recompense;
    private double vitesse;
    private int degats;
    private int nbRecul = 0;
    private boolean enRecul = false;
    private double vitesseNormale;
    private boolean ralenti;
    private int tempRalenti;



    public PoissonAttaque(String nom, int pv,int degats, double x, double y, int recompense, double vitesse ) {
        super(nom, pv, x, y);
        this.recompense = recompense;
        this.vitesse = vitesse;
        this.degats = degats;
        this.vitesseNormale = vitesse;
        this.ralenti = false;
        this.tempRalenti=0;
    }

    public void avancer(){

        if (ralenti) {
            tempRalenti++;
            if (tempRalenti >= 5) {
                retablirVitesse();
                tempRalenti = 0;
            }
        }

        if (enRecul) {
            this.setX(this.getX() + 2); // recule de 2 pixels
            nbRecul++;
            if (nbRecul >= 3) {
                enRecul = false;
                nbRecul = 0;
            }
            return;
        }

        this.setX(this.getX() - this.getVitesse());
        /*if(this.getX() <= 0){
            this.setX(6 * 114);
            this.setPv(50);
        }*/

    }


    public void subirAttaque(int degats) {
        super.setPv(super.getPv() - degats);
        enRecul = true;
        nbRecul = 0;
    }

    @Override
    public boolean estMort() {
        return getPv() <= 0;
    }

    public int getRecompense() {
        return this.recompense; }


    public double getVitesse() { return vitesse; }

    public int getPv(){
        return super.getPv();
    }


    public void enleveVie(Bulle e){
        this.pv -= e.getDegats();
    }

    public void toucher(Bulle projectile, PoissonAttaque requin) {
        if (!projectile.estActive()) return; //  ignore les bulles déjà utilisées

        double distanceX = Math.abs(projectile.getX() - requin.getX());
        int ligneBuille = (int)(projectile.getY() / 114);
        int ligneRequin = (int)(requin.getY() / 114);

        if (distanceX < 57 && ligneBuille == ligneRequin) {
            enleveVie(projectile);
            if (projectile.getPouvoir().equals("gele")) {
                this.ralentir();
            }
            projectile.desactiver(); // ✅
        }
    }
    public void ralentir() {
        //System.out.println("Vitesse avant : " + this.vitesse);
        if (!ralenti) {
            this.vitesse = 0;
            this.ralenti = true;
        }
      //  System.out.println("Vitesse après : " + this.vitesse);
    }

    public void retablirVitesse() {
        this.vitesse = this.vitesseNormale;
        this.ralenti = false;
    }
}












