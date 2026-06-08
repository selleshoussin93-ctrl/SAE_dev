package universite_paris8.iut.jbouguerba.sae_jeux.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.*;
import universite_paris8.iut.jbouguerba.sae_jeux.vue.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Environnement env;
    private String outilSelectionne = null;
    private Timeline gameLoop;
    private int temps;
    private int[][] mapOriginale;

    private TerrainVue terrainVue;
    private RequinVue requinVue;
    private PoissonVue poissonVue;

    @FXML private ImageView poissonRouge;
    @FXML private ImageView etoileDeMer;
    @FXML private ImageView crabe;
    @FXML private ImageView poulpe;
    @FXML private ImageView poissonGlobe;
    @FXML private ImageView pelle;
    @FXML private TilePane map;
    @FXML private Label nbRessource;
    @FXML private Pane coucheEnnemi;
    @FXML private Pane coucheBulle;
    @FXML
  //void setOutilSelectionnerPoissonRouge(MouseEvent) {
    //    outilSelectionne ="poisson_rouge.png";
    //}


    public void initialize() {
        this.env = new Environnement(6, 4);

        this.terrainVue = new TerrainVue(map);
        this.requinVue = new RequinVue(coucheEnnemi);
        this.poissonVue = new PoissonVue(map, coucheBulle,
                ImageLoader.charger("bulle2.png")
        );

        initialiserOutils();
        initialiserTerrain();
        initialiserRequins();

        nbRessource.textProperty().bind(env.ressourcesProperty().asString());
        initAnimation();
        gameLoop.play();
    }

    private void initialiserOutils() {
        poissonRouge.setImage(ImageLoader.charger("poisson_rouge.png"));
        etoileDeMer.setImage(ImageLoader.charger("etoile_mer.png"));
        crabe.setImage(ImageLoader.charger("crabe.png"));
        poulpe.setImage(ImageLoader.charger("poulpe.png"));
        poissonGlobe.setImage(ImageLoader.charger("poissonGlobe2.png"));
        pelle.setImage(ImageLoader.charger("pelle.png"));

        poissonRouge.setOnMouseClicked(e -> outilSelectionne = "poisson_rouge.png");
        etoileDeMer.setOnMouseClicked(e -> outilSelectionne = "etoile_mer.png");
        crabe.setOnMouseClicked(e -> outilSelectionne = "crabe.png");
        poulpe.setOnMouseClicked(e -> outilSelectionne = "poulpe.png");
        poissonGlobe.setOnMouseClicked(e -> outilSelectionne = "poissonGlobe2.png");
        pelle.setOnMouseClicked(e -> outilSelectionne = "pelle");
    }

    private void initialiserTerrain() {
        mapOriginale = new int[env.getHauteur()][env.getLargeur()];

        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {
                ImageView imv = new ImageView(
                        ImageLoader.imageCase(env.getMap()[i][j])
                );
                imv.setFitWidth(114);
                imv.setFitHeight(114);
                imv.setPickOnBounds(true);

                final int col = j;
                final int l = i;
                imv.setOnMouseClicked(e -> gererClicCase(col, l));

                terrainVue.ajouterCase(imv);
                mapOriginale[i][j] = env.getMap()[i][j];
            }
        }
    }

    private void initialiserRequins() {
        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
            requinVue.ajouterRequin(
                    ImageLoader.imageRequin(e.getNom()),
                    e.getX(), e.getY()
            );
        }
    }

    private void gererClicCase(int col, int ligne) {
        if (outilSelectionne == null) return;

        if (outilSelectionne.equals("pelle") && env.getMap()[ligne][col] == 2) {
            env.getMap()[ligne][col] = mapOriginale[ligne][col];
            env.supprimerPoissonDeffense(col * 114, ligne * 114);
            poissonVue.effacerPoisson(
                    ligne * env.getLargeur() + col,
                    ImageLoader.imageCase(mapOriginale[ligne][col])
            );

        } else if (!outilSelectionne.equals("pelle")) {
            int prix = getPrix(outilSelectionne);
            if (env.getRessources() >= prix) {
                env.setRessources(env.getRessources() - prix);
                env.getMap()[ligne][col] = 2;
                env.ajouterPoissonDeffense(
                        new PoissonDeffense(outilSelectionne, 100, col * 114, ligne * 114, 10)
                );
                poissonVue.afficherPoisson(
                        ligne * env.getLargeur() + col,
                        ImageLoader.imagePoisson(outilSelectionne)
                );
            } else {
                System.out.println("Pas assez de ressources ! (Prix : " + prix + ")");
            }
        }
    }

    private void mettreAJourVue() {

        // Requins
        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
            e.avancer();
            requinVue.mettreAJourPosition(
                    env.getListePoissonsAttaque().indexOf(e),
                    e.getX(), e.getY()
            );

            int col = (int)(e.getX() / 114);
            int ligne = (int)(e.getY() / 114);

            if (col >= 0 && col < env.getLargeur() && ligne >= 0 && ligne < env.getHauteur()) {
                if (env.getMap()[ligne][col] == 2) {
                    e.subirAttaque(10);
                    if (e.estMort()) {
                        env.getMap()[ligne][col] = mapOriginale[ligne][col];
                        env.supprimerPoissonDeffense(col * 114, ligne * 114);
                        terrainVue.mettreAJourCase(
                                ligne * env.getLargeur() + col,
                                ImageLoader.imageCase(mapOriginale[ligne][col])
                        );
                    }
                }
            }
        }

        // Poissons de défense
        for (PoissonDeffense p : env.getListePoissonsDeffense()) {
            p.agit();
        }

        // Collecte positions bulles → passe à la vue
        List<double[]> positions = new ArrayList<>();
        for (PoissonDeffense p : env.getListePoissonsDeffense()) {
            for (Bulle b : p.getBull()) {
                positions.add(new double[]{b.getX(), b.getY()});
            }
        }
        poissonVue.mettreAJourBulles(positions);

        // Collisions
        for (PoissonDeffense p : env.getListePoissonsDeffense()) {
            for (Bulle b : p.getBull()) {
                for (PoissonAttaque requin : env.getListePoissonsAttaque()) {
                    requin.toucher(b, requin);
                }
            }
        }
    }

    private int getPrix(String nom) {
        if (nom.equals("poisson_rouge.png")) return 10;
        if (nom.equals("etoile_mer.png")) return 5;
        if (nom.equals("crabe.png")) return 10;
        if (nom.equals("poulpe.png")) return 20;
        if (nom.equals("poissonGlobe2.png")) return 15;
        return 0;
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(Duration.seconds(0.010), ev -> {
            if (temps % 50 == 0) mettreAJourVue();
            temps++;
        });
        gameLoop.getKeyFrames().add(kf);
    }
}