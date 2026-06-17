package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class PoissonAttaqueTest {
    private PoissonAttaque poisson;

    @BeforeEach
    void setUp() { // création d'un poisson pour chaque test
        poisson = new PoissonAttaque("Requin", 100, 20, 500.0, 300.0, 50, 2.0);
    }

    @Test
    void constructeur_positionCorrecte() { //verifie si l'emplacement des poisson sur la map est correct
        assertEquals(500.0, poisson.getX(), 0.001);
        assertEquals(300.0, poisson.getY(), 0.001);
    }

    ////////////////////////////////////avancer()

    @Test
    void avancer_deplaceLePoissonVersLaGauche() { //regarde si le poisson se deplace uniquement vers la gauche
        double xAvant = poisson.getX();
        poisson.avancer();
        assertEquals(xAvant - 2.0, poisson.getX(), 0.001);
    }

    @Test
    void avancer_cinqFois_cumuleLesDeplacements() { //regarde si le poisson avance vers la gauche plusieurs fois
        double xAvant = poisson.getX();
        for (int i = 0; i < 5; i++) poisson.avancer();
        assertEquals(xAvant - 10.0, poisson.getX(), 0.001);
    }

    ////////////////////////////////subirAttaque()

    @Test
    void subirAttaque_reduitsLesPV() {  // regarde si le requin perd des pv apres avoir subi une attaque
        poisson.subirAttaque(30);
        assertEquals(70, poisson.getPv());
    }

    @Test
    void subirAttaque_deuxFois_cumuleLesPertes() { //regarde si le poisson perd des pv d'affiler
        poisson.subirAttaque(30);
        poisson.subirAttaque(20);
        assertEquals(50, poisson.getPv());
    }

    @Test
    void subirAttaque_declencheLeRecul() {  //regarde si le requin recule de 2 pixcel apres avoir subi une attaque
        poisson.subirAttaque(10);
        double xApresAttaque = poisson.getX();
        poisson.avancer(); // tick de recul → +2
        assertEquals(xApresAttaque + 2.0, poisson.getX(), 0.001);
    }

    @Test
    void subirAttaque_Reavancer() {  //regarde si le poisson recule de 3 pixcel apres avoir subi une attaque avec la class avancer et reavance normalement
        poisson.subirAttaque(10);
        poisson.avancer(); // recul 1
        poisson.avancer(); // recul 2
        poisson.avancer(); // recul 3 — fin du recul
        double xApresRecul = poisson.getX();
        poisson.avancer(); // doit avancer normalement
        assertEquals(xApresRecul - 2.0, poisson.getX(), 0.001);
    }

    ////////////////////estMort()

    @Test
    void estMort_retourneFaux_siPVPositifs() {
        assertFalse(poisson.estMort());
    }  //nous retourne un boolean false si le poisson n'est pas mort

    @Test
    void estMort_retourneVrai_siPVNuls() {  //nous retourne true si le poisson est mort apres avoir subi une attaque avec 100 de dégats et que pv=0
        poisson.subirAttaque(100);
        assertTrue(poisson.estMort());
    }

    @Test
    void estMort_retourneVrai_siPVNegatifs() {  //nous retourne true si le poisson est mort apres avoir subi une attaque avec 150 de dégats et que pv= -50
        poisson.subirAttaque(150);
        assertTrue(poisson.estMort());
    }

    //////////////////////////////////////////ralentir()

    @Test
    void ralentir_metsLaVitesseAZero() {  // ralenti le requin avec une vitesse égale a zéro
        poisson.ralentir();
        assertEquals(0.0, poisson.getVitesse(), 0.001);
    }


    @Test
    void retablirVitesse() {  // remet la vitesse d'un requin s'il a subit un ralentissement
        poisson.ralentir();
        poisson.retablirVitesse();
        assertEquals(2.0, poisson.getVitesse(), 0.001);
    }

    @Test
    void avancerTest() {  // ralentissement automatiquement
        poisson.ralentir();
        for (int i = 0; i < 5; i++) poisson.avancer();
        // au 6e tick la vitesse est rétablie
        double xAvant = poisson.getX();
        poisson.avancer();
        assertEquals(xAvant - 2.0, poisson.getX(), 0.001);
    }
}