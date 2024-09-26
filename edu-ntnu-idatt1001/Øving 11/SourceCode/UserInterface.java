import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klassen UserInterface som representerer et brukergrensesnitt
 * for klassene RealEstate og RealEstateRegister. Tar ingen konstruktør
 * @author Jens Christian Aanestad
 */
public class UserInterface {
    private final int LIST_ALL_PROPERTIES = 1;
    private final int FIND_PROPERTY = 2;
    private final int NUMBER_OF_PROPERTIES = 3;
    private final int ADD_PROPERTY = 4;
    private final int REMOVE_PROPERTY = 5;
    private final int PROPERTIES_BY_LOT_NUMBERS = 6;
    private final int CALCULATE_AVERAGE_AREA = 7;
    private final int EXIT = 8;
    

    /**
     * Metode for å presentere menyen for brukeren, og får inn svar
     * fra brukeren i form av et tall. Tallet blir deretter returnert
     * @return svar fra brukeren
     */
    private int showMenu() {
        int menuChoice = 0;
        System.out.println("\n" + "*".repeat(180) + "\n \nChoose operation: \n");
        System.out.println("1. List all properties");
        System.out.println("2. Search property");
        System.out.println("3. Number of properties");
        System.out.println("4. Add property");
        System.out.println("5. Remove property");
        System.out.println("6. Properties by lot number");
        System.out.println("7. Calculate average area");
        System.out.println("8. Quit");
        System.out.println("\nPlease enter a number between 1 and 8.\n");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            menuChoice = scanner.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * Hovedmetoden for grensesnittet. Starter applikasjonen som er hovedloopen
     * og presenterer menyen, og henter return verdien fra brukeren og utfører
     * valgte funksjonalitet
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        RealEstateRegister register = new RealEstateRegister();
        register.addProperty(1445, "Gloppen",77,631, null,1017.6,"Jens Olsen");
        register.addProperty(1445, "Gloppen",77,131, "Syningom",661.3,"Nicolay Madsen");
        register.addProperty(1445, "Gloppen",75,19, "Fugletun",650.6,"Evilyn Jensen");
        register.addProperty(1445, "Gloppen",74,188, null,1457.2,"Karl Ove Bråten");
        register.addProperty(1445, "Gloppen",69,47, "Høiberg",1339.4,"Elsa Indergård");




        boolean finished = false;
        try {
            while (!finished) {
                int menuChoice = this.showMenu();
                switch (menuChoice)
                {
                    case LIST_ALL_PROPERTIES:
                    System.out.println(register);
                    break;
    
                    case FIND_PROPERTY:
                    System.out.println("Skriv inn kommunenummer: ");
                    int municipalityNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Skriv inn gårdsnummer: ");
                    int lotNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("Skriv inn bruksnummer: ");
                    int sectionNumber = Integer.parseInt(scanner.nextLine());
                    System.out.println("\n" + register.findProperty(municipalityNumber, lotNumber, sectionNumber));
                    break;
    
                    case NUMBER_OF_PROPERTIES:
                    System.out.println("\nEiendommer i registeret: " + register.numberOfProperties());
                    break;
    
                    case ADD_PROPERTY:
                    System.out.println("Skriv inn kommunenummer: ");
                    int kommunenummer = Integer.parseInt(scanner.nextLine());
                    System.out.println("Skriv inn kommunenavn: ");
                    String kommunenavn = scanner.nextLine();
                    if (kommunenummer == 1445) {kommunenavn = "Gloppen";}
                    System.out.println("Skriv inn gårdsnummer: " );
                    int gårdsnummer = Integer.parseInt(scanner.nextLine());
                    System.out.println("Skriv inn bruksnummer: " );
                    int bruksnummer = Integer.parseInt(scanner.nextLine());
                    System.out.println("Skriv inn bruksnavn: ");
                    String bruksnavn = scanner.nextLine();
                    if (bruksnavn.equals("")) {bruksnavn = null;}
                    System.out.println("Skriv inn areal: ");
                    double areal = Double.parseDouble(scanner.nextLine());
                    System.out.println("Skriv inn navn på eier: ");
                    String eiernavn = scanner.nextLine();
                    register.addProperty(kommunenummer, kommunenavn, gårdsnummer, bruksnummer, bruksnavn, areal, eiernavn);
                    System.out.println("Eiendom registrert!");
                    break;
    
                    case REMOVE_PROPERTY:
                    System.out.println("Skriv inn kommunenummer på eiendommen du vil fjerne: ");
                    int kommuneNummerDelete = Integer.parseInt(scanner.nextLine());
                    System.out.println("Skriv inn gårdsnummeret på eiendommen du vil fjerne: ");
                    int gårdsNummerDelete = Integer.parseInt(scanner.nextLine());
                    System.out.println("Skriv inn bruksnummer på eiendommen du vil fjerne: ");
                    int bruksNummerDelete = Integer.parseInt(scanner.nextLine());
                    if (register.findProperty(kommuneNummerDelete, gårdsNummerDelete, bruksNummerDelete) == null) {
                        System.out.println("Eiendommen du vil fjerne finnes ikke");
                    } else {
                        System.out.println("Eiendommen fjernet!");
                    }
                    register.deleteProperty(kommuneNummerDelete, gårdsNummerDelete, bruksNummerDelete);
                    break;
    
                    case PROPERTIES_BY_LOT_NUMBERS:
                    System.out.println("Skriv inn gårdsnummer: ");
                    int gårdsNummer = Integer.parseInt(scanner.nextLine());
                    for (RealEstate i : register.propertiesByLotNumber(gårdsNummer)) {
                        System.out.println(i.toString());
                    }
                    break;
    
                    case CALCULATE_AVERAGE_AREA:
                    System.out.println("Gjennomsnittlig areal av alle eiendommene er " + register.findAverageAreal() + " m^2");
                    break;
                    
                    case EXIT:
                    System.out.println("Avslutter... \n");
                    finished = true;
                    break;
                    default:
                    System.out.println("Unrecognized menu selected..");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Du trykket inn feil datatype. Start programmet på nytt");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        UserInterface i = new UserInterface();
        i.start();
    }
}