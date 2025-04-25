import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestorCSV {

    public static void guardarContactosCSV(List<Contacto> contactos, String nombreArchivo) {
        String ruta = "C:\\Users\\Andryu\\Desktop\\Universidad Rafael Landívar\\Tercer Semestre\\Estructura de Datos I (Práctica)\\Proyecto_01_Andry_Kevin\\Datos_Proyecto_01\\" + nombreArchivo;

        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) { //Se abre el archivo en modo lectura
            for (Contacto contacto : contactos) { //Recorre la lista de Contacto y lo escribe en CSV
                writer.println(contacto.toCSV());
            }
            System.out.println("Contactos guardados correctamente en " + ruta);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static List<Contacto> cargarContactosCSV(String nombreArchivo) {
        List<Contacto> contactos = new ArrayList<>(); //Lista donde se guardan los contactos cargados
        String ruta = "C:\\Users\\Andryu\\Desktop\\Universidad Rafael Landívar\\Tercer Semestre\\Estructura de Datos I (Práctica)\\Proyecto_01_Andry_Kevin\\Datos_Proyecto_01\\" + nombreArchivo;

        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) { //Se abre en modo lectura
            String linea;
            boolean esPrimeraLinea = true; //Se salta la primera linea

            while ((linea = reader.readLine()) != null) { //Se lee cada linea del archivo
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }

                String[] partes = linea.split(","); //Separa los datos con ,
                if (partes.length == 8) { //Se valida que el contacto este completo
                    int id = Integer.parseInt(partes[0]);
                    Contacto contacto = new Contacto( //Se crea el nuevo contacto
                            id,
                            partes[1], // nombre
                            partes[2], // apellido
                            partes[3], // apodo
                            partes[4], // telefono
                            partes[5], // correo
                            partes[6], // direccion
                            partes[7]  // fechaNacimiento
                    );
                    contactos.add(contacto); //Se añade a la Lista
                }
            }

            System.out.println("Contactos cargados desde: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return contactos;
    }
}