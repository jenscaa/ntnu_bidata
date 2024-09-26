public class NyString {
    //Bruk Regex din løk
    private final String tekst;

    public NyString(String tekst) {
        this.tekst = tekst;
    }

    public String forkorting(String tekst) {
        String[] ord = tekst.split(" ");
        String returnString = "";
        for (String i : ord) {
            returnString += String.valueOf(i.charAt(0));
        }
        return returnString;
    }

    public String fjerningAvTegn(String tekst, char tegn) {
        String returnTekst = "";
        for (int i = 0; i < tekst.length(); i++) {
            if (!String.valueOf(tekst.charAt(i)).toLowerCase().equals(String.valueOf(tegn).toLowerCase())) {
                returnTekst += String.valueOf(tekst.charAt(i)); 
            }
        }
        return returnTekst;
    }
}