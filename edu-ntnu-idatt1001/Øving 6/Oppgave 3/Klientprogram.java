import static javax.swing.JOptionPane.*;
public class Klientprogram {

    public static void main(String[] args) {
        while (true) {

            char operasjon = showInputDialog(null, "Velg mellom addisjon, multiplikasjon eller transponering: ").toLowerCase().charAt(0);
            if (operasjon == 'a') {
                String matrise1 = showInputDialog(null, 
                "(addisjon)\nSkriv inn en matrise på formen: \n [[x1, x2, ... xn], ... [m1, m2, mn]] \n").replace(" ", "");
                String matrise2 = showInputDialog(null, 
                "(addsijon)\nSkriv inn en annen matrise på formen: \n [[x1, x2, ... xn], ... [m1, m2, mn]] \n").replace(" ", "");
                Matrise matrix1 = new Matrise(matrise1);
                Matrise matrix2 = new Matrise(matrise2);
                int[][] matriseTabell1 = matrix1.getMatrisetabell(matrise1);
                int[][] matriseTabell2 = matrix2.getMatrisetabell(matrise2);
                int m1 = matrix1.getM(matrise1);
                int n1 = matrix1.getN(matrise1);
                int m2 = matrix2.getM(matrise2);
                int n2 = matrix2.getN(matrise2);
                System.out.println(matrix1.addere(matriseTabell1, matriseTabell2, m1, n1, m2, n2));
            }
            else if (operasjon == 'm') {
                String matrise1 = showInputDialog(null, 
                "(multiplikasjon)\nSkriv inn en matrise på formen: \n [[x1, x2, ... xn], ... [m1, m2, mn]] \n").replace(" ", "");
                String matrise2 = showInputDialog(null, 
                "(multiplikasjon)\nSkriv inn en annen matrise på formen: \n [[x1, x2, ... xn], ... [m1, m2, mn]] \n").replace(" ", "");
                Matrise matrix1 = new Matrise(matrise1);
                Matrise matrix2 = new Matrise(matrise2);
                int[][] matriseTabell1 = matrix1.getMatrisetabell(matrise1);
                int[][] matriseTabell2 = matrix2.getMatrisetabell(matrise2);
                int m1 = matrix1.getM(matrise1);
                int n1 = matrix1.getN(matrise1);
                int m2 = matrix2.getM(matrise2);
                int n2 = matrix2.getN(matrise2);
                System.out.println(matrix1.multiplisere(matriseTabell1, matriseTabell2, m1, n1, m2, n2));
            }
            else if (operasjon == 't') {
                String matrise1 = showInputDialog(null, 
                "(transponere)\nSkriv inn en matrise på formen: \n [[x1, x2, ... xn], ... [m1, m2, mn]] \n").replace(" ", "");
                Matrise matrix1 = new Matrise(matrise1);
                int[][] matriseTabell1 = matrix1.getMatrisetabell(matrise1);
                int m1 = matrix1.getM(matrise1);
                int n1 = matrix1.getN(matrise1);
                System.out.println(matrix1.transponere(matriseTabell1, m1, n1));
            }
            else {
                System.out.println("Det du trykket inn ble ikke akseptert");
                break;
            }
            int choice = showConfirmDialog(null, "Vil du bruke denne matrise kalkulatoren en gang til?", "The Matrix", YES_OPTION);
            
            if (choice == YES_OPTION){
    
            } else {
                break;
            }
        }
    }
}