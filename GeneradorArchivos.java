import java.io.*;
import java.util.*;

public class GeneradorArchivos {

    private static final String rutaBase = "C:\\Users\\Andryu\\Desktop\\Universidad Rafael Landívar\\Tercer Semestre\\Estructura de Datos I (Práctica)\\Proyecto_01_Andry_Kevin\\Datos_Proyecto_01\\";

    public static void generarArchivoRecorridoPorNivelesBST(ArbolBST arbol, String nombreArchivo) {
        List<String> ids = arbol.recorridoPorNiveles(); //Se obtiene la lista de Ids
        escribirArchivo(nombreArchivo, ids); //Se guarda el archivo
    }

    public static void generarArchivoRecorridoPorNivelesAVL(ArbolAVL arbol, String nombreArchivo) {
        List<String> ids = arbol.recorridoPorNiveles();
        escribirArchivo(nombreArchivo, ids);
    }

    private static void escribirArchivo(String nombreArchivo, List<String> ids) {
        String rutaCompleta = rutaBase + nombreArchivo;

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaCompleta))) { //Se crea el archivo
            writer.println(String.join(",", ids)); //Se juntan los ids separados de una coma
            System.out.println("Archivo generado: " + rutaCompleta);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static List<Integer> leerIdsDesdeTxt(String nombreArchivo) {
        List<Integer> ids = new ArrayList<>();
        String ruta = "C:\\Users\\Andryu\\Desktop\\Universidad Rafael Landívar\\Tercer Semestre\\Estructura de Datos I (Práctica)\\Proyecto_01_Andry_Kevin\\Datos_Proyecto_01\\" + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) { //Se abre el archivo de los ID's
            String linea = br.readLine();
            if (linea != null) { //Si la linea tiene los datos
                String[] partes = linea.split(",");
                for (String parte : partes) {
                    if (!parte.equalsIgnoreCase("null")) {
                        ids.add(Integer.parseInt(parte.trim())); //Se convierten a Int guardando en la lista
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de IDs: " + e.getMessage());
        }
        return ids;
    }

}
