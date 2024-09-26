import java.util.Scanner;
public class Testprogram1 {

    public static void main(String[] args) {
        Student student = new Student("Aleksander", 10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            
            System.out.println("\nHvilken operasjon vil du utføre: \n(1) getNavn \n(2) getAntOppg \n(3) økAntOppg \n(4) toString \n(5) Avslutt ");
            String svar = scanner.nextLine();
            
            if (svar.equals("1")) {
                System.out.println("\n" + student.getNavn());
            } else if (svar.equals("2")) {
                System.out.println("\n" + student.getAntOppg());
            } else if (svar.equals("3")) {
                System.out.println("\nØk antall oppgaver med: ");
                int økning = Integer.parseInt(scanner.nextLine()); 
                student.økAntOppg(økning);
            } else if (svar.equals("4")) {
                System.out.println("\n" + student);
            } else if (svar.equals("5")) {
                break;
            } else {
                System.out.println("\nDet du skrev inn var feil");
                break;
            }
        }
        
        scanner.close();
    }    
}