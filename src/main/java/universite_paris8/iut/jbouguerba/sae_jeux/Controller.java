package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.fxml.FXML;

import javafx.scene.input.MouseEvent;
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


public class Controller {

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
    private


    @FXML
    private TilePane map;


    public void initialize() {
        this.env = new Environnement(6,4);
        creeVueModele();
        poissonRouge.setImage(chargerImage("poisson_rouge.png"));
        etoileDeMer.setImage(chargerImage("etoile_mer.png"));

        poissonRouge.setOnMouseClicked(e -> {outilSelectionne = "poisson_rouge.png";
        System.out.println("poisson rouge");}
        );
        etoileDeMer.setOnMouseClicked(e -> outilSelectionne = "etoile_mer.png");


        initAnimation();
        gameLoop.play();

        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
           // ImageView imv = new ImageView(chargerImage("requin-normal.png"));
            ImageView imv = new ImageView();
            imv.setFitWidth(114);
            imv.setFitHeight(114);
            imv.setLayoutX(e.getX() * 114);
            imv.setLayoutY(e.getY() * 114);

            if (e.getNom().equals("Requin Basic")) {
                imv.setImage(chargerImage("requin-normal.png"));
            } else if (e.getNom().equals("Requin Marteau")) {
                imv.setImage(chargerImage("requin-marteau.png"));
            } else if (e.getNom().equals("Requin Baleine")) {
                imv.setImage(chargerImage("requin-baleine.png"));
            }


            coucheEnnemi.getChildren().add(imv);

        }

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.010),  //0.017
                (ev -> {
                    if (temps % 50 == 0) {
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
           // System.out.println("IMAGE INTROUVABLE : " + nomFichier);
            return null;
        }
        return new Image(String.valueOf(url));
    }

    public void creeVueModele(){

        Image imgEau = chargerImage("Carré_vert_foncéee.png");
        Image imgRocher = chargerImage("New Piskel-1.png(3).png");


        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {

                ImageView imv = new ImageView();
                imv.setFitWidth(114);   ///128
                imv.setFitHeight(114);
                final int col = j;
                final int l = i;


                imv.setOnMouseClicked(e -> {
                   // System.out.println("CLIC DETECTE" );
                    if (outilSelectionne != null) {
                       // System.out.println("Pret");
                        imv.setImage(chargerImage(outilSelectionne));
                        env.getMap()[l][col] = 2;
                     //   System.out.println("MAP["+l+"]["+col+"] = " + env.getMap()[l][col]);



                    }
                });


                if (env.getMap()[i][j] == 0) {
                    imv.setImage(imgEau);
                } else {
                    imv.setImage(imgRocher);
                }

                map.getChildren().add(imv);
            }
        }

    }

    private void mettreAJourVue() {

        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
            e.avancer();
            ImageView imv = (ImageView) coucheEnnemi.getChildren().get(env.getListePoissonsAttaque().indexOf(e));
            imv.setLayoutX(e.getX());
            imv.setLayoutY(e.getY() ); //*114

            int col = (int)(e.getX() / 114);
            int ligne = (int)(e.getY() / 114);


            if (col >= 0 && col < env.getLargeur() && ligne >= 0 && ligne < env.getHauteur()) {
                if (env.getMap()[ligne][col] == 2) {
                    e.subirAttaque(10);
                    if (e.estMort()) {
                        env.getMap()[ligne][col ] = 0;
                        ImageView imvCase = (ImageView) map.getChildren().get(ligne * env.getLargeur() + col);
                        imvCase.setImage(chargerImage("Carré_vert_foncéee.png"));

                    }
                }
            }


        }
    }






}