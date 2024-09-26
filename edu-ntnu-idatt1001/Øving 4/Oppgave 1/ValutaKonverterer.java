import static javax.swing.JOptionPane.*;
import java.util.*;
public class ValutaKonverterer{

    public double kurs;
    public String name;

    static void feilmelding(){
        showMessageDialog(null, "Det du trykket inn ble ikke akseptert");
    }


    static double rundAvTilToDesimaler(double desimaltall){
        double toDesimaler = desimaltall * 100;
        toDesimaler = Math.round(toDesimaler);
        return toDesimaler/100;
    }


    static double konverterer(double kurs, double ny_kurs, double sum){
        double konvertert;
        konvertert = sum/ny_kurs*kurs;
        return konvertert;
    }


    public ValutaKonverterer(double kurs, String name){
        this.kurs = kurs;
        this.name = name;
    }


    public static void main(String[]args){

        boolean run = true;
        int number = 0;
        double sum = 0;
        double nySum = 0;
        double kurs = 0;
        double nyKurs = 0;
        String valuta = "";
        String nyValuta = "";

        ValutaKonverterer usDollar = new ValutaKonverterer(9.7222, "dollar");
        ValutaKonverterer euro = new ValutaKonverterer(9.7553, "euro"); 
        ValutaKonverterer ukPund = new ValutaKonverterer(11.3904, "pund"); 
        ValutaKonverterer svKroner = new ValutaKonverterer(0.916, "svenske kroner");
        ValutaKonverterer noKroner = new ValutaKonverterer(1, "norske kroner");

        try{
            while (run){
            
                number = Integer.parseInt(showInputDialog("Velg valuta: \n \n 1: dollar \n \n 2: euro \n \n 3: pund \n \n 4: svenske kroner \n \n 5: norske kroner \n \n 6: avslutt \n "));
    
                if (number == 1){
                    valuta = usDollar.name; 
                    kurs = usDollar.kurs;
                } else if (number == 2){
                    valuta = euro.name; 
                    kurs = euro.kurs;
                } else if (number == 3){
                   valuta = ukPund.name; 
                   kurs = ukPund.kurs;
                } else if (number == 4){
                    valuta = svKroner.name; 
                    kurs = svKroner.kurs;
                } else if (number == 5){
                    valuta = noKroner.name;
                    kurs = noKroner.kurs;
                } else if (number == 6){
                    break;
                } else {
                    feilmelding();
                    break;
                }
                    
                sum = Double.parseDouble(showInputDialog("Hvor mange " + valuta + " ønsker du å konvertere?"));
                number = Integer.parseInt(showInputDialog("Hva ønsker du å konvertere " + sum + " " + valuta + " til? \n \n Velg valuta: \n \n 1: dollar \n \n 2: euro \n \n 3: pund \n \n 4: svenske kroner \n \n 5: norske kroner \n \n 6: avslutt \n "));

                if (number == 1){
                    nyValuta = usDollar.name; 
                    nyKurs = usDollar.kurs;
                } else if (number == 2){
                    nyValuta = euro.name; 
                    nyKurs = euro.kurs;
                } else if (number == 3){
                    nyValuta = ukPund.name; 
                    nyKurs = ukPund.kurs;
                } else if (number == 4){
                    nyValuta = svKroner.name; 
                    nyKurs = svKroner.kurs;
                } else if (number == 5){
                    nyValuta = noKroner.name;
                    nyKurs = noKroner.kurs;
                } else if (number == 6){
                    break;
                } else {
                    feilmelding();
                    break;
                }

                nySum = konverterer(kurs, nyKurs, sum);
                showMessageDialog(null,  sum + " " + valuta + " tilsvarer " + rundAvTilToDesimaler(nySum) + " " + nyValuta);
                int choice = showConfirmDialog(null, "Vil du bruke denne konverteren en gang til?", "Valuta konverterer", YES_OPTION);

                if (choice == YES_OPTION){

                } else {
                    break;
                }
            }
        } catch (NumberFormatException e){
            feilmelding();
        }

    }
}