package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.fxml.FXML;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

import universite_paris8.iut.jbouguerba.sae_jeux.modele.Bulle;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonAttaque;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.Environnement;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonDeffense;

import java.net.URL;


public class Controller {

    private Environnement env;
    private String outilSelectionne = null;
    private Timeline gameLoop;
    private int temps;
    private int[][] mapOriginale;

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
    private ImageView pelle;

    @FXML
    private TilePane map;


    @FXML
    private Pane coucheBulle;


    public void initialize() {
        this.env = new Environnement(6,4);
        creeVueModele();
        poissonRouge.setImage(chargerImage("poisson_rouge.png"));
        etoileDeMer.setImage(chargerImage("etoile_mer.png"));
        crabe.setImage(chargerImage("crabe.png"));
        poissonRouge.setOnMouseClicked(e -> {outilSelectionne = "poisson_rouge.png";
            System.out.println("poisson rouge");}
        );
        etoileDeMer.setOnMouseClicked(e -> outilSelectionne = "etoile_mer.png");
        crabe.setOnMouseClicked(e -> outilSelectionne = "crabe.png");

        pelle.setImage(chargerImage("pelle.png"));
        pelle.setOnMouseClicked(e -> {
            outilSelectionne = "pelle";
        });


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
                    if (outilSelectionne != null) {
                        if (outilSelectionne.equals("pelle") && env.getMap()[l][col] == 2) {
                            env.getMap()[l][col] = mapOriginale[l][col];
                            if (mapOriginale[l][col] == 1) {
                                imv.setImage(chargerImage("New Piskel-1.png(3).png"));
                            } else {
                                imv.setImage(chargerImage("Carré_vert_foncéee.png"));
                            }
                        } else if (!outilSelectionne.equals("pelle")) { //si l'outilSelectionne n'est pas la pelle alors on place un poisson de deffense
                            imv.setImage(chargerImage(outilSelectionne));
                            env.getMap()[l][col] = 2;
                            env.ajouterPoissonDeffense(
                                    new PoissonDeffense(outilSelectionne, 100, col * 114, l * 114, 10)
                            );
                        }
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

        mapOriginale = new int[env.getHauteur()][env.getLargeur()];
        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {
                mapOriginale[i][j] = env.getMap()[i][j];
            }
        }

    }

    private void mettreAJourVue() {

        // Boucle ennemis
        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
            e.avancer();
            ImageView imv = (ImageView) coucheEnnemi.getChildren().get(env.getListePoissonsAttaque().indexOf(e));
            imv.setLayoutX(e.getX());
            imv.setLayoutY(e.getY());

            int col = (int)(e.getX() / 114);
            int ligne = (int)(e.getY() / 114);

            if (col >= 0 && col < env.getLargeur() && ligne >= 0 && ligne < env.getHauteur()) {
                if (env.getMap()[ligne][col] == 2) {
                    e.subirAttaque(10);


                    if (e.estMort()) {
                        ImageView imvCase = (ImageView) map.getChildren().get(ligne * env.getLargeur() + col);

                        if (mapOriginale[ligne][col] == 1) {
                            imvCase.setImage(chargerImage("New Piskel-1.png(3).png"));
                        } else {
                            imvCase.setImage(chargerImage("Carré_vert_foncéee.png"));
                        }
                        env.getMap()[ligne][col] = mapOriginale[ligne][col];
                    }



                }
            }
        }

        coucheBulle.getChildren().clear();
        for (PoissonDeffense p : env.getListePoissonsDeffense()) {  //affect un poisson de deffense a la variable p
            p.agit(); //agit creer une bulle et ou la fait avancer

            Bulle b = p.getBull();
            if (b != null) {
                // Afficher la bulle
                ImageView imvBulle = new ImageView(chargerImage("bulle2.png"));
                imvBulle.setFitWidth(30);
                imvBulle.setFitHeight(30);
                imvBulle.setLayoutX(b.getX());
                imvBulle.setLayoutY(b.getY());
                coucheBulle.getChildren().add(imvBulle);

                // Vérifier collision avec chaque requin
                for (PoissonAttaque requin : env.getListePoissonsAttaque()) {
                    requin.toucher(b, requin);
                }
            }
        }
    }






}