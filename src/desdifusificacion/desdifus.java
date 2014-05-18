/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package desdifusificacion;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Alejandro Gómez Cruz
 */
public class desdifus 
{
     
    //ESTE ES EL METOD QUE REALIZA TODO LA DESDIFUZIFICACION Y PARA ELLO NECESITA 
    //EL MODELO DIFUSO, QUE SE ENCUENTRA EN EL ARCHIVO --->MatrizSistemaDifSal.bin
    //ADEMAS DE QUE NECESITARA LEER EL ARCHIVO QUE SALE DE LA INFERENCIA , ESTE ARCHIVO CONTIENE
    //LOS VALORES DIFUSOS DE CADA ETIQUETA , Y CON ESOS VALORES SE PRETENDE HACER LA DESDIFUZIFICACION
    //ESTE ARCHIVO ES ----->SalidaDifusa
    
    public void desdifuzificar () throws FileNotFoundException, IOException
    {
        //RandomAccessFile sal_dif=new RandomAccessFile ("Salidas_difusas.bin", "r");
        RandomAccessFile sal_dif=new RandomAccessFile ("src/archivos/bin/Salidas_difusas.bin", "r");
        RandomAccessFile art=new RandomAccessFile ("src/archivos/bin/Matriz_Modelo_Difuso.bin", "r");
        
        //FORMATO DEL ARCHIVO DE SALIDAS DIFUSAS--->SalidaDifusa
        
        //char 2 bytes
        //double 8 bytes       ------------char,duble.......char,double..............
        
        boolean ban=true;
        char Etiqueta;
        double grado, x1, x2, x3, x4;
        double y, y1, y2, xt, x, mui, mult, acum=0, acumy=0;
        try
        {
           String leer_Etiqueta="",aux="";
           System.out.println("-----------------DESDIFUZIFICANDO---------------------------");
           while (ban)
           {
               Etiqueta=sal_dif.readChar();         //-----leer la etiqueta
               aux+=" "+Etiqueta;
               grado=sal_dif.readDouble();     //-----leer valor de la etiqueta
               aux+=" "+grado;
               leer_Etiqueta+=art.readChar(); 
               
               System.out.println(aux);
               aux="";
               
               x1=art.readDouble();             
               x2=art.readDouble();              
               x3=art.readDouble();             
               x4=art.readDouble();
              
               leer_Etiqueta="";
               y=grado;
               y1=0;
               y2=1;
               xt=(((y-y1)/(y2-y1))*(x2-x1))+x1;
               x=x1;
               while (x<=xt)
               {
                    mui=funcion(x, x1, x2, y1, y2);
                     
                    mult=mui*x;
                    
                    x+=0.001;
                     
                    acum+=mult;
                   
                   
                    acumy+=mui;
               }
               xt=(((y-1)/(0-1))*(x4-x3))+x3;
               while(x<=xt){
                   mui=grado;
                   mult=mui*x;
                   x+=0.001;
                   acum+=mult;
                   
                   acumy+=mui;
               }
               xt=x4;
               while (x<=xt){
                   mui=funcion(x, x3, x4, 1, 0);
                   mult=mui*x;
                   x+=0.001;
                   acum+=mult;
                 
                   acumy+=mui;
               }

           }
       
       }catch (EOFException e){ }
        
       
        double centroide=acum/acumy;
        
        char Etiqueta_salida=Obtener_Etiqueta_Real_Sallida(centroide);
        if( Double.isNaN(centroide) )
        {
            System.out.println("VALOR REAL DE SALIDA = 0");
            System.out.println("ETIQUETA DE SALIDA = ");
            JOptionPane.showMessageDialog(null, "VALOR REAL DE SALIDA = 0\nNo Merece ser Nombrado Profesor" );
        }
        else
        {
            System.out.println("VALOR REAL DE SALIDA = "+centroide);
            System.out.println("ETIQUETA DE SALIDA = "+Etiqueta_salida);
            String Salida="";
            switch(Etiqueta_salida)
            {
                case 'P': Salida="Pesimo";
                    break;
                case 'M': Salida="Malo";
                    break;
                case 'R': Salida="Regular";
                    break;
                case 'B': Salida="Bueno";
                    break;
                case 'E': Salida="Excelente";
                    break;                
            }
            JOptionPane.showMessageDialog(null, "VALOR REAL DE SALIDA = "+centroide+"\nProfesor: "+Salida );
            
        }
    }
    
    
    
    private double funcion(double x, double x1, double x2, double y1, double y2) {
        double y=(((x-x1)/(x2-x1))*(y2-y1))+y1;
        return y;
    }
  // ----------------------------------------------------------------------------------------------------------------------
  // ----------------------------------------------------------------------------------------------------------------------
    
    //ESTE METODO LO UTILIZO UNA VEZ QUE YA TERMINA EL PROCESO DE DESDIFUZIFICACION, YA CUANDO
    //HE OBTENIDO UN VALOR REAL, Y LO QUE QUIERO ES SABER A QUE ETIQUETA CORRESPONDE ESE VALOR---->P,M,R,B,E
    
