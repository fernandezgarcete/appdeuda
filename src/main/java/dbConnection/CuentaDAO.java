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
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import entity.Cuenta;
import entity.Empleado;

/**
 *
 * @author Administrador
 */
public class CuentaDAO {
    public static Cuenta getCuentaByEmpleado(String Empleado){
        Cuenta cuentaBean = null;
        Empleado empleadoBean = null;
        try {
            Statement stmtConsulta = AdminConexion.getConnection().createStatement();
            ResultSet rs = stmtConsulta.executeQuery("SELECT id FROM empleado WHERE nombre = '"+Empleado+"';");
            
            if (rs.next()) {
                empleadoBean = new Empleado();
                empleadoBean.setId(rs.getInt("id"));
                empleadoBean.setNombre(rs.getString("nombre"));
            }
            
            rs = stmtConsulta.executeQuery("SELECT * FROM cuenta WHERE empleado = "+empleadoBean.getId());
            
            if (rs.next()) {
                cuentaBean = new Cuenta();
                cuentaBean.setId(rs.getInt("id"));
                cuentaBean.setEmpleado(rs.getInt("empleado"));
                cuentaBean.setMontoDeuda(rs.getFloat("montodeuda"));
                cuentaBean.setAquien(rs.getInt("aquien"));
                cuentaBean.setEstado(rs.getString("estado"));
                cuentaBean.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return cuentaBean;
    }
    
    public static void insertarCuenta(Cuenta cuentaBean){
        try {
            PreparedStatement preparedStmt = AdminConexion.getConnection().prepareStatement(
                "INSERT INTO cuenta(empleado,montodeuda,aquien,estado,fecha) VALUES (?,?,?,?,?)");
            
            preparedStmt.setInt(1, cuentaBean.getEmpleado());
            preparedStmt.setFloat(2, cuentaBean.getMontoDeuda());
            preparedStmt.setInt(3, cuentaBean.getAquien());
            preparedStmt.setString(4, cuentaBean.getEstado());
            preparedStmt.setDate(5, (Date) cuentaBean.getFecha());
            
            preparedStmt.execute();
            preparedStmt.close();
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    public static void borrarCuenta(int id){
        try {
            Connection conn = AdminConexion.getConnection();
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM cuenta WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.execute();
            pstmt.close();
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    public static void modificarCuenta(Cuenta cuentaBean){
        try {

            PreparedStatement preparedStatement = AdminConexion.getConnection().prepareStatement(
                    "UPDATE cuenta SET empleado=?, montodeuda=?, aquien=?, estado=?, fecha=? WHERE id=?");

            preparedStatement.setInt(1, cuentaBean.getEmpleado());
            preparedStatement.setFloat(2, cuentaBean.getMontoDeuda());
            preparedStatement.setInt(3, cuentaBean.getAquien());
            preparedStatement.setString(4, cuentaBean.getEstado());
            preparedStatement.setDate(5, (Date) cuentaBean.getFecha());
            preparedStatement.setLong(6, cuentaBean.getId());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    public static List<Cuenta> listarCuentas(){
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        
        try {
            Statement stmt = AdminConexion.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cuenta");
            
            while (rs.next()) {
                Cuenta cuentaBean = new Cuenta();
                cuentaBean.setId(rs.getInt("id"));
                cuentaBean.setEmpleado(rs.getInt("empleado"));
                cuentaBean.setMontoDeuda(rs.getFloat("montodeuda"));
                cuentaBean.setAquien(rs.getInt("aquien"));
                cuentaBean.setEstado(rs.getString("estado"));
                
                cuentas.add(cuentaBean);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        return cuentas;
    }
    
    public static List<Cuenta> listarCuentasPor(String atributo){
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        
        try {
            Statement stmtConsulta = AdminConexion.getConnection().createStatement();
            ResultSet rs = stmtConsulta.executeQuery("SELECT * FROM cuenta ORDER BY "+ atributo);


            while (rs.next()) {
                // Arma el objeto auto con los datos de la consulta
                Cuenta cuentaBean = new Cuenta();
                cuentaBean.setId(rs.getInt("id"));
                cuentaBean.setEmpleado(rs.getInt("empleado"));
                cuentaBean.setMontoDeuda(rs.getFloat("montodeuda"));
                cuentaBean.setAquien(rs.getInt("aquien"));
                cuentaBean.setEstado(rs.getString("estado"));
                cuentaBean.setFecha(rs.getDate("fecha"));

                cuentas.add(cuentaBean);
            }

            // Cierra el Statement
            stmtConsulta.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        
        return cuentas;
    }
}
