import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


/**
 * https://www.geeksforgeeks.org/arrays-in-java/
 */
public class Tekstanalyse {
    public String tekst = new String();
    public int antallTegn[] = new int[30];

    public Tekstanalyse(String random_tekst){
        this.tekst = random_tekst;
        for (int i = 0; i < random_tekst.length();i++){
            if (String.valueOf(random_tekst.charAt(i)).equals("a")){
                antallTegn[0]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("b")) {
                antallTegn[1]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("c")) {
                antallTegn[2]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("d")) {
                antallTegn[3]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("e")) {
                antallTegn[4]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("f")) {
                antallTegn[5]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("g")) {
                antallTegn[6]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("h")) {
                antallTegn[7]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("i")) {
                antallTegn[8]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("j")) {
                antallTegn[9]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("k")) {
                antallTegn[10]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("l")) {
                antallTegn[11]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("m")) {
                antallTegn[12]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("n")) {
                antallTegn[13]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("o")) {
                antallTegn[14]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("p")) { 
                antallTegn[15]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("q")) {
                antallTegn[16]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("r")) {
                antallTegn[17]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("s")) {
                antallTegn[18]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("t")) {
                antallTegn[19]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("u")) {
                antallTegn[20]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("v")) {
                antallTegn[21]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("w")) {
                antallTegn[22]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("x")) {
                antallTegn[23]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("y")) {
                antallTegn[24]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("z")) {
                antallTegn[25]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("æ")) {
                antallTegn[26]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("ø")) {
                antallTegn[27]++;
            } else if (String.valueOf(random_tekst.charAt(i)).equals("å")) {
                antallTegn[28]++;
            } else {
                antallTegn[29] ++;
            }
        }
    }

    public Tekstanalyse(){
        this.tekst = "";
    }
}

/*     public static void main(String[] args) throws IOException{

        Path filePath = Path.of("D:/OneDrive/SkoleMappe/1.Bach. DataIng/IDATT1001/Øvelsesoppgaver/Øving 6/Oppgave 2/text.txt");
        String tekst = Files.readString(filePath);
        Tekstanalyse randomTekst = new Tekstanalyse(tekst);
        Verktøy verktøy = new Verktøy();

        System.out.println("\nAntall forskjellige bokstaver: " + verktøy.getAntallForskjelligeBokstaver(randomTekst.tekst) + 
        "\nAntall totalt bokstaver: " + verktøy.getTotaltAntallBokstaver(randomTekst.tekst) + 
        "\nAntall del som ikke er bokstaver: " + verktøy.getProsent(randomTekst.tekst) + "%" + 
        "\nAntall forekomster av bokstav {} er " + verktøy.getForekomsterAvBokstav(randomTekst.tekst, 'a') +
        "\n" + verktøy.getOftestBokstaver(randomTekst.tekst) + "\n "); 
    }
}
*/