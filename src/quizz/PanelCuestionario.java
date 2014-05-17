package quizz;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class PanelCuestionario 
{
    JPanel PanelPrincipal,Contenedor,Estrellas,BarraPreguntas;
    JTextPane Preg;
    JLabel Ant;
    JLabel Sig;
    int pregunta=1;
    public static Barra Preguntas;
    JMenuBar menuBar;
    JRadioButtonMenuItem rbMenuItem;
    JCheckBoxMenuItem cbMenuItem;
    JRadioButton r[];
    ButtonGroup bg;
    final int totalradios=5;
    
    ImageIcon sig_over,ant_over,sig_exit,ant_exit;
    
     public PanelCuestionario()
     {
        int Ancho=600, Alto=300;
        PanelPrincipal = new JPanel();
        PanelPrincipal.setBounds(0, 0, Ancho, Alto);
        
        Preg = new JTextPane();//"Pregunta 1: Del 1 al 10"
        Contenedor = new JPanel(); //
        Preg.setBackground(new Color(245,245,220));
        Preg.setOpaque(true);
        PanelPrincipal.add(Preg);
        Preg.setBounds(20,20, (Ancho-60), (120) );
        Preg.setFont(new Font("Arial", 2, 20));
        Preg.setEditable(false);
        //PanelPrincipal.setBackground(new Color(0.0f, 0.0f, 0.0f, .2f));        
        PanelPrincipal.setBackground(new Color(245,245,220));//
        ImageIcon icon = new ImageIcon("src/imgs/Star_1.png");
        r = new JRadioButton[totalradios];
        
        for (int i = 0; i < totalradios/2; i++) 
        { r[i] = new JRadioButton(icon); }
        r[totalradios/2-1].setSelected(true);
        icon = new ImageIcon("src/imgs/Star_0.png");
        
        for (int i = totalradios/2; i < totalradios; i++) 
        { r[i] = new JRadioButton(icon); }
        bg = new ButtonGroup();
        
        Estrellas = new JPanel();
        Estrellas.setLayout(null);
        PanelPrincipal.add(Estrellas);
        Estrellas.setBounds( (Ancho/3), (150), 3*Ancho/2, 100 );
        
        int aux=0;
        for ( int i = 0; i < totalradios; i++) 
        {
            final int z=i;
            Estrellas.add( r[i] );
            r[i].setMnemonic(i);
            r[i].setBounds(aux, 0, 30, 30);
            //r[i].setBackground(new Color(0,0,0,0));
            r[i].setBackground(Color.LIGHT_GRAY);
            bg.add(r[i]);
            aux=aux+35;
            r[i].addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    //System.out.println( (z+1)+"  "+ (Preguntas.Seleccion+1) );
                    Preguntas.CalifEst[Preguntas.Seleccion]=(z+1);
                    ImageIcon icon = new ImageIcon("src/imgs/Star_1.png");
                    for (int j = 0; j <= z; j++) 
                    {
                        r[j].setIcon(icon);
                    }
                    icon = new ImageIcon("src/imgs/Star_0.png");
                    for (int j = z+1; j < totalradios; j++) 
                    {
                        r[j].setIcon(icon);
                    }
                }
            });
        }
        Estrellas.setBackground(new Color(0,0,0,0));
        Contenedor.setBounds(0, (Alto-120), Ancho, (Alto/4));
        Contenedor.setBackground(Color.blue);
        Ant = new JLabel();
        Sig = new JLabel();
        int x=50; 
        int y =50;
        ImageIcon img = new ImageIcon("src/Imgs/Siguiente.png");
        ImageIcon LabImg = new ImageIcon( img.getImage().getScaledInstance(x,y, 1) ); 
	Sig.setIcon( LabImg );
        img = new ImageIcon("src/Imgs/Anterior.png");
        LabImg = new ImageIcon( img.getImage().getScaledInstance(x,y, 1) ); 
	Ant.setIcon( LabImg );
        PanelPrincipal.setLayout(null);
        PanelPrincipal.add(Ant);
        PanelPrincipal.add(Sig);
        
        Ant.setBounds(10, (Alto-50), x, y);
        Sig.setBounds( ( (Ancho-(x*2))+(x/2) ) , (Alto-50), x, y);
        
        Preguntas = new Barra(( (Ancho-(x*3)-30) ), y ,PanelCuestionario.this);
        PanelPrincipal.add(Preguntas);
        Preguntas.setBounds(x+30, (Alto-50), ( (Ancho-(x*3)-30) ), y);
        
        sig_over = new ImageIcon(new ImageIcon("src/Imgs/Siguiente_2.png").getImage().getScaledInstance(x, y, 1));
        sig_exit = new ImageIcon(new ImageIcon("src/Imgs/Siguiente.png").getImage().getScaledInstance(x, y, 1));
        ant_over = new ImageIcon(new ImageIcon("src/Imgs/Anterior_2.png").getImage().getScaledInstance(x, y, 1));
        ant_exit = new ImageIcon(new ImageIcon("src/Imgs/Anterior.png").getImage().getScaledInstance(x, y, 1));
        
        Ant.addMouseListener( new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) 
            { Preguntas.Anterior(PanelCuestionario.this);}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e){}

            @Override
            public void mouseEntered(MouseEvent e) {Ant.setIcon( ant_over );}

            @Override
            public void mouseExited(MouseEvent e) {Ant.setIcon( ant_exit );}
        });
        
        Sig.addMouseListener( new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) 
            {  Preguntas.Siguiente(PanelCuestionario.this); }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e){}

            @Override
            public void mouseEntered(MouseEvent e) {Sig.setIcon( sig_over );}

            @Override
            public void mouseExited(MouseEvent e) {Sig.setIcon( sig_exit );}
        });
     }
}
