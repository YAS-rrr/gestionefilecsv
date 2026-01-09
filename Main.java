public class Main {
    public static void main(String[] args) throws Exception {
        Gestore g = new Gestore();

        g.leggi();
        g.aggiungiCampiExtra();
        System.out.println("Numero campi: " + g.contaCampi());

        g.lunghezzeMassime();
        g.normalizzaCampi();

        String[] nuovoRecord = {"12/31/2021", "01 02 03 04 05", "25", "03"};
        g.aggiungiRecord(nuovoRecord);

        g.visualizza(0, 1, 2);

        Record r = g.cerca(0, "12/31/2021");
        if (r != null) {
            g.modifica(r, 1, "99 99 99 99 99");
            g.cancella(r);
        }

        g.salva();
        System.out.println("File modificato e salvato.");



    }
}