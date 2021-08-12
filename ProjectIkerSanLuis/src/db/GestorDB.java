package db;

import java.sql.Statement;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class GestorDB {
    
	private static GestorDB instancia = new GestorDB();
    
	private Connection conn = null;
    private String server = "127.0.0.1";
    private String nombreDB = "sanluis";
    /* A rellenar
    /**/ private String username = "";
    /**/ private String password = "";
    
    private GestorDB() {
        this.conOpen();
    }
    
    public static GestorDB getGestorDB() {
        return instancia;
    }
    
    private void conOpen() {

        try {
        	
        	Class.forName("com.mysql.cj.jdbc.Driver"); //Inicializar el driver
	 		String newConnectionURL = "jdbc:mysql://" + server + "/" + nombreDB + "?" + "user=" + username + "&password=" + password;
	 		conn = DriverManager.getConnection(newConnectionURL);

        } catch (SQLException ex) {            
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    public ResultSet execSQL(String query) {
        int count = 0;
        Statement s = null;
        ResultSet rs = null;
        try {
            s = (Statement) conn.createStatement();
            if (query.toLowerCase().indexOf("select") == 0) {
                // select agindu bat
                rs = this.query(s, query);
            } else {
                // update, delete, create agindu bat
                count = s.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    private ResultSet query(Statement s, String query) {

        ResultSet rs = null;

        try {
            s.executeQuery(query);
            rs = s.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
    public ResultSet exeqSQLExc(String query) throws SQLException, SQLIntegrityConstraintViolationException {
        int count = 0;
        Statement s = null;
        ResultSet rs = null;
        s = (Statement) conn.createStatement();
        if (query.toLowerCase().indexOf("select") == 0) {
            // select agindu bat
            rs = this.query(s, query);
        } else {
            // update, delete, create agindu bat
            count = s.executeUpdate(query);
        }
        return rs;
    }
}
