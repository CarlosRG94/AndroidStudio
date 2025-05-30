package modelo;

@SuppressWarnings("serial")
public class ServidorPHPException extends Exception
{
    /**
     * Constructor de la clase
     * @param message Mensaje
     */
    public ServidorPHPException(String message)
    {
        super(message);
    }
}