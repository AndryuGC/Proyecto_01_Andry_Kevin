import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in); //Lectura de las Entradas del Usuario
    private static List<Contacto> listaContactos = new ArrayList<>(); //Lista de Contactos en Memoria
    private static ArbolBST arbolBST = new ArbolBST(); //Arbol (Ordenado por Nombre)
    private static ArbolAVL arbolAVL = new ArbolAVL(); //Arbol AVL Autobalanceado (Ordenado por Nombre)
    private static int contadorId = 1; //ID's unicos

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n==== MENÚ PRINCIPAL ====");
            System.out.println("Seleccione una opcion entre 1-12");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Mostrar contactos (BST)");
            System.out.println("3. Mostrar contactos (AVL)");
            System.out.println("4. Buscar contacto (BST)");
            System.out.println("5. Buscar contacto (AVL)");
            System.out.println("6. Guardar contactos en CSV");
            System.out.println("7. Cargar contactos desde CSV");
            System.out.println("8. Generar archivo de recorrido por niveles (nombre - BST)");
            System.out.println("9. Generar archivo de recorrido por niveles (apellido - AVL)");
            System.out.println("10. Generar archivo de recorrido por niveles (apodo - AVL)");
            System.out.println("11. Reconstruir árbol desde archivo de ID");
            System.out.println("12. Salir");
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
                case 8 -> GeneradorArchivos.generarArchivoRecorridoPorNivelesBST(arbolBST, "nombre-bst.txt");
                case 9 -> GeneradorArchivos.generarArchivoRecorridoPorNivelesAVL(arbolAVL, "apellido-avl.txt");
                case 10 -> GeneradorArchivos.generarArchivoRecorridoPorNivelesAVL(arbolAVL, "apodo-avl.txt");
                case 11 -> {
                    System.out.print("Nombre del archivo a Reconstruir: ");
                    String archivo = scanner.nextLine();
                    System.out.print("¿Que tipo de Arbol (BST o AVL)? ");
                    String tipo = scanner.nextLine();
                    reconstruirArbolDesdeTxt(listaContactos, archivo, tipo);
                }
                case 12 -> {
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
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Apodo: ");
        String apodo = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Fecha de nacimiento (YYYYMMDD): ");
        String fechaNacimiento = scanner.nextLine();

        //Se crea el contacto con el ID unico
        Contacto nuevo = new Contacto(contadorId++, nombre, apellido, apodo, telefono, correo, direccion, fechaNacimiento);
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
        System.out.print("Inserte nombre del Archivo.csv: ");
        String archivo = scanner.nextLine();
        GestorCSV.guardarContactosCSV(listaContactos, archivo);
    }

    private static void cargarContactos() { //Se cargan los contactos desde un CSV, y reconstruye los árboles e ID
        System.out.print("Escribe el nombre del archivo a cargar: ");
        String archivo = scanner.nextLine();

        listaContactos = GestorCSV.cargarContactosCSV(archivo);
        arbolBST = new ArbolBST();
        arbolAVL = new ArbolAVL();

        for (Contacto c : listaContactos) {
            arbolBST.insertar(c);
            arbolAVL.insertar(c);
        }
        //Se actualiza el contador al último espacio disponible
        contadorId = listaContactos.stream().mapToInt(Contacto::getId).max().orElse(0) + 1;
        System.out.println("Contactos Cargados.");
    }

    // Reconstruye un árbol desde un archivo de IDs por niveles
    public static void reconstruirArbolDesdeTxt(List<Contacto> listaContactos, String archivoTxt, String tipo) {
        List<Integer> ids = GeneradorArchivos.leerIdsDesdeTxt(archivoTxt);
        if (tipo.equalsIgnoreCase("BST")) {
            ArbolBST nuevoBST = new ArbolBST();
            for (int id : ids) {
                for (Contacto c : listaContactos) {
                    if (c.getId() == id) {
                        nuevoBST.insertar(c);
                        break;
                    }
                }
            }
            System.out.println("Árbol BST reconstruido desde " + archivoTxt);
            nuevoBST.imprimirEnOrden();
        } else if (tipo.equalsIgnoreCase("AVL")) {
            ArbolAVL nuevoAVL = new ArbolAVL();
            for (int id : ids) {
                for (Contacto c : listaContactos) {
                    if (c.getId() == id) {
                        nuevoAVL.insertar(c);
                        break;
                    }
                }
            }
            System.out.println("Árbol AVL reconstruido desde " + archivoTxt);
            nuevoAVL.imprimirEnOrden();
        }
    }
}
