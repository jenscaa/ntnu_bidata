import java.util.ArrayList;
/**
 * Klassen Meny som har attributtene navn og retter
 * 
 * @author Jens Christian Aanestad 
 */
public class Meny {
    private final String navn;
    private final ArrayList<Rett> retter;

    /**
     * Konstruktøren som tar parameterne navn og retter
     * @param navn navn på Menyen
     * @param retter retter i Menyen
     */
    public Meny(String navn, ArrayList<Rett> retter) {
        this.retter = retter;
        this.navn = navn;
    }

    /**
     * getmetode for å returnere navnet til menyen
     * @return navn
     */
    public String getName() {
        return navn;
    }

    /**
     * getmetode for å returnere alle retter i menyen
     * @return retter
     */
    public ArrayList<Rett> getRetter() {
        return retter;
    }

    @Override
    public String toString() {
        String returnString = "\nMeny: " + navn + "\n";
        for (Rett i : retter) {returnString += "   " + i + "\n";}
        return returnString;
    }
}