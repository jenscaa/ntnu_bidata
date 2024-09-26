/**
 * Klasse Arrangement som tar inn arrangementets nummer, sted, arrangør, type og tidspunkt
 * @author Jens Christian Aanestad
 */
public class Arrangement {
    private final int nummer;
    private final String sted;
    private final String arrangør;
    private final String type;
    private final Long tidspunkt;

    /**
     * Konstruktør som tar parameterne nummer, sted, arrangør, type og tidspunkt
     * @param nummer arrangementets nummer
     * @param sted arrangementets sted
     * @param arrangør arrangementets arrangør
     * @param type arrangementets type
     * @param tidspunkt arrangementets tidspunkt
    */
    public Arrangement(int nummer, String sted, String arrangør, String type, Long tidspunkt) {
        this.nummer = nummer;
        this.sted = sted;
        this.arrangør = arrangør;
        this.type = type;
        this.tidspunkt = tidspunkt;
    }

    /**
     * getmetode for å returnere arrangementets nummer
     * @return nummer
     */
    public int getNummer() {
        return nummer;
    }
    /**
     * getmetode for å returnere arrangementets sted
     * @return sted
     */
    public String getSted() {
        return sted;
    }

    /**
     * getmetode for å returnere arrangementets arrangør
     * @return arrangør
     */
    public String getArrangør() {
        return arrangør;
    }

    /**
     * getmetode for å returnere arrangementets type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * getmetode for å returnere arrangementets tidspunkt
     * @return tidspunkt
     */
    public Long getTidspunkt() {
        return tidspunkt;
    }

    @Override
    public String toString() {
        return "Nummer: " + nummer + ". Sted: " + sted + ". Arrangør: " + arrangør + ". Type: " + type + ". Tidspunkt: " + tidspunkt;
    }
}