
package dw.servicios.impl;

import dw.modelos.dao.UsuarioDAO;
import dw.modelos.dao.impl.UsuarioMsql;
import dw.modelos.entidades.user.Usuario;
import java.util.List;


public class UsuarioService {
    private final UsuarioDAO usuarioDAO;
       
    public UsuarioService(){
        this.usuarioDAO = new UsuarioMsql();
    }
    
    public List<Usuario> obtenerTodosUsuarios(){
       return usuarioDAO.getAll();
    }
}
