public class Main {
    public static void main(String[] args) throws Exception {

        Gestore g = new Gestore();

        g.leggi();

        System.out.println("Numero campi: " + g.contaCampi());

        g.lunghezzeMassime();

        g.normalizzaCampi();

        g.visualizza(0, 1, 2);

        Record r = g.cerca(0, "VALORE_CHIAVE");
        if (r != null) {
            g.modifica(r, 1, "MODIFICATO");
            g.cancella(r);
        }

        g.salva();
        System.out.println("File aggiornato correttamente.");
    }
}
