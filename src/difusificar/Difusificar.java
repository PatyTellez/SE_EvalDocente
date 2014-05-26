package difusificar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

public class Difusificar {

     public Difusificar() throws FileNotFoundException, IOException {
          this.inicio();
     }
     
     
    
    void inicio()throws FileNotFoundException, IOException{
         //Archivo txt con promedios
        File texto = new File("PromedioIndicadores.txt");
        FileReader fileR = new FileReader(texto);
        BufferedReader bufferR = new BufferedReader(fileR);
        
        //Archivo binario de modelo difuso
        RandomAccessFile modeloDifuso;
        
        String aux = "",etiqueta;
        StringTokenizer tokens;
        double a,b,c,d,variable_difusa,entrada;

        while ((aux = bufferR.readLine()) != null) {
            System.out.println(aux);
            tokens = new StringTokenizer(aux, " ");

            //abre el modelo difuso conrrespondiente a la categoria
           String ch=tokens.nextToken();
            System.out.println("ch "+ch);
            modeloDifuso =  new RandomAccessFile("./src/archivos/bin/"+ch+"modelodifuso.bin", "rw");
            entrada=Double.parseDouble(tokens.nextToken());//entrada real
           while (modeloDifuso.getFilePointer() != modeloDifuso.length()){
            etiqueta = modeloDifuso.readUTF();
            a=modeloDifuso.readDouble();
            b=modeloDifuso.readDouble();
            c=modeloDifuso.readDouble();
            d=modeloDifuso.readDouble();           
            variable_difusa=Trapezoidal(a,b,c,d,entrada);
            System.out.println("etiqueta "+ etiqueta+" variable difusa "+variable_difusa+"\n");
            actualizarFAMA(etiqueta,variable_difusa);
           }
        }
        
    }
    
    void actualizarFAMA(String etiqueta,double valor) throws FileNotFoundException, IOException{
    RandomAccessFile archi=new RandomAccessFile("./src/archivos/bin/FAMA","rw");
    String etiqueta_buscada;
        
    while(archi.getFilePointer()!=archi.length()){
        archi.readInt();;
        etiqueta_buscada = archi.readUTF();
        if(etiqueta.equals(etiqueta_buscada)){
             archi.writeDouble(valor);
        }else
            archi.readDouble();
    
    }
   }
    
    double Trapezoidal( double a, double b, double c,double d, double variable){
         System.out.println("a="+a+" b="+b+" c="+c+" d="+d);
        double resultado=0;
        if(variable<=a)
         resultado=0;
 
        
        if(variable>a&&variable<=b)
            resultado=(variable-a)/(b-a); 

        if(variable>b&&variable<=c)      
            resultado=1; 
        
        if(variable>c&&variable<=d)
            resultado=(d-variable)/(d-c); 
        
        if(variable>d)       
           resultado=0; 
 
            return resultado;
    }    
}