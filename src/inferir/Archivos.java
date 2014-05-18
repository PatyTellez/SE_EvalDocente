package inferir;

import java.io.*;
import java.util.StringTokenizer;

public class Archivos {    
    
    public static void leer() throws FileNotFoundException, IOException {
        int llave = 0;
        String etiqueta = "";
        double membresia = 0.0;

        RandomAccessFile leer_fama = new RandomAccessFile("src/archivos/bin/FAMA", "r");
        RandomAccessFile leer_famc = new RandomAccessFile("src/archivos/bin/FAMC", "r");
        
        System.out.println("\n LECTURA DE LA FAM (EN BINARIO): \n");

        while ((leer_fama.getFilePointer()) != (leer_fama.length())) {
            for (int c = 0; c < 4; c++) {
                llave = leer_fama.readInt();
                etiqueta = leer_fama.readUTF()+"";
                membresia = leer_fama.readDouble();
                System.out.print(llave + " " + etiqueta + " " + membresia + " ");
            }
            etiqueta = leer_famc.readUTF() + "";
            membresia = leer_famc.readDouble();
            System.out.println(etiqueta + " " + membresia);

        }

        leer_fama.close();
        leer_famc.close();

    }
    
    public static void Escribir_entrada_desdifusificar() throws FileNotFoundException, IOException{
        
        File texto = new File("src/archivos/txt/entrada_desdifusificar.txt");
        FileReader fileR = new FileReader(texto);
        BufferedReader bufferR = new BufferedReader(fileR);
        
        RandomAccessFile escribir_modeloc = new RandomAccessFile("src/archivos/bin/entrada_desdifusificar.bin", "rw");
        String datos="";
        while((datos=bufferR.readLine())!=null){
            StringTokenizer tokens=new StringTokenizer(datos, " ");
            escribir_modeloc.writeUTF(tokens.nextToken());
            escribir_modeloc.writeDouble(Double.parseDouble(tokens.nextToken()));
                 
        }
         
    }
    
    public static void Leer_entrada_desdifusificar() throws FileNotFoundException, IOException{
        
        RandomAccessFile leer_modeloc = new RandomAccessFile("src/archivos/bin/entrada_desdifusificar.bin", "r");
        String etiqueta="";
        
         System.out.println("\n LECTURA DEL archivo entrada_desdifusificar (EN BINARIO): \n");
        
        while ((leer_modeloc.getFilePointer()) != (leer_modeloc.length())) {
            etiqueta=leer_modeloc.readUTF();
            System.out.print(etiqueta+" ");
            System.out.print(leer_modeloc.readDouble()+" ");

            System.out.println("");
        }
         
    }

  /*  public static void main(String[] args) throws IOException {
        leer();
        Escribir_entrada_desdifusificar();
        Leer_entrada_desdifusificar();
    }*/
}