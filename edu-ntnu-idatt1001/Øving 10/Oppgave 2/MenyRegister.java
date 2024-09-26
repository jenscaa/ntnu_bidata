import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Klassen MenyRegister som har attributtene menyer og retter
 * 
 * @author Jens Christian Aanestad
 */
public class MenyRegister {
    private ArrayList<Meny> menyer;
    private ArrayList<Rett> retter = new ArrayList<>();
    
    /**
     * Konstruktør som tar parameteren menyer
     * @param menyer menyer i registeret
     */
    public MenyRegister(ArrayList<Meny> menyer) {
        this.menyer = menyer;

        ArrayList<Rett> duplicate = new ArrayList<Rett>();                      //Oppretter duplicate liste for å håndtere duplicates i retter
        ArrayList<Integer> duplicateIndex = new ArrayList<Integer>();           //Oppretter duplicateIndex liste for å lagre indekser i dublicate som må fjernes

        for (Meny i : menyer) {
            for (Rett j : i.getRetter()) {
                duplicate.add(j);
            }
        }
        for (int i = 0; i < duplicate.size(); i ++) {
            Boolean erNevnt = false;
            for (int j = 0; j < duplicate.size(); j ++) {
                if (duplicate.get(i).getNavn().equals(duplicate.get(j).getNavn()) && erNevnt == false) {
                    erNevnt = true;
                } else if (duplicate.get(i).getNavn().equals(duplicate.get(j).getNavn()) && erNevnt == true) {
                    duplicateIndex.add(j);
                }
            }
        }
        for (int i = 0; i < duplicate.size(); i++) {
            if (!duplicateIndex.contains(i)) {
                this.retter.add(duplicate.get(i));                             
            }
        }
    }

    /**
     * Metode for å registrere ny rett i registeret
     * @param nyRett ny rett i registeret
     */
    public void registrerNyRett(Rett nyRett) {
        retter.add(nyRett);
    }

    /**
     * Metode for å returnere rett etter gitt navn
     * @param navn navn på rett
     * @return toString() av rett
     */
    public String getRett(String navn) {
        String rett = "\n";
        for (Rett i : retter) {
            if (i.getNavn().equals(navn)) {
                rett += i.toString() + "\n";
            }
        }
        return rett;
    }

    /**
     * Metode for å returnere alle retter i registeret
     * @return retter i registeret
     */
    public ArrayList<Rett> getRetter() {
        return retter;
    }

    /**
     * Metode for å returnere alle retter av gitt type
     * @param type type av retter
     * @return retter av gitt type
     */
    public String getRettAvGittType(String type) {
        String rett = "\n" + type + ": \n";
        for (Rett i : retter) {
            if (i.getType().toLowerCase().equals(type.toLowerCase())) {
                rett += "   " + i + "\n";
            }
        }
        return rett;
    }

    /**
     * Metode for å registrere en ny meny
     * @param nyMeny ny meny
     */
    public void registrerNyMeny(Meny nyMeny) {
        menyer.add(nyMeny);
        
        ArrayList<Rett> duplicate = new ArrayList<Rett>();                      //Oppretter duplicate liste for å håndtere duplicates i retter
        ArrayList<Integer> duplicateIndex = new ArrayList<Integer>();           //Oppretter duplicateIndex liste for å lagre indekser i dublicate som må fjernes

        for (Meny i : menyer) {
            for (Rett j : i.getRetter()) {
                this.retter.add(j);
            }
        }
        for (Rett i : retter) {
            duplicate.add(i);
        }
        //
        for (int i = 0; i < duplicate.size(); i ++) {
            Boolean erNevnt = false;
            for (int j = 0; j < duplicate.size(); j ++) {
                if (duplicate.get(i).getNavn().equals(duplicate.get(j).getNavn()) && erNevnt == false) {
                    erNevnt = true;
                } else if (duplicate.get(i).getNavn().equals(duplicate.get(j).getNavn()) && erNevnt == true) {
                    duplicateIndex.add(j);
                }
            }
        }
        retter.removeAll(retter);
        for (int i = 0; i < duplicate.size(); i++) {
            if (!duplicateIndex.contains(i)) {
                this.retter.add(duplicate.get(i));                             
            }
        }
    }

    /**
     * Metode for å returnere alle menyer med totalpris innenfor et gitt intervall
     * @param startpris startpris i intervallet
     * @param sluttpris sluttprisen i intervallet
     * @return menyer innenfor gitt intervall
     */
    public String getTotalprisMenyerInnenforIntervall(double startpris, double sluttpris) {
        String returnString = "\nInnenfor intervallet [" + startpris + ", " + sluttpris + "]:" + "\n";
        for (Meny i : menyer) {
            double totalpris = 0;
            for (Rett j : i.getRetter()) {
                totalpris += j.getPris();
            }
            if (totalpris >= startpris && totalpris <= sluttpris) {
                returnString += "   Meny: " + i.getName() + ". Totalpris: " + totalpris + "\n"; 
            }
        }
        return returnString;
    }

    /**
     * Metode for å returnere alle retter i registeret
     * @return retter i registeret
     */
    public String toStringRetter() {
        String returnString = "\n";
        Collections.sort(retter, new Comparator<Rett>() {
            @Override
            public int compare(Rett o1, Rett o2) {
                return o1.getNavn().compareTo(o2.getNavn());
            }
        });
        for (Rett i : retter) 
            {returnString +=  i.toString() + "\n";}
        return returnString;
    }

    @Override
    public String toString() {
        String returnString = "";
        Collections.sort(menyer, new Comparator<Meny>() {
            @Override
            public int compare(Meny o1, Meny o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (Meny i : menyer) {
            returnString += i;
        }
        return returnString;
    }
}