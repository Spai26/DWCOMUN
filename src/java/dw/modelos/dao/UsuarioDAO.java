package dw.modelos.dao;

import dw.modelos.entidades.user.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioDAO{
    // CRUD
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(String id);
    List<Usuario> getAll();
    boolean update(Usuario usuario);
    boolean delete(String id);
    
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByDni(String usuario);  
    // Búsquedas con filtros
    List<Usuario> finByNames(String nombre);
    List<Usuario> findAllByActive();
}