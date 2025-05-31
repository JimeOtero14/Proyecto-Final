package itc.Model;

public class Maestro {
    private String cveMaestro;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String carrera;
    private byte[] foto;

    public Maestro(String cveMaestro, String nombre, String primerApellido,
                   String segundoApellido, String carrera, byte[] foto) {
        this.cveMaestro = cveMaestro;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.carrera = carrera;
        this.foto = foto;
    }

    public String getCveMaestro() {
        return cveMaestro;
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

    public byte[] getFoto() {
        return foto;
    }

    public String getNombreCompleto() {
        return nombre + " " + primerApellido +
               (segundoApellido != null && !segundoApellido.isEmpty() ? " " + segundoApellido : "");
    }
}
