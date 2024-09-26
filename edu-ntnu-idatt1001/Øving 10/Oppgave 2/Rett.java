/**
 * Klassen Rett som har attributtene navn, type, pris og oppskrift
 * 
 * @author Jens Christian Aanestad
 */
public class Rett {
    private final String navn;
    private final String type;
    private final double pris;
    private final String oppskrift;

    /**
     * Konstruktør som tar inn parameterne navn, type, pris og oppskrift
     * @param navn navn til retten
     * @param type typen til retten
     * @param pris prisen til retten
     * @param oppskrift oppskriften til retten
     */
    public Rett(String navn, String type, double pris, String oppskrift) {
        this.navn = navn;
        this.type = type;
        this.pris = pris;
        this.oppskrift = oppskrift;
    }

    /**
     * getmetode for å returnere rettens navn
     * @return navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * getmetode for å returnere rettens type
     * @return type
     */ 
    public String getType() {
        return type;
    }
    /**
     * getmetode for å returnere rettens pris
     * @return pris
     */
    public double getPris() {
        return pris;
    }

    /**
     * getmetode for å returnere rettens oppskrift 
     * @return oppskrift
     */
    public String getOppskrift() {
        return oppskrift;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + ". Type: " + type + ". Pris: " + pris + " kr. Oppskrift: " + oppskrift;
    }
}