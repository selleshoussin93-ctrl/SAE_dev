package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.animation.AnimationTimer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Environnement;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Tile;
import java.net.URL;


public class HelloController {
    @FXML
    private AnimationTimer gameLoop;

    @FXML
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
        creeVueModele();
    }

    private Image chargerImage(String nomFichier){
        URL url = getClass().getResource(nomFichier);
        if (url == null) {
            System.out.println("IMAGE INTROUVABLE : " + nomFichier);
            return null;
        }
        return new Image(String.valueOf(url));
    }

    public void creeVueModele(){
       // Tile[][] grille = env.getMap();


        Image imgEau = chargerImage("Carré_vert_foncé.jpg");
        Image imgRocher = chargerImage("carré_marron.jpg");

        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {

                ImageView imv = new ImageView();
                imv.setFitWidth(114.23);
                imv.setFitHeight(114.2);

                if (env.getMap()[i][j] == 0) {
                    imv.setImage(imgEau);
                } else {
                    imv.setImage(imgRocher);
                }

                map.getChildren().add(imv);
            }
        }
    }




}