import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.actividades.Actividad;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.certificacion.Certificable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        /* Esto era del ejercicio 1
        modelo.EventoUniversitario evento1 = new modelo.EventoUniversitario(
                "Evento1", "Fiestaegresados", 500,false
        );
       modelo.EventoUniversitario copia1 = new modelo.EventoUniversitario(evento1);

        evento1.mostrarDatos();
        copia1.mostrarDatos();

        modelo.EventoUniversitario evento2 = new modelo.EventoUniversitario(
                "Evento1", "modelo.actividades.Charla", 0,true
        );
        modelo.EventoUniversitario copia2 = new modelo.EventoUniversitario(evento2);

        evento2.mostrarDatos();
        copia2.mostrarDatos();

        System.out.println("Eventos creados: " + modelo.EventoUniversitario.getCantidadEventos());
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

            // Asignar modelo.Sala
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

                System.out.print("La actividad es una charla, un taller o un curso?: ");
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
                                try {
                                    act.inscribir(est);
                                    asignado = true;
                                    System.out.println("Estudiante inscripto con exito.");
                                } catch (CupoExcedidoException e) {
                                    System.out.println("ERROR: " + e.getMessage());
                                    asignado = true;
                                }
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
            // Generar certificado
            System.out.println("\n--- GENERACION DE CERTIFICADO ---");

            System.out.print("Ingrese el legajo del estudiante: ");
            String legajoCertificado = scanner.nextLine().trim();

            System.out.print("Ingrese el numero de la actividad: ");
            int idActividadCertificado = scanner.nextInt();
            scanner.nextLine();

            for (Estudiante est : estudiantes) {

                if (est.getLegajo().equalsIgnoreCase(legajoCertificado)) {

                    for (Actividad act : evento.getActividades()) {

                        if (act.getId() == idActividadCertificado) {

                            if (act instanceof Certificable) {

                                Certificable certificable = (Certificable) act;

                                System.out.println(
                                        "\n" + certificable.generarCertificado(est)
                                );

                            } else {

                                System.out.println(
                                        "La actividad seleccionada no emite certificados."
                                );
                            }

                            break;
                        }
                    }
                }
            }
            // Filtrar actividades por tipo
            System.out.println("\n--- FILTRADO DE ACTIVIDADES POR TIPO ---");

            List<Actividad> actividades = evento.getActividades();

            List<modelo.actividades.Taller> talleres =
                    evento.filtrarActividadesPorTipo(modelo.actividades.Taller.class);

            List<modelo.actividades.Curso> cursos =
                    evento.filtrarActividadesPorTipo(modelo.actividades.Curso.class);

            List<modelo.actividades.Charla> charlas =
                    evento.filtrarActividadesPorTipo(modelo.actividades.Charla.class);

            System.out.println("Cantidad de talleres: " + talleres.size());
            System.out.println("Cantidad de cursos: " + cursos.size());
            System.out.println("Cantidad de charlas: " + charlas.size());


// Calcular costo de materiales
            System.out.println("\n--- COSTO DE MATERIALES ---");

            double costoTalleres = evento.calcularCostoMateriales(talleres);
            double costoCursos = evento.calcularCostoMateriales(cursos);
            double costoTotal = evento.calcularCostoMateriales(actividades);

            System.out.println("Costo de materiales de talleres: $" + costoTalleres);
            System.out.println("Costo de materiales de cursos: $" + costoCursos);
            System.out.println("Costo total de materiales: $" + costoTotal);
            try {
                if (evento.persistirEvento()) {
                    System.out.println("-> Evento guardado correctamente.");
                } else {
                    System.out.println("-> No se pudo guardar el evento.");
                }

                EventoUniversitario eventoRecuperado = EventoUniversitario.recuperarEvento("evento.dat");

                if (eventoRecuperado != null) {
                    System.out.println("-> Evento recuperado correctamente.");
                } else {
                    System.out.println("-> No se pudo recuperar el evento.");
                }

            } catch (Exception e) {
                System.out.println("-> Error durante la persistencia: " + e.getMessage());

            } finally {
                System.out.println("-> Proceso de persistencia finalizado.");
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