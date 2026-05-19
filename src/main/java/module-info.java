module universite_paris8.iut.jbouguerba.sae_jeux {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens universite_paris8.iut.jbouguerba.sae_jeux to javafx.fxml;
    exports universite_paris8.iut.jbouguerba.sae_jeux;
}