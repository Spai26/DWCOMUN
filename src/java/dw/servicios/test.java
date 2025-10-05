package dw.servicios;

import dw.modelos.entidades.user.Usuario;
import dw.modelos.dto.RegistrarDTO;
import facade.FacadeRegistrarUsuario;
import utils.valuesObjects.AsignRol;

public class test {

    public static void main(String[] args) {
        try {
            /*   // Usando el facade
            RegistrarDTO registroDTO = new RegistrarDTO();
            registroDTO.setEmail("testing7@hotmail.com");
            registroDTO.setPassword("Password123");
            registroDTO.setConfirmPassword("Password123");
            registroDTO.setDni("22345676");
            registroDTO.setNombres("testing");
            registroDTO.setApellidos("prueba");
            registroDTO.setRol(AsignRol.PACIENTE);

            FacadeRegistrarUsuario facade = new FacadeRegistrarUsuario();
            boolean resultado = facade.run(registroDTO);

            System.out.println("Registro exitoso: " + resultado);*/

            AutentificacionService service = new AutentificacionService();
            Usuario user = service.registrarConRol("testing21@hotmail.com", "password1", "12234561", "testing", "apeliidos", AsignRol.ADMIN);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
