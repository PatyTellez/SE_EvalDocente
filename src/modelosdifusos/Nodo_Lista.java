package modelosdifusos;

public class Nodo_Lista<E> 
{
    
    E elemento;
    Nodo_Lista<E> siguiente;
    
    public Nodo_Lista(E elemento)
    {
        this.elemento = elemento;
        this.siguiente = null;
    }
    
    public Nodo_Lista(E elemento, Nodo_Lista<E> siguiente)
    {
        this.elemento = elemento;
        this.siguiente = siguiente;
    }
    
}