package universite_paris8.iut.jbouguerba.sae_jeux.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import universite_paris8.iut.jbouguerba.sae_jeux.Main1;

public class ControllerDefaite {

    @FXML
    private Button boutonRejouer;

    @FXML
    private void rejouer() {
        try {
            Stage stage = (Stage) boutonRejouer.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Main1.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(loader.load(), 800, 651);
            stage.setScene(scene);
        } catch (IOException ignored) {}
    }
}