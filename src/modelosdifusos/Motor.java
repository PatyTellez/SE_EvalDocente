
package modelosdifusos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;


public class Motor {
    public void leer_archivo() throws FileNotFoundException, IOException
    {
        String acum="";
        RandomAccessFile var=new RandomAccessFile("Indice.bin", "r");
        try{
            boolean ban=true;
            while (ban){
                acum+=var.readChar();
                acum+=var.readChar();
                double val=var.readDouble();
                System.out.println(acum+ " "+val);
                acum="";
            }
            
        }catch(EOFException e){}
        
    }
    public void difusificar() throws FileNotFoundException, IOException{
        File f = new File("Indice.txt");
        BufferedReader entrada;
        //RandomAccessFile indi=new RandomAccessFile("Indice.txt", "rws");
        String indicador="";
        
        try
        {
            entrada = new BufferedReader(new FileReader(f));
            while (entrada.ready()) {
                indicador = entrada.readLine();
                System.out.println(indicador);
                GradoMembresia(indicador);
                indicador="";
            }
        }catch (EOFException e){}
    }
    
    //Difusificar Leyendo un ArrayList de Valores Reales
    public void difusificar(ArrayList<ValorReal> entradas_reales) throws FileNotFoundException, IOException{
        File f = new File("Indice.txt");
        BufferedReader entrada;
        //RandomAccessFile indi=new RandomAccessFile("Indice.txt", "rws");
        String indicador="";
        
        ValorReal tmp;
        int i = 0;
        
        try
        {
            entrada = new BufferedReader(new FileReader(f));
            while (entrada.ready()) {
                tmp = entradas_reales.get(i);
                i++;
                indicador = entrada.readLine();
                System.out.println(indicador);
                GradoMembresia(indicador,tmp.getCalificacion());
                indicador="";
            }
        }catch (EOFException e){}
    }
    
    
    public void GradoMembresia(String indicador) throws FileNotFoundException, IOException {
        
        double inicio, fin, grados, m1, m2, x;
        try (RandomAccessFile modelos = new RandomAccessFile(indicador + "modelodifuso.bin", "r")) {
            RandomAccessFile variabled = new RandomAccessFile(indicador + "var_dif.bin", "rw");
            //Como Obtener solo los valores x1 y xt
            try {
                boolean bandera = true;
               x = Double.parseDouble(JOptionPane.showInputDialog("Dame valor de x para "+indicador));
                while (bandera) {
                    String eti =modelos.readUTF();
//                    eti+=modelos.readChar();
//                    eti+=modelos.readChar();
                    System.out.println("eti "+eti);
                    
                   inicio = modelos.readDouble();
                    m1 = modelos.readDouble();
                    m2 = modelos.readDouble();
                    fin = modelos.readDouble();
                    System.out.println("Eti"+eti+" inicio "+inicio+" m1 "+m1+" m2 "+m2+" fin "+fin);
                   comparar(inicio, m1, m2, fin, x, eti, indicador, variabled);
                }
              
            } catch (EOFException e) {
            }
            variabled.close(); 
                
        }
        
//        Desdifusificacion();
    }

    
    //Metodo modificado para recibir la calificación de parámetro
    public void GradoMembresia(String indicador, double calificacion) throws FileNotFoundException, IOException {
        
        double inicio, fin, grados, m1, m2;
        try (RandomAccessFile modelos = new RandomAccessFile(indicador + "modelodifuso.bin", "r")) {
            RandomAccessFile variabled = new RandomAccessFile(indicador + "var_dif.bin", "rw");
            //Como Obtener solo los valores x1 y xt
            try {
                boolean bandera = true;
               
                while (bandera) {
                    String eti =modelos.readUTF();
//                    eti+=modelos.readChar();
//                    eti+=modelos.readChar();
                    System.out.println("eti "+eti);
                    
                   inicio = modelos.readDouble();
                    m1 = modelos.readDouble();
                    m2 = modelos.readDouble();
                    fin = modelos.readDouble();
                    System.out.println("Eti"+eti+" inicio "+inicio+" m1 "+m1+" m2 "+m2+" fin "+fin);
                   comparar(inicio, m1, m2, fin, calificacion, eti, indicador, variabled);
                }
              
            } catch (EOFException e) {
            }
            variabled.close(); 
                
        }
        
//        Desdifusificacion();
    }
    
    
    
