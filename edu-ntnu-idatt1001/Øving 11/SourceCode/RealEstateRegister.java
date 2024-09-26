import java.util.ArrayList;

/**
 * RealEstateRegister er en klasse som representerer et register over
 * objekter av klassen RealEstate. Klassen ArrayList blir brukt siden jeg 
 * har mest erfaring med denne klassen, og jeg mener den er den beste løsningen. 
 * Klassen RealEstateRegister inneholder metodene for å registrere/slette 
 * RealEstate objekter, finne antall objekter i registeret, finne bestemt objekt,
 * finne gjennomsnittsareal og finne objekter av samme lotNumber/gårdsnummer. 
 * @author Jens Christian Aanestad
 */
public class RealEstateRegister {
    private ArrayList<RealEstate> properties;
    
    /**
     * Konstruktør som tar inn parameteren properties/eiendommer i form av en ArrayList<RealEstate>.
     *
     */
    public RealEstateRegister() {
        this.properties = new ArrayList<>();
    }

    /**
     * Metode for å registrere/legge til et nytt objekt av klassen RealEstate. Tar inn parameteren
     * newProperty/ny eiendom
     * @param newProperty Objekt av klassen RealEstate som skal legges inn
     */
    public void addProperty(int municipalityNumber, String municipalityName, int lotNumber, int sectionNumber, String name, double area, String nameOfOwner) {
        RealEstate newProperty = new RealEstate(municipalityNumber, municipalityName, lotNumber, sectionNumber, name, area, nameOfOwner);

        properties.add(newProperty);
    }

    /**
     * Metode for å slette/fjerne et gitt objekt av klassen RealEstate. Tar inn parameteren
     * deleteProperty/slett property. Antatt at det ikke finnes duplicates og at alle objektene 
     * har forskjellige kombinasjoner av ID: kommunenummer, gårdsnummer og bruksnummer. Hvis ikke 
     * vil metoden bli ufullstendig ettersom all annen informasjon blir ignorert. 
     * @param deleteProperty Objekt av klassen RealEstate som skal fjernes
     */
    public void deleteProperty(int municipalityNumber, int lotNumber, int sectionNumber) {
        int removeIndex = 0;
        boolean indexFound = false;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).realEstateID().equals(municipalityNumber + "-" + lotNumber + "/" +sectionNumber)) {
                removeIndex = i;
                indexFound = true;
            }
        }
        if (indexFound) {
            properties.remove(properties.get(removeIndex));
        }
    }


    /**
     * Metode for å finne antall objekter/eiendommer i registeret
     * @return Antall objekter i registeret
     */
    public int numberOfProperties() {
        return properties.size();
    }

    /**
     * Metode for å finne bestemt objekt/eiendom i registeret antatt at det ikke 
     * finnes duplicates. Tar inn parameterene municipalityNumber/Kommunenummer, 
     * lotNUmber/Gårdsnummer og sectionNumber/Bruksnummer. Returnerer datetypen
     * RealEstate.
     * @param municipalityNumber Kommunenummer
     * @param lotNumber Gårdsnummer
     * @param sectionNumber Bruksnummer
     * @return Objekt 
     */
    public RealEstate findProperty(int municipalityNumber, int lotNumber, int sectionNumber) {
        int realEstateIndex = 0;
        boolean realEstateFound = false;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).realEstateID().equals(municipalityNumber + "-" + lotNumber + "/" + sectionNumber)) {
                realEstateIndex = i;
                realEstateFound = true;
            }
        }
        if (realEstateFound) {
            int newMunicipalityNumber = properties.get(realEstateIndex).getMunicipalityNumber();
            String newMunicipalityName = properties.get(realEstateIndex).getMunicipalityName();
            int newLotNumber = properties.get(realEstateIndex).getLotNumber();
            int newSectionNumber = properties.get(realEstateIndex).getSectionNumber();
            String name = properties.get(realEstateIndex).getName();
            double newArea = properties.get(realEstateIndex).getArea();
            String newNameOfOwner = properties.get(realEstateIndex).getNameOfOwner();
            RealEstate newProperty = new RealEstate(newMunicipalityNumber, newMunicipalityName, newLotNumber, newSectionNumber, name, newArea, newNameOfOwner);
            return newProperty;
        } else {return null;}
    }

    /**
     * Metode for å finne gjennomsnittelig areal for alle objektene/eiendommene i registeret
     * @return Gjennomsnittelig areal
     */
    public double findAverageAreal() {
        double sumArea = 0;
        for (RealEstate i : properties) {
            sumArea += i.getArea(); 
        }
        return sumArea/properties.size();
    }

    /**
     * Metode for å finne alle objekter/eiendommer med samme lotNumber/gårdsnummer.
     * Returnerer datetypen String siden toString-metoden i RealEstate gir god oversikt.
     * @param lotNumber Gårsnummer
     * @return ArrayList med eiendommer
     */
    public ArrayList<RealEstate> propertiesByLotNumber(int lotNumber) {
        ArrayList<RealEstate> propertiesOfLotNumber = new ArrayList<RealEstate>();
        for (RealEstate i : properties) {
            if (i.getLotNumber() == lotNumber) {
                int newMunicipalityNumber = i.getMunicipalityNumber();
                String newMunicipalityName = i.getMunicipalityName();
                int newLotNumber = i.getLotNumber();
                int newSectionNumber = i.getSectionNumber();
                String name = i.getName();
                double newArea = i.getArea();
                String newNameOfOwner = i.getNameOfOwner();
                RealEstate newProperty = new RealEstate(newMunicipalityNumber, newMunicipalityName, newLotNumber, newSectionNumber, name, newArea, newNameOfOwner);
                propertiesOfLotNumber.add(newProperty);
            }
        }
        return propertiesOfLotNumber;
    }

    @Override
    public String toString() {
        String returnText = "\n";
        for (RealEstate i : properties) {
            returnText += i.toString() + "\n";
        }
        return returnText;
    }
}