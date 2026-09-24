package excepciones;

public class CupoExcedidoException extends Exception {

    //Constructor
        public CupoExcedidoException(String mensaje) {
            super(mensaje);
            //Super para que llame al constructor del padre (Exception)

        }
}
