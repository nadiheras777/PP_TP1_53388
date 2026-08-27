import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class EventoUniversitario {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private static List<Actividad> actividades;


    public EventoUniversitario(String id, String nombre, double costo, boolean esGratuito) {
        this.id = id;
        setTitulo(nombre);
        this.costoBase = costo;
        this.gratuito = esGratuito;
        cantidadEventos ++;
        this.actividades = new ArrayList<>();
    }
    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id + "-COPIA";
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.actividades = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.titulo = nombre;
        }
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public static List<Actividad> getActividades() {
        return Collections.unmodifiableList(actividades);
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }
        return costoBase *1.21;
    }
    public void asignarSala(Sala sala) {

        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, int cupoMax) {
        Actividad actividad = new Actividad(id, titulo, cupoMax);
        actividades.add(actividad);
    }

    public void mostrarDatos() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Id= " + id);
        System.out.println("Titulo=  " + titulo);
        System.out.println("Es gratuito= " + gratuito);
        System.out.println("Costo estimado= " + calcularCostoEstimado());
        System.out.println("Sala= " + sala);
        System.out.println("Actividades: " + getActividades());

    }

}
