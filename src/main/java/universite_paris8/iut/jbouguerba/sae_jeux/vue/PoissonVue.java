package universite_paris8.iut.jbouguerba.sae_jeux.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Bulle;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Poisson;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonDeffense;

import java.util.ArrayList;

public class PoissonVue {

    private Pane coucheBulle;
    private Image imageBulle;
    private ArrayList<PoissonDeffense> poissonDeffenses;

    public PoissonVue(Pane coucheBulle, Image imageBulle) {
        this.coucheBulle = coucheBulle;
        this.imageBulle = imageBulle;
        this.poissonDeffenses = new ArrayList<>();
    }



    public void afficherPoisson(ArrayList<PoissonDeffense> poissonDeffenses){

        for(PoissonDeffense p : poissonDeffenses){




        }



    }
    // Met à jour les bulles à l'écran
    public void mettreAJourBulles(ArrayList<PoissonDeffense> poissons) {
        coucheBulle.getChildren().clear();
        for (PoissonDeffense p : poissons) {
            for (Bulle b : p.getBull()) {
                ImageView imvBulle = new ImageView(imageBulle);
                imvBulle.setFitWidth(30);
                imvBulle.setFitHeight(30);
                imvBulle.setLayoutX(b.getX());
                imvBulle.setLayoutY(b.getY());
                coucheBulle.getChildren().add(imvBulle);
            }
        }
    }
}