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

import java.net.URL;


public class HelloController {

    private Environnement env;
    private String outilSelectionne = null;
    private Timeline gameLoop;
    private int temps;

    @FXML
    private ImageView poissonRouge;

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

        Image imgEau = chargerImage("Carré_vert_foncé.jpg");
        Image imgRocher = chargerImage("carré_marron.jpg");

        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {

                ImageView imv = new ImageView();
                imv.setFitWidth(114);   ///128
                imv.setFitHeight(114);
                final int col = j;
                final int ligne = i;


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
                    imv.setImage(chargerImage("Carré_vert_foncé.jpg"));
                } else {
                    imv.setImage(chargerImage("carré_marron.jpg"));
                }


            }
        }

        for (PoissonAttaque e : env.getListeEnnemi()) {
            e.avancer();
            int col = (int) e.getX();
            int ligne = (int) e.getY();
            int index = ligne * env.getLargeur() + col;
            ImageView imv = (ImageView) map.getChildren().get(index);

            if (e.getNom().equals("Requin Basic")) {
                imv.setImage(chargerImage("requin-normal.png"));
            } else if (e.getNom().equals("Requin Marteau")) {
                imv.setImage(chargerImage("requin-marteau.png"));
            } else if (e.getNom().equals("Requin Baleine")) {
                imv.setImage(chargerImage("requin-baleine.png"));
            }
        }
    }




}