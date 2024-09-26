/**
 * Oppgaveoversikt klasse. 
 * @author Jens Christian Aanestad
 */
public class Oppgaveoversikt {
    private Student[] studenter;
    private int antStud = 0;

    /**
     * Konstruktør som tar inn en tabell av Student klassen
     * @param studenter Tabell av studenter
     */
    public Oppgaveoversikt(Student[] studenter) { 
        this.studenter = studenter;
        this.antStud = studenter.length;
    }

    /**
     * getmetode for å få antall studenter i Oppgaveoversikt objekt
     * @return antall studenter
     */
    public int getAntStud() {
        return antStud;
    }
    
    /**
     * getmetode for å få antall oppgaver en bestemt person har løst
     * @param navn Studentens navn
     * @return oppgaver løst
     */
    public int antallOppgaverBestemtPersonHarLøst(String navn) {
        int oppgaverLøst = 0;
        for (int i = 0; i < antStud; i++) {
            if (navn.equals(studenter[i].getNavn())) {
                oppgaverLøst = studenter[i].getAntOppg();
            }
        }
        return oppgaverLøst;
    }

    /**
     * setmetode for å registrere en ny student
     * @param nyStudent Student objekt
     */
    public void registrerNyStudent(Student nyStudent) {
        Student[] nyStudenter = new Student[antStud + 1];
        for (int j = 0; j < antStud; j ++) {
            nyStudenter[j] = studenter[j];
        }
        nyStudenter[antStud] = nyStudent;
        this.studenter = nyStudenter;
        this.antStud ++;
    }

    /**
     * setmetode for å øke antall oppgaver en bestemt student har løst
     * @param navn Studentens navn
     * @param økning økning i løste oppgaver
     */
    public void økAntallOppgaverForBestemtStudent(String navn, int økning) {
        for (int i = 0; i < antStud; i++) {
            if (navn.equals(studenter[i].getNavn())) {
                studenter[i].økAntOppg(økning);
            }
        }
    }

    @Override
    public String toString() {
        String returnString = "\n";
        for (Student i : studenter) {
            returnString += "Student navn: " + i.getNavn() + ". Antall oppgaver løst: " + i.getAntOppg() + "\n";
        }
        return returnString;
    }

}