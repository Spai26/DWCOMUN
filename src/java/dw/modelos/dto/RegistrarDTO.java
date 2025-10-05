package dw.modelos.dto;

import java.util.regex.Pattern;
import utils.valuesObjects.AsignRol;

public class RegistrarDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String dni;
    private String nombres;
    private String apellidos;
    private AsignRol rol;

    // Patrones de validación
    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PASSWORD_PATTERN
            = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    private static final Pattern DNI_PATTERN
            = Pattern.compile("^[0-9]{8}$");
    private static final Pattern NOMBRES_PATTERN
            = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,50}$");

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setRol(AsignRol rol) {
        this.rol = rol;
    }

    public boolean isValid() {
        return isEmailValid()
                && isPasswordValid()
                && passwordsMatch()
                && isDniValid()
                && isNombresValid()
                && isApellidosValid();
    }

    // Validaciones individuales
    public boolean isEmailValid() {
        return email != null
                && !email.trim().isEmpty()
                && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isPasswordValid() {
        return password != null
                && !password.trim().isEmpty()
                && PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean passwordsMatch() {
        return password != null
                && confirmPassword != null
                && password.equals(confirmPassword);
    }

    public boolean isDniValid() {
        return dni != null
                && !dni.trim().isEmpty()
                && DNI_PATTERN.matcher(dni).matches();
    }

    public boolean isNombresValid() {
        return nombres != null
                && !nombres.trim().isEmpty()
                && NOMBRES_PATTERN.matcher(nombres).matches();
    }

    public boolean isApellidosValid() {
        return apellidos != null
                && !apellidos.trim().isEmpty()
                && NOMBRES_PATTERN.matcher(apellidos).matches();
    }

    // Método para obtener mensajes de error específicos
    public String getValidationErrors() {
        if (isValid()) {
            return null;
        }

        StringBuilder errors = new StringBuilder();

        if (!isEmailValid()) {
            errors.append("Email inválido. ");
        }
        if (!isPasswordValid()) {
            errors.append("Password debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número. ");
        }
        if (!passwordsMatch()) {
            errors.append("Las contraseñas no coinciden. ");
        }
        if (!isDniValid()) {
            errors.append("DNI debe tener 8 dígitos. ");
        }
        if (!isNombresValid()) {
            errors.append("Nombres inválidos. ");
        }
        if (!isApellidosValid()) {
            errors.append("Apellidos inválidos. ");
        }

        return errors.toString().trim();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public AsignRol getRol() {
        return rol;
    }
}
