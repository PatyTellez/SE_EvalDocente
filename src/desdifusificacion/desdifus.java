/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package desdifusificacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Alejandro Gómez Cruz
 */
public class desdifus 
{
    static double valor, x, p1, m1, m2, p2, max;
    static String etiq;
    
    static double limite=5.0, intervalo=0.001;
    public double desdifusificar() throws IOException
    {
        //Inicialización del calculo de centroide
        double centroide = 0.0 , area = 0.0, grados = 0.0, mayor = 0.0;
        double [] valor = new double[5];
        x = 0;
        while(x < limite)
        {
            for (int i = 0; i < 5; i++) 
            {
                leerDatos(i);//Leemos los puntos del conjunto
                if(x < m1)
                {
                    valor[i]=evaluarMenorM(x,p1,m1,m2,p2,max);
                }
                else if((x >= m1) && ( x <= m2))
                {
                    valor[i]=evaluarMedioM(x,p1,m1,m2,p2,max);
                }
                else if(x > m2)
                {
                    valor[i]=evaluarMayorM(x,p1,m1,m2,p2,max);
                }         
            } //fin de for
            for (int i = 0; i < 1; i++) 
            {
                mayor= obtenerMayor(valor[i],valor[i+1],valor[i+2],valor[i+3],valor[i+4]);
                System.out.println("El Grado de Pertenecia en "+x+" es "+mayor);
            }
            
            //Realizamos la sumatoria Xi M(Xi)
            area += (x * mayor);
            
            //Sumatoria de los grados de pertenencia
            grados += mayor;
            
            //Se incrementa X el valor del intervalo
            x += intervalo;
        }
        System.out.println("Área Final = " + area);
        System.out.println("Sumatoria Grados de Membresia Final = " + grados);
        
        //Calculo de centroide
        centroide = area / grados;
        
        return centroide;
    }
    static void leerDatos(int llave) throws FileNotFoundException, IOException
    {
        String etiqTemp;
        //Formato Variables | etiq->String | P1, m1, m2, p2, max -> double
        RandomAccessFile modelo_c = new RandomAccessFile("archivos/bin/modelo_c", "r");
        RandomAccessFile entrada_desdif = new RandomAccessFile("archivos/bin/entrada_desdifusificacion", "rw");
        modelo_c.seek(llave * 36);
        etiq = modelo_c.readUTF();
        p1 = modelo_c.readDouble();
        m1 = modelo_c.readDouble();
        m2 = modelo_c.readDouble();
        p2 = modelo_c.readDouble();
        for (int i = 0; i < 5; i++) 
        {
            entrada_desdif.seek(i * 12);
            etiqTemp = entrada_desdif.readUTF();
            if(etiqTemp.equalsIgnoreCase(etiq))
                max = modelo_c.readDouble();
        }
    }
    static double evaluarMenorM(double x,double a, double m1, double m2, double b, double max)
    {
        double resultado=0.0;
        
        resultado=((x-a)/(m1-a));
        
        return resultado;
    }
    static double evaluarMedioM(double x,double a, double m1, double m2, double b, double max)
    {
        double resultado=0.0;
        
        resultado=max;
        
        return resultado;
    }
    static double evaluarMayorM(double x,double a, double m1, double m2, double b, double max)
    {
        double resultado=0.0;
        
        resultado=((b-x)/(b-m2));
        
        return resultado;
    }
    static double obtenerMayor(double A,double B, double C, double D, double E)
    {
        double mayor=0.0;
       
        if(A >= B && A >= C && A >= D && A >= E)
        {  
            mayor=A;  
        }
        else
        {  
            if(B >= A && B >= C && B >= D && B >= E)
            {
                mayor=B;  
            }
            else
            {  
                if(C >= A && C >= B && C >= D && C >= E)
                {  
                    mayor=C;  
                }
                else
                {
                    if(D >= A && D >= B && D >= C && D >= E)
                    {
                        mayor=D;  
                    }
                    else  
                        mayor=E;  
                }  
            }
        }
        return mayor;
   }
}
