import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Actividad {
    private int id;
    private String titulo;
    private int cupoMaximo;
    private static final int cupoMinimo;
    private List<Inscripcion> inscripciones;

    //inicializador estatico
    static {
        cupoMinimo = 10;
        System.out.println("Inicializador estatico: Se cargo la clase actividad.");
    }

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = (cupoMaximo > cupoMinimo) ? cupoMaximo : cupoMinimo;
        //para que cada actividad tenga su lista de inscripciones:
        this.inscripciones = new ArrayList<>();
    }


    public Inscripcion inscribir(Estudiante estudiante) {
        Inscripcion inscripcion = new Inscripcion(LocalDate.now(), "REGISTRADA", estudiante, this );
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(titulo == null || titulo.isBlank()) {
            return;
        }
        this.titulo = titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = (cupoMaximo > cupoMinimo) ? cupoMaximo : cupoMinimo;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void mostrarInscripciones() {
        if (inscripciones.isEmpty()) {
            System.out.println("Sin inscripciones registradas: ");
            return;
        }
        System.out.println("Inscripciones registradas: ");
        for (Inscripcion inscripcion: inscripciones) {
            System.out.println("  " + inscripcion.getFecha() + " - " + inscripcion.getEstado() + " - " + inscripcion.getEstudiante().getNombre() + " (Legajo: " + inscripcion.getEstudiante().getLegajo() + ")");
        }
    }
}
