package universite_paris8.iut.jbouguerba.sae_jeux;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import universite_paris8.iut.jbouguerba.sae_jeux.modele.PoissonDeffense;

import java.io.IOException;

public class Main1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main1.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 651);
        stage.setTitle("///");
        stage.setScene(scene);
        PoissonDeffense p = new PoissonDeffense("poisson rouge",30,0,0);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
