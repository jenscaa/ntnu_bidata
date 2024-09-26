/*
 * PrimtallUtenInndata.java
 * 
 * The program determends wheter or not a number is a prime number or not. Input data is not available
 */
import static javax.swing.JOptionPane.*;
import java.util.ArrayList;
import java.util.List;
class PrimtallUtenInndata{
    public static void main(String[]args){

        List<Integer> numberList = new ArrayList<Integer>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(4);
        numberList.add(5);
        numberList.add(9);
        List<String> primtallList = new ArrayList<>();
        boolean primeNumber = true;
        int divisor = 2;

        for (int i : numberList){
            while (primeNumber && divisor < (i/2+1)){
                
                if (i % divisor == 0){
                    primeNumber = false;
                }
                divisor++;
            }
            divisor = 2;

            if (i == 1){
                primeNumber = false;
            }
            if (primeNumber){
                primtallList.add("er et primtall");
            }
            else{
                primtallList.add("er ikke et primtall");
            }
            primeNumber = true;
        }
        
        showMessageDialog(null, numberList.get(0) + " " + primtallList.get(0) + ". \n" 
        + numberList.get(1) + " " + primtallList.get(1) + ". \n"
        + numberList.get(2) + " " + primtallList.get(2) + ". \n"
        + numberList.get(3) + " " + primtallList.get(3) + ". \n"
        + numberList.get(4) + " " + primtallList.get(4) + ". ");

    }
}
