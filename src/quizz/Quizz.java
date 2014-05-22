package quizz;
import desdifusificacion.desdifus;
import inferir.Archivos;
import inferir.inferir;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import modelosdifusos.Motor;
import static quizz.PanelCuestionario.Preguntas;
/** @author Edson Leon */
public class Quizz extends JFrame
{
    int pregunta=1;
    JMenuBar menuBar;
    JMenu menu, submenu;
    JMenuItem menuItem;
    JTabbedPane Paneles = new JTabbedPane();
     inferir i = new inferir(); 
    
    public Quizz()
    {
        int Ancho=600, Alto=400;
        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setBounds( (tk.getScreenSize().width/2)-(Ancho/2), (tk.getScreenSize().height/2)-(Alto/2), Ancho, Alto);
        this.setVisible(rootPaneCheckingEnabled);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setTitle("Evaluación Docentes");
        this.setResizable(false);
        menuBar = new JMenuBar();
        menu = new JMenu("Archivo");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        submenu = new JMenu("Calificar");
        submenu.setMnemonic(KeyEvent.VK_S);
        menuItem = new JMenuItem("Procesar");
        menuItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                 try {
                      generarArchivoDeEntradasParaDifusificar();
                      
       //////////////////////////////////////DIFUCIFICACION ///////////////////////
      Motor m= new Motor();
      m.difusificar( m.lee_entradas_reales("entradasReales.txt"));
      /////////////////////////////////////////////////////////////////////////////
      
      
      
        ///////////////////////////////////INFERENCIA///////////////////////////////////////////////////              
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
        ////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        /////////////////////////////////////DESDIFUCIFICACION/////////////////
           desdifus des = new desdifus ();  
          // des.archivo();                     //se crea el archivo del modelo difuso esto solo se hace una vez
         // des.archivo_modelo_difuso();
            des.acomodar_etiquetas();
            des.desdifuzificar();
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////    
        
        
                      
                      
                 } catch (IOException ex) {
                      Logger.getLogger(Quizz.class.getName()).log(Level.SEVERE, null, ex);
                 }
                                  
                 }
                ///////////////////////////////////////////////////////////////////////////////////////
                //AGREGAR AQUÍ CÓDIGO PARA INFERIR, Y DESDIFUSIFICAR///////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////////////
            
               
       
        
        
        
            
            
        });
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);
        menuItem = new JMenuItem("Salir");
        menu.add(submenu);
        menuItem.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if(JOptionPane.showOptionDialog(null, "¿Seguro que Deseas Salir?", "Aviso:", 2, 2, null, null, e)==JOptionPane.YES_NO_OPTION)
                {
                    JOptionPane.showMessageDialog(null, "Muchas Gracias. Hasta Luego ^-^");
                    System.exit(0);
                }
                else{}
            }
        });
        menu.add(menuItem);
        this.setJMenuBar(menuBar);
        
        this.add(Paneles);
        Paneles.setBounds(0, 0, Ancho, Alto);
        
        PanelBienvenida pb = new PanelBienvenida(Paneles);
        Paneles.addTab("Bienvenida",pb);
        
        PanelCuestionario fp = new PanelCuestionario();
        Paneles.addTab("Cuestionario",fp.PanelPrincipal);
        
        PanelAcercaDe pad = new PanelAcercaDe();
        Paneles.addTab("Acerca De",pad);
        
    }
    
    
    public static void main(String[] args) 
    {
         try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    //System.out.println(""+info.getName());
                    if ("Windows".equals(info.getName())) {
                         javax.swing.UIManager.setLookAndFeel(info.getClassName());
                         break;
                    }
                }
          } catch (ClassNotFoundException ex) {
               java.util.logging.Logger.getLogger(Quizz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
               java.util.logging.Logger.getLogger(Quizz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
               java.util.logging.Logger.getLogger(Quizz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (javax.swing.UnsupportedLookAndFeelException ex) {
               java.util.logging.Logger.getLogger(Quizz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
         
        Quizz qz = new Quizz();
        qz.repaint();
    }
    
    private void generarArchivoDeEntradasParaDifusificar() throws IOException{

               FileWriter w = new FileWriter(new File("entradasReales.txt"));
               BufferedWriter bw = new BufferedWriter(w);
               
               for (int i = 0; i < Preguntas.CalifEst.length; i++) {
                      bw.write((i+1)+" "+Preguntas.CalifEst[i]+"\n");
               }
               
               bw.close();
               w.close();

         
         
         
    }


    
    
    
}
