
package dw.servicios;
import dw.modelos.entidades.user.Usuario;
import dw.servicios.impl.UsuarioService;
import java.util.List;


public class test {
    public static void main(String[] args){
        UsuarioService user = new UsuarioService();
        
        List<Usuario> usuarios =user.obtenerTodosUsuarios();
         for (int i = 0; i < usuarios.size(); i++) {
                    Usuario usuario = usuarios.get(i);
                    System.out.println("\n--- Usuario " + (i + 1) + " ---");
                    System.out.println("ID: " + usuario.getId().toString());
                    System.out.println("Email: " + usuario.email());
                    System.out.println("Nombres: " + usuario.names());
                    System.out.println("Apellidos: " + usuario.lastNames());
                    System.out.println("Activo: " + usuario.isActive());
                }
    }   
    
}
