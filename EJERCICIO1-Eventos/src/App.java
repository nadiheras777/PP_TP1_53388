import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        int id = 1;

        // Construccion de lista de estudiantes
        List<Estudiante> estudiantes = new ArrayList<>();

        System.out.println("REGISTRO DE ESTUDIANTES: ");
        System.out.println("----------------------------------------- ");

        while (continuar) {
            System.out.println("Ingrese legajo del estudiante: ");
            String legajo = scanner.nextLine();
            System.out.println("Ingrese nombre y apellido del estudiante: ");
            String apenomb = scanner.nextLine();

            estudiantes.add(new Estudiante(legajo, apenomb));
            System.out.println("Desea crear otro estudiante? S/N: ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            continuar = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
        };

        //Se construyen eventos
        System.out.println("REGISTRO DE EVENTOS: ");
        System.out.println("----------------------------------------- ");
        continuar = true;

        while (continuar) {
            System.out.println("Ingrese el titulo del evento: ");
            String titulo = scanner.nextLine();
            System.out.println("Ingrese el costo base: ");
            double costoBase = scanner.nextDouble();
            scanner.nextLine(); //Limpia el enter
            System.out.println("El evento tendra costo para los participantes? S/N");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            boolean esGratuito = true;

            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                esGratuito = false;
            }

            EventoUniversitario evento = new EventoUniversitario("EVT-" + id, titulo, costoBase, esGratuito);

            // SE ASIGNEN SALAS AL EVENTO
            System.out.println("Ingrese nombre de la sala donde se realizara el evento: ");
            String nombreSala = scanner.nextLine();

            Sala sala = new Sala(id, nombreSala);
            evento.asignarSala(sala);

            // ACTIVIDADES DE CADA EVENTO
            System.out.println("REGISTRO DE ACTIVIDADES PARA EL EVENTO: " + evento.getTitulo());
            System.out.println("---------------------------------------------");
            continuar = true;
            int idActividad = 1;
            while (continuar) {
                System.out.println("Ingrese el titulo de la actividad: ");
                String tituloActividad = scanner.nextLine();
                System.out.println("Ingrese cupo maximo de estudiantes que pueden participar: ");
                int cupo = scanner.nextInt();
                scanner.nextLine();
                evento.crearActividad(idActividad, tituloActividad, cupo);

                System.out.println("Desea crear otra actividad para el evento " + evento.getTitulo() + "? S/N");
                respuesta = scanner.nextLine().trim().toLowerCase();
                continuar = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
                ++idActividad;
            }

            //MOSTRAR DATOS DE EVENTO
            System.out.println("DATOS DEL EVENTO");
            evento.mostrarDatos();

            System.out.println("Desea crear otro evento? S/N");
            respuesta = scanner.nextLine().trim().toLowerCase();
            continuar = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;

        }

        //TOTAL EVENTOS CREADOS
        System.out.println("TOTAL DE EVENTOS CREADOS: " + EventoUniversitario.getCantidadEventos());

    }
}
