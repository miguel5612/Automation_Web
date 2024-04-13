package bo.com.test.prueba.tecnica.utils.database;

import bo.com.test.prueba.tecnica.utils.GeneralConstants;
import bo.com.test.prueba.tecnica.utils.PropertiesReader;
import oracle.jdbc.internal.OracleTypes;

import java.sql.*;
import java.util.*;

public class ConexionDAOV2 {

    private ConexionDAO conexion;
    private String username;
    private String password;
    public ConexionDAOV2(String schema) throws SQLException {
        if (schema.equals("B")){
            username = PropertiesReader.getInstance().getProperties("userBDatabase");
            password = PropertiesReader.getInstance().getProperties("passwordBDatabase");
        }else if(schema.equals("AUTOSRV")){
            username = PropertiesReader.getInstance().getProperties("userAutoservDatabase");
            password = PropertiesReader.getInstance().getProperties("passwordAutoservDatabase");
        }else if(schema.equals("C")){
            username = PropertiesReader.getInstance().getProperties("userBDatabase");
            password = PropertiesReader.getInstance().getProperties("passworBDatabase");
        }
         conexion = new ConexionDAO(username,password);
    }

    public void agregar(String tabla, Map<String, Object> valores) throws SQLException {
        String columnas = String.join(", ", valores.keySet());
        String parametros = String.join(", ", Collections.nCopies(valores.size(), "?"));

        String sql = "INSERT INTO " + tabla + " (" + columnas + ") VALUES (" + parametros + ")";

        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            int i = 1;
            for (Object valor : valores.values()) {
                stmt.setObject(i++, valor);
            }
            stmt.executeUpdate();
        }
        conexion.cerrarConexion();
    }
    public Map<String, Object> personalizado(String query) throws SQLException {
        Map<String, Object> respuesta = new HashMap<>();
        String queryUpperCase= query.toUpperCase();
        if(queryUpperCase.contains("INSERT") || queryUpperCase.contains("DELETE") || queryUpperCase.contains("UPDATE")){
            try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
                stmt.executeUpdate();
                respuesta.put("Resultado del query", "Exitoso");
                conexion.cerrarConexion();
                return respuesta;
            }
        }
        if(queryUpperCase.contains("SELECT")){
            try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Map<String, Object> valores = new HashMap<>();
                        ResultSetMetaData metadata = rs.getMetaData();
                        int columnas = metadata.getColumnCount();
                        for (int j = 1; j <= columnas; j++) {
                            String nombreColumna = metadata.getColumnName(j);
                            Object valorColumna = rs.getObject(j);
                            valores.put(nombreColumna, valorColumna);
                        }
                        conexion.cerrarConexion();
                        return valores;
                    } else {
                        return null;
                    }
                }
            }
        }
        respuesta.put("Resultado del query", "Error");
        conexion.cerrarConexion();
        return respuesta;
    }
    public List<Map<String, Object>> procedimientoAlmacenado(String query, List<Object> parametrosEntrada, List<Object> parametrosSalida) throws SQLException {
        try (CallableStatement stmt = conexion.getConnection().prepareCall(query)) {
            // Configurar parámetros de entrada
            int indice = 1;
            for (Object parametroE : parametrosEntrada) {
                if (parametroE instanceof Integer) {
                    stmt.setInt(indice, (Integer) parametroE);
                } else if (parametroE instanceof Double) {
                    stmt.setDouble(indice, (Double) parametroE);
                } else if (parametroE instanceof String) {
                    stmt.setString(indice, (String) parametroE);
                }
                indice++;
            }
            // Registrar parámetros de salida
            for (int i = 0; i < parametrosSalida.size(); i++) {
                Object parametroS = parametrosSalida.get(i);
                if (parametroS == null) {
                    stmt.registerOutParameter(indice, Types.VARCHAR);
                } else if (parametroS.equals(GeneralConstants.INTEGER)) {
                    stmt.registerOutParameter(indice, Types.INTEGER);
                } else if (parametroS.equals(GeneralConstants.DOUBLE)) {
                    stmt.registerOutParameter(indice, Types.DOUBLE);
                } else if (parametroS.equals(GeneralConstants.VARCHAR)) {
                    stmt.registerOutParameter(indice, Types.VARCHAR);
                } else if (parametroS.equals(GeneralConstants.REFCURSOR)) {
                    stmt.registerOutParameter(indice, OracleTypes.CURSOR);
                }
                indice++;
            }
            stmt.execute();

            // Obtener valores de los parámetros de salida
            List<Map<String, Object>> resultados = new ArrayList<>();
            Map<String, Object> valores = new HashMap<>();
            for (int i = 0; i < parametrosSalida.size(); i++) {
                Object parametroS = parametrosSalida.get(i);
                if (parametroS.equals(GeneralConstants.REFCURSOR)) {
                    ResultSet rs = (ResultSet) stmt.getObject(i + parametrosEntrada.size() + 1);
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnas = metadata.getColumnCount();
                    while (rs.next()) {
                        for (int a = 1; a <= columnas; a++) {
                            String nombreColumna = metadata.getColumnName(a);
                            Object valorColumna = rs.getObject(a);
                            valores.put(nombreColumna, valorColumna);
                        }
                        resultados.add(valores);
                    }
                } else {
                    Object valorSalida = stmt.getObject(i + parametrosEntrada.size() + 1);
                    valores.put("parametro" + (i + 1), valorSalida);
                    resultados.add(0,valores);
                }
            }

            conexion.cerrarConexion();
            return resultados;
        }
    }
    public List<Map<String, Object>> obtenerTodosPersonalizado(String query) throws SQLException {
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            List<Map<String, Object>> resultados = new ArrayList<>();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnas = metadata.getColumnCount();
            while (rs.next()) {
                Map<String, Object> valores = new HashMap<>();
                for (int i = 1; i <= columnas; i++) {
                    String nombreColumna = metadata.getColumnName(i);
                    Object valorColumna = rs.getObject(i);
                    valores.put(nombreColumna, valorColumna);
                }
                resultados.add(valores);
            }
            conexion.cerrarConexion();
            return resultados;
        }
    }
    public Map<String, Object> obtenerPorId(String tabla,Map<String, Object> condiciones) throws SQLException {
        String condicionesString = "";
        int i = 1;
        for (String columna : condiciones.keySet()) {
            if (i > 1) {
                condicionesString += " AND ";
            }
            condicionesString += columna + " = ?";
            i++;
        }
        String sql = "SELECT * FROM " + tabla + " WHERE " + condicionesString;
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            i = 1;
            for (Object valor : condiciones.values()) {
                stmt.setObject(i++, valor);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Map<String, Object> valores = new HashMap<>();
                    ResultSetMetaData metadata = rs.getMetaData();
                    int columnas = metadata.getColumnCount();
                    for (int j = 1; j <= columnas; j++) {
                        String nombreColumna = metadata.getColumnName(j);
                        Object valorColumna = rs.getObject(j);
                        valores.put(nombreColumna, valorColumna);
                    }
                    conexion.cerrarConexion();
                    return valores;
                } else {
                    conexion.cerrarConexion();
                    return null;
                }
            }
        }
    }

    public List<Map<String, Object>> obtenerTodos(String tabla) throws SQLException {
        String sql = "SELECT * FROM " + tabla;
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<Map<String, Object>> resultados = new ArrayList<>();
            ResultSetMetaData metadata = rs.getMetaData();
            int columnas = metadata.getColumnCount();
            while (rs.next()) {
                Map<String, Object> valores = new HashMap<>();
                for (int i = 1; i <= columnas; i++) {
                    String nombreColumna = metadata.getColumnName(i);
                    Object valorColumna = rs.getObject(i);
                    valores.put(nombreColumna, valorColumna);
                }
                resultados.add(valores);
            }
            conexion.cerrarConexion();
            return resultados;
        }
    }

    public void actualizar(String tabla, Map<String, Object> condiciones, Map<String, Object> valores) throws SQLException {
        String columnas = String.join(" = ?, ", valores.keySet()) + " = ?";
        String condicionesString = "";
        int i = 1;
        for (String columna : condiciones.keySet()) {
            if (i > 1) {
                condicionesString += " AND ";
            }
            condicionesString += columna + " = ?";
            i++;
        }
        String sql = "UPDATE " + tabla + " SET " + columnas + " WHERE "+ condicionesString;
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            i = 1;
            for (Object valor : valores.values()) {
                stmt.setObject(i++, valor);
            }
            for (Object valor : condiciones.values()) {
                stmt.setObject(i++, valor);
            }
            stmt.executeUpdate();
        }
        conexion.cerrarConexion();
    }

    public void eliminar(String tabla,Map<String, Object> condiciones) throws SQLException {
        String condicionesString = "";
        int i = 1;
        for (String columna : condiciones.keySet()) {
            if (i > 1) {
                condicionesString += " AND ";
            }
            condicionesString += columna + " = ?";
            i++;
        }
        String sql = "DELETE FROM " + tabla + " WHERE "+condicionesString;
        try (PreparedStatement stmt = conexion.getConnection().prepareStatement(sql)) {
            i = 1;
            for (Object valor : condiciones.values()) {
                stmt.setObject(i++, valor);
            }
            stmt.executeUpdate();
        }
        conexion.cerrarConexion();
    }
}