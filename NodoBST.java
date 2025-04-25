//
public class NodoBST {
    public Contacto contacto;
    //Referencia a los hijos del nodo
    public NodoBST izquierdo;
    public NodoBST derecho;

    public NodoBST(Contacto contacto) { //Constructor y se inicializan en null
        this.contacto = contacto;
        this.izquierdo = null;
        this.derecho = null;
    }
}
