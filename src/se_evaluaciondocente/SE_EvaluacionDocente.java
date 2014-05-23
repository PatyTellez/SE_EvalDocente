
//Desarrollos Inteligentes

package se_evaluaciondocente;

import difusificar.Difusificar;
import java.io.IOException;

public class SE_EvaluacionDocente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
       // Escribir y leer FAMA y FAMC
        RW_FAM fbin = new RW_FAM();
        fbin.escribir_bin();
        //fbin.leer_bin();
        
        Difusificar d = new Difusificar();
        fbin.leer_bin();
        
        
    }
    
}
