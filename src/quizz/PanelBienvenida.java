package quizz;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelBienvenida extends JPanel
{
    JFrame thiss;
    JPanel pTitulo, panelt,panelu,panelv, panelw,irPreg;
    JLabel intro,b,c,d,e,img; 
    JButton comenzar;
    JPanel esp,esp2;
    JTabbedPane Paneles;
    
    public PanelBienvenida(JTabbedPane Paneles)
    {
        this.Paneles=Paneles;
        pTitulo();
        Panelt();
        Panelu();
        pIr_preg();
        Crear_Inicio();
    }
    
    void Crear_Inicio() 
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(pTitulo);
        this.add(panelt);
        this.add(panelu);
        this.add(irPreg);
        this.setVisible(true);
    }
    
    class BackgroundPanel extends JPanel{
        Image imagen;
        
        public BackgroundPanel(){
            try{
                imagen = (new ImageIcon(getClass().getResource("/imgs/itcelaya.jpg"))).getImage();
            }
            catch(Exception e){}
            }
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(imagen != null) g.drawImage(imagen, 0, 0, 590, 81, this); //(imagen,location x, location y, size x, size y)
        }
    }
    
    
    void pTitulo(){
        pTitulo = new JPanel();
        pTitulo = new quizz.PanelBienvenida.BackgroundPanel();
        pTitulo.setBackground(Color.WHITE); 
   }

    void Panelt(){
        panelt = new JPanel();
        panelt.setLayout(new FlowLayout());
        b=new JLabel(" BIENVENIDO A LA EVALUACIÓN DOCENTE ");
        panelt.add(b);
        panelt.setBackground(new Color(245,245,220));
        
    }
    
    void Panelu(){
        panelu = new JPanel();
        panelu.setLayout(new FlowLayout());
        c=new JLabel("Esta evaluación es un proceso de recolección, sistematización y análisis de");
        d=new JLabel("información útil, suficiente, variada y pertinente que permita guiar la toma" );
        e=new JLabel("de decisiones para mejorar los procesos de enseñanza y de aprendizaje");
        panelu.add(c);
        panelu.add(d);
        panelu.add(e);
        panelu.setBackground(new Color(245,245,220));
    }
        
     void pIr_preg(){
        irPreg = new JPanel();
        irPreg.setLayout(new FlowLayout());
        comenzar = new JButton("Iniciar Cuestionario");
        comenzar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Paneles.setSelectedIndex(1);
            }
        });
        
        irPreg.add(comenzar);
        irPreg.setBackground(new Color(245,245,220));
     }
     
     
}
