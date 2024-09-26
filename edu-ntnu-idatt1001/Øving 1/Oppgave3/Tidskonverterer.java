/**
 * Tidskonverterer.java
 * 
 * Programmet omgjør sekunder til timer, minutter og sekunder
 */
import static javax.swing.JOptionPane.*; 
class Tidskonverterer{
    public static void main(String[]args){
        String sekunderInput = showInputDialog("Antall sekunder:");
        Integer sekunder = Integer.valueOf(sekunderInput);
        int timer = sekunder/3600;
        int minutter = (sekunder % 3600)/60;
        int sekunder0 = ((sekunder % 3600) % 60);
        showMessageDialog(null,"timer: "+timer+"   minutter: "+minutter+"   sekunder: "+sekunder0);
    }
}