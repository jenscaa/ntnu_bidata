import java.util.ArrayList;
import java.util.Scanner;
/**
 * Klassen Testklient for å teste Øving 10 Oppgave 2
 * 
 * @author Jens Christian Aanestad
 */
public class Testklient {

    /**
     * Statisk metode for å konvertere en tabell av Meny til ArrayList<Meny> 
     * @param menyTabell menyTabell
     * @return ArrayList<Meny> menyer
     */
    static ArrayList<Meny> convertToArrayListMeny(Meny[] menyTabell) {
        ArrayList<Meny> menyer = new ArrayList<>();
        for (Meny i : menyTabell) {
            menyer.add(i);
        }
        return menyer;
    }

    /**
     * Statisk metode for å konvertere en tabell av Rett til ArrayList<Rett> 
     * @param rettTabell rettTabell
     * @return ArrayList<Rett> retter
     */
    static ArrayList<Rett> convertToArrayListRett(Rett[] rettTabell) {
        ArrayList<Rett> retter = new ArrayList<>();
        for (Rett i : rettTabell) {
            
            retter.add(i);
        }
        return retter;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
//Hardcode
//**************************************************************************************************************************************************** */

        Rett[] meny1 = {new Rett("Taco", "Forrett", 169.90, "N/A"), 
                        new Rett("Pizza", "Hovedrett", 369.90, "N/A"),
                        new Rett("Iskrem", "Dessert", 80, "N/A")};

        Rett[] meny2 = {new Rett("Brunost", "Forrett", 61.90, "N/A"),
                        new Rett("Forikål", "Hovedrett", 321.90, "N/A"),
                        new Rett("Ostekake", "Dessert", 200, "N/A")};

        Rett[] meny3 = {new Rett("Sushi", "Forett", 69.90, "N/A"),
                        new Rett("Risotto", "Hovedrett", 223.90, "N/A"),
                        new Rett("Iskrem", "Dessert", 80, "N/A")};     

        Meny[] menyer = {new Meny("Amerikaneren", convertToArrayListRett(meny1)), 
                         new Meny("Nordmannen", convertToArrayListRett(meny2)),
                         new Meny("Kineseren", convertToArrayListRett(meny3))};

        MenyRegister register = new MenyRegister(convertToArrayListMeny(menyer));

//**************************************************************************************************************************************************** */

        boolean run = true;
        while (run) {
            System.out.println("\nHvilken operasjon vil du utføre: \n1: Meny oversikt \n2: Rett oversikt \n3: Registerer en ny rett " + 
            "\n4: Registrer en ny meny \n5: Finne en rett ved navn \n6: Finne retter av en gitt type \n7: Finne menyer med totalpris innenfor et intervall \n8: Avslutt");
            int nummer = Integer.parseInt(scanner.nextLine());
            
            if (nummer == 1) {
                System.out.println(register);
            } 
            else if (nummer == 2) {
                System.out.println(register.toStringRetter());
            } 
            else if (nummer == 3) {
                System.out.println("\nNavn på ny rett: ");
                String navn = scanner.nextLine();
                System.out.println("Type: ");
                String type = scanner.nextLine();
                System.out.println("Pris: ");
                double pris = Double.parseDouble(scanner.nextLine());
                Rett nyRett = new Rett(navn, type, pris, "N/A");
                register.registrerNyRett(nyRett);
                System.out.println("Rett registrert\n");
            } 
            else if (nummer == 4) {
                ArrayList<Rett> array = new ArrayList<Rett>();
                System.out.println("\nMeny navn: ");
                String navn = scanner.nextLine();
                while (true) {
                    System.out.println("Vil du velge eksisterende retter i denne menyen? (ja/nei) ");
                    String flereRetter = scanner.nextLine().toLowerCase();
                    if (flereRetter.equals("ja")) {
                        System.out.println(register.toStringRetter());
                        System.out.println("\nVelg navn fra listen over");
                        String navnFraRett = scanner.nextLine().toLowerCase();
                        for (Rett i : register.getRetter()) {
                            if (navnFraRett.equals(i.getNavn().toLowerCase())) {
                                array.add(i);
                            }
                        }
                    } else {
                        System.out.println("\nRett:\n    navn: ");
                        String rettNavn = scanner.nextLine();
                        System.out.println("\n    type: ");
                        String type = scanner.nextLine();
                        System.out.println("\n    pris: ");
                        double pris = Double.parseDouble(scanner.nextLine());
                        array.add(new Rett(rettNavn, type, pris, "N/A"));
                    }
                    System.out.println("Vil du legge til en ny rett? (ja/nei)");
                    String nyRett = scanner.nextLine().toLowerCase();
                    if (nyRett.equals("nei")) {
                        System.out.println("\n");
                        break;
                    }
                }
                Meny nyMeny = new Meny(navn, array);
                register.registrerNyMeny(nyMeny);
            } 
            else if (nummer == 5) {
                System.out.println("\nNavn på rett: ");
                String navn = scanner.nextLine();
                System.out.println(register.getRett(navn));
            } 
            else if (nummer == 6) {
                System.out.println("\nVelg type: ");
                String type = scanner.nextLine();
                System.out.println(register.getRettAvGittType(type));
            } 
            else if (nummer == 7) {
                System.out.println("\nVelg startintervall: ");
                double startIntervall = Double.parseDouble(scanner.nextLine());
                System.out.println("\nVelg sluttintervall: ");
                double sluttIntervall = Double.parseDouble(scanner.nextLine());
                System.out.println(register.getTotalprisMenyerInnenforIntervall(startIntervall, sluttIntervall));
            } 
            else if (nummer == 8) {
                System.out.println("Avslutter ...");
                run = false;
            } 
            else {
                System.out.println("Det du trykket inn ble ikke akspetert");
                break;
            }
            System.out.println("*".repeat(100));
        }
        scanner.close(); 
    }
}