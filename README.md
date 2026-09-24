TP1 - Programación Orientada a Objetos en Java

Descripción

Este proyecto implementa un sistema para gestionar eventos universitarios.

El sistema permite:

- Registrar estudiantes.
- Crear eventos universitarios.
- Asignar una sala a cada evento.
- Crear modelo.actividades asociadas a los eventos.
- Crear distintos tipos de modelo.actividades: `Charla` y `Taller`.
- Inscribir estudiantes en las modelo.actividades.
- Consultar las inscripciones realizadas.
- Calcular el costo estimado de un evento.
- Crear copias de eventos mediante un constructor de copia.
- Llevar un contador de la cantidad de eventos creados.

-------------------------------------------------------------------------------------------
Configuración inicial

Para clonar el proyecto y ejecutarlo localmente, seguí los siguientes pasos.

1. Clonar el repositorio

Creá o seleccioná una carpeta donde quieras guardar el proyecto.

Luego, abrí una terminal dentro de esa carpeta y ejecutá el siguiente comando:

git clone https://github.com/nadiheras777/PP_TP1_53388.git

2. Ingresar al proyecto

Una vez finalizada la clonación, ingresá a la carpeta del proyecto:

cd PP_TP1_53388

3. Abrir el proyecto en IntelliJ IDEA

Abrí la carpeta del proyecto PP_TP1_53388 utilizando IntelliJ IDEA.

Una vez abierto el proyecto:

Buscá la clase App.java.
Abrí el archivo.
Localizá el método main.
Ejecutá el método main para iniciar la aplicación.
Ejecución

Si la configuración se realizó correctamente, el proyecto debería ejecutarse desde IntelliJ IDEA sin inconvenientes.

Estructura del proyecto

El proyecto está compuesto por las siguientes clases:

- App
- modelo.EventoUniversitario
- modelo.Sala
- modelo.actividades.Actividad
- modelo.actividades.Charla
- modelo.actividades.Taller
- modelo.Estudiante
- modelo.Inscripcion

-------------------------------------------------------------------------------------------

Funcionamiento general

El programa comienza ejecutándose desde la clase App, que se encuentra ingresando:
    - Primero a la carpeta "PP_TP1_53388".
    - Luego ingresando a la carpeta "EJERCICIO1-Eventos".
    - Luego a la carpeta "src".

1. Registro de estudiantes

Primero se solicita al usuario la información de los estudiantes:

- Legajo.
- Nombre y apellido.

Cada estudiante se representa mediante un objeto de la clase modelo.Estudiante.
Los objetos creados se almacenan en una lista.

![RegistroEstudiantes](./images/RegistroAlumno.png)

2. Registro del evento

Luego se solicita al usuario informar la informacion del evento:
- Titulo.
- Costo base.
- Costo para los participantes.
- Asignacion de sala donde se realiza el evento.

![RegistroEvento](./images/RegistroEvento.png)


3. Registro de modelo.actividades

Luego permite ingresar las modelo.actividades del evento, diferenciando si se trata de una "modelo.actividades.Charla" o de un "modelo.actividades.Taller".
Asignando a cada caso su correspondiente costo e información.
- Titulo de la actividad.
- Cupo maximo de estudiantes.
- Tipo de actividad (modelo.actividades.Charla o taller).
    - En el caso de elegir taller, se pregunta si va a ser necesario el uso de NoteBook.
- Nombre de la charla/taller.

![RegistroActividad](./images/RegistroActividad.png)

4. Inscripción de estudiantes

Se insriben los estudiantes registrados anteriormente que deseen participar del evento, solicitando la siguiente informacion:
- Legajo de estudiante a inscribir.
- modelo.actividades.Actividad del evento a la que se desea inscribir.

![InscripcionEstudiante](./images/InscripcionEstudiante.png)

5. Datos del evento registrado

Se muestran los siguientes datos acerca del evento creado:
- Id del evento.
- Titulo.
- Costo.
- modelo.Sala asignada.
- Actividades registradas.
    - Nombre de la actividad.
    - Inscripciones registradas.

![DatosEvento](./images/DatosEvento.png)

6. Total eventos creados

El programa finaliza mostrando la cantidad de eventos creados.

![CantidadEventos](./images/CantidadEventosCreados.png)

EJERCICIO 4
![Ejercicio4](images/ejercicio4.png)