/**
 * ArbTaker
 */
public class ArbTaker {

    private Person personalia;
    private final int arbtakernr;
    private final int ansettelsesår;
    private int månedslønn;
    private int skatteprosent;


    public ArbTaker(String fornavn, String etternavn, int fødselsår, int arbtakernr, int ansettelsesår, int månedslønn, int skatteprosent) {
        this.personalia = new Person(fornavn, etternavn, fødselsår);
        this.arbtakernr = arbtakernr;
        this.ansettelsesår = ansettelsesår;
        this.månedslønn = månedslønn;
        this.skatteprosent = skatteprosent;
    }

    public Person getPersonalia() {
        return personalia;
    }

    public int getArbtakernr() {
        return arbtakernr;
    }

    public int getAnsettelsesår() {
        return ansettelsesår;
    }

    public int getMånedslønn() {
        return månedslønn;
    }

    public int getSkatteprosent() {
        return skatteprosent;
    }

    public void setSkatteprosent(int skatteprosent) {
        this.skatteprosent = skatteprosent;
    }

    public void setMånedslønn(int månedslønn) {
        this.månedslønn = månedslønn;
    }

    public double skatteTrekkPerMåned() {
        return månedslønn * (skatteprosent)/100;
    }

    public int bruttolønnPerÅr() {
        return månedslønn * 12;
    }

    public double skatteTrekkPerÅr() {
        return skatteTrekkPerMåned() * 10.5;
    }

    public String navnPåSpesiellForm() {
        return personalia.getEtternavn() + ", " + personalia.getFornavn();
    }

    public int alder() {
        java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
        int år = kalender.get(java.util.Calendar.YEAR);
        return år - personalia.getFødselsår();
    }

    public int årAnsattIBedriften() {
        java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
        int år = kalender.get(java.util.Calendar.YEAR);
        return år - ansettelsesår;
    }

    public boolean ansattMerEnnGittAntallÅr(int antallÅr) {
        return årAnsattIBedriften() > antallÅr;
    }

    public String toString() {
        return personalia + ". Arbeidstakernummer: " + arbtakernr + ". Ansettelsesår: " + ansettelsesår + 
        ". Månedslønn: " + månedslønn + " kr. \nSkatteprosent: " + skatteprosent + "%. Månends skatteavdrag: " + skatteTrekkPerMåned() + 
        ". \nBruttolønn per år: " + bruttolønnPerÅr() + " kr. Årerts skatteavdrag: " + skatteTrekkPerÅr() + ". Alder: " + alder() + 
        ". År ansatt i bedriften: " + årAnsattIBedriften() + ". ";
    }
}