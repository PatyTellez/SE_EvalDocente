package modelosdifusos;

public class Lista<E>
{
    Nodo_Lista<E> nodo;
    boolean vacio;
    int longitud = 0;
    
    public Lista(Nodo_Lista<E> a)
    {
        this.nodo = a;
        longitud++;
    }
    Lista()
    {
    
    }
    public void push(E a)
    {
        Nodo_Lista<E> tmp = nodo;
        Nodo_Lista<E> nuevo = new Nodo_Lista<E>(a);
        if(nodo == null)
        {
            nodo = nuevo;
            longitud++;
            return;
        }
        while(tmp.siguiente!=null)
        {
            tmp = tmp.siguiente;
        }
        tmp.siguiente = nuevo;
        longitud++;
    }
    
    public E pop()
    {
        Nodo_Lista<E> tmp = nodo;
        this.nodo = nodo.siguiente;
        longitud--;
                
        return tmp.elemento;
    }
    public E revisa()
    {
     Nodo_Lista<E> tmp = nodo;
     while(tmp.siguiente!=null)
     {
         tmp = tmp.siguiente;
     }
     return tmp.elemento;
     
    }
    public boolean estaVacio()
    {
        if(nodo == null)
        {
            this.vacio = true;
        }
        else
        {
            this.vacio = false;
        }
        return vacio;
    }
    public int getLongitud()
    {
    return longitud;
    }
    
    public E get(int i)
    {
        Nodo_Lista<E> tmp = nodo;
        if(i != 0 )
        {
            for (int j = 0; j < i; j++) 
            {
                tmp = tmp.siguiente;                
            }
        }
        return tmp.elemento;
    } 
           
    
}