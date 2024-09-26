class KlientprogramV2 {

    public static void main(String[] args) {

        ArbTaker arbTaker = new ArbTaker("Matt", "Smitty", 2001, 666, 2019, 1000000, 30);
        System.out.println("\nSkjekker personalia før og etter setmetodene: \n" + arbTaker.getPersonalia());
        System.out.println(arbTaker.getPersonalia() + "\n \nSjekker arbTaker før og etter setmetodene:");

        System.out.println(arbTaker);

        arbTaker.setMånedslønn(900000);
        arbTaker.setSkatteprosent(20);
        System.out.println("\n" + arbTaker);

        System.out.println("\nSjekker metodene: \nMåneds skatteavdrag: " + arbTaker.skatteTrekkPerMåned());
        System.out.println("Bruttolønn per år: " + arbTaker.bruttolønnPerÅr());
        System.out.println("Års saktteavdrag: " + arbTaker.skatteTrekkPerÅr());
        System.out.println(arbTaker.navnPåSpesiellForm());
        System.out.println("Alder: " + arbTaker.alder());
        System.out.println("År ansatt i bedriften: " + arbTaker.årAnsattIBedriften());
        int årJobbet = 21;
        System.out.println("Jobbet mer enn "+ årJobbet + " år: " + arbTaker.ansattMerEnnGittAntallÅr(årJobbet));
    }
}