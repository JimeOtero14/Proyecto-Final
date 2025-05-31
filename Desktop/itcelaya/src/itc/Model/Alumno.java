package itc.Model;

public class Alumno {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String carrera;
    private String noControl;
    private byte[] foto;

    public Alumno(String nombre, String primerApellido, String segundoApellido,
                  String carrera, String noControl, byte[] foto) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.carrera = carrera;
        this.noControl = noControl;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getNoControl() {
        return noControl;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setNoControl(String noControl) {
        this.noControl = noControl;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNombreCompleto() {
        return nombre + " " + primerApellido + (segundoApellido != null ? " " + segundoApellido : "");
    }
}
