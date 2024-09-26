import java.util.Scanner;
public class Testprogram2 {

    public static void main(String[] args) {

        Student[] tabell = {new Student("Aleksander Andersen", 23), 
                            new Student("Bernt Bjørnsen", 13),
                            new Student("Christian Christiansen", 72),
                            new Student("Dennis Damsgård", 62),
                            new Student("Erlend Ekdal", 11)
        };
        Oppgaveoversikt oversikt = new Oppgaveoversikt(tabell);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            
            System.out.println("\nHvilken operasjon vil du utføre: \n1: oversikt \n2: antall studenter registrert " + 
            "\n3: antall oppgaver en bestemt person har løst \n4: registrer ny student \n5: øk antall oppgaver for bestemt student \n6: avslutt");
            String svar = scanner.nextLine();
            
            
            if (svar.equals("1")) {
                System.out.println("\n" + oversikt);
            } else if (svar.equals("2")) {
                System.out.println("\nAntall studenter registrert: " + oversikt.getAntStud());
            } else if (svar.equals("3")) {
                System.out.println("\nVelg person: ");
                String navn = scanner.nextLine();
                System.out.println("\nAntall oppgaver " + navn + " har løst: " + oversikt.antallOppgaverBestemtPersonHarLøst(navn));
            } else if (svar.equals("4")) {
                System.out.println("\nOppgi navn: ");
                String navn = scanner.nextLine();
                System.out.println("Oppgi oppgaver " + navn + " har løst: ");
                int oppgaver = Integer.parseInt(scanner.nextLine());
                Student nyStudent = new Student(navn, oppgaver);
                oversikt.registrerNyStudent(nyStudent);
                System.out.println("\nStudent registrert!");
            } else if (svar.equals("5")) {
                System.out.println("\nOppgi navn: ");
                String navn = scanner.nextLine();
                System.out.println("Oppgi økning: ");
                int økning = Integer.parseInt(scanner.nextLine());
                oversikt.økAntallOppgaverForBestemtStudent(navn, økning);
                System.out.println("\nLøste oppgaver oppdatert");
            } else if (svar.equals("6")) {break;}
            
            else {
                System.out.println("\nDet du skrev inn var feil");
                break;
            }
        }
        
        scanner.close();
    }
}