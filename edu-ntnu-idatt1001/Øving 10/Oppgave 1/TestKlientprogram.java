import java.util.ArrayList;
import java.util.Scanner;
/**
 * TestKlientprogram Klasse for å teste Øving 10 Oppgave 1
 * 
 * @author Jens Christian Aanestad
 */
public class TestKlientprogram {

    /**
     * Statisk metode for å konvertere dato og klokkeslett med tegn om til sammenlignbar Long-verdi
     * @param dato dato
     * @param klokkeslett klokkeslett
     * @return tidspunkt
     */
    static Long convertToTidspunkt(String dato, String klokkeslett) {
        String tidspunkt = "";
        String[] datoTabell = dato.split("[.]");
        String[] klokkeTabell = klokkeslett.split("[:]");
        tidspunkt += datoTabell[2] + datoTabell[1] + datoTabell[0] + klokkeTabell[0] + klokkeTabell[1];
        return Long.parseLong(tidspunkt);
    }

    /**
     * Statisk metode for å konvertere dato om til sammenlignbar Long-verdi
     * @param dato dato
     * @return tidspunkt uten klokkesletts spesifikasjon
     */
    static Long convertToDato(String dato) {
        String returnDato = "";
        String[] datoTabell = dato.split("[.]");
        returnDato += datoTabell[2] + datoTabell[1] + datoTabell[0];
        return Long.parseLong(returnDato);
    }

    /**
     * Statisk metode for å konvertere en tabell om til en ArrayList
     * @param arrangementTabell tabell over arrangementer
     * @return ArrayListe<Arrangement> arrangementer
     */
    static ArrayList<Arrangement> convertToArrayList(Arrangement[] arrangementTabell) {
        ArrayList<Arrangement> arrangementer = new ArrayList<>();
        for (Arrangement i : arrangementTabell) {
            arrangementer.add(i);
        }
        return arrangementer;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Arrangement[] tabell = {new Arrangement(1, "Solsiden", "Tihlde", "Lunsj", 202210301800L),                  
                                new Arrangement(2, "Lerkendal", "Eliteserien", "Fotballkamp", 202106101600L),
                                new Arrangement(3, "Solsiden", "NTNUI", "Nach", 202106101400L),
                                new Arrangement(3, "Gløshaugen", "NTNU", "Forelesning", 202008151200L)};
        ArrangementRegister register = new ArrangementRegister(convertToArrayList(tabell));
        
        while (true) {

            System.out.println("\nHvilken operasjon vil du utføre: \n1: Total oversikt \n2: Arrangementer på ett sted " + 
            "\n3: Arrangementer på én dato \n4: Registrer et nytt arrangement \n5: Arrangementer mellom to datoer \n6: Sortere arrangementer etter ønske \n7: Avslutt");
            int svar = Integer.parseInt(scanner.nextLine());
            
            if (svar == 1) {
                System.out.println(register);
            } 
            else if (svar == 2) {
                System.out.println("\nSted: ");
                String sted = scanner.nextLine();
                System.out.println(register.finneArrangementerPåGittSted(sted));
            } 
            else if (svar == 3) {
                System.out.println("\nDato: ");
                String dato = scanner.nextLine();
                System.out.println(register.finneArrangementerPåGittDato(convertToDato(dato)));
            } 
            else if (svar == 4) {
                System.out.println("\nNummer: ");
                int nummer = Integer.parseInt(scanner.nextLine());
                System.out.println("\nSted: ");
                String sted = scanner.nextLine();
                System.out.println("\nArrangør: ");
                String arrangør = scanner.nextLine();
                System.out.println("\nType: ");
                String type = scanner.nextLine();

                System.out.println("\nDato: ");
                String dato = scanner.nextLine();
                System.out.println("\nKlokkeslett");
                String klokkeslett = scanner.nextLine();
                Arrangement nyttArrangement = new Arrangement(nummer, sted, arrangør, type, convertToTidspunkt(dato, klokkeslett));
                register.registrereNyttArrangement(nyttArrangement);
                System.out.println("Arrangement registrert!");
            } 
            else if (svar == 5) {
                System.out.println("\nStart dato: ");
                String startDato = scanner.nextLine();
                System.out.println("\nSlutt dato: ");
                String sluttDato = scanner.nextLine();
                System.out.println(register.finneArrangementerITidsintervall(convertToDato(startDato), convertToDato(sluttDato)));
            } 
            else if (svar == 6) {
                System.out.println("\nSorter etter:");
                String sortering = scanner.nextLine();
                System.out.println(register.arrangementerSortert(sortering));
            } 
            else if (svar == 7) {
                System.out.println("Avslutter ...");
                break;
            } 
            else {
                System.out.println("Det du trykket inn ble ikke akspetert");
                break;
            }
        }
        scanner.close();
    }   
}