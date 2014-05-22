//Acceso para escribir y leer sobre la FAM (Archivos FAMA y FAMC)

package se_evaluaciondocente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class RW_FAM {
    
        int num_antecedentes=0;
    void escribir_bin() throws FileNotFoundException, IOException {
        
        //Archivo txt con FAM completa  
        File texto = new File("./src/archivos/txt/FAM.txt");
        FileReader fileR = new FileReader(texto);
        BufferedReader bufferR = new BufferedReader(fileR);

        //Archivo binario de antecedentes 
        RandomAccessFile fama = new RandomAccessFile("./src/archivos/bin/FAMA", "rw");
        //Archivos binarios de consecuente
        RandomAccessFile famc = new RandomAccessFile("./src/archivos/bin/FAMC", "rw");

        String aux = "";
        StringTokenizer tokens;
        int llave = 0;//llave en antecedentes que indica el registro del consecuente

        while ((aux = bufferR.readLine()) != null) {
            System.out.println(aux);
            tokens = new StringTokenizer(aux, " ");
            num_antecedentes=tokens.countTokens()-1; //La ultima etiqueta es el consecuente

            for (int i = 0; i < num_antecedentes; i++) {
                //Escribe antecedentes
                fama.writeInt(llave);//campo llave
                fama.writeUTF(tokens.nextToken());//Etiqueta
                fama.writeDouble(Math.random()*1.0);//Valor obtenido en difusificacion
            }
            
            //Escribe consecuente
             famc.writeUTF(tokens.nextToken());//Etiqueta
             famc.writeDouble(0.0);//valor obtenido de la inferencia (min)
            
            llave++;

        }
        fileR.close();
        fama.close();
        famc.close();
    }
    
    void leer_bin() throws FileNotFoundException, IOException{
        RandomAccessFile fama = new RandomAccessFile("./src/archivos/bin/FAMA", "r");
        RandomAccessFile famc = new RandomAccessFile("./src/archivos/bin/FAMC", "r");
        
        while (fama.getFilePointer() != fama.length()) {
            for (int i = 0; i < num_antecedentes; i++) {
                System.out.print("  "+fama.readInt());
                System.out.print(" "+fama.readUTF());
                System.out.print(" "+fama.readDouble());
            }
            
            System.out.print(" -> "+famc.readUTF());
            System.out.println(" "+famc.readDouble());
        }
        
        fama.close();
        famc.close();
        
    }
}

