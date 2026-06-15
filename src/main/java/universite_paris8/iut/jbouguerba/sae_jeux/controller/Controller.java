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


    public void initialize() {
        this.env = new Environnement(6, 4);

        this.terrainVue = new TerrainVue(map);
        this.requinVue = new RequinVue(coucheEnnemi);
        this.poissonVue = new PoissonVue(map, coucheBulle);

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
        // Le controller gère mapOriginale
        mapOriginale = new int[env.getHauteur()][env.getLargeur()];
        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {
                mapOriginale[i][j] = env.getMap()[i][j];
            }
        }
        // TerrainVue gère uniquement l'affichage
        terrainVue.initialiserTerrain(env.getMap(), (col, l) -> gererClicCase(col, l));
    }
    private void initialiserRequins() {
        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
            requinVue.ajouterRequin(e);
        }
    }
    private void gererClicCase(int col, int ligne) {
        if (outilSelectionne == null) return;
        if (outilSelectionne.equals("pelle")) {
            boolean succes = env.utiliserPelle(col, ligne, mapOriginale);
            if (succes) {
                poissonVue.effacerPoisson(ligne * env.getLargeur() + col, mapOriginale[ligne][col]);
            }
        } else {
            boolean succes = env.placerPoisson(outilSelectionne, col, ligne);

            if (succes) {
                poissonVue.afficherPoisson(ligne * env.getLargeur() + col, outilSelectionne);
            }
        }
    }
    private void mettreAJourVue() {

        env.avancerRequins();

        for (int i = 0; i < env.getListePoissonsAttaque().size(); i++) {
            PoissonAttaque e = env.getListePoissonsAttaque().get(i);
            requinVue.mettreAJourPosition(i, e.getX(), e.getY());
        }

        List<int[]> casesDetruites = env.verifierCollisionsRequins();
        for (int[] c : casesDetruites) {
            terrainVue.mettreAJourCase(c[0] * env.getLargeur() + c[1], mapOriginale[c[0]][c[1]]);
            env.getMap()[c[0]][c[1]] = mapOriginale[c[0]][c[1]];
        }

        env.agirPoissonsDeffense();
        poissonVue.mettreAJourBulles(env.getPositionsBulles());

        // ✅ gererCollisions retourne les requins morts
        List<PoissonAttaque> morts = env.gererCollisions();

        // ✅ Resynchronise la vue si quelque chose a changé
        if (!casesDetruites.isEmpty() || !morts.isEmpty()) {
            synchroniserRequins();
        }

        if (env.vagueTerminee() && env.aUneProchainerVague()) {
            env.lancerVague();
            synchroniserRequins();
        }
    }

    private void synchroniserRequins() {
        requinVue.viderRequins();
        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
            requinVue.ajouterRequin(e);
        }
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