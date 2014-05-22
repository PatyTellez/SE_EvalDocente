/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package difusificar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

/**
 *
 * @author azul
 */
public class Difusificar {
    
    void inicio()throws FileNotFoundException, IOException{
         //Archivo txt con promedios
        File texto = new File("entradasReales.txt");
        FileReader fileR = new FileReader(texto);
        BufferedReader bufferR = new BufferedReader(fileR);
        
        //Archivo binario de modelo difuso
        RandomAccessFile modeloDifuso;
        
        String aux = "",etiqueta;
        StringTokenizer tokens;
        double a,b,c,d;

        while ((aux = bufferR.readLine()) != null) {
            System.out.println(aux);
            tokens = new StringTokenizer(aux, " ");

            //abre el modelo difuso conrrespondiente a la categoria
            modeloDifuso =  new RandomAccessFile("./src/archivos/bin/"+tokens.nextToken()+"modelodifuso.bin", "w");
           while (modeloDifuso.getFilePointer() != modeloDifuso.length()){
            etiqueta = modeloDifuso.readUTF();
            a=modeloDifuso.readDouble();
            b=modeloDifuso.readDouble();
            c=modeloDifuso.readDouble();
            d=modeloDifuso.readDouble();
            
            actualizarFAMA(etiqueta,Trapezoidal(a,b,c,d,Double.parseDouble(tokens.nextToken())));

           }
        }
        
    }
    
    void actualizarFAMA(String etiqueta,double valor) throws FileNotFoundException, IOException{
    RandomAccessFile archi=new RandomAccessFile("./src/archivos/bin/FAMA","rw");
    String etiqueta_buscada;
    long tam_reg;
    char aux2;
     
    while(archi.getFilePointer()!=archi.length()){
        archi.readInt();
        etiqueta_buscada = archi.readUTF();        
        if(etiqueta_buscada.equals(etiqueta)){
             
        }
    
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
