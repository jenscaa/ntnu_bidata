/**
 * Verktøy
 */
import java.util.*;
public class Verktøy extends Tekstanalyse{

    public String[] tegn = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "æ", "ø", "å"};
    public List<String> tegnListe = Arrays.asList(tegn);


    public double rundAvTilToDesimaler(double desimaltall){
        double toDesimaler = desimaltall * 100;
        toDesimaler = Math.round(toDesimaler);
        return toDesimaler/100;
    }


    public int getAntallForskjelligeBokstaver(String tekst){
        List<String> bokstaver = new ArrayList<String>();
        int forskjelligeBokstaver = 0;

        for (int i = 0; i < tekst.length(); i++){
            if (!bokstaver.contains(String.valueOf(tekst.charAt(i)).toLowerCase()) && tegnListe.contains(String.valueOf(tekst.charAt(i)).toLowerCase())) {
                bokstaver.add(String.valueOf(tekst.charAt(i)).toLowerCase());
            }           
        } for (String i : bokstaver){
            forskjelligeBokstaver ++;
        }
        return forskjelligeBokstaver;
    }


    public int getTotaltAntallBokstaver(String tekst){
        int forskjelligeBokstaver = 0;
        for (int i = 0; i < tekst.length(); i++){
            if (tegnListe.contains(String.valueOf(tekst.charAt(i)).toLowerCase())){
                forskjelligeBokstaver++;
            }
        }
        return forskjelligeBokstaver;
    }


    public double getProsent(String tekst){
        Tekstanalyse analyse = new Tekstanalyse(tekst);
        double totalTegn = 0;
        double andreTegn = analyse.antallTegn[29];
        for (int i = 0; i <= 29; i++){
            totalTegn += analyse.antallTegn[i];
        }
        return rundAvTilToDesimaler(andreTegn/totalTegn * 100);
    }


    public int getForekomsterAvBokstav(String tekst, char bokstav){
        int position = 0;
        int index = 0;
        for (String i : tegn){
            if (i.equals(String.valueOf(bokstav))) {
                index = position;
            }
            position++;
        }
        Tekstanalyse analyse = new Tekstanalyse(tekst);
        return analyse.antallTegn[index];
    }


    public String getOftestBokstaver(String tekst){
        List<Integer> listeTall = new ArrayList<Integer>();
        List<Integer> indexStore = new ArrayList<Integer>();
        List<String> listeBokstav = new ArrayList<String>();
        Tekstanalyse analyse = new Tekstanalyse(tekst);
        String svar = "";
        int highestIndex = 0;

        for (int i = 0; i <= 29; i++){
            listeTall.add(analyse.antallTegn[i]);
        } for (int i = 0; i < 29; i++){
            if (listeTall.get(i) > highestIndex){
                highestIndex = listeTall.get(i);
            }
        } for (int i = 0; i <= 29; i++){
            if (listeTall.get(i) == highestIndex){
                indexStore.add(listeTall.get(i));
            }
        }

        System.out.println(listeTall);
        System.out.println(tegnListe);
        System.out.println(indexStore);

        for (int i = 0; i <= 29; i++){
            if (indexStore.get(0) == listeTall.get(i))
            listeBokstav.add(String.valueOf(tegnListe.get(i)));
        } for (String q : listeBokstav){
            svar += q + ", ";
        }

        return "Bokstaven/Bokstavene: " + svar + "er den/de bokstaven/bokstavene som forekommer oftest: "+ highestIndex;
    }
}