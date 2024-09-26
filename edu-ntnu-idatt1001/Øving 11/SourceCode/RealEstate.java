/**
 * RealEstate er en klasse som representerer en eiendom. Klassen har attributtene
 * municipalityNumber/kommunenummer, municipalityName/kommunenavn, lotNumber/gårdsnummer,
 * sectionNumber/bruksnummer, name/bruksnavn, area/areal i m^2 og nameOfOwner/navn på eier. Klassen
 * har bare getmetoder, med unntak av en setmetode for nameOfOwner siden en eiendom kan bli kjøpt
 * opp og få en ny eier.
 *
 * @author Jens Christian Aanestad
 */
public class RealEstate {

  private final int municipalityNumber;
  private final String municipalityName;
  private final int lotNumber;
  private final int sectionNumber;
  private final String name;
  private final double area;
  private String nameOfOwner;

  /**
   * Konstruktør som tar inn parameterene municipalityNumber/kommunenummer,
   * municipalityName/kommunenavn, lotNumber/gårdsnummer, sectionNumber/bruksnummer, name/bruksnavn,
   * area/areal i m^2 og nameOfOwner/navn på eier
   *
   * @param municipalityNumber Kommunenummer
   * @param municipalityName   Kommunenavn
   * @param lotNumber          Gårdsummer
   * @param sectionNumber      Bruksnummer
   * @param name               Bruksnavn
   * @param area               Areal i kvadratmeter
   * @param nameOfOwner        Navn på eier
   */

  //Bruk trim() og isBlank()
  public RealEstate(int municipalityNumber, String municipalityName, int lotNumber,
      int sectionNumber, String name, double area, String nameOfOwner) {
    if (municipalityNumber < 101 || municipalityNumber > 5054) {
      throw new IllegalArgumentException("Kommunenummer må melom det og det");
    }
    this.municipalityNumber = municipalityNumber;
    this.municipalityName = municipalityName;
    this.lotNumber = lotNumber;
    this.sectionNumber = sectionNumber;
    if (name == null) {
      this.name = "Har ikke bruksnavn";
    } else {
      this.name = name;
    }
    this.area = area;
    this.nameOfOwner = nameOfOwner;
  }

  /**
   * Getmetode for å returnere kommunenummer
   *
   * @return Kommunenummer
   */
  public int getMunicipalityNumber() {
    return municipalityNumber;
  }

  /**
   * Getmetode for å returnere kommunenavn
   *
   * @return Kommunenavn
   */
  public String getMunicipalityName() {
    return municipalityName;
  }

  /**
   * Getmetode for å returnere gårdsnummer
   *
   * @return Gårdsnummer
   */
  public int getLotNumber() {
    return lotNumber;
  }

  /**
   * Getmetode for å returnere bruksnummer
   *
   * @return Bruksnummer
   */
  public int getSectionNumber() {
    return sectionNumber;
  }

  /**
   * Getmetode for å returnere bruksnavn
   *
   * @return Bruksnavn
   */
  public String getName() {
    return name;
  }

  /**
   * Getmetode for å returnere areal i kvadratmeter
   *
   * @return Areal
   */
  public double getArea() {
    return area;
  }

  /**
   * Getmetode for å returnere eierens navn
   *
   * @return Navn på eier
   */
  public String getNameOfOwner() {
    return nameOfOwner;
  }

  /**
   * Setmetode for å mutere eierens navn
   *
   * @param newNameOfOwner Nytt navn på eier
   */
  public void setNameOfOwner(String newNameOfOwner) {
    nameOfOwner = newNameOfOwner;
  }

  /**
   * Metode for å generere og returnere en unik ID av eiendommen
   *
   * @return ID av eiendommen
   */
  public String realEstateID() {
    String realEstateID = municipalityNumber + "-" + lotNumber + "/" + sectionNumber;
    return realEstateID;
  }


  @Override
  public String toString() {
    return "Kommunenummer: " + municipalityNumber + ". Kommunenavn: " + municipalityName +
        ". Gårdsnummer: " + lotNumber + ". Bruksnummer: " + sectionNumber + ". EiendsomsID: "
        + realEstateID() +
        ". Bruksnavn: " + name + ". Areal: " + area + " m^2. Navn på eier: " + nameOfOwner;
  }
}