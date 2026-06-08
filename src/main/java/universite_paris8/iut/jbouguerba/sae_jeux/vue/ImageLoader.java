package universite_paris8.iut.jbouguerba.sae_jeux.vue;

import javafx.scene.image.Image;
import java.net.URL;

public class ImageLoader {

    private static final String CHEMIN = "/universite_paris8/iut/jbouguerba/sae_jeux/";

    public static Image charger(String nomFichier) {
        URL url = ImageLoader.class.getResource(CHEMIN + nomFichier);
        if (url == null) {
            System.out.println("IMAGE INTROUVABLE : " + nomFichier);
            return null;
        }
        return new Image(String.valueOf(url));
    }

    public static Image imageRequin(String nom) {
        if (nom.equals("Requin Basic")) return charger("requin-normal.png");
        if (nom.equals("Requin Marteau")) return charger("requin-marteau.png");
        if (nom.equals("Requin Baleine")) return charger("requin-baleine.png");
        return null;
    }

    public static Image imageCase(int valeur) {
        return valeur == 1
                ? charger("New Piskel-1.png(3).png")
                : charger("Carré_vert_foncéee.png");
    }

    public static Image imagePoisson(String nom) {
        return charger(nom);
    }
}