package modelo;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import modelo.actividades.Curso;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private List<Actividad> actividades;

    static {
        cantidadEventos = 0;
        System.out.println("Inicializador estatico: Se cargo la clase modelo.EventoUniversitario. ");
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
            case "curso":
                System.out.print("Ingrese el nivel del curso: ");
                int nivel = scanner.nextInt();
                scanner.nextLine();

                Actividad curso = new Curso(id, titulo, nivel, cupo);
                this.actividades.add(curso);
                break;
            default:
                System.out.print("Error: tipo de actividad no reconocido");
        }
    }

    public List<Actividad> getActividades() {
        return Collections.unmodifiableList(actividades);
    }
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {

        List<T> resultado = new ArrayList<>();

        for (Actividad actividad : actividades) {

            if (tipo.isInstance(actividad)) {
                resultado.add(tipo.cast(actividad));
            }
        }

        return resultado;
    }
    public double calcularCostoMateriales(List<? extends Actividad> actividades) {
        double total = 0.0;

        for (Actividad actividad : actividades) {
            total += actividad.calcularCostoMateriales();
        }

        return total;
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

    public boolean persistirEvento() {
        // para que los datos no se pierdan cuando cerramos el programa
        ObjectOutputStream salida = null;
        // nos permite escribir objetos Java en un archivo
        try {
            //try por si ocurre un error lo  puedo capturar
            FileOutputStream archivo = new FileOutputStream("evento.dat");
            //creamos el archivo
            salida = new ObjectOutputStream(archivo);
            //conectamos el ObjectOutputStream con el archivo
            salida.writeObject(this);

            return true;
            /* todo salio bien se guardo */

        } catch (IOException e) {
            System.out.println("Error al guardar el evento: " + e.getMessage());
            return false;
            //no se logra guardar

        } finally {
            if (salida != null) {
                try {
                    salida.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el archivo: " + e.getMessage());
                    //por si al cerrarse ocurre un error
                }
            }
        }
    }

        public static EventoUniversitario recuperarEvento(String id) {
            ObjectInputStream entrada = null;
            try {
                FileInputStream archivo = new FileInputStream(id);
                //usamos ese id como nombre de archivo
                entrada = new ObjectInputStream(archivo);

                EventoUniversitario evento = (EventoUniversitario) entrada.readObject();
                //Lee el objeto que estaba guardando
                return evento;
            } catch (IOException e) {
                System.out.println("Error al recuperar el evento: " + e.getMessage());
                return null;
            } catch (ClassNotFoundException e) {
                System.out.println("No se encontro la clase del evento.");
                return null;
            } finally {
                if(entrada != null) {
                    try {
                        entrada.close();
                    } catch (IOException e) {
                        System.out.println("Error al cerrar el archivo: " + e.getMessage());
                    }
                }
            }
        }
    }

