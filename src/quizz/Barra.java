package quizz;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
/** @author Edson Leon */
public class Barra extends JPanel
{
    JPanel[] arr;
    int Seleccion=0;
    Color Resp= new Color(52,97,250);
    Color SinResp = new Color(135,161,250);
    Color Act = new Color(0,204,48);
    Border blackline,oprimido,levantado;
    int CalifEst[];
    ArrayList<String> Preguntas;
    
    public Barra( int ancho, int alto, PanelCuestionario f)
    {
        Scanner pr=null;
        try { pr = new Scanner( new File("src/quizz/Preguntas.txt") ); } 
        catch (FileNotFoundException ex) {}
        int tam=0;
        Preguntas = new ArrayList<String>();
        while(pr.hasNextLine())
        {
            Preguntas.add( pr.nextLine() );
            tam++;
        }
         System.out.println("tamaño "+tam);
        this.setLayout(null);
        int AnchoPreg= (ancho/tam)-1;
        int aux=5;
        arr = new JPanel[tam];
        CalifEst = new int[tam];
        oprimido = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        blackline = BorderFactory.createLineBorder(Color.black);
        levantado = BorderFactory.createRaisedBevelBorder();
        
        for (int i = 0; i < tam; i++)
        {
            CalifEst[i]=5;
            arr[i]= new JPanel();
            this.add(arr[i]);
            arr[i].setBorder(oprimido);
            arr[i].setBounds(aux, 0, AnchoPreg, (alto) );
            //arr[i].setBackground( new Color(i*10, i*20 , i*15) );
            arr[i].setBackground( SinResp );
            aux+=AnchoPreg;
        }
        this.setBackground( new Color(0,0,0,0) );
        this.arr[0].setBackground(Act);
        this.arr[0].setBorder(levantado);
        f.Preg.setText(Preguntas.get(Seleccion));
    }
    
    public void Siguiente(PanelCuestionario f)
    {
        if( Seleccion==(arr.length-1) ) return;
        arr[Seleccion].setBorder(oprimido);
        arr[Seleccion].setBackground( Resp );
        Seleccion++;
        arr[Seleccion].setBorder(levantado);
        arr[Seleccion].setBackground( Act );
        System.out.println(CalifEst[Seleccion]);
        f.r[(CalifEst[Seleccion]-1)].doClick();
        f.Preg.setText(Preguntas.get(Seleccion));
    }
    
    public void Anterior(PanelCuestionario f)
    {
        if(Seleccion==0) return;
        arr[Seleccion].setBorder(oprimido);
        arr[Seleccion].setBackground( Resp );
        Seleccion--;
        arr[Seleccion].setBorder(levantado);
        arr[Seleccion].setBackground( Act );
        f.r[(CalifEst[Seleccion]-1)].doClick();
        f.Preg.setText(Preguntas.get(Seleccion));
    }
}
