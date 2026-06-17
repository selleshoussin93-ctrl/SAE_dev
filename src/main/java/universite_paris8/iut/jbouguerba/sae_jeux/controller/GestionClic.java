package universite_paris8.iut.jbouguerba.sae_jeux.controller;



public interface GestionClic {
    // permet a la vue d'envoyer la case cliquee au controller
    void gererClic(int col, int ligne);
}