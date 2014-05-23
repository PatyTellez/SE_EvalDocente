/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelosdifusos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Modelosdifusos {

 
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        mod_dif md =new mod_dif();
//        md.difusos_bin("salida");
//        md.generar_indice();
//        md.leer_indice();
//        md.modelos_difusos();
        
      Motor m= new Motor();
      m.difusificar( m.lee_entradas_reales("entradasReales.txt"));
       
    }
    
    
  
}
