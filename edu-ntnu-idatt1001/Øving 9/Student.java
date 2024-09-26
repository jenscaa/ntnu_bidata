/**
 * Student klasse som tar inn navn og antall oppgaver studenten har løst.
 * 
 * @author Jens Christian Aanestad
 */
public class Student {
    private final String navn;
    private int antOppg;

    /**
     * Konstruktør som tar inn navn og antall oppgaver løst
     * @param navn Studentens navn
     * @param antOppg Antall oppgaver studenten har løst
     */
    public Student(String navn, int antOppg) {
        this.navn = navn;
        this.antOppg = antOppg;
    }

    /**
     * getmetode for å få studentens navn
     * @return Studentens navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * getmetode for å få antall oppgaver studenten harløst
     * @return Antall oppgaver løst
     */
    public int getAntOppg() {
        return antOppg;
    }

    /**
     * metode for å øke antall oppgaver studenten har løst
     * @param økning Økning i antall oppgaver
     */
    public void økAntOppg(int økning) {
        antOppg += økning;
    }

    @Override
    public String toString() {
        return "Student navn: " + navn + ". Antall oppgaver: " + antOppg;
    }
}