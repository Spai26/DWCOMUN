package dw.servicios;

import dw.modelos.entidades.user.Usuario;
import dw.servicios.impl.UsuarioService;
import java.util.Optional;
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

    public Optional<Usuario> loginDni(String dni, String plainPassword) {
        Optional<Usuario> usuarioOpt = userService.existeDNI(dni);

        if (userService.verificarPassword(usuarioOpt, plainPassword)) {
            return usuarioOpt;
        }

        return Optional.empty();
    }

    public Optional<Usuario> loginEmail(String email, String plainPassword) {
        Optional<Usuario> opt = userService.existeEmail(email);

        if (userService.verificarPassword(opt, plainPassword)) {
            return opt;
        }

        return Optional.empty();
    }

    public boolean canAccess(Usuario usuario) {
        return usuario != null && usuario.isActive();
    }
}
