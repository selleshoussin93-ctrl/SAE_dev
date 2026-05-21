package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class Bulle {

    private double x,y;
    private int degats;
    private double vitesse;
    private String pouvoir;


    public Bulle(int degats, double vitesse, double x, double y, String pouvoir){

        this.vitesse = vitesse;
        this.degats = degats;
        this.pouvoir = pouvoir;
        this.y =y;
        this.x =x;

    }
    public void avancer(){
        this.setX(this.getX()+vitesse);// si la bulle se deplace vers la droite x augmente
        if(this.getX() >= 6){
            this.setX(0);
            this.setY(this.getY() + 1); // descend d'une ligne
            if (this.getY() >= 4) {
                this.setY(0);
            }
        }
    }



    public double getX(){return this.x;}
    public void setX(double x) {this.x = x;}
    public double getY(){return this.y;}
    public void setY(double y){this.y = y;}
    public int getDegats(){
        return this.degats;
    }
}
