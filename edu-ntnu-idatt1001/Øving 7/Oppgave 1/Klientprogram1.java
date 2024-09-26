
import static javax.swing.JOptionPane.*;
public class Klientprogram1 {

    public static void main(String[] args) {
        
        String tekst = showInputDialog(null, "Skriv inn en tekst: ");
        NyString objekt = new NyString(tekst);
        char bokstav = showInputDialog(null, "Hvilken bokstav vil du fjerne? ").charAt(0);
        System.out.println("\nForkortet: " + objekt.forkorting(tekst) + "\nUten bokstav (" + String.valueOf(bokstav) + "): " + 
        objekt.fjerningAvTegn(tekst, bokstav) + "\n")
        ;
    
    }
}