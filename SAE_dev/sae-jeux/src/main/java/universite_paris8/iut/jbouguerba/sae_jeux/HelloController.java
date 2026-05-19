package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.animation.AnimationTimer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Ennemi;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Environnement;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Tile;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
public class HelloController {
    private AnimationTimer gameLoop;
    private Environnement env;

    @FXML
    private Label welcomeText;


    @FXML
    private TilePane map;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() {
        this.env = new Environnement(6,4);

    }

    public void afficherTerrain(){
        map.getChildren().clear();
        Tile[][] grille = env.getMap();
        Image imgEau = new Image(getClass().getResource("").toExternalForm());
        Image imgRocher = new Image(getClass().getResource("").toExternalForm());

        for (int l = 0; l < env.getHauteur(); l++) {
            for(int c =0; c < env.getLargeur(); c++){

                Pane casePane = new Pane();
                casePane.setPrefSize(40,40);

                ImageView vueImage = new ImageView();
                if (grille[l][c].getType() == TilePane.)
            }
        }
    }




}