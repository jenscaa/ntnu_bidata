/*
 * FirstPriceFinder.java
 * 
 * Programmet finner ut hvilket merke som er billigst
 */
import static javax.swing.JOptionPane.*;
import java.util.ArrayList;
class FirstPriceFinder{
    public static void main(String[]args){
        showMessageDialog(null, "Kjøttdeig av merke A koster kr 35,90 for 450 gram, mens kjøttdeig av merke B koster kr 39,50 for 500 gram.");
        double merkeAPrisPerKg = (35.90/0.450);
        double merkeBPrisPerKg = (39.50/0.500);
        
        if (merkeAPrisPerKg < merkeBPrisPerKg){
            showMessageDialog(null, "merka A er billigst og koster "+merkeAPrisPerKg+" kr/kg, mens merke B koster "+merkeBPrisPerKg+" kr/kg" );
        }
        else{
            showMessageDialog(null, "merke B er billigst og koster "+ merkeBPrisPerKg+ " kr/kg, mens merke A koster "+merkeAPrisPerKg+" kr/kg");
        }
    }
}