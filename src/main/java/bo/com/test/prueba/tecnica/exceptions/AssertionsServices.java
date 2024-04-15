package bo.com.test.prueba.tecnica.exceptions;

public class AssertionsServices extends AssertionError{
    public static final String EL_CODIGO_DE_RESPUESTA_ES_DIFERENTE_AL_APROPIADO = "El codigo de respuesta es diferente al apropiado";
    
    private static final String ERROR = "Error desde la ";
    public AssertionsServices(String message, Throwable cause){
        super(message, cause);
    }
    public static String Error(String donde) {
        return ERROR + donde;
    }
}
