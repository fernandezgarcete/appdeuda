/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import dbConnection.CuentaDAO;
import dbConnection.EmpleadoDAO;
import entity.Cuenta;
import entity.Empleado;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Listar {
    private Object tipoObj = null;
    
    public List<Empleado> listEmpleado(){
        List<Empleado> empleados = EmpleadoDAO.listarEmpleados();
        return empleados;
    }
    
    public List<Cuenta> listCuenta(){
        List<Cuenta> cuentas = CuentaDAO.listarCuentas();
        return cuentas;
    }
    
    public List<Empleado> listarEmpleadoPor(String atributo){
        List<Empleado> empleados = EmpleadoDAO.listarEmpleadosPor(atributo);
        return empleados;
    }
    
    public List<Cuenta> listarCuentaPor(String atributo){
        List<Cuenta> cuentas = CuentaDAO.listarCuentasPor(atributo);
        return cuentas;
    }
}
