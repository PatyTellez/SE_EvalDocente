package quizz;
import desdifusificacion.desdifus;
import difusificar.Difusificar;
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
      Difusificar m= new Difusificar();
      /////////////////////////////////////////////////////////////////////////////
      
      
      
        ///////////////////////////////////INFERENCIA///////////////////////////////////////////////////              
        i.Realizar_Inferencia();
        ////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        /////////////////////////////////////DESDIFUCIFICACION/////////////////
           desdifus des = new desdifus ();  
          // des.archivo();                     //se crea el archivo del modelo difuso esto solo se hace una vez
          //des.archivo_modelo_difuso();
          //  des.acomodar_etiquetas();
            des.desdifuzificar();
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////    
        
                 } catch (IOException ex) {
                      Logger.getLogger(Quizz.class.getName()).log(Level.SEVERE, null, ex);
                 }
                                  
                 }
                 
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
               
               double dominio,planeacion,ambiente,impacto;
               dominio = planeacion = ambiente = impacto = 0.0;
               
               
               for (int i = 0; i < Preguntas.CalifEst.length; i++) {
                      bw.write((i+1)+" "+Preguntas.CalifEst[i]+"\n");
                      if(i==0 || i==2 || i==8 || i==10){dominio+=Preguntas.CalifEst[i];}
                      else if(i==1 || i==3 || i==5){planeacion+=Preguntas.CalifEst[i];}
                      else if(i==6 || i==7 || i==9 || i==11){ambiente+=Preguntas.CalifEst[i];}
                      else if(i==4 || i==12){impacto+=Preguntas.CalifEst[i];}
               }
               
               bw.close();
               w.close();
               
                FileWriter w2 = new FileWriter(new File("PromedioIndicadores.txt"));
               BufferedWriter bw2 = new BufferedWriter(w2);
               
               bw2.write("Dominio "+(dominio/4.0)+"\n");
               bw2.write("Planeacion "+(planeacion/3.0)+"\n");
               bw2.write("Ambiente "+(ambiente/4.0)+"\n");
               bw2.write("Impacto "+(impacto/2.0)+"\n");
               
               bw2.close();
               w2.close();

         
         
         
    }


    
    
    
}
