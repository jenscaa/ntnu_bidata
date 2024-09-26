/**
 * SekundKonverterer.java
 * 
 * Omgjør timer, sekunder og miutter til "bare" sekunder
 */
import static javax.swing.JOptionPane.*; 
class SekundKonverterer{
public static void main(String[]args){
    String timeInput = showInputDialog("antall timer: "); 
    String minuttInput = showInputDialog("antall minutter: ");
    String sekundInput = showInputDialog("antall sekunder: ");
    double time = Double.parseDouble(timeInput);
    double minutt = Double.parseDouble(minuttInput);
    double sekund = Double.parseDouble(sekundInput);
    double totaltSekunder = time * 60 * 60 + minutt * 60 + sekund;
    showMessageDialog(null, "Totalt antall sekunder: "+totaltSekunder);
}
}