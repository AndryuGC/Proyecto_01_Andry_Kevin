import java.util.*;

public class ArbolAVL {
    private NodoAVL raiz;

    public void insertar(Contacto contacto) {
        raiz = insertarRec(raiz, contacto);
    }

    private NodoAVL insertarRec(NodoAVL nodo, Contacto contacto) {
        if (nodo == null) return new NodoAVL(contacto);

        if (contacto.getNombre().compareToIgnoreCase(nodo.contacto.getNombre()) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, contacto);
        } else {
            nodo.derecho = insertarRec(nodo.derecho, contacto);
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));

        int balance = getBalance(nodo);

        // Rotaciones
        if (balance > 1 && contacto.getNombre().compareToIgnoreCase(nodo.izquierdo.contacto.getNombre()) < 0) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && contacto.getNombre().compareToIgnoreCase(nodo.derecho.contacto.getNombre()) > 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && contacto.getNombre().compareToIgnoreCase(nodo.izquierdo.contacto.getNombre()) > 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && contacto.getNombre().compareToIgnoreCase(nodo.derecho.contacto.getNombre()) < 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public Contacto buscar(String nombre) {
        return buscarRec(raiz, nombre);
    }

    private Contacto buscarRec(NodoAVL nodo, String nombre) {
        if (nodo == null) return null;

        int comp = nombre.compareToIgnoreCase(nodo.contacto.getNombre());
        if (comp == 0) return nodo.contacto;
        if (comp < 0) return buscarRec(nodo.izquierdo, nombre);
        return buscarRec(nodo.derecho, nombre);
    }

    public void imprimirEnOrden() {
        imprimirEnOrdenRec(raiz);
    }

    private void imprimirEnOrdenRec(NodoAVL nodo) {
        if (nodo != null) {
            imprimirEnOrdenRec(nodo.izquierdo);
            System.out.println(nodo.contacto);
            System.out.println("-------------------");
            imprimirEnOrdenRec(nodo.derecho);
        }
    }

    private int altura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int getBalance(NodoAVL nodo) {
        return (nodo == null) ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T2 = x.derecho;

        x.derecho = y;
        y.izquierdo = T2;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T2;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    public List<String> recorridoPorNiveles() {
        List<String> resultado = new ArrayList<>();
        Queue<NodoAVL> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            NodoAVL actual = cola.poll();
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

