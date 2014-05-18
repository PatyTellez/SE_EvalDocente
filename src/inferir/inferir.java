
package inferir;

import java.io.*;
import java.util.StringTokenizer;

public class inferir {
    
    //private static final Archivos arc = new Archivos();
    private static String ConjuntosDif[];
    
    public void Min_gm() throws FileNotFoundException, IOException {
        int llave = 0;
        String etiqueta = "";

        RandomAccessFile leer_fama = new RandomAccessFile("src/archivos/bin/FAMA", "r");
        RandomAccessFile escribir_famc = new RandomAccessFile("src/archivos/bin/FAMC", "rw");

        while ((leer_fama.getFilePointer()) != (leer_fama.length())) {
            double gm = 1.0,temp_gm=0.0;
            for (int c = 0; c < 4; c++) {
                llave = leer_fama.readInt();
                etiqueta = leer_fama.readUTF()+ "";
                temp_gm = leer_fama.readDouble();
                
                if (temp_gm<gm) {
                    gm=temp_gm;
                }
                //System.out.print(llave + " " + etiqueta + " " + temp_gm + " ");
            }
            etiqueta = escribir_famc.readUTF()+ "";
            escribir_famc.writeDouble(gm);
            //System.out.println(etiqueta + " " + gm);

        }

        leer_fama.close();
        escribir_famc.close();

    }
    
public void Max_gm() throws FileNotFoundException, IOException{
        String etiqueta = "";

        RandomAccessFile leer_famc = new RandomAccessFile("src/archivos/bin/FAMC", "r");
        RandomAccessFile escribir_entrada_desdi = new RandomAccessFile("src/archivos/bin/entrada_desdifusificar.bin", "rw");
        
        for (int i = 0; i < ConjuntosDif.length; i++) {

            double gm=0.0,temp_gm=0.0;
            
        while ((leer_famc.getFilePointer()) != (leer_famc.length())) {
            etiqueta=leer_famc.readUTF();
            temp_gm=leer_famc.readDouble();
            
            if (ConjuntosDif[i].equals(etiqueta) && temp_gm>gm) {
                gm=temp_gm;
            }
        }
        
        escribir_entrada_desdi.writeUTF(ConjuntosDif[i]);
        escribir_entrada_desdi.writeDouble(gm);//Escribe el grado de membresia maximo en entrada_desdifusificacion.bin
        
        //System.out.println(ConjuntosDif[i]+" : "+gm);
        leer_famc.seek(0);
      }

        escribir_entrada_desdi.close();
        leer_famc.close();
    }

public void DeterminarCD_C() throws FileNotFoundException, IOException{//Metodo para determinar el numero de conjuntos difusos (etiquetas) en el archivo modelo_c.binarito
   
    String etiquetas="";
    
    RandomAccessFile leer_modeloc = new RandomAccessFile("src/archivos/bin/entrada_desdifusificar.bin", "r");
    
    while ((leer_modeloc.getFilePointer()) != (leer_modeloc.length())) {
            etiquetas+=leer_modeloc.readUTF()+",";
                leer_modeloc.readDouble();
            

        }

    StringTokenizer tokens = new StringTokenizer(etiquetas, ",");
    ConjuntosDif=new String[tokens.countTokens()];
    
    System.out.println("Etiquetas Modelo Difuso Salida: ");
    
    for (int i = 0; i < ConjuntosDif.length; i++) {
        ConjuntosDif[i]=tokens.nextToken();
        System.out.print(ConjuntosDif[i]+"   ");
    }
    
}

public void Leer_Entrada_desdi() throws FileNotFoundException, IOException{
    
    RandomAccessFile leer_entrada_desdi = new RandomAccessFile("src/archivos/bin/entrada_desdifusificar.bin", "r");
            
    System.out.println("LECTURA ARCHIVO Entrada_desdifusificar.bin : ");
    
        while ((leer_entrada_desdi.getFilePointer()) != (leer_entrada_desdi.length())) {
            String etiqueta=leer_entrada_desdi.readUTF();
            double gm=leer_entrada_desdi.readDouble();
            
            System.out.println("Etiqueta: "+etiqueta+" , Grado Membresia Max:"+gm);
        }
        
}
  
    public static void main(String[] args) throws IOException {
        
        inferir i = new inferir();  
        i.Min_gm();//Elige el Minimo Grado de Membresia de los indicadores que conforma una regla en la FAM
                  //Y ese minimo grado se reescribe en el grado de membresia del consecuente FAMC
        i.DeterminarCD_C();//Determina el numero de conjuntos difusos y la etiqueta de ellos
        i.Max_gm();//Obtiene el grado de membresia maximo de los conjuntos difusos consecuentes FAMC y reescribe el archivo modelo_c.binarito con este grado a cada etiqueta correspondiente
      
        Archivos a =new Archivos();
        a.leer();
        //a.Escribir_modeloc();
        //a.Leer_modeloc();
        
        i.Leer_Entrada_desdi();
      
    }
    
}