    private char Obtener_Etiqueta_Real_Sallida(double centroide) throws FileNotFoundException, IOException {
        char eti = 0; boolean ban=true;
        double x1, x2;
        RandomAccessFile ar=new RandomAccessFile ("src/archivos/bin/Matriz_Modelo_Difuso.bin", "r");
        try{
            while (ban)
            {
                eti=ar.readChar();
                x1=ar.readDouble();
                ar.readDouble();
                ar.readDouble();
                x2=ar.readDouble();
                if (centroide>=x1&&centroide<=x2){
                    ban=false;
                }
            }
        }
        catch (EOFException e){}
        return eti;
    }
    
    
    ///ESTE METODO ME AYUDA PARA ACOMODAR LAS ETIQUETAS EN EL ORDEN CORRECTO ANTES DE EMPEZAR LA DEZDIFUZIFICACION
 public void acomodar_etiquetas () throws FileNotFoundException, IOException
    {
        boolean ban=true;
        char Etiqueta;
        double grado;
        String matrix [] = new String [10];
        RandomAccessFile sal_dif=new RandomAccessFile ("src/archivos/bin/Salidas_difusas.bin", "rw");
        String formato="";
       
        try
        {
      System.out.println("-----------------ACOMODANDO---------------------------");
         while (ban)
           {
               Etiqueta=sal_dif.readChar();         //-----leer la etiqueta
               formato+=" "+Etiqueta;
               grado=sal_dif.readDouble();     //-----leer valor de la etiqueta
               formato+=" "+grado;
               System.out.println(formato);
               
               
           }
          
         
         }catch (EOFException e){ }
       
        try
        {
        StringTokenizer  tokens = new StringTokenizer(formato," ");
        int cont=0;
        String eti;
        while (ban)
        {
            eti=tokens.nextToken();
           
            if(eti.equals("P"))
            {
               matrix[0]=eti;
               eti=tokens.nextToken();
               matrix[1]=eti;
             
            }
            if(eti.equals("M"))
            {
                matrix[2]=eti;
               eti=tokens.nextToken();
               matrix[3]=eti;
            }
            if(eti.equals("R"))
            {
                matrix[4]=eti;
               eti=tokens.nextToken();
               matrix[5]=eti;
            }
            if(eti.equals("B"))
            {
                matrix[6]=eti;
               eti=tokens.nextToken();
               matrix[7]=eti;
            }
            if(eti.equals("E"))
            {
               matrix[8]=eti;
               eti=tokens.nextToken();
               matrix[9]=eti;
            }
        }
        
        }catch (Exception e){
            
        }
        sal_dif.seek(0);
        for(int i=0;i<10;i++)
        {
        
        Etiqueta = matrix[i].charAt(0);
        grado=Double.parseDouble(matrix[i+1]);
        sal_dif.writeChar(Etiqueta);
        sal_dif.writeDouble(grado);
        i++;
        System.out.println( "Etiqueta "+Etiqueta+" valor "+grado );
        }
    }
    
 
 
 /*LOS METODOS SIGUIENTES SOLO SE USARON UNA SOLA VEZ PARA PODER IR PROBANDO EL SISTEMA POR PARTES
ESTO ES QUE GENERABA LOS ARCHIVOS CON LOS QUE LA DESDIFUSIFICACION NECESITA, ESTO CON EL FIN DE ESTAR PROBANDO ESTA PARTE 
DEL PROGRAMA*/
   
   //ESTE METODO SOLO ME AYUDO A GENERAR EL ARCHIVO QUE CONTIENE LOS VALORES DIFUSOS DE CADA ETIQUETA
   //ESTO CON EL FIN DE PODER PROBAR LA DESDIFUZIFICACION 
   //
    public void archivo () throws IOException
    {
        char etiquetas []={'P','M','R','B','E'};
        double valsalida []={1.0,0.0,0.0,0.0,0.0};
        char a;
        double b;
        //double = 8 bytes
        //char = 4 bytes
        
        int TamRegistro=10;
        
        RandomAccessFile WriteInd;
        File Ind = null;
        
        Ind = new File ("src/archivos/bin/Salidas_difusas.bin");
        WriteInd = new RandomAccessFile(Ind, "rw");
    
      for (int contador=0;contador<5;contador++)
      {
          
                  
                    WriteInd.seek(TamRegistro*contador);
                    
                    
                    WriteInd.writeChar(etiquetas[contador]);
                   
                    WriteInd.seek((TamRegistro*contador)+2); 
                  
                    WriteInd.writeDouble(valsalida[contador]);
                   
                   
      }
       WriteInd.close(); 
    }
    //ESTE METODO SOLO LO UTILICE PARA GENERAR EL MODELO DIFUSO
    //ESTO PARA FINES DE PRUEBA DEL SISTEMA
    public void archivo_modelo_difuso () throws IOException
    {
        char etiquetas []={'P','M','R','B','E'};
        double p1 []={0.0,   20.0,  55.0,  77.5,93.2};
        double p2 []={0.1,   30.0,  70.0,  85.0,95.0};
        double p3 []={15.0,  50.0,  70.0,  90.0,100.0};
        double p4 []={25.0,  60.0,  85.0,  97.0,100.0};
       
        char a;
        double b;
        //double = 8 bytes
        //char = 4 bytes
        
        int TamRegistro=34;
        
        RandomAccessFile WriteInd;
        File Ind = null;
        
        Ind = new File ("Matriz_Modelo_Difuso.bin");
        WriteInd = new RandomAccessFile(Ind, "rw");
    
      for (int contador=0;contador<5;contador++)
      {
          
                     System.out.println(contador*TamRegistro);
                    WriteInd.seek(TamRegistro*contador);
                    a=etiquetas[contador];
                    WriteInd.writeChar(a);
                   
                
                    
                    WriteInd.seek((TamRegistro*contador)+2); 
                    WriteInd.writeDouble(p1[contador]);
                   
                    WriteInd.seek((TamRegistro*contador)+10); 
                    WriteInd.writeDouble(p2[contador]);
                    
                    WriteInd.seek((TamRegistro*contador)+18); 
                    WriteInd.writeDouble(p3[contador]);
                    
                    WriteInd.seek((TamRegistro*contador)+26); 
                    WriteInd.writeDouble(p4[contador]);
                    
                    
                   
      }
       WriteInd.close(); 
    }
}