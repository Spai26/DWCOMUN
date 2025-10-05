package dw.servicios;

import dw.modelos.dto.LoginDTO;
import dw.modelos.entidades.user.Usuario;
import dw.modelos.dto.RegistrarDTO;
import facade.FacadeLogin;
import facade.FacadeRegistro;
import utils.valuesObjects.AsignRol;

public class test {

    public static void main(String[] args) {
        try {
             // Usando el facade
              // Test con EMAIL
            System.out.println("\n--- Test con EMAIL ---");
            LoginDTO loginEmail = new LoginDTO();
            loginEmail.setUsuario("testing3@hotmail.com");
            loginEmail.setPassword("Password123");
            
            FacadeLogin facade = new FacadeLogin();
            var resultadoEmail = facade.run(loginEmail);
            System.out.println("Login con email: " + (resultadoEmail.isPresent() ? "EXITOSO" : "FALLIDO"));
            
            // Test con DNI
            System.out.println("\n--- Test con DNI ---");
            LoginDTO loginDni = new LoginDTO();
            loginDni.setUsuario("22345676"); // El DNI que registraste
            loginDni.setPassword("Password123");
            
            var resultadoDni = facade.run(loginDni);
            System.out.println("Login con DNI: " + (resultadoDni.isPresent() ? "EXITOSO" : "FALLIDO"));
             /*
            RegistrarDTO registroDTO = new RegistrarDTO();
            registroDTO.setEmail("testing17@hotmail.com");
            registroDTO.setPassword("Password123");
            registroDTO.setConfirmPassword("Password123");
            registroDTO.setDni("22345676");
            registroDTO.setNombres("testing");
            registroDTO.setApellidos("prueba");
            registroDTO.setRol(AsignRol.PACIENTE);

            FacadeRegistro facade = new FacadeRegistro();
            boolean resultado = facade.run(registroDTO);

            System.out.println("Registro exitoso: " + resultado);

            AutentificacionService service = new AutentificacionService();
            Usuario user = service.registrarConRol("testing21@hotmail.com", "password1", "12234561", "testing", "apeliidos", AsignRol.ADMIN);
            */
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
