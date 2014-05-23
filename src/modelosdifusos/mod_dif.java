/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelosdifusos;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;

/**
 *
 * @author Ana Laura
 */
public class mod_dif {
    void generar_indice() throws FileNotFoundException, IOException{
        //este método solo se ejecuta para agregar un nuevo indicador, si no es así, ejecutar el método modelos_difusos
    RandomAccessFile indbin=new RandomAccessFile("Indice.bin", "rw");
    RandomAccessFile indtxt=new RandomAccessFile("Indice.txt", "r");
    
   String cad=indtxt.readLine();
   System.out.println(cad);
   while (cad!=null){
       indbin.writeUTF(cad);
       if (cad!=null){
       RandomAccessFile md=new RandomAccessFile("src/archivos/txt/"+cad+"modelodifuso.txt", "rw");
       cad=indtxt.readLine();
       md.close();
//       System.out.println(cad);
       }
   }
   indbin.close();
   indtxt.close();
//      modelos_difusos();
    }
    void leer_indice() throws FileNotFoundException, IOException{
        RandomAccessFile indbin=new RandomAccessFile("Indice.bin", "r");
        try{
            boolean ban=true;
            while (ban){
                System.out.println(indbin.readUTF());
            }
            
        }catch (EOFException e){}
    }
    
    void modelos_difusos() throws FileNotFoundException, IOException
    {
        RandomAccessFile indbin=new RandomAccessFile("Indice.bin", "r");
        
        try{
            boolean ban=true;
            while(ban){
                String cad=indbin.readUTF();
                difusos_bin(cad);
            }
        
    }catch (EOFException e){}
    }

     void difusos_bin(String cad) throws FileNotFoundException, IOException {
          RandomAccessFile txt=new RandomAccessFile("src/archivos/txt/"+cad+"modelodifuso.txt","r");
          RandomAccessFile bin=new RandomAccessFile("src/archivos/bin/"+cad+"modelodifuso.bin","rw");
          String cadena=txt.readLine();
          
          try{
              boolean ban=true;
              while(ban){
                  if (cadena!=null)
                  {
//                  System.out.println("cadena "+cadena);
                  StringTokenizer st=new StringTokenizer(cadena);
                  while (st.hasMoreTokens()){
                      String a=st.nextToken();
                      double b=Double.parseDouble(st.nextToken());
                      double c=Double.parseDouble(st.nextToken());
                      double d=Double.parseDouble(st.nextToken());
                      double e=Double.parseDouble(st.nextToken());
                      bin.writeUTF(a);
                      bin.writeDouble(b);bin.writeDouble(c);
                      bin.writeDouble(d);bin.writeDouble(e);
                      System.out.println(a+" "+b+" "+c+" "+d+ " "+e);
                      
                  }
                  cadena=txt.readLine();
                  }
                  else
                  {
                      ban=false;
                  }
              }
              
          }catch (EOFException e){}
          
    }

   
}

