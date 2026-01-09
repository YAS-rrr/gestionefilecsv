public class Record {
    private String drawDate;
    private String winningNumbers;
    private String megaBall;
    private String multiplier;
    private int miovalore;
    private boolean cancellatoLogicamente;
    
    public Record(String drawDate, String winningNumbers, String megaBall, String multiplier) {
        this.drawDate = drawDate;
        this.winningNumbers = winningNumbers;
        this.megaBall = megaBall;
        this.multiplier = multiplier;
        this.miovalore = generaMiovalore();
        this.cancellatoLogicamente = false;
    }
    
    private int generaMiovalore() {
        return 10 + (int)(Math.random() * 11); // 10-20
    }
    
    
    public String getDrawDate()  { 
        return drawDate; 
    }

    public String getWinningNumbers() { 
        return winningNumbers; 
        }

    public String getMegaBall() { 
        return megaBall; 
        }

    public String getMultiplier() { 
        return multiplier; 
        }

    public int getMiovalore() { 
        return miovalore; 
        }

    public boolean isCancellatoLogicamente() { 
        return cancellatoLogicamente; 
        }
    
    public void setWinningNumbers(String winningNumbers) { 
        this.winningNumbers = winningNumbers;
         }

    public void setMegaBall(String megaBall) { 
        this.megaBall = megaBall;
         }

    public void setMultiplier(String multiplier) { 
        this.multiplier = multiplier;
   }

    public void setMiovalore(int miovalore) { 
        this.miovalore = miovalore; 
       
 }

    public void cancellaLogicamente() { 
        this.cancellatoLogicamente = true
        ; }
    
    public int contaCampi() {
        return 6; // 4 campi originali + miovalore + flag cancellazione
    }
    
    public String getRecordFormattato(int[] lunghezzeMaxCampi) {
        StringBuilder sb = new StringBuilder();
        String[] campi = {drawDate, winningNumbers, megaBall, multiplier};
        
        for (int i = 0; i < campi.length; i++) {
            sb.append(campi[i]);
            for (int j = campi[i].length(); j < lunghezzeMaxCampi[i]; j++) {
                sb.append(" ");
            }
            if (i < campi.length - 1) sb.append(",");
        }
        sb.append(",").append(miovalore).append(",");
        sb.append(cancellatoLogicamente ? "1" : "0");
        
        return sb.toString();
    }
    
    public void visualizzaTreCampi() {
        System.out.println("Draw Date: " + drawDate + 
                         ", Winning Numbers: " + winningNumbers + 
                         ", miovalore: " + miovalore);
    }
    
    @Override
    public String toString() {
        return drawDate + "," + winningNumbers + "," + megaBall + "," + 
               multiplier + "," + miovalore + "," + 
               (cancellatoLogicamente ? "1" : "0");
    }
}