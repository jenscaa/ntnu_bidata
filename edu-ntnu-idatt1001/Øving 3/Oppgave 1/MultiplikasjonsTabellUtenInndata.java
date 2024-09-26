/*
 * MultiplikasjonsTabell.java
 *   
 * The program writes out a part of the multiplication table from a chosen range. Input data is not available
 */
import java.util.*;
class MultiplikasjonsTabellUtenInndata{
    public static void main(String[]args){
        int startIntervall = 5;
        int sluttIntervall = 10;
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