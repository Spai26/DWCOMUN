package dw.modelos.dao.impl;

import dw.conexion.Conexion;
import dw.modelos.dao.BaseDAO;
import dw.modelos.dao.UsuarioDAO;
import dw.modelos.entidades.user.Usuario;
import java.util.List;
import java.util.Optional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.valuesObjects.AsignRol;
import utils.valuesObjects.CustomID;

public class UsuarioMsql extends BaseDAO implements UsuarioDAO {

    private static final String TABLE = "Usuarios";

    public UsuarioMsql() {
        super(Conexion.getInstance().getCon());
    }

    @Override
    public Usuario save(Usuario usuario) {
        String sql = "INSERT INTO " + TABLE + "(id, email, password, dni, nombres, apellidos, isActive, rol) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            boolean success = executeUpdate(sql,
                    usuario.getId().toString(),
                    usuario.email(),
                    usuario.password(),
                    usuario.dni(),
                    usuario.names(),
                    usuario.lastNames(),
                    usuario.isActive(),
                    usuario.rol().name());

            return success ? usuario : null;
        } catch (Exception e) {
            throw new RuntimeException("Error en registro validar los campos." + e.getMessage());
        }

    }

    @Override
    public Optional<Usuario> findById(String id) {
        String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";

        try {
            ResultSet rs = executeQuery(sql, id);
            if (rs.next()) {
                return Optional.of(mapResultSetToUsuario(rs));
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> getAll() {
        String sql = "SELECT * FROM " + TABLE;

        try (ResultSet rs = executeQuery(sql)) {
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                usuarios.add(mapResultSetToUsuario(rs));
            }
            return usuarios;
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los usuarios: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean update(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id = ?";

        try {
            return executeUpdate(sql, id);
        } catch (Exception e) {
            throw new RuntimeException("Error en eliminar");
        }
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT * FROM " + TABLE + " WHERE email = ?";

        try {
            ResultSet rs = executeQuery(sql, email);
            if (rs.next()) {
                return Optional.of(mapResultSetToUsuario(rs));
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por email: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> findAllByActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> finByNames(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("dni"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                AsignRol.fromString(rs.getString("rol")),
                new CustomID(rs.getString("id"))
        );
    }

    @Override
    public Optional<Usuario> findByDni(String usuario) {
        String sql = "SELECT * FROM " + TABLE + " WHERE dni = ?";

        try {
            ResultSet rs = executeQuery(sql, usuario);
            if (rs.next()) {
                return Optional.of(mapResultSetToUsuario(rs));
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar por email o dni: " + e.getMessage(), e);
        }
    }

}
