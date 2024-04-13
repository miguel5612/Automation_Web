package bo.com.test.prueba.tecnica.utils;




import static bo.com.test.prueba.tecnica.utils.exceptions.ExceptionsConstants.CONSTANT_CLASS;

public class GeneralConstants {
    public static final String ACTOR = "client";
    //BASE DE DATOS
    public static final String ACTUALIZAR = "ACTUALIZAR";
    public static final String AGREGAR = "AGREGAR";
    public static final String ELIMINAR = "ELIMINAR";
    public static final String OBTENER_POR_ID = "OBTENER_POR_ID";
    public static final String OBTENER_TODOS = "OBTENER_TODOS";
    public static final String PERSONALIZADO = "PERSONALIZADO";
    public static final String PERSONALIZADO_VARIOS = "PERSONALIZADO_TODOS";
    public static final String PROCEDIMIENTOS_ALMACENADOS = "PROCEDIMIENTOS_ALMACENADOS";

    public static final String VARCHAR = "VARCHAR";
    public static final String INTEGER = "INTEGER";
    public static final String DOUBLE = "DOUBLE";
    public static final String REFCURSOR = "CURSOR";


 private GeneralConstants() {
            throw new IllegalStateException(CONSTANT_CLASS);
        }
}