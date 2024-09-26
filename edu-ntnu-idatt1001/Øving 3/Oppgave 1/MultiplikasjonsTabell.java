/*
 * MultiplikasjonsTabell.java
 *   
 * The program writes out a part of the multiplication table from a chosen range. Input data is here available and managed
 */
import java.util.*;
import static javax.swing.JOptionPane.*;
class MultiplikasjonsTabell{
    public static void main(String[]args){

        
        int startIntervall = Integer.parseInt(showInputDialog("Velg startverdien for intervallet: "));
    
        int sluttIntervall = Integer.parseInt(showInputDialog("Velg sluttverdien for intervallet: "));
        int[] intArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 };

        do{
            for (int i : intArray){
                int answer = startIntervall * i;
                System.out.println(startIntervall + " x " + i + " = " + answer);
            }
            startIntervall ++;
        }while(startIntervall<sluttIntervall+1);
    }
}