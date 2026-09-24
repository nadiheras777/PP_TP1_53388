import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class EventoUniversitario {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private List<Actividad> actividades;

    static {
        cantidadEventos = 0;
        System.out.println("Inicializador estatico: Se cargo la clase EventoUniversitario. ");
    }

    public EventoUniversitario(String id, String nombre, double costo, boolean esGratuito) {
        this.id = id;
        setTitulo(nombre);
        this.costoBase = costo;
        this.gratuito = esGratuito;
        cantidadEventos++;
        this.actividades = new ArrayList<>();
    }

    public EventoUniversitario(EventoUniversitario otroEvento) {
        this(otroEvento.id + "-COPIA",
                otroEvento.titulo,
                otroEvento.costoBase,
                otroEvento.gratuito);
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            this.titulo = nombre;
        }
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0.0;
        }
        double costoTotal = costoBase;

        for (Actividad actividad : actividades) {
            costoTotal += actividad.calcularCostoMateriales();
        }
        return costoTotal * 1.21;
    }

    public Sala getSala() {
        return sala;
    }

    public void asignarSala(Sala sala) {

        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, String tipoActividad, int cupo) {
        Scanner scanner = new Scanner(System.in);

        switch (tipoActividad) {
            case "charla":
                System.out.print("Ingrese el nombre disertante para la charla " + titulo + " : ");
                String disertante = scanner.nextLine();
                Actividad charla = new Charla(id, titulo, disertante, cupo);
                this.actividades.add(charla);
                break;
            case "taller":
                System.out.print("El taller " + titulo + " requiere el uso de Notebook? S/N ");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                boolean requiereNotebook = false;
                if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                    requiereNotebook = true;
                }
                Actividad taller = new Taller(id, titulo, requiereNotebook, cupo);
                this.actividades.add(taller);
                break;
            default:
                System.out.print("Error: tipo de actividad no reconocido");
        }
    }

    public List<Actividad> getActividades() {
        return Collections.unmodifiableList(actividades);
    }

    public void mostrarDatos() {
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Id= " + id);
        System.out.println("Titulo=  " + titulo);
        System.out.println("Costo= " + this.calcularCostoEstimado());
        System.out.println("Sala= " + (sala != null ? sala.getNombre() : "Sin sala"));
        System.out.println("Actividades: ");
        System.out.println("----------------------------------");

        for (Actividad actividad : actividades) {
            actividad.mostrarIdentificacion();
            actividad.mostrarInscripciones();
        }
        System.out.println("------------------------------------------------");

    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }
}
