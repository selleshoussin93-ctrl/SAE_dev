package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.fxml.FXML;

import javafx.scene.layout.TilePane;

import universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonAttaque;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Environnement;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

import java.net.URL;


public class HelloController {

    private Environnement env;
    private String outilSelectionne = null;
    private Timeline gameLoop;
    private int temps;

    @FXML
    private ImageView poissonRouge;

    @FXML
    private Pane coucheEnnemi;

    @FXML
    private ImageView etoileDeMer;

    @FXML
    private ImageView crabe;

    @FXML
    private ImageView poulpe;

    @FXML
    private ImageView poissonGlobe;

    @FXML
    private TilePane map;


    public void initialize() {
        this.env = new Environnement(6,4);
        creeVueModele();
        poissonRouge.setImage(chargerImage("poisson_rouge.png"));
        etoileDeMer.setImage(chargerImage("etoile_mer.png"));

        poissonRouge.setOnMouseClicked(e -> outilSelectionne = "poisson_rouge.png");
        etoileDeMer.setOnMouseClicked(e -> outilSelectionne = "etoile_mer.png");

        initAnimation();
        gameLoop.play();

        for (PoissonAttaque e : env.getListeEnnemi()) {
            ImageView imv = new ImageView(chargerImage("requin-normal.png"));
            imv.setFitWidth(114);
            imv.setFitHeight(114);
            imv.setLayoutX(e.getX() * 114);
            imv.setLayoutY(e.getY() * 114);
            coucheEnnemi.getChildren().add(imv);
        }

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                (ev -> {
                    if (temps % 60 == 0) {
                        mettreAJourVue();
                    }
                    temps++;
                })
        );

        gameLoop.getKeyFrames().add(kf);
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

        Image imgEau = chargerImage("fond-chemin-droit.png");
        Image imgRocher = chargerImage("fond-chemin-droit.png");

        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {

                ImageView imv = new ImageView();
                imv.setFitWidth(114);   ///128
                imv.setFitHeight(114);
                imv.setPreserveRatio(false);



                if (env.getMap()[i][j] == 0) {
                    imv.setImage(imgEau);
                } else {
                    imv.setImage(imgRocher);
                }

                imv.setOnMouseClicked(e -> {
                    if (outilSelectionne != null) {
                        imv.setImage(chargerImage(outilSelectionne));
                    }
                });

                map.getChildren().add(imv);
            }
        }

    }

    private void mettreAJourVue() {

        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {
                ImageView imv = (ImageView) map.getChildren().get(i * env.getLargeur() + j);
                if (env.getMap()[i][j] == 0) {
                    imv.setImage(chargerImage("fond-chemin-droit.png"));
                } else {
                    imv.setImage(chargerImage("carré_marron.jpg"));
                }


            }
        }

        for (PoissonAttaque e : env.getListeEnnemi()) {
            e.avancer();
            ImageView imv = (ImageView) coucheEnnemi.getChildren().get(env.getListeEnnemi().indexOf(e));
            imv.setLayoutX(e.getX());
            imv.setLayoutY(e.getY() * 114);
        }
    }




}