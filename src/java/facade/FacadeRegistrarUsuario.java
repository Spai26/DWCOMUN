package facade;

import dw.modelos.dto.RegistrarDTO;
import dw.modelos.entidades.user.Usuario;
import dw.servicios.AutentificacionService;
import utils.valuesObjects.AsignRol;

public class FacadeRegistrarUsuario {

    public boolean run(RegistrarDTO newUsuario) {

        if (newUsuario == null || !newUsuario.isValid()) {
            throw new IllegalArgumentException("Datos de registro inválidos");
        }

        AutentificacionService auth = new AutentificacionService();
        AsignRol rol = newUsuario.getRol() != null ? newUsuario.getRol() : AsignRol.PACIENTE;

        Usuario usuarioRegistrado = auth.registrarConRol(
                newUsuario.getEmail(),
                newUsuario.getPassword(),
                newUsuario.getDni(),
                newUsuario.getNombres(),
                newUsuario.getApellidos(),
                rol
        );

        return usuarioRegistrado != null;

    }
}
