import java.time.LocalDate;

public class Inscripcion {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private Actividad actividad;

    public Inscripcion (LocalDate fecha, String estado, Estudiante estudiante, Actividad actividad) {
        this.fecha = fecha;
        this.estado = estado;
        this.actividad = actividad;
        this.estudiante = estudiante;
    }

    public void confirmar() {
        this.estado = "CONFIRMADA" ;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
