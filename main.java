import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeggiCSV {
    public static void main(String[] args) {
        String nomeFile = "C:\\Users\\ouldib.21029\\Downloads\\Lottery_Mega_Millions_Winning_Numbers__Beginning_2002 (1).csv\";
        String linea;
        String separatore = ",";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(nomeFile));
            while ((linea = br.readLine()) != null) {

                if (linea.startsWith("nome_colonna1,nome_colonna2")) { // Esempio di salto intestazione
                    continue;
                }

                String[] campi = linea.split(separatore);

                System.out.println("Campo 1: " + campi[0] + ", Campo 2: " + campi[1]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
