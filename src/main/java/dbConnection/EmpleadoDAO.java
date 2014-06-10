/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbConnection;

import dbConnection.AdminConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import entity.Empleado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class EmpleadoDAO {
    public static void insertarEmpleado(Empleado empleadoBean){
        try {
            PreparedStatement preparedStmt = AdminConexion.getConnection().prepareStatement(
                "INSERT INTO empleado(nombre) VALUES (?)");
            
            preparedStmt.setString(1, empleadoBean.getNombre());
                        
            preparedStmt.execute();
            preparedStmt.close();
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    public static void borrarEmpleado(long id) {
        try {
            Connection conn=AdminConexion.getConnection();
            PreparedStatement psmt = conn.prepareStatement("DELETE FROM empleado WHERE id =?");
            psmt.setLong(1, id);
            psmt.execute();
            psmt.close();
            
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    public static void modificarEmpleado(Empleado empleadoBean) {
        try {
            PreparedStatement preparedStatement = AdminConexion.getConnection().prepareStatement(
                    "UPDATE empleado SET nombre=? WHERE id=?");

            preparedStatement.setString(1, empleadoBean.getNombre());
            preparedStatement.setLong(2, empleadoBean.getId());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    public static List<Empleado> listarEmpleados(){
    List<Empleado> empleados = new ArrayList<Empleado>();
    
        try {
            Statement stmt = AdminConexion.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleado");
            
            while (rs.next()) {
                Empleado empleadoBean = new Empleado();
                empleadoBean.setId(rs.getInt("id"));
                empleadoBean.setNombre(rs.getString("nombre"));
                
                empleados.add(empleadoBean);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
    return empleados;
    }
    
    public static List<Empleado> listarEmpleadosPor(String atributo){
        List<Empleado> empleados = new ArrayList<Empleado>();
    
        try {
            Statement stmtConsulta = AdminConexion.getConnection().createStatement();
            ResultSet rs = stmtConsulta.executeQuery("SELECT * FROM empleado ORDER BY "+ atributo);


            while (rs.next()) {
                // Arma el objeto auto con los datos de la consulta
                Empleado empleadoBean = new Empleado();
                empleadoBean.setId(rs.getInt("id"));
                empleadoBean.setNombre(rs.getString("nombre"));

                empleados.add(empleadoBean);
            }

            // Cierra el Statement
            stmtConsulta.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    
    return empleados;
    }
}
