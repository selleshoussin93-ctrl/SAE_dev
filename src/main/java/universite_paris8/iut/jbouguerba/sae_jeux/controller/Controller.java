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

import static universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonDeffense.getPrix;

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
        // Le controller gère mapOriginale
        mapOriginale = new int[env.getHauteur()][env.getLargeur()];
        for (int i = 0; i < env.getHauteur(); i++) {
            for (int j = 0; j < env.getLargeur(); j++) {
                mapOriginale[i][j] = env.getMap()[i][j];
            }
        }
        // TerrainVue gère uniquement l'affichage
        terrainVue.initialiserTerrain(
                env.getMap(),
                (col, l) -> gererClicCase(col, l)
        );
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

        if (outilSelectionne.equals("pelle")) {
            // Modèle
            boolean succes = env.utiliserPelle(col, ligne, mapOriginale);
            // Vue
            if (succes) {
                poissonVue.effacerPoisson(
                        ligne * env.getLargeur() + col,
                        ImageLoader.imageCase(mapOriginale[ligne][col])
                );
            }
        } else {
            int prix = getPrix(outilSelectionne);
            boolean succes = env.placerPoisson(outilSelectionne, col, ligne, prix);
            if (succes) {
                poissonVue.afficherPoisson(
                        ligne * env.getLargeur() + col,
                        ImageLoader.imagePoisson(outilSelectionne)
                );
            }
        }
    }

    private void mettreAJourVue() {
        // Modèle — avancer les requins
        env.avancerRequins();

        // met à jour positions requins dans la vue
        for (int i = 0; i < env.getListePoissonsAttaque().size(); i++) {
            PoissonAttaque e = env.getListePoissonsAttaque().get(i);
            requinVue.mettreAJourPosition(i, e.getX(), e.getY());
        }

        // collisions, retourne les cases à mettre à jour
        List<int[]> casesDetruites = env.verifierCollisionsRequins();

        // met à jour les cases détruites dans la vue
        for (int[] c : casesDetruites) {
            terrainVue.mettreAJourCase(
                    c[0] * env.getLargeur() + c[1],
                    ImageLoader.imageCase(mapOriginale[c[0]][c[1]])
            );
            env.getMap()[c[0]][c[1]] = mapOriginale[c[0]][c[1]];
        }
        // fais agir les poissons de défense
        env.agirPoissonsDeffense();
        // passe les positions des bulles à la vue
        poissonVue.mettreAJourBulles(env.getPositionsBulles());
        // collisions bulles/requins
      //  env.gererCollisions();


        List<PoissonAttaque> morts = env.gererCollisions();

        // Supprimer de la vue (indices décroissants pour ne pas décaler)
        for (PoissonAttaque mort : morts) {
            // On cherche l'index AVANT la suppression du modèle
            // donc on reconstruit la vue entière
        }

        // Resynchroniser la vue avec le modèle
        requinVue.viderRequins();
        for (PoissonAttaque e : env.getListePoissonsAttaque()) {
            requinVue.ajouterRequin(
                    ImageLoader.imageRequin(e.getNom()),
                    e.getX(), e.getY()
            );
        }
        if (env.vagueTerminee() && env.aUneProchainerVague()) { //declanche la vague suivante
            env.lancerVague();
            requinVue.viderRequins();
            for (PoissonAttaque e : env.getListePoissonsAttaque()) {
                requinVue.ajouterRequin(
                        ImageLoader.imageRequin(e.getNom()),
                        e.getX(), e.getY()
                );
            }
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