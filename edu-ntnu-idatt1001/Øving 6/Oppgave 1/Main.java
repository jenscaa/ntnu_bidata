import static javax.swing.JOptionPane.*;
/**
 * Main
 */
public class Main {
    
    static int[] antall = new int[10];

    public static void main(String[] args) {

        TilfeldigTallGenerator tilfeldig = new TilfeldigTallGenerator();
        int gjennomløp = Integer.parseInt(showInputDialog(null, "Velg antall ganger løkken skal gå: "));

        for (int i = 0; i < gjennomløp; i++) {
            int tall = tilfeldig.tilfeldigTall();

            switch (tall) {
                case 0:
                antall[0] ++; break;
                case 1:
                antall[1]++;
                    break;
                case 2:
                antall[2]++;
                    break;
                case 3:
                antall[3]++;
                    break;
                case 4:
                antall[4]++;
                    break;
                case 5:
                antall[5]++;
                    break;
                case 6:
                antall[6]++;
                    break;
                case 7:
                antall[7]++;
                    break;
                case 8:
                antall[8]++;
                    break;
                case 9:
                antall[9]++;
                    break;
                default:
                    break;
            }
        }
        System.out.println("0:   " + antall[0] + "\n1:   " + antall[1] + "\n2:   " + antall[2] + "\n3:   " + antall[3] + 
        "\n4:   " + antall[4] + "\n5:   " + antall[5] + "\n6:   " + antall[6] + "\n7:   " + antall[7] + "\n8:   " + antall[8] + 
        "\n9:   " + antall[9]);
    }
}