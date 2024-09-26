import static javax.swing.JOptionPane.*;
class Klientprogram2 {
    public static void main(String[] args) {
        
        while (true) {
            String tekst = showInputDialog(null, "Skriv inn en tekst");
            Tekstbehandling objekt = new Tekstbehandling(tekst);
            int operasjon = Integer.parseInt(showInputDialog(null, "Velg operasjon: \n1: Finn antall ord i teksten \n2: Finn gjennomsnittlig ordlengde " + 
            "\n3: Finn gjennomsnittlig antall ord per periode \n4: Erstatt et ord med et annet \n5: Få teksten uten endreinger \n6: Få teksten med store bokstaber \n7: Avslutt"));

            if (operasjon == 1) {
                System.out.println("\nAntall ord: " + objekt.finneAntallOrd(tekst));
            } else if (operasjon == 2) {
                System.out.println("Gjennomsnittlig ordlengde: " + objekt.gjennomsnitteligOrdlengde(tekst));
            } else if (operasjon == 3) {
                System.out.println("Gjennomsnittlig antall ord per periode: " + objekt.gjennomsnitteligAntallOrdPerPeriode(tekst));
            } else if (operasjon == 4) {
                String søkeOrd = showInputDialog(null, "Hvilket ord vil du erstatte? ");
                String erstatt = showInputDialog(null, "Hva vil du erstatte med");
                objekt.ordSubstitusjon(tekst, søkeOrd, erstatt);
            } else if (operasjon == 5) {
                System.out.println(objekt.getOrginialTekst());
            } else if (operasjon == 6) {
                System.out.println(objekt.tekstIStoreBokstaver());
            } else if (operasjon == 7) {
                break;
            } else {
                objekt.feilmelding();
                break;
            }

            int choice = showConfirmDialog(null, "Vil du bruke dette programmet en gang til? ", "HOTD" , YES_OPTION);
            if (choice != YES_OPTION) {
                break;
            }
        }
    }
}