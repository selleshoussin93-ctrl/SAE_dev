package universite_paris8.iut.jbouguerba.sae_jeux.vue;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonAttaque;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RequinVue {
    private Pane coucheEnnemi;

    public RequinVue(Pane coucheEnnemi) {
        this.coucheEnnemi = coucheEnnemi;
    }

    public void ajouterRequin(PoissonAttaque requin) {

        ImageView imv = new ImageView(
                ImageLoader.imageRequin(requin.getNom())
        );

        imv.setFitWidth(114);
        imv.setFitHeight(114);
        imv.setLayoutX(requin.getX());
        imv.setLayoutY(requin.getY());

        coucheEnnemi.getChildren().add(imv);
    }

    public void mettreAJourPosition(int index, double x, double y) {
        ImageView imv = (ImageView) coucheEnnemi.getChildren().get(index);
        imv.setLayoutX(x);
        imv.setLayoutY(y);
    }

    public void viderRequins() {
        coucheEnnemi.getChildren().clear();
    }
}