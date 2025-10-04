package dw.servicios;

import dw.modelos.dao.UsuarioDAO;
import dw.modelos.entidades.user.Usuario;


public class AutentificacionService {
    private final UsuarioDAO userDao;
    
    public AutentificacionService(UsuarioDAO usuarioDAO){
        this.userDao = usuarioDAO;
    }
    
    public Usuario registrar(String email, String plainPass, String nombre, String apellidos){
        if(userDao.existEmail(email)){
            throw new RuntimeException("Email registrado");
        }
        
        String password = PasswordHasher.hash(plainPass);
        
        Usuario usuario = new Usuario(email, password, nombre, apellidos);
        
        return userDao.save(usuario);
    }}
