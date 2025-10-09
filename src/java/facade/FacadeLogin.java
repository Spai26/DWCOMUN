package facade;

import dw.modelos.dto.LoginDTO;
import dw.modelos.entidades.user.Usuario;
import dw.servicios.AutentificacionService;
import java.util.Optional;

public class FacadeLogin {

    public Optional<Usuario> run(LoginDTO loginDTO) {
        try {
            if (loginDTO == null || !loginDTO.isValid()) {
                return Optional.empty();
            }

            String tipo = loginDTO.getTypeEntry();
            if ("INVALID".equals(tipo)) {
                return Optional.empty();
            }

            AutentificacionService authService = new AutentificacionService();
            Optional<Usuario> usuarioOpt;

            // Buscar por DNI o Email según corresponda
            if ("dni".equals(tipo)) {
                usuarioOpt = authService.loginDni(loginDTO.getUsuarioClean(), loginDTO.getPassword());
            } else {
                usuarioOpt = authService.loginEmail(loginDTO.getUsuarioClean(), loginDTO.getPassword());
            }

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                

                // Verificar si el usuario está activo
                if (!authService.canAccess(usuario)) {
                    return Optional.empty();
                }

            } else {
                System.out.println("❌ Credenciales incorrectas");
            }

            return usuarioOpt;

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
