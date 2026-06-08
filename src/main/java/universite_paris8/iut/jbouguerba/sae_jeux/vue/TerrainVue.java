package universite_paris8.iut.jbouguerba.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TerrainVue {
    private TilePane map;

    public TerrainVue(TilePane map) {
        this.map = map;
    }

    public void ajouterCase(ImageView imv) {
        map.getChildren().add(imv);
    }

    public void mettreAJourCase(int index, Image image) {
        ImageView imv = (ImageView) map.getChildren().get(index);
        imv.setImage(image);
    }
}