/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrador
 */
public class AdminConexion {
    
    public AdminConexion() {
    }
    
    public static Connection getConnection() throws Exception{
        // Establece el nombre del driver a utilizar
       String dbDriver = "org.postgresql.Driver";

        // Establece la conexion a utilizar contra la base de datos
        String dbConnString = "jdbc:postgresql://127.0.0.1:5432/abm";
        
        // Establece el usuario de la base de datos
        String dbUser = "apollo";
        
        // Establece la contraseña de la base de datos
        String dbPassword = "Apollo2000";
        
        // Establece el driver de conexion
        Class.forName(dbDriver).newInstance();
        
        // Retorna la conexion
        return DriverManager.getConnection(dbConnString, dbUser, dbPassword);
    }
}
