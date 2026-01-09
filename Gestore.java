import java.io.*;
import java.util.*;

public class Gestore {
    private String file = "ouldib.csv";
    private String[] intestazione;
    private List<Record> records = new ArrayList<>();

    
    public void leggi() throws IOException {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            intestazione = br.readLine().split(",");
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",");
                boolean cancellato = Boolean.parseBoolean(campi[campi.length - 1]);
                records.add(new Record(
                        Arrays.copyOf(campi, campi.length - 1),
                        cancellato
                ));
            }
        }
    }

   
    public void salva() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println(String.join(",", intestazione));
            for (Record r : records) {
                pw.println(r.toCSV());
            }
        }
    }




    public void aggiungiCampiExtra() {
        Random rnd = new Random();
        intestazione = Arrays.copyOf(intestazione, intestazione.length + 2);
        intestazione[intestazione.length - 2] = "miovalore";
        intestazione[intestazione.length - 1] = "cancellato";

        for (Record r : records) {
            String[] nuovi = Arrays.copyOf(r.getCampi(), r.numeroCampi() + 1);
            nuovi[nuovi.length - 1] = String.valueOf(10 + rnd.nextInt(11));
            r = new Record(nuovi, false);
        }
    }


    public int contaCampi() {
        return intestazione.length;
    }


    public void lunghezzeMassime() {
        int maxRecord = 0;
        int[] maxCampi = new int[intestazione.length - 1];

        for (Record r : records) {
            maxRecord = Math.max(maxRecord, r.lunghezzaRecord());
            for (int i = 0; i < r.numeroCampi(); i++) {
                maxCampi[i] = Math.max(maxCampi[i], r.getCampo(i).length());
            }
        }

        System.out.println("Lunghezza max record: " + maxRecord);
        for (int i = 0; i < maxCampi.length; i++) {
            System.out.println("Campo " + intestazione[i] + ": " + maxCampi[i]);
        }
    }


    public void normalizzaCampi() {
        int[] max = new int[intestazione.length - 1];

        for (Record r : records) {
            for (int i = 0; i < r.numeroCampi(); i++) {
                max[i] = Math.max(max[i], r.getCampo(i).length());
            }
        }

        for (Record r : records) {
            for (int i = 0; i < r.numeroCampi(); i++) {
                r.setCampo(i, String.format("%-" + max[i] + "s", r.getCampo(i)));
            }
        }
    }


    public void aggiungiRecord(String[] campi) {
        records.add(new Record(campi, false));
    }

    // 6️⃣ Visualizza 3 campi
    public void visualizza(int a, int b, int c) {
        for (Record r : records) {
            if (!r.isCancellato()) {
                System.out.println(
                        r.getCampo(a) + " | " +
                        r.getCampo(b) + " | " +
                        r.getCampo(c)
                );
            }
        }
    }


    public Record cerca(int index, String valore) {
        for (Record r : records) {
            if (!r.isCancellato() && r.getCampo(index).equals(valore)) {
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
    }
}
