package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public abstract class Poisson {
    private String nom;
    private int degats;
    private int pv;
    private String pouvoir;
    private int x , y ;
   // private String image;

    public Poisson(String nom, int degats, int pv, String pouvoir) {
        this.nom = nom;
        this.degats = degats;
        this.pv = pv;
        this.pouvoir = pouvoir;
    }

}
