import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /* Esto era del ejercicio 1
        EventoUniversitario evento1 = new EventoUniversitario(
                "Evento1", "Fiestaegresados", 500,false
        );
       EventoUniversitario copia1 = new EventoUniversitario(evento1);

        evento1.mostrarDatos();
        copia1.mostrarDatos();

        EventoUniversitario evento2 = new EventoUniversitario(
                "Evento1", "Charla", 0,true
        );
        EventoUniversitario copia2 = new EventoUniversitario(evento2);

        evento2.mostrarDatos();
        copia2.mostrarDatos();

        System.out.println("Eventos creados: " + EventoUniversitario.getCantidadEventos());
         */
                Scanner scanner = new Scanner(System.in);
                int idEvento = 1;

                // 1. REGISTRO DE ESTUDIANTES
                List<Estudiante> estudiantes = new ArrayList<>();
                System.out.println("REGISTRO DE ESTUDIANTES:");
                System.out.println("-----------------------------------------");

                boolean continuar = true;
                while (continuar) {
                    System.out.print("Ingrese legajo del estudiante: ");
                    String legajo = scanner.nextLine().trim();
                    System.out.print("Ingrese nombre y apellido del estudiante: ");
                    String apenomb = scanner.nextLine().trim();

                    estudiantes.add(new Estudiante(legajo, apenomb));
                    System.out.print("Desea crear otro estudiante? S/N: ");
                    String respuesta = scanner.nextLine().trim().toLowerCase();
                    continuar = respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");
                }

                // 2. REGISTRO DE EVENTOS
                continuar = true;
                while (continuar) {
                    System.out.println("\n=========================================");
                    System.out.println("REGISTRO DE EVENTO #" + idEvento);
                    System.out.println("=========================================");

                    System.out.print("Ingrese el titulo del evento: ");
                    String titulo = scanner.nextLine().trim();
                    System.out.print("Ingrese el costo base: ");
                    double costoBase = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer

                    System.out.print("El evento tendra costo para los participantes? S/N: ");
                    String respCosto = scanner.nextLine().trim().toLowerCase();
                    boolean esGratuito = !respCosto.equals("s") && !respCosto.equals("si") && !respCosto.equals("sí");

                    // Crear Evento
                    EventoUniversitario evento = new EventoUniversitario("EVT-" + idEvento, titulo, costoBase, esGratuito);

                    // Asignar Sala
                    System.out.print("Ingrese nombre de la sala donde se realizara el evento: ");
                    String nombreSala = scanner.nextLine().trim();
                    Sala sala = new Sala(idEvento, nombreSala);
                    evento.asignarSala(sala);

                    // Registrar Actividades del Evento
                    System.out.println("\n--- REGISTRO DE ACTIVIDADES ---");
                    boolean continuarActividades = true;
                    int idActividad = 1;

                    while (continuarActividades) {
                        System.out.print("Ingrese el titulo de la actividad: ");
                        String tituloActividad = scanner.nextLine().trim();
                        System.out.print("Ingrese cupo maximo de estudiantes: ");
                        int cupo = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        System.out.print("La actividad es una charla o un taller?: ");
                        String tipo = scanner.nextLine().trim().toLowerCase();

                        evento.crearActividad(idActividad, tituloActividad, tipo, cupo);

                        System.out.print("Desea crear otra actividad para este evento? S/N: ");
                        String respAct = scanner.nextLine().trim().toLowerCase();
                        continuarActividades = respAct.equals("s") || respAct.equals("si") || respAct.equals("sí");
                        idActividad++;
                    }

                    // Inscribir Estudiantes
                    System.out.println("\n--- INSCRIPCION DE ESTUDIANTES ---");
                    boolean continuarInscripcion = true;

                    while (continuarInscripcion) {
                        System.out.print("Ingrese el legajo del estudiante a inscribir: ");
                        String legajoBuscado = scanner.nextLine().trim();
                        System.out.print("Ingrese el numero de la actividad (ej: 1, 2...): ");
                        int idActBuscada = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer

                        boolean asignado = false;
                        for (Estudiante est : estudiantes) {
                            if (est.getLegajo().equalsIgnoreCase(legajoBuscado)) {
                                for (Actividad act : evento.getActividades()) {
                                    if (act.getId() == idActBuscada) {
                                        act.inscribir(est);
                                        asignado = true;
                                        System.out.println("-> Estudiante inscripto con exito.");
                                        break;
                                    }
                                }
                            }
                        }

                        if (!asignado) {
                            System.out.println("-> No se pudo encontrar el estudiante con ese legajo o la actividad con ese ID.");
                        }

                        System.out.print("Desea generar otra inscripcion en este evento? S/N: ");
                        String respInsc = scanner.nextLine().trim().toLowerCase();
                        continuarInscripcion = respInsc.equals("s") || respInsc.equals("si") || respInsc.equals("sí");
                    }

                    // Muestra los datos actualizados del evento recién creado
                    System.out.println("\nDATOS DEL EVENTO REGISTRADO:");
                    evento.mostrarDatos();

                    // Incrementar contador de eventos para la proxima iteracion
                    idEvento++;

                    System.out.print("\nDesea crear otro evento general? S/N: ");
                    String respEvt = scanner.nextLine().trim().toLowerCase();
                    continuar = respEvt.equals("s") || respEvt.equals("si") || respEvt.equals("sí");
                }

                // Resumen final
                System.out.println("\n=========================================");
                System.out.println("TOTAL DE EVENTOS CREADOS: " + EventoUniversitario.getCantidadEventos());
                System.out.println("=========================================");
            }
        }