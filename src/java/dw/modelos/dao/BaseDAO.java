package dw.modelos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO {
    protected Connection conexion;

    public BaseDAO(Connection conexion) {
        this.conexion = conexion;
    }

    protected Long executeInsert(String sql, Object... parametros) {
       try (PreparedStatement query = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setParameters(query, parametros);
            int affectedRows = query.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = query.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error insert:" + e.getMessage(), e);
        }
    }

    protected boolean executeUpdate(String sql, Object... parametros) {
        try (PreparedStatement query = conexion.prepareStatement(sql)) {
            setParameters(query, parametros);
            return query.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error update: " + e.getMessage(), e);
        }
    }

    protected ResultSet executeQuery(String sql, Object... parametros) {
        try {
            PreparedStatement query = conexion.prepareStatement(sql);
            setParameters(query, parametros);
            return query.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error query: " + e.getMessage(), e);
        }
    }
    
     private void setParameters(PreparedStatement stmt, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }
    }

}
