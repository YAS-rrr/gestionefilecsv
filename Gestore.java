import java.io.*;
import java.util.*;

public class Gestore {
    private String file = "ouldib.csv";
    private String[] intestazione;
    private Record[] records = new Record[0];
    private int size = 0;

    public void leggi() throws IOException {
        size = 0;
        records = new Record[0];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            intestazione = br.readLine().split(",");
            String riga;

            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",");
                aggiungiInterno(new Record(campi, false));
            }
        }
    }

    public void salva() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(String.join(",", intestazione));
            for (int i = 0; i < size; i++) {
                if (!records[i].isCancellato()) {
                    pw.println(records[i].toCSV());
                }
            }
        }
    }

    public void aggiungiCampiExtra() {
        Random rnd = new Random();

        String[] nuovaIntestazione = new String[intestazione.length + 2];
        System.arraycopy(intestazione, 0, nuovaIntestazione, 0, intestazione.length);
        nuovaIntestazione[intestazione.length] = "miovalore";
        nuovaIntestazione[intestazione.length + 1] = "cancellato";
        intestazione = nuovaIntestazione;

        for (int i = 0; i < size; i++) {
            Record vecchio = records[i];
            String[] vecchiCampi = vecchio.getCampi();
            String[] nuoviCampi = new String[vecchiCampi.length + 2];
            System.arraycopy(vecchiCampi, 0, nuoviCampi, 0, vecchiCampi.length);
            nuoviCampi[nuoviCampi.length - 2] = String.valueOf(10 + rnd.nextInt(11));
            nuoviCampi[nuoviCampi.length - 1] = "false";
            records[i] = new Record(nuoviCampi, false);
        }
    }

    public int contaCampi() {
        return intestazione.length;
    }

    public void lunghezzeMassime() {
        int maxRecord = 0;
        int[] maxCampi = new int[intestazione.length];

        for (int i = 0; i < size; i++) {
            Record r = records[i];
            maxRecord = Math.max(maxRecord, r.lunghezzaRecord());
            for (int j = 0; j < r.numeroCampi(); j++) {
                maxCampi[j] = Math.max(maxCampi[j], r.getCampo(j).length());
            }
        }

        System.out.println("Lunghezza max record: " + maxRecord);
        for (int i = 0; i < intestazione.length; i++) {
            System.out.println(intestazione[i] + ": " + maxCampi[i]);
        }
    }

    public void normalizzaCampi() {
        int[] max = new int[intestazione.length];

        for (int i = 0; i < size; i++) {
            Record r = records[i];
            for (int j = 0; j < r.numeroCampi(); j++) {
                max[j] = Math.max(max[j], r.getCampo(j).length());
            }
        }

        for (int i = 0; i < size; i++) {
            Record r = records[i];
            for (int j = 0; j < r.numeroCampi(); j++) {
                String campo = r.getCampo(j);
                String normalizzato = campo + " ".repeat(max[j] - campo.length());
                r.setCampo(j, normalizzato);
            }
        }
    }

    public void aggiungiRecord(String[] campi) {
        String[] campiEstesi = new String[campi.length + 2];
        System.arraycopy(campi, 0, campiEstesi, 0, campi.length);
        campiEstesi[campiEstesi.length - 2] = String.valueOf(10 + new Random().nextInt(11));
        campiEstesi[campiEstesi.length - 1] = "false";
        aggiungiInterno(new Record(campiEstesi, false));
    }

    public void visualizza(int a, int b, int c) {
        for (int i = 0; i < size; i++) {
            Record r = records[i];
            if (!r.isCancellato()) {
                System.out.println(r.getCampo(a) + " | " + r.getCampo(b) + " | " + r.getCampo(c));
            }
        }
    }

    public Record cerca(int index, String valore) {
        for (int i = 0; i < size; i++) {
            Record r = records[i];
            if (!r.isCancellato() && r.getCampo(index).trim().equals(valore.trim())) {
                return r;
            }
        }
        return null;
    }

    public void modifica(Record r, int campo, String nuovoValore) {
        r.setCampo(campo, nuovoValore);
    }

    public void cancella(Record r) {
        r.cancella();
        r.setCampo(r.numeroCampi() - 1, "true");
    }

    private void aggiungiInterno(Record r) {
        Record[] nuoviRecords = new Record[size + 1];
        System.arraycopy(records, 0, nuoviRecords, 0, size);
        nuoviRecords[size] = r;
        records = nuoviRecords;
        size++;
    }



}