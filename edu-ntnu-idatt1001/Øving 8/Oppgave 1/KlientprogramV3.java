/**
 * KlientprogramV3
 */
import static javax.swing.JOptionPane.*;
public class KlientprogramV3 {

    public static void main(String[] args) {

        ArbTaker arbTaker = new ArbTaker("Matt", "Smith", 1982, 666, 2019, 1000000, 30);

        System.out.println(arbTaker);
        
        while (true) {

            String svar = showInputDialog(null, "Hva vil du endre på? \nEks:\n -fornavn \n -etternavn \n -fødselsår" + 
            "\n -arbeidstakernummer \n -annsettelsesår \n -månedslønn \n -skatteprosent").toLowerCase();

            
                
           if (svar.equals("månedslønn")) {
                int set = Integer.parseInt(showInputDialog(null, "Endre månedslønn til: "));
                arbTaker.setMånedslønn(set);
            } else if (svar.equals("skatteprosent")) {
                int set = Integer.parseInt(showInputDialog(null, "Endre skatteprosent til: "));
                arbTaker.setSkatteprosent(set);
            } else {
                showMessageDialog(null, "Det du skrev inn ble ikke akseptert");
            }

            System.out.println("\n" + arbTaker);

            int choice = showConfirmDialog(null, "Vil du ha flere endringer? ", "Repaet?", YES_OPTION);
            if (choice == YES_OPTION) {}
            else {break;}
        }
    }
}