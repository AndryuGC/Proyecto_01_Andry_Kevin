public class NodoBST {
    public Contacto contacto;
    public NodoBST izquierdo;
    public NodoBST derecho;

    public NodoBST(Contacto contacto) {
        this.contacto = contacto;
        this.izquierdo = null;
        this.derecho = null;
    }
}
