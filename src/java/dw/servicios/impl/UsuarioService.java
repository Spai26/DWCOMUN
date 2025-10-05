package dw.servicios.impl;

import dw.modelos.dao.UsuarioDAO;
import dw.modelos.dao.impl.UsuarioMsql;
import dw.modelos.entidades.user.Usuario;
import dw.servicios.PasswordHasher;
import java.util.List;
import java.util.Optional;
import utils.valuesObjects.AsignRol;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final PasswordHasher passwordService;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioMsql();
        this.passwordService = new PasswordHasher();
    }

    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioDAO.getAll();
    }

    public Usuario crearUsuario(String email, String plainPassword, String dni,
            String nombres, String apellidos, AsignRol rol) {

        if (existeEmail(email).isPresent() || existeDNI(dni).isPresent()) {
            throw new RuntimeException("Usuario no valido para registro.");
        }

        String hashPassword = passwordService.hash(plainPassword);
        Usuario usuario = new Usuario(email, hashPassword, dni, nombres, apellidos, rol);

        /**
         * !Falta mapperlo para evitar retornar la contraseña
         */
        return usuarioDAO.save(usuario);
    }

    public Optional<Usuario> existeEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    public Optional<Usuario> existeDNI(String dni) {
        return usuarioDAO.findByDni(dni);
    }
}
