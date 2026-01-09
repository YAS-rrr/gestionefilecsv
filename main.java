import java.io.IOException;

public class main {
    public static void main(String[] args) {
        try {
            
            gestore Gestore = new gestore(5000);
            
           
            Gestore.caricaDaFile("ouldib.csv");
            
              
            Record nuovoRecord = new Record("2024-01-15", "10 20 30 40 50", "15", "3X");
            Gestore.aggiungiRecord(nuovoRecord);
           
            
          
            Gestore.visualizzaTreCampiPerTutti();
            
          
            
            Record cercato = Gestore.cercaRecord("2024-01-15");
            if (cercato != null) {
                System.out.println("TROVATO: " + cercato);
            } else {
                System.out.println("Record non trovato!");
            }
            
            
            if (cercato != null) {
                System.out.println("\n=== MODIFICA RECORD ===");
                Gestore.modificaRecord("2024-01-15", "11 22 33 44 55", "25", "2X");
                System.out.println("Record modificato: " + gestore.cercaRecord("2024-01-15"));
            }
            
          
           
           // Gestore.cancellaLogicamente("2024-01-15");
            //System.out.println("Record cancellato logicamente");
            
           
           // Record dopoCancellazione = gestore.cercaRecord("2024-01-15");
          //  if (dopoCancellazione == null || dopoCancellazione.isCancellatoLogicamente()) {
           //     System.out.println("Conferma: record risulta cancellato");
           // }
            
           
            Gestore.stampaStatistiche();
            
            
         
                if (gestore.getNumRecords() > 0) {
                    System.out.println("\n=== CONTEggio CAMPI ===");
                    int campi = Gestore.getRecords()[0].contaCampi();
                    System.out.println("Il primo record ha " + campi + " campi");
                }
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}