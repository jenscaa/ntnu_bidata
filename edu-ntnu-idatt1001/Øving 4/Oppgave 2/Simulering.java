import java.util.*;
class Simulering{

    public int sumPoeng;
    

    static int kastTerning(){
        Random ran = new Random();
        int terning = ran.nextInt(6) + 1;
        return terning;
    }


    static void erFerdig(String winner){
        System.out.println(winner + " har vunnet!");
    }


    public Simulering(int sumPoeng){
        this.sumPoeng = sumPoeng;
    }


    public static void main(String[] args) {
        
        boolean run = true;
        int rundenummer = 1;
        int terningA = 0;
        int terningB = 0;
        String winner = "";

        Simulering spillerA = new Simulering(0);
        Simulering spillerB = new Simulering(0);

        while (run){

            terningA = kastTerning();
            spillerA.sumPoeng += terningA;
            terningB = kastTerning();
            spillerB.sumPoeng += terningB;

            System.out.println("Runde: " + rundenummer + " \n Poengsum spiller1: " + spillerA.sumPoeng + " \n Poengsum spiller2: " + spillerB.sumPoeng +  " \n");

            if (spillerA.sumPoeng > 100){
                spillerA.sumPoeng -= terningA;
            } if (spillerB.sumPoeng > 100){
                spillerB.sumPoeng -= terningB;
            } if (spillerA.sumPoeng == 100 && spillerB.sumPoeng == 100){
                spillerA.sumPoeng = 0;
                spillerB.sumPoeng = 0;
            } if (terningA == 1){
                spillerA.sumPoeng = 0;
            } if (terningB == 1){
                spillerB.sumPoeng = 0;
            } if (spillerA.sumPoeng == 100){
                winner = "Spiller1";
                break;
            } if (spillerB.sumPoeng == 100){
                winner = "Spiller2";
                break;
            }
            rundenummer ++;
        }

    erFerdig(winner);

    }
}