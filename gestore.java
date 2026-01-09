// File: GestoreRecord.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class gestore {
    private Record[] records;
    private int numRecords;
    private int[] lunghezzeMaxCampi;
    
    public gestore(int maxRecords) {
        records = new Record[maxRecords];
        numRecords = 0;
        lunghezzeMaxCampi = new int[4]; 
    }
    
    public void caricaDaFile(String nomeFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeFile));
        String linea;
        
        while ((linea = br.readLine()) != null && numRecords < records.length) {
            if (linea.startsWith("Draw Date, Winning Numbers , Mega ball, Multiplier")) {
                continue;
            }
            
            String[] campi = linea.split(",");
            if (campi.length >= 4) {
                Record record = new Record(campi[0].trim(), campi[1].trim(), 
                                          campi[2].trim(), campi[3].trim());
                aggiungiRecord(record);
                
                // Aggiorna lunghezze massime
                for (int i = 0; i < 4; i++) {
                    if (campi[i].trim().length() > lunghezzeMaxCampi[i]) {
                        lunghezzeMaxCampi[i] = campi[i].trim().length();
                    }
                }
            }
        }
        br.close();
    }
    
    public void aggiungiRecord(Record record) {
        if (numRecords < records.length) {
            records[numRecords] = record;
            numRecords++;
        }
    }
    
    public Record cercaRecord(String drawDate) {
        for (int i = 0; i < numRecords; i++) {
            if (!records[i].isCancellatoLogicamente() && 
                records[i].getDrawDate().equals(drawDate)) {
                return records[i];
            }
        }
        return null;
    }
    
    public void modificaRecord(String drawDate, String nuovoWinningNumbers, 
                              String nuovoMegaBall, String nuovoMultiplier) {
        Record record = cercaRecord(drawDate);
        if (record != null) {
            record.setWinningNumbers(nuovoWinningNumbers);
            record.setMegaBall(nuovoMegaBall);
            record.setMultiplier(nuovoMultiplier);
            record.setMiovalore(10 + (int)(Math.random() * 11));
        }
    }
    
    public void cancellaLogicamente(String drawDate) {
        Record record = cercaRecord(drawDate);
        if (record != null) {
            record.cancellaLogicamente();
        }
    }
    
   
    
    public void visualizzaTreCampiPerTutti() {
        System.out.println("\n=== VISUALIZZAZIONE 3 CAMPI ===");
        for (int i = 0; i < numRecords; i++) {
            if (!records[i].isCancellatoLogicamente()) {
                records[i].visualizzaTreCampi();
            }
        }
    }
    
    public void stampaStatistiche() {
        System.out.println("\n=== STATISTICHE ===");
        System.out.println("Numero totale record: " + numRecords);
        System.out.println("Lunghezze massime campi:");
        for (int i = 0; i < 4; i++) {
            System.out.println("  Campo " + (i+1) + ": " + lunghezzeMaxCampi[i] + " caratteri");
        }
        
        int attivi = 0;
        for (int i = 0; i < numRecords; i++) {
            if (!records[i].isCancellatoLogicamente()) {
                attivi++;
            }
        }
        System.out.println("Record attivi: " + attivi);
        System.out.println("Record cancellati logicamente: " + (numRecords - attivi));
    }
    
    public Record[] getRecords() { return records; }
    public int getNumRecords() { return numRecords; }
}