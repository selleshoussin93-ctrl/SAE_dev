package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainDefaite {
    public static void afficher(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main1.class.getResource("defaite-view.fxml"));
        Scene scene = new Scene(loader.load(), 800, 651);
        stage.setScene(scene);
    }
}