package quizz;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;

public class PanelAcercaDe extends JPanel
{
    JLabel ImgInfo;
    JTextPane Acerca;
    JTextPane InfoPeq;
    JScrollPane SP;
    
    public PanelAcercaDe()
    {
        int Alto=300;
        int Ancho=480;
        this.setBounds(0, 0, Ancho, Alto);
        this.setLayout(null);
        this.setBackground( new Color(50, 200, 105, 4) );
        ImgInfo = new JLabel();
        this.add(ImgInfo);
        ImgInfo.setBounds(20, 20, 200, 200);
        Image img=null;
        
        try{ img = (new ImageIcon(getClass().getResource("/imgs/Info.png"))).getImage(); }
        catch(Exception e){}
        
        Image scaledImage = img.getScaledInstance( 200, 200, Image.SCALE_SMOOTH);
        
        ImageIcon icon = new ImageIcon(scaledImage);
        ImgInfo.setBackground(Color.BLUE);
        ImgInfo.setOpaque(true);
        ImgInfo.setIcon(icon);
        
        Acerca = new JTextPane();
       
        SP = new JScrollPane(Acerca);
        this.add(SP);
        SP.setBounds(250, 20, 320, 280);
        Acerca.setBackground(new Color(153,204,255));
        Acerca.setFont(new Font("Arial", 2, 14));
        Acerca.setOpaque(true);
        Acerca.setText("DESARROLLADO POR:\n\n"
                + "Patricia Tellez\n"
                + "Mayra Ortiz Nava\n"
                + "Karla Teresa Arroyo Calero\n"
                + "Edson Raúl León Hurtado\n"
                + "Ricardo Mendéz Rico\n"
                + "Juan Carlos Arteaga Amate\n"
                + "Alejandro Gomez Cruz\n"
                + "Carlos Alberto Ruiz Ayala\n"
                + "Elías Gómez\n"
                + "Ana Laura Huichapa\n"
                + "Juan Luis Escoto Hernández\n"
                + "Jorge Méndez Medina\n"
                + "Daniel Espitia Rico");
        Acerca.setEditable(false);
        
        InfoPeq = new JTextPane();
        this.add(InfoPeq);
        InfoPeq.setBounds(20, 250, 200, 50);
        InfoPeq.setBackground(new Color(153,204,255));
        InfoPeq.setOpaque(true);
        InfoPeq.setEditable(false);
        InfoPeq.setText("Instituto Tecnologico de Celaya \n"
                + "Desarrollos Inteligentes \n"
                + "Mayo de 2014.");
        
    }
}
