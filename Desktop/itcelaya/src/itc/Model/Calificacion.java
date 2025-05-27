package itc.Model;

public class Calificacion {
    private int parcial;
    private int calificacion;
    //commity
    public Calificacion(int parcial, int calificacion) {
        this.parcial = parcial;
        this.calificacion = calificacion;
    }

    public int getParcial() {
        return parcial;
    }

    public void setParcial(int parcial) {
        this.parcial = parcial;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
