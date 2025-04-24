import java.io.*;
import java.util.*;

public class GeneradorArchivos {

    private static final String rutaBase = "C:\\Users\\Andryu\\Desktop\\Universidad Rafael Landívar\\Tercer Semestre\\Estructura de Datos I (Práctica)\\Proyecto_01_Andry_Kevin\\Datos_Proyecto_01\\";

    public static void generarArchivoRecorridoPorNivelesBST(ArbolBST arbol, String nombreArchivo) {
        List<String> ids = arbol.recorridoPorNiveles();
        escribirArchivo(nombreArchivo, ids);
    }

    public static void generarArchivoRecorridoPorNivelesAVL(ArbolAVL arbol, String nombreArchivo) {
        List<String> ids = arbol.recorridoPorNiveles();
        escribirArchivo(nombreArchivo, ids);
    }

    private static void escribirArchivo(String nombreArchivo, List<String> ids) {
        String rutaCompleta = rutaBase + nombreArchivo;

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaCompleta))) {
            writer.println(String.join(",", ids));
            System.out.println("Archivo generado: " + rutaCompleta);
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public static List<Integer> leerIdsDesdeTxt(String nombreArchivo) {
        List<Integer> ids = new ArrayList<>();
        String ruta = "C:\\Users\\Andryu\\Desktop\\Universidad Rafael Landívar\\Tercer Semestre\\Estructura de Datos I (Práctica)\\Proyecto_01_Andry_Kevin\\Datos_Proyecto_01\\" + nombreArchivo;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] partes = linea.split(",");
                for (String parte : partes) {
                    if (!parte.equalsIgnoreCase("null")) {
                        ids.add(Integer.parseInt(parte.trim()));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de IDs: " + e.getMessage());
        }
        return ids;
    }

}
