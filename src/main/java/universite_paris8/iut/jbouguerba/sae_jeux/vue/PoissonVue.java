package universite_paris8.iut.jbouguerba.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.List;

public class PoissonVue {
    private TilePane map;
    private Pane coucheBulle;
    private Image imageBulle;

    public PoissonVue(TilePane map, Pane coucheBulle) {
        this.map = map;
        this.coucheBulle = coucheBulle;
        this.imageBulle = ImageLoader.charger("bulle2.png");
    }

    public void afficherPoisson(int index, String nomImage) {
        // recupere la case de la map pour mettre l'image du poisson
        ImageView imv = (ImageView) map.getChildren().get(index);
        imv.setImage(
                ImageLoader.imagePoisson(nomImage)
        );
    }

    public void effacerPoisson(int index, int typeCase) {
        // remet le fond d'origine de la case (pelle ou destruction)
        ImageView imv = (ImageView) map.getChildren().get(index);
        imv.setImage(
                ImageLoader.imageCase(typeCase)
        );
    }

    public void mettreAJourBulles(List<double[]> positions) {
        coucheBulle.getChildren().clear(); // vide l'affichage d'avant

        // repositionne toutes les bulles de la liste
        for (double[] pos : positions) {
            ImageView imvBulle = new ImageView(imageBulle);
            imvBulle.setFitWidth(30);
            imvBulle.setFitHeight(30);
            imvBulle.setLayoutX(pos[0]);
            imvBulle.setLayoutY(pos[1]);
            coucheBulle.getChildren().add(imvBulle); // ajoute a la vue
        }
    }
}