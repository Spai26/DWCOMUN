package dw.modelos.dto;

public class LoginDTO {

    private String usuario;
    private String password;
    private boolean recordSession;

    public LoginDTO() {
    }

    public LoginDTO(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRecordSession() {
        return recordSession;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRecordSession(boolean recordSession) {
        this.recordSession = recordSession;
    }

    public boolean isValid() {
        return usuario != null && !usuario.trim().isEmpty()
                && password != null && !password.trim().isEmpty();
    }

    public String getUsuarioClean() {
        return usuario != null ? usuario.trim() : "";
    }

    // numerico - 8 digitos
    public boolean isValidDni() {
        return getUsuarioClean().matches("\\d{8}");
    }
    
    // verificar si cumple con email
    public boolean isValidEmail() {
        return getUsuarioClean().matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    }
    
    public String getTypeEntry(){
        if(isValidDni()) return "dni";
        if(isValidEmail()) return "email";
        return "INVALID";
    }
}
