package universite_paris8.iut.jbouguerba.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.jbouguerba.sae_jeux.controller.GestionClic;

public class TerrainVue {
    private TilePane map;


    public TerrainVue(TilePane map) {
        this.map = map;
    }

    public void ajouterCase(ImageView imv) {
        map.getChildren().add(imv);
    }

    public void mettreAJourCase(int index, int typeCase) {
        ImageView imv = (ImageView) map.getChildren().get(index);
        imv.setImage(ImageLoader.imageCase(typeCase));
    }

    public void initialiserTerrain(int[][] map, GestionClic gestionClic) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                ImageView imv = new ImageView(
                        ImageLoader.imageCase(map[i][j])
                );
                imv.setFitWidth(114);
                imv.setFitHeight(114);
                imv.setPickOnBounds(true);

                final int col = j;
                final int l = i;
                imv.setOnMouseClicked(e -> gestionClic.gererClic(col, l));

                ajouterCase(imv); //  uniquement l'affichage
            }
        }
    }

}