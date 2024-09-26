import static javax.swing.JOptionPane.*;
import java.util.*;
class Brøk{
    private int teller;
    private int nevner;
    

    static void feilmelding(){
        showMessageDialog(null, "Det du trykket inn ble ikke akseptert");
    }

    public void forkorting(int tellerSvar, int nevnerSvar){
        int nevner = nevnerSvar;
        int teller = tellerSvar;
        int divisor = 2;
        while (nevner >= divisor - 1 && teller >= divisor - 1){
            while (nevner % divisor == 0 && teller % divisor == 0){
                nevner /= divisor;
                teller /= divisor;
            }
            divisor ++;
        }
        this.teller = teller;
        this.nevner = nevner;
        
    }   


    public void summere(int brøk_A_teller, int brøk_A_nevner, int brøk_B_teller, int brøk_B_nevner) {
        if (brøk_A_nevner == brøk_B_nevner){
            this.teller = brøk_A_teller + brøk_B_teller;
            this.nevner = brøk_A_nevner;
        } else{
            this.nevner = brøk_A_nevner * brøk_B_nevner;
            brøk_A_teller *= brøk_B_nevner;
            brøk_B_teller *= brøk_A_nevner;
            this.teller = brøk_A_teller + brøk_B_teller;
        }
    }


    public void subtrahere(int brøk_A_teller, int brøk_A_nevner, int brøk_B_teller, int brøk_B_nevner){
        if (brøk_A_nevner == brøk_B_nevner){
            this.teller = brøk_A_teller - brøk_B_teller;
            this.nevner = brøk_A_nevner;
        } else{
            this.nevner = brøk_A_nevner * brøk_B_nevner;
            brøk_A_teller *= brøk_B_nevner;
            brøk_B_teller *= brøk_A_nevner;
            this.teller = brøk_A_teller - brøk_B_teller; 
        }
    }


    public void multiplisere(int brøk_A_teller, int brøk_A_nevner, int brøk_B_teller, int brøk_B_nevner){
        this.teller = brøk_A_teller * brøk_B_teller;
        this.nevner = brøk_A_nevner * brøk_B_nevner;
    }


    public void dividere(int brøk_A_teller, int brøk_A_nevner, int brøk_B_teller, int brøk_B_nevner){
        this.teller = brøk_A_teller * brøk_B_nevner;
        this.nevner = brøk_A_nevner * brøk_B_teller;
    }


    public Brøk(int teller, int nevner){
        this.teller = teller;
            try{
                if (nevner == 0){
                    throw new IllegalAccessException();
                }
            } catch (IllegalAccessException e){
                System.out.println("Teller kan ikke deles på null!");
            }
        this.nevner = nevner;
    }

    public Brøk(int teller){
        this.teller = teller;
        this.nevner = 1; 
    }
    public static void main(String[] args) {
        
        String brøkUttrykk;
        String[] brøkSplit = {};
        boolean run = true;
        int teller1 = 0;
        int nevner1 = 0;
        int teller2 = 0;
        int nevner2 = 0;
        int choice = 0;      
        String operator = "";
        String divisjon = "/";
        String addisjon = "+";
        String subtraksjon = "-";
        String multiplikasjon = "*";

        Brøk brøk_A = null;
        Brøk brøk_B = null;
        

        while (run){

            brøkUttrykk = showInputDialog(null, "Skriv inn et tall eller en brøk: ");

            if (brøkUttrykk.contains("/")){
                brøkSplit =  brøkUttrykk.split("/");
                try{
                    teller1 = Integer.parseInt(brøkSplit[0]);
                    nevner1 = Integer.parseInt(brøkSplit[1]);
                    brøk_A = new Brøk(teller1, nevner1);
                } catch (Exception e){
                    feilmelding();
                    break;
                }
            }else{
                teller1 = Integer.parseInt(brøkUttrykk);
                nevner1 = 1;
                brøk_A = new Brøk(teller1);
            }
            
            operator = showInputDialog(null, "Tast inn valgt operasjon: \n \n (+ , - , * , /) ");
            brøkUttrykk = showInputDialog(null, "Skriv inn et nytt tall eller en ny brøk: ");

            if (brøkUttrykk.contains("/")){
                brøkSplit = brøkUttrykk.split("/");
                try{
                    teller2 = Integer.parseInt(brøkSplit[0]);
                    nevner2 = Integer.parseInt(brøkSplit[1]);
                    brøk_B = new Brøk(teller2, nevner2);
                } catch (Exception e){
                    feilmelding();
                    break;
                }
            }else{
                teller2 = Integer.parseInt(brøkUttrykk);
                nevner2 = 1;
                brøk_B = new Brøk(teller2);
            }

        
            
            if (operator.equals(addisjon)){
                brøk_A.summere(brøk_A.teller, brøk_A.nevner, brøk_B.teller, brøk_B.nevner);
            } else if (operator.equals(subtraksjon)){
                brøk_A.subtrahere(brøk_A.teller, brøk_A.nevner, brøk_B.teller, brøk_B.nevner);
            } else if (operator.equals(multiplikasjon)){
                brøk_A.multiplisere(brøk_A.teller, brøk_A.nevner, brøk_B.teller, brøk_B.nevner);
            } else if (operator.equals(divisjon)){
                brøk_A.dividere(brøk_A.teller, brøk_A.nevner, brøk_B.teller, brøk_B.nevner);
            } else{
                feilmelding();
                break;
            }

        
            brøk_A.forkorting(brøk_A.teller, brøk_A.nevner);

            showMessageDialog(null, teller1 + "/" + nevner1 + " " + operator + " " + teller2 + "/" + nevner2 + " = " +  (brøk_A.teller)+ "/" + (brøk_A.nevner));
            choice = showConfirmDialog(null, "Vil du bruke denne konverteren en gang til?", "Valuta konverterer", YES_OPTION);
            
            if (choice == YES_OPTION){
    
            } else {
                break;
            }
        }
    }
}