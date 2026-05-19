package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.animation.AnimationTimer;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Ennemi;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Environnement;

import java.net.URL;


public class HelloController {
    @FXML
    private AnimationTimer gameLoop;


    private Environnement env;
    private String outilSelectionne = null;

    @FXML
    private Label welcomeText;

    @FXML
    private ImageView poissonRouge;

    @FXML
    private ImageView etoileDeMer;


    @FXML
    private TilePane map;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() {
        this.env = new Environnement(6,4);
        creeVueModele();
        poissonRouge.setImage(chargerImage("poisson_rouge.png"));
        etoileDeMer.setImage(chargerImage("etoile_mer.png"));

        poissonRouge.setOnMouseClicked(e -> outilSelectionne = "poisson_rouge.png");
        etoileDeMer.setOnMouseClicked(e -> outilSelectionne = "etoile_mer.png");

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // ici on fera bouger les requins
                mettreAJourVue();
            }
        };
       // gameLoop.start();

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
                imv.setFitWidth(128);
                imv.setFitHeight(128);

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

     /*   for (Ennemi e : env.getListeEnnemi()) {
            ImageView imvEnnemi = new ImageView();
            imvEnnemi.setFitWidth(114.23);
            imvEnnemi.setFitHeight(114.2);

            if (e.getNom().equals("Requin Basic")) {
                imvEnnemi.setImage(chargerImage("requin-noraml.jpg"));
            }
            if (e.getNom().equals("Requin Marteau")) {
                imvEnnemi.setImage(chargerImage("requin-marteau.png"));
            }
            if (e.getNom().equals("Requin Baleine")) {
                imvEnnemi.setImage(chargerImage("requin-balaine.jpg"));
            }

            map.getChildren().add(imvEnnemi);
        }*/
    }

    private void mettreAJourVue() {
        int nbCases = env.getHauteur() * env.getLargeur();

        for (int i = 0; i < env.getListeEnnemi().size(); i++) {
            Ennemi e = env.getListeEnnemi().get(i);
            e.avancer();

            int col = (int) e.getX();
            int ligne = (int) e.getY();
            int nouvelIndex = ligne * env.getLargeur() + col;

            ImageView imv = (ImageView) map.getChildren().get(nouvelIndex);

        }
    }




}