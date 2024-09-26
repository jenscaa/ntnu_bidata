/**
 * Klientprogram
 */
import static javax.swing.JOptionPane.*;
public class KlientprogramOppg2 {

    public static void main(String[] args) {

        String tekst = showInputDialog(null, "Skriv inn en tekst du ønsker å analysere:");
        char bokstav = showInputDialog(null, "Skriv inn en bokstav du ønsker å få antall forekomster av: ").toLowerCase().charAt(0);

        Tekstanalyse randomTekst = new Tekstanalyse(tekst);
        Verktøy verktøy = new Verktøy();

        System.out.println(
        "\nAntall forskjellige bokstaver: " + verktøy.getAntallForskjelligeBokstaver(randomTekst.tekst) + 
        "\nAntall totalt bokstaver: " + verktøy.getTotaltAntallBokstaver(randomTekst.tekst) + 
        "\nAntall del som ikke er bokstaver: " + verktøy.getProsent(randomTekst.tekst) + "%" + 
        "\nAntall forekomster av bokstav "+ "\'" + bokstav + "\'" + " er " + verktøy.getForekomsterAvBokstav(randomTekst.tekst, bokstav) +
        "\n" + verktøy.getOftestBokstaver(randomTekst.tekst) + "\n "); 
    }
}