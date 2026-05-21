package universite_paris8.iut.jbouguerba.sae_jeux.modele;

public class Lancement {
    public static void main(String[]args){
        PoissonAttaque requin = new PoissonAttaque("requin",100,10,6,0,20,50);
        PoissonDeffense poissonRouge = new PoissonDeffense("poisson rouge",30,"aucun",0,0,20);
        System.out.println(requin);
        System.out.println(poissonRouge);
        System.out.println(requin.getPv());
    }


}