    public void comparar(double inicio, double m1, double m2, double fin, double x, String eti, String indicador, RandomAccessFile variabled) throws FileNotFoundException, IOException {
        double grados;
        double y2, y1, x1, x2;
        if (x >= inicio && x <= fin) {
            if (x >= inicio && x <= m1) {
                x1 = inicio;
                x2 = m1;
                y2 = 1;
                y1 = 0;
                grados = (((x - x1) / (x2 - x1)) * (y2 - y1)) + y1;
                System.out.println("Grados "+grados);
                variabled.writeChars(eti);
                variabled.writeDouble(grados);
            } else if (x >= m1 && x <= m2) {
                x1 = m1;
                x2 = m2;
                y2 = 1;
                y1 = 1;
                grados = (((x - x1) / (x2 - x1)) * (y2 - y1)) + y1;
                System.out.println("Grados "+grados);
                variabled.writeChars(eti);
                variabled.writeDouble(grados);
            } else if (x >= m2 && x <= fin) {
                x1 = m2;
                x2 = fin;
                y2 = 1;
                y1 = 0;
                grados = (((x - x1) / (x2 - x1)) * (y2 - y1)) + y1;
                System.out.println("Grados "+grados);
                variabled.writeChars(eti);
                variabled.writeDouble(grados);
            }
        } else {
            System.out.println("Grados es 0.0");
            grados=0.0;
            variabled.writeChars(eti);
            variabled.writeDouble(grados);
            //Grado de membresia es 0 directo
        }
    }
    
    
      //Devuelve ArrayList con las Entradas Reales leídas de un archivo de texto
      //con el siguiente formato:
    /*
     *  Pregunta Calificación
     *  Pregunta Calificación
     *  ...
     */
    public ArrayList<ValorReal> lee_entradas_reales(String archivo)
    {
        BufferedReader br = null;
        ArrayList<ValorReal> entradas = new ArrayList<ValorReal>();
		try {
 
			String sLinea;
                        System.out.println("Abriendo archivo "+archivo+" ...");
			br = new BufferedReader(new FileReader(archivo));
                        System.out.println("Archivo abierto! Iniciando proceso de lectura");
                        
                        StringTokenizer st;
			while ((sLinea = br.readLine()) != null) {
                            st = new StringTokenizer(sLinea);
                            int numero_pregunta = Integer.parseInt(st.nextToken());
                            double calificacion = Double.parseDouble(st.nextToken());
                            System.out.println("Pregunta: "+numero_pregunta+" Calificación: "+calificacion);
                             entradas.add(new ValorReal(numero_pregunta,calificacion));  
                                
                                
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
           return entradas;
    }
    
    //Usar este método si no quieren usar ArrayList
     public  Lista<ValorReal> lee_entradas_reales_l(String archivo)
    {
        BufferedReader br = null;
        Lista<ValorReal> entradas = new Lista<ValorReal>();
		try {
 
			String sLinea;
                        System.out.println("Abriendo archivo "+archivo+" ...");
			br = new BufferedReader(new FileReader(archivo));
                        System.out.println("Archivo abierto! Iniciando proceso de lectura");
                        
                        StringTokenizer st;
			while ((sLinea = br.readLine()) != null) {
                            st = new StringTokenizer(sLinea);
                            int numero_pregunta = Integer.parseInt(st.nextToken());
                            double calificacion = Double.parseDouble(st.nextToken());
                            System.out.println("Pregunta: "+numero_pregunta+" Calificación: "+calificacion);
                             entradas.push(new ValorReal(numero_pregunta,calificacion));  
                                
                                
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
           return entradas;
    }
    
}
