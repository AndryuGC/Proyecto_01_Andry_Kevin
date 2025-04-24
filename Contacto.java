public class Contacto {
    private int id;
    private String nombre;
    private String apellido;
    private String apodo;
    private String telefono;
    private String correo;
    private String direccion;
    private String fechaNacimiento;

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

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getApodo() { return apodo; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getDireccion() { return direccion; }
    public String getFechaNacimiento() { return fechaNacimiento; }

    // Setters
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + " " + apellido + " (" + apodo + ")\nTeléfono: " + telefono +
                "\nCorreo: " + correo + "\nDirección: " + direccion + "\nFecha de nacimiento: " + fechaNacimiento;
    }

    public String toCSV() {
        return id + "," + nombre + "," + apellido + "," + apodo + "," +
                telefono + "," + correo + "," + direccion + "," + fechaNacimiento;
    }
}
