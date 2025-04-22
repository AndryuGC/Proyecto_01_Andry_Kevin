import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Contacto> listaContactos = new ArrayList<>();
    private static ArbolBST arbolBST = new ArbolBST();
    private static ArbolAVL arbolAVL = new ArbolAVL();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==== MENÚ PRINCIPAL ====");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Mostrar contactos (BST)");
            System.out.println("3. Mostrar contactos (AVL)");
            System.out.println("4. Buscar contacto (BST)");
            System.out.println("5. Buscar contacto (AVL)");
            System.out.println("6. Guardar contactos en CSV");
            System.out.println("7. Cargar contactos desde CSV");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarContacto();
                case 2 -> arbolBST.imprimirEnOrden();
                case 3 -> arbolAVL.imprimirEnOrden();
                case 4 -> buscarContactoBST();
                case 5 -> buscarContactoAVL();
                case 6 -> guardarContactos();
                case 7 -> cargarContactos();
                case 8 -> {
                    salir = true;
                    System.out.println("Saliendo...");
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void agregarContacto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        Contacto nuevo = new Contacto(nombre, telefono, correo, direccion);
        listaContactos.add(nuevo);
        arbolBST.insertar(nuevo);
        arbolAVL.insertar(nuevo);

        System.out.println("Contacto agregado correctamente.");
    }

    private static void buscarContactoBST() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine();
        Contacto c = arbolBST.buscar(nombre);
        if (c != null) {
            System.out.println("Contacto encontrado (BST):\n" + c);
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void buscarContactoAVL() {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine();
        Contacto c = arbolAVL.buscar(nombre);
        if (c != null) {
            System.out.println("Contacto encontrado (AVL):\n" + c);
        } else {
            System.out.println(" Contacto no encontrado.");
        }
    }

    private static void guardarContactos() {
        System.out.print("Nombre del archivo (ej: contactos.csv): ");
        String archivo = scanner.nextLine();
        GestorCSV.guardarContactosCSV(listaContactos, archivo);
    }

    private static void cargarContactos() {
        System.out.print("Nombre del archivo (ej: contactos.csv): ");
        String archivo = scanner.nextLine();
        listaContactos = GestorCSV.cargarContactosCSV(archivo);
        arbolBST = new ArbolBST();
        arbolAVL = new ArbolAVL();

        for (Contacto c : listaContactos) {
            arbolBST.insertar(c);
            arbolAVL.insertar(c);
        }

        System.out.println("Contactos cargados y árboles reconstruidos.");
    }
}
