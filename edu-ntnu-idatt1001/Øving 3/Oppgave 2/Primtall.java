/*
 * Primtall.java
 * 
 * The program determends wheter or not a number is a prime number or not. If 0 or lower is pressed, the loop will break
 */
import static javax.swing.JOptionPane.*;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

class Primtall{
    public static void main(String[]args){
        
        List<Integer> numberList = new ArrayList<Integer>();
        List<String> primtallList = new ArrayList<String>();
        boolean primeNumber = true;
        boolean run = true;
        String message = "";
        int divisor = 2;
        int numbersGiven = -1;
        int i = 0; // for the while loop later on
    
        while (run){

            try{
                
                int number = Integer.parseInt(showInputDialog("Velg et nummer: "));
                numbersGiven ++;

                while (primeNumber && divisor < ((number)/2 + 1)){

                    if (number % divisor == 0){
                        primeNumber = false;
                    }
                    divisor++;
                }
                
                divisor = 2;

                if (number == 1){
                    primeNumber = false;
                } else if (number <= 0){
                    primeNumber = false;
                    run = false;

                } if (primeNumber){
                    primtallList.add("er et primtall");
                    showMessageDialog(null, "Tallet "+ number + " er et primtall");
                } else if (!primeNumber){
                    primtallList.add("er ikke et primtall");
                    showMessageDialog(null, "Tallet " + number + " er ikke et primtall");
                }

                primeNumber = true;
                numberList.add(number);  

            }
            catch (NumberFormatException e){
                showMessageDialog(null, "You typed something wrong"); 
                run = false;
            }
        }

        while (i <= numbersGiven ){
            message += numberList.get(i) + " " + primtallList.get(i) + ". \n";
            i ++;
        }

        showMessageDialog(null, message); 
    }
}