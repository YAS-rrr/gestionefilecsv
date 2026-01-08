import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        String nomeFile = "C://Users//ouldib.21029//Downloads//ouldib.csv/";
        String linea;
        String separatore = ",";
        BufferedReader br = null;
        int numerocampi = 0;

        try {
            br = new BufferedReader(new FileReader(nomeFile));
            while ((linea = br.readLine()) != null) {

                if (linea.startsWith("Draw Date, Winning Numbers , Mega ball, Multiplier")) {
                    continue;
                }

                String[] campi = linea.split(separatore);

                System.out.println("Campo 1: " + campi[0] + ", Campo 2: " + campi[1] + ", Campo 3: " + campi[2] + ", Campo 4: " + campi[3]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {

                if (br != null)
                {
                    br.close();
                }
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
