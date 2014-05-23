package inferir;

import java.io.*;
import java.util.StringTokenizer;

public class Archivos {
    
    int conta = 0, reg_difuso = 0;
    
    public void get_tamaño_reg() throws FileNotFoundException, IOException{
        
        RandomAccessFile fama = new RandomAccessFile("src/archivos/bin/FAMA", "r");
        
        fama.readInt();//LLave
        fama.readUTF();//etiqueta
        fama.readDouble();//grado
        
        reg_difuso=(int)fama.getFilePointer();
        
        fama.close();
    }       

    public void escribir_A() throws FileNotFoundException, IOException {
        conta = 0;
        int indicador = 0;
        String etiqueta = "", aux = "";
        boolean ban = true;
        
        this.get_tamaño_reg();

        RandomAccessFile fama = new RandomAccessFile("src/archivos/bin/FAMA", "rw");
        RandomAccessFile entrada_difusa = new RandomAccessFile("src/archivos/bin/entrada_desdifusificar.bin", "r");

        while (fama.getFilePointer() < fama.length()) {
            ban = true;
            fama.readInt();
            
            etiqueta = fama.readUTF();

            entrada_difusa.seek((indicador-1) * reg_difuso);

            while (ban && entrada_difusa.getFilePointer() != (reg_difuso * indicador)) {
                aux = entrada_difusa.readUTF();
                
                if (aux.equals(etiqueta)) {
                    fama.writeDouble(entrada_difusa.readDouble());
                    ban = false;                    
                } else {                    
                    entrada_difusa.readDouble();
                }
            }           
        }

        fama.close();
        entrada_difusa.close();
    }
    
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
    
    public  void Escribir_entrada_desdifusificar() throws FileNotFoundException, IOException{
        
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