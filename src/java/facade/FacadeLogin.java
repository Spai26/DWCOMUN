package facade;

import dw.modelos.dto.LoginDTO;
import dw.modelos.entidades.user.Usuario;
import dw.servicios.AutentificacionService;
import java.util.Optional;

public class FacadeLogin {

    public Optional<Usuario> run(LoginDTO loginDTO) {
        try {
            System.out.println("=== INICIANDO LOGIN ===");
            System.out.println("Usuario ingresado: " + loginDTO.getUsuario());
            System.out.println("Tipo de entrada: " + loginDTO.getTypeEntry());

            if (loginDTO == null || !loginDTO.isValid()) {
                System.out.println("❌ Datos de login inválidos");
                return Optional.empty();
            }

            String tipo = loginDTO.getTypeEntry();
            if ("INVALID".equals(tipo)) {
                System.out.println("❌ El usuario no es email ni DNI válido");
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
                System.out.println("✅ Login exitoso: " + usuario.email());

                // Verificar si el usuario está activo
                if (!authService.canAccess(usuario)) {
                    System.out.println("❌ Usuario inactivo");
                    return Optional.empty();
                }

            } else {
                System.out.println("❌ Credenciales incorrectas");
            }

            return usuarioOpt;

        } catch (Exception e) {
            System.err.println("❌ Error en login: " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
