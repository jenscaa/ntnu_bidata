/**
 * Konverterer.java
 * 
 * Omgjør tommer til centimeter
 */
import static javax.swing.JOptionPane.*;
class Konverterer{
    public static void main(String[]args){
        final double TOMMER_CENTIMETER = 2.54;
        String tommerInput = showInputDialog("Tommer: ");
        double tommer = Double.parseDouble(tommerInput);
        double centimeter = tommer * TOMMER_CENTIMETER;
        showMessageDialog(null, "Centimeter: " + centimeter + " cm.");
    }
}