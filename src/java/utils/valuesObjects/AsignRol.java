package utils.valuesObjects;

public enum AsignRol {
    ADMIN("Administrador del sistema"),
    PACIENTE("Usuario regular"),
    MEDICO("Gerente o supervisor"),
    OPERADOR("Usuario invitado");

    private final String descripcion;

    AsignRol(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static AsignRol fromString(String nombre) {
        for (AsignRol rol : values()) {
            if (rol.name().equalsIgnoreCase(nombre)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol no encontrado: " + nombre);
    }
}
