
package modelosdifusos;

public class ValorReal 
{
    int numero_pregunta;
    double calificacion;

    public ValorReal(int numero_pregunta, double calificacion) {
        this.numero_pregunta = numero_pregunta;
        this.calificacion = calificacion;
    }

    public int getNumero_pregunta() {
        return numero_pregunta;
    }

    public void setNumero_pregunta(int numero_pregunta) {
        this.numero_pregunta = numero_pregunta;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
    
    
    
}