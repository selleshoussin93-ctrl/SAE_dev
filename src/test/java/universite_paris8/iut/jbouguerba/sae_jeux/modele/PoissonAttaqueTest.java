package universite_paris8.iut.jbouguerba.sae_jeux.modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoissonAttaqueTest {
    private PoissonAttaque poisson;

    // ── Constructeur ──────────────────────────────────────────────────────────

    @Test
    void constructeur_pvCorrects() {
        assertEquals(100, poisson.getPv());
    }

    @Test
    void constructeur_degatsCorrects() {
        assertEquals(20, poisson.getDegats());
    }

    @Test
    void constructeur_vitesseCorrecte() {
        assertEquals(2.0, poisson.getVitesse(), 0.001);
    }

    @Test
    void constructeur_recompenseCorrecte() {
        assertEquals(50, poisson.getRecompense());
    }

    @Test
    void constructeur_positionCorrecte() {
        assertEquals(500.0, poisson.getX(), 0.001);
        assertEquals(300.0, poisson.getY(), 0.001);
    }

    // ── avancer() normal ──────────────────────────────────────────────────────

    @Test
    void avancer_deplaceLePoissonVersLaGauche() {
        double xAvant = poisson.getX();
        poisson.avancer();
        assertEquals(xAvant - 2.0, poisson.getX(), 0.001);
    }

    @Test
    void avancer_cinqFois_cumuleLesDeplacements() {
        double xAvant = poisson.getX();
        for (int i = 0; i < 5; i++) poisson.avancer();
        assertEquals(xAvant - 10.0, poisson.getX(), 0.001);
    }

    // ── subirAttaque() ────────────────────────────────────────────────────────

    @Test
    void subirAttaque_reduitsLesPV() {
        poisson.subirAttaque(30);
        assertEquals(70, poisson.getPv());
    }

    @Test
    void subirAttaque_deuxFois_cumuleLesPertes() {
        poisson.subirAttaque(30);
        poisson.subirAttaque(20);
        assertEquals(50, poisson.getPv());
    }

    @Test
    void subirAttaque_declencheLeRecul() {
        poisson.subirAttaque(10);
        double xApresAttaque = poisson.getX();
        poisson.avancer(); // tick de recul → +2
        assertEquals(xApresAttaque + 2.0, poisson.getX(), 0.001);
    }

    @Test
    void subirAttaque_reculDure3Ticks_puisRepriseNormale() {
        poisson.subirAttaque(10);
        poisson.avancer(); // recul 1
        poisson.avancer(); // recul 2
        poisson.avancer(); // recul 3 — fin du recul
        double xApresRecul = poisson.getX();
        poisson.avancer(); // doit avancer normalement
        assertEquals(xApresRecul - 2.0, poisson.getX(), 0.001);
    }

    // ── estMort() ─────────────────────────────────────────────────────────────

    @Test
    void estMort_retourneFaux_siPVPositifs() {
        assertFalse(poisson.estMort());
    }

    @Test
    void estMort_retourneVrai_siPVNuls() {
        poisson.subirAttaque(100);
        assertTrue(poisson.estMort());
    }

    @Test
    void estMort_retourneVrai_siPVNegatifs() {
        poisson.subirAttaque(150);
        assertTrue(poisson.estMort());
    }

    // ── ralentir() ────────────────────────────────────────────────────────────

    @Test
    void ralentir_metsLaVitesseAZero() {  // ralenti le requin avec une vitesse égale a zéro
        poisson.ralentir();
        assertEquals(0.0, poisson.getVitesse(), 0.001);
    }

    @Test
    void ralentir_poissonNeBougePlus() {
        poisson.ralentir();
        double xAvant = poisson.getX();
        poisson.avancer();
        assertEquals(xAvant, poisson.getX(), 0.001);
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