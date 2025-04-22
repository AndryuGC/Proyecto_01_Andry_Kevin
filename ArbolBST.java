//
public class ArbolBST {
    private NodoBST raiz;

    public ArbolBST() {
        raiz = null;
    }

    public void insertar(Contacto contacto) {
        raiz = insertarRec(raiz, contacto);
    }

    private NodoBST insertarRec(NodoBST nodo, Contacto contacto) {
        if (nodo == null) {
            return new NodoBST(contacto);
        }

        if (contacto.getNombre().compareToIgnoreCase(nodo.contacto.getNombre()) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, contacto);
        } else {
            nodo.derecho = insertarRec(nodo.derecho, contacto);
        }

        return nodo;
    }

    public Contacto buscar(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Contacto buscarRec(NodoBST nodo, String nombre) {
        if (nodo == null) return null;

        int comparacion = nombre.compareToIgnoreCase(nodo.contacto.getNombre());
        if (comparacion == 0) {
            return nodo.contacto;
        } else if (comparacion < 0) {
            return buscarRec(nodo.izquierdo, nombre);
        } else {
            return buscarRec(nodo.derecho, nombre);
        }
    }

    public void imprimirEnOrden() {
        imprimirEnOrdenRec(raiz);
    }

    private void imprimirEnOrdenRec(NodoBST nodo) {
        if (nodo != null) {
            imprimirEnOrdenRec(nodo.izquierdo);
            System.out.println(nodo.contacto);
            System.out.println("-------------------");
            imprimirEnOrdenRec(nodo.derecho);
        }
    }
}

