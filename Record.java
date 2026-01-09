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

    public int lunghezzaRecord() {
        int tot = 0;
        for (String c : campi) tot += c.length();
        return tot;
    }

    public boolean isCancellato() {
        return cancellato;
    }

    public void cancella() {
        cancellato = true;
    }

    public String[] getCampi() {
        return campi;
    }

    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < campi.length; i++) {
            sb.append(campi[i]);
            if (i < campi.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }



}