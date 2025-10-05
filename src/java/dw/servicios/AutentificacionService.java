package dw.servicios;

import dw.modelos.entidades.user.Usuario;
import dw.servicios.impl.UsuarioService;
import utils.valuesObjects.AsignRol;

public class AutentificacionService {

    private final UsuarioService userService;

    public AutentificacionService() {
        this.userService = new UsuarioService();
    }

    // Registro de usuario con rol definido
    public Usuario registrarConRol(String email, String plainPass, String dni, String nombre, String apellidos, AsignRol rol) {
        return userService.crearUsuario(email, plainPass, dni, nombre, apellidos, rol);
    }

    public boolean canAccess(Usuario usuario) {
        return usuario != null && usuario.isActive();
    }
}
