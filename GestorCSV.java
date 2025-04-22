import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV {

    public static void guardarContactosCSV(List<Contacto> contactos, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Contacto contacto : contactos) {
                writer.println(contacto.toCSV());
            }
            System.out.println("Contactos guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static List<Contacto> cargarContactosCSV(String nombreArchivo) {
        List<Contacto> contactos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    Contacto contacto = new Contacto(partes[0], partes[1], partes[2], partes[3]);
                    contactos.add(contacto);
                }
            }
            System.out.println("Contactos cargados desde " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return contactos;
    }
}

