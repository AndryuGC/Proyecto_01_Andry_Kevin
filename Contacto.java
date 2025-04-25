public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private String apodo;
    private String telefono;
    private String correo;
    private String direccion;
    private String fechaNacimiento;

    //Constructor para crear el objeto Contacto
    public Contacto(int id, String nombre, String apellido, String apodo, String telefono, String correo, String direccion, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getApodo() { return apodo; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getDireccion() { return direccion; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    //Se pasa a toString para imprimir en la Consola
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + " " + apellido + " (" + apodo + ")\nTeléfono: " + telefono +
                "\nCorreo: " + correo + "\nDirección: " + direccion + "\nFecha de nacimiento: " + fechaNacimiento;
    }

    //Se guardan los datos en el CSV
    public String toCSV() {
        return id + "," + nombre + "," + apellido + "," + apodo + "," +
                telefono + "," + correo + "," + direccion + "," + fechaNacimiento;
    }
}
