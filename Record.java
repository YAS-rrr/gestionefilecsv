import java.util.Arrays;

public class Record {
    private String[] campi;
    private boolean cancellato;

    public Record(String[] campi, boolean cancellato) {
        this.campi = campi;
        this.cancellato = cancellato;
    }

    public String getCampo(int i) {
        return campi[i];
    }

    public void setCampo(int i, String valore) {
        campi[i] = valore;
    }

    public int numeroCampi() {
        return campi.length;
    }

    public String[] getCampi() {
        return campi;
    }

    public boolean isCancellato() {
        return cancellato;
    }

    public void cancella() {
        cancellato = true;
    }

    public int lunghezzaRecord() {
        return Arrays.stream(campi).mapToInt(String::length).sum();
    }

    public String toCSV() {
        return String.join(",", campi) + "," + cancellato;
    }
}
