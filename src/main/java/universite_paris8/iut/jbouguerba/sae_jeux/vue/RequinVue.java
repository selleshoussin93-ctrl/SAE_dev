package universite_paris8.iut.jbouguerba.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonAttaque;

public class RequinVue {

    private Pane coucheEnnemi;

    public RequinVue(Pane coucheEnnemi) {
        this.coucheEnnemi = coucheEnnemi;
    }

    // Ajoute un requin à la vue
    public void ajouterRequin(PoissonAttaque e, Image image) {
        ImageView imv = new ImageView(image);
        imv.setFitWidth(114);
        imv.setFitHeight(114);
        imv.setLayoutX(e.getX());
        imv.setLayoutY(e.getY());
        coucheEnnemi.getChildren().add(imv);
    }

    // Met à jour la position d'un requin
    public void mettreAJourRequin(PoissonAttaque e, int index) {
        ImageView imv = (ImageView) coucheEnnemi.getChildren().get(index);
        imv.setLayoutX(e.getX());
        imv.setLayoutY(e.getY());
    }

    // Supprime un requin
    public void supprimerRequin(int index) {
        coucheEnnemi.getChildren().remove(index);
    }
}