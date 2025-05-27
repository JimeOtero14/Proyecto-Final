package itc.Model;

public class Alumno {
    private int noControl;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String carrera;
    //commit
    public Alumno(int noControl, String nombre, String primerApellido, String segundoApellido, String carrera) {
        this.noControl = noControl;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.carrera = carrera;
    }

    // Getters y setters

    public int getNoControl() {
        return noControl;
    }

    public void setNoControl(int noControl) {
        this.noControl = noControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    // Método para obtener el nombre completo concatenado
    public String getNombreCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombre).append(" ").append(primerApellido);
        if (segundoApellido != null && !segundoApellido.isEmpty()) {
            sb.append(" ").append(segundoApellido);
        }
        return sb.toString();
    }
}
