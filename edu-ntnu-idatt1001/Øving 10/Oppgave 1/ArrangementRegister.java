import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Klassen ArrangementRegister som har attributten ArrayList<Arrangementer> arrangementer
 * 
 * @author Jens Christian Aanestad
 */
public class ArrangementRegister {
    private ArrayList<Arrangement> arrangementer;

    /**
     * Konstruktør som tar parameteren arrangementer
     * @param arrangementer ArrayList<Arrangementer> arrangementer
     */
    public ArrangementRegister(ArrayList<Arrangement> arrangementer) {
        this.arrangementer = arrangementer;
    }

    /**
     * Metode for å registrere et nytt arrangement
     * @param nyttArrangement nytt arrangement
     */
    public void registrereNyttArrangement(Arrangement nyttArrangement) {
        arrangementer.add(nyttArrangement);
    }

    /**
     * Metode for å finne arrangementer på et gitt sted. Har parameteren gittSted
     * @param gittSted sted for å søke etter arrangementer i registeret
     * @return arrangementer på gitt sted
     */
    public String finneArrangementerPåGittSted(String gittSted) {
        String returnTekst = "";
        for (Arrangement i : arrangementer) {
            if (i.getSted().equals(gittSted)) {
                returnTekst += i + "\n";
            }
        }
        return returnTekst;
    }

    /**
     * Metode for å finne arrangementer på gitt dato. Har parameteren dato
     * @param dato dato for å søke etter arrangementer i registeret
     * @return arrangementer på gitt dato
     */
    public String finneArrangementerPåGittDato(Long dato) {
        String returnTekst = "";
        for (Arrangement i : arrangementer) {
            if (i.getTidspunkt()/10000 == dato) {
                returnTekst += i + "\n";
            }
        }
        return returnTekst;
    }

    /**
     * Metode for å finne arrangementer innenfor gitt tidsintervall. Har parameterene startdato og sluttdato
     * @param startDato startdatoen for søket av arrangementer innenfor intetvallet
     * @param sluttDato sluttdatoen for søket av arrangementer innenfor intetvallet
     * @return arrangementer innenfor gitt tidsintervall
     */
    public String finneArrangementerITidsintervall(Long startDato, Long sluttDato) {
        String returnTekst = "\n";
        ArrayList<Arrangement> liste = new ArrayList<Arrangement>();                                       

        for (Arrangement i : arrangementer) {
            if (i.getTidspunkt()/10000 >= startDato && i.getTidspunkt()/10000 <= sluttDato) {
                liste.add(i);
            }
        }

        Collections.sort(liste, new Comparator<Arrangement>() {                                         
            @Override
            public int compare(Arrangement o1, Arrangement o2) {
                return Long.valueOf(o1.getTidspunkt()).compareTo(o2.getTidspunkt());
            }});

        for (Arrangement i : liste) {                                                                      
            returnTekst += i + "\n";
        }
        return returnTekst;
    }

    /**
     * Metode for å finne arrangementer sortert etter ønsket behov. Har parameteren sortering
     * @param sortering sortering
     * @return arrangementer sortert etter ønsket behov
     */
    public String arrangementerSortert(String sortering) {
        String returnliste = "";
        ArrayList<Arrangement> liste = new ArrayList<Arrangement>();

        for (Arrangement i : arrangementer) {                     
            liste.add(i);
        }

        if (sortering.toLowerCase().equals("sted")) {
            Collections.sort(liste, new Comparator<Arrangement>() {
                @Override
                public int compare(Arrangement o1, Arrangement o2) {
                    return o1.getSted().compareTo(o2.getSted());
                }});                                            
        } else if (sortering.toLowerCase().equals("type")) {
            Collections.sort(liste, new Comparator<Arrangement>() {
                @Override
                public int compare(Arrangement o1, Arrangement o2) {
                    return o1.getType().compareTo(o2.getType());
                }});   
        } else {
            Collections.sort(liste, new Comparator<Arrangement>() {                                           
                @Override
                public int compare(Arrangement o1, Arrangement o2) {
                    return Long.valueOf(o1.getTidspunkt()).compareTo(o2.getTidspunkt());
                }});
        }

        for (Arrangement i : liste) {
            returnliste += i + "\n";
        }
        return returnliste;
    }

    @Override
    public String toString() {
        String returnString = "\n";
        for (Arrangement i : arrangementer) {
            returnString += i + "\n";
        }
        return returnString;
    }
}