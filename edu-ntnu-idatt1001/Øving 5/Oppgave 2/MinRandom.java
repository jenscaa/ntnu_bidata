import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import static javax.swing.JOptionPane.*;


public class MinRandom {

    private Random random = new Random();


    static double rundAvTilToDesimaler(double desimaltall){
        double toDesimaler = desimaltall * 100;
        toDesimaler = Math.round(toDesimaler);
        return toDesimaler/100;
    }

    public int nesteHeltall(int nedre, int ovre) {
        int tilfeldigTall = random.nextInt(nedre, ovre);
        return tilfeldigTall; 
    } 
    
    public double nesteDesimaltall(double nedre, double ovre) {
        double tilfeldigTall = random.nextDouble(nedre, ovre);
        return tilfeldigTall;
    }

    public MinRandom(){
        
    }

    public static void main(String[] args) {

        MinRandom minRandom = new MinRandom();

        int tilfeldigHeltall;
        double tilfeldigDesimaltall;
        List<Integer> listeHeltall = new ArrayList<Integer>();
        List<Double> listeDesimaltall = new ArrayList<Double>();
        int startIntervall = Integer.parseInt(showInputDialog(null, "Velg startintervall for tilfeldig heltall: "));
        int sluttIntervall = Integer.parseInt(showInputDialog(null, "Velg sluttintervall for tilfeldig heltall: "));
        double startIntervall2 = Double.parseDouble(showInputDialog(null, "Velg startintervall for tilfeldig desimaltall: "));
        double sluttIntervall2 = Double.parseDouble(showInputDialog(null, "Velg sluttintervall for tilfeldig desimaltall: "));

        for (int i = 0; i < 10; i ++){
            tilfeldigHeltall = minRandom.nesteHeltall(startIntervall, sluttIntervall);
            listeHeltall.add(tilfeldigHeltall);
            tilfeldigDesimaltall = minRandom.nesteDesimaltall(startIntervall2, sluttIntervall2);
            listeDesimaltall.add(rundAvTilToDesimaler(tilfeldigDesimaltall));
        }
        showMessageDialog(null, "I intervallet   [" + startIntervall + ", " + 
        sluttIntervall + ">   er tilfeldige heltall: \n \n " + listeHeltall +
         "\n \n I intervallet   [" + startIntervall2 + ", " + sluttIntervall2 +
          ">   er tilfeldige desimaltall: \n \n " + listeDesimaltall);

    }
}