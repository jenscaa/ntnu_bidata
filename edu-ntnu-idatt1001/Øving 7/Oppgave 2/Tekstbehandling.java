public class Tekstbehandling {

    public String tekst;

    public Tekstbehandling(String tekst) {
        this.tekst = tekst;
    }

    static double rundAvTilToDesimaler(double desimaltall){
        double toDesimaler = desimaltall * 100;
        toDesimaler = Math.round(toDesimaler);
        return toDesimaler/100;
    }

    public void feilmelding() {
        System.out.println("Det du trykket ble ikke aksepter");
    }

    public int finneAntallOrd(String tekst) {
        String[] ord = tekst.split(" ");
        return ord.length;
    }

    public double gjennomsnitteligOrdlengde(String tekst) {
        double sum = 0;
        String[] ord = tekst.split(" ");
        for (int i = 0; i < ord.length; i++) {
            sum += ord[i].length();
        }
        return rundAvTilToDesimaler(sum / ord.length);
    }

    public double gjennomsnitteligAntallOrdPerPeriode(String tekst) {
        double divident = finneAntallOrd(tekst);
        String[] periode = tekst.split("[,?.!:;]+");
        double divisor = periode.length;
        return rundAvTilToDesimaler( divident / divisor);
    }

    public void ordSubstitusjon(String tekst, String søkeOrd, String erstatt) {
        String returntekst = "";
        String[] ord = tekst.split(" ");
        for (String i : ord) {
            if (i.equals(søkeOrd)) {
                i = erstatt; 
            }
            returntekst += i + " ";
        }       
        System.out.println(returntekst);
    }

    public String getOrginialTekst() {
        return tekst;
    }

    public String tekstIStoreBokstaver() {
        return tekst.toUpperCase();
    }
}