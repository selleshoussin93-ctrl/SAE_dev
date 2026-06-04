package universite_paris8.iut.jbouguerba.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    private TilePane map;

    public TerrainVue(TilePane map) {
        this.map = map;
    }

    // Met à jour l'image d'une case
    public void mettreAJourCase(int index, Image image) {
        ImageView imvCase = (ImageView) map.getChildren().get(index);
        imvCase.setImage(image);
    }

    // Ajoute une case à la grille
    public void ajouterCase(ImageView imv) {
        map.getChildren().add(imv);
    }
}