package dw.modelos.dao;

import dw.conexion.Conexion;
import dw.modelos.dao.impl.UsuarioMsql;

public class DAOFactory {
    private static DAOFactory instance;
    private final Conexion conexion;
    
    
    private DAOFactory(){
        this.conexion = Conexion.getInstance();
    }
    
    public static synchronized DAOFactory getInstance(){
        if(instance == null){ 
            instance = new DAOFactory();
        }
        return instance;
    }
    
    public UsuarioDAO getUsuarios(){
        return new UsuarioMsql();
    }
    
}
