//
public class NodoAVL {
    public Contacto contacto;
    //Hijos nodo
    public NodoAVL izquierdo;
    public NodoAVL derecho;
    public int altura;

    public NodoAVL(Contacto contacto) {
        this.contacto = contacto;
        this.altura = 1;
    }
}

