package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import javafx.scene.paint.Color;


public class Tile {

    private int type; // 1 pour Eau, 2 pour Rocher


    public Tile(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }



}
