/*
 * LeapYearFinder.java
 * 
 * Programmet finner ut om et år er skuddår eller ikke
 */
import static javax.swing.JOptionPane.*;
class LeapYearFinder{
    public static void main(String[]args){
    Boolean yearLeap = false;
    String yearInput = showInputDialog("Velg et årstall: ");
    Integer year = Integer.valueOf(yearInput);
    if (year % 4 == 0){
        yearLeap = true;
    }
    if (year % 100 == 0){
        yearLeap = false;
        if (year % 400 == 0){
            yearLeap = true;
        } 
    }
    if (yearLeap){
        showMessageDialog(null,"Året er et skuddår.");
    }
    else{
        showMessageDialog(null,"Året er ikke et skuddår.");
    }
    }
}