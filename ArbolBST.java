import java.util.*;

public class ArbolBST {
    private NodoBST raiz; //Nodo principal

    public ArbolBST() {
        raiz = null;
    }

    public void insertar(Contacto contacto) {
        raiz = insertarRec(raiz, contacto);
    }

    private NodoBST insertarRec(NodoBST nodo, Contacto contacto) {
        if (nodo == null) {
            return new NodoBST(contacto); //Si no existe nodo, crea uno
        }

        if (contacto.getNombre().compareToIgnoreCase(nodo.contacto.getNombre()) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, contacto); //Si es menor alfabeticamente a la izquierda, de lo contrario derecha
        } else {
            nodo.derecho = insertarRec(nodo.derecho, contacto);
        }

        return nodo;
    }

    public Contacto buscar(String nombre) {
        return buscarRec(raiz, nombre); //llama a la funcion para buscar un contacto
    }

    private Contacto buscarRec(NodoBST nodo, String nombre) {
        if (nodo == null) return null; //No existe el contacto
        int comparacion = nombre.compareToIgnoreCase(nodo.contacto.getNombre());
        if (comparacion == 0) {
            return nodo.contacto; //Igual lo devuelve
        } else if (comparacion < 0) {
            return buscarRec(nodo.izquierdo, nombre); //Si es menor, ve en el nodo izquierdo
        } else {
            return buscarRec(nodo.derecho, nombre); //Si es mayor, ve en el nodo derecho
        }
    }

    public void imprimirEnOrden() {
        imprimirEnOrdenRec(raiz); //Muestra los contactos InOrder
    }

    private void imprimirEnOrdenRec(NodoBST nodo) { //Se hace el recorrido inorder para imprimir los contactos
        if (nodo != null) {
            imprimirEnOrdenRec(nodo.izquierdo);
            System.out.println(nodo.contacto);
            System.out.println("-------------------");
            imprimirEnOrdenRec(nodo.derecho);
        }
    }

    public List<String> recorridoPorNiveles() { //Se utiliza una cola para hacer recorrido por nivel
        List<String> resultado = new ArrayList<>();
        Queue<NodoBST> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) { //Si existe, agrega el ID
            NodoBST actual = cola.poll();
            if (actual != null) {
                resultado.add(String.valueOf(actual.contacto.getId()));
                cola.add(actual.izquierdo);
                cola.add(actual.derecho);
            } else {
                resultado.add("null");
            }
        }

        return resultado;
    }
}
