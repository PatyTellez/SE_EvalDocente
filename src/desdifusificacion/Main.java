/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package desdifusificacion;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Alejandro Gómez Cruz
 */
public class Main 
{
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
         desdifus des = new desdifus ();  
       //des.archivo();                     //se crea el archivo del modelo difuso esto solo se hace una vez
      // des.archivo_modelo_difuso();
       des.acomodar_etiquetas();
       des.desdifuzificar();
    }
}
