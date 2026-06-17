package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class Bulle {

    private double x, y;
    private int degats;
    private double vitesse;
    private String pouvoir;
    private boolean active = true;
    private boolean aExplose = false;
    private int rayonExplosion;

    public Bulle(int degats, double vitesse, double x, double y, String pouvoir, int rayonExplosion) {
        this.vitesse = vitesse;
        this.degats = degats;
        this.pouvoir = pouvoir;
        this.y = y;
        this.x = x;
        this.rayonExplosion = rayonExplosion;
        this.active = true;
    }

    public void avancer() {
        this.setX(this.getX() + vitesse); // bulle va vers la gauche
    }

    public double getX() { return this.x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return this.y; }
    public void setY(double y) { this.y = y; }
    public int getDegats() { return this.degats; }
    public String getPouvoir() { return this.pouvoir; }
    public boolean estActive() { return active; }
    public void desactiver() { active = false; }
    public boolean aExplose() { return aExplose; }
    public int getRayonExplosion() { return rayonExplosion; }

    public void exploser() {
        aExplose = true;
        desactiver();
    }
}