package dw.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instance;
    private Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/DWCOMUN";
    private static final String USER = "root";
    private static final String PASS = "";

    private Conexion() {
        connect();
    }

    public static synchronized Conexion getInstance() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connect ... true!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: Driver o Servicio de MySQL no encontrado");
            System.out.println("Error" + e.getMessage());
        }
    }
    
    public Connection getCon() {
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            System.err.println("Error verificando conexión: " + e.getMessage());
            connect();
        }
        return conn;
    }
    
    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connect ... false!");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

}
