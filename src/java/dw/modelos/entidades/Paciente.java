
import dw.modelos.entidades.user.Usuario;
import utils.valuesObjects.AsignRol;
import utils.valuesObjects.CustomID;

public class Paciente extends Usuario {

    private String _telefono;
    private String _direccion;
    private String _fechaNacimiento;
    private String _biografia;
    private String _fotoUrl;

    public Paciente(String email, String password, String dni,
            String names, String lastNames, String telefono,
            String direccion, String fechaNacimiento, String biografia, String fotourl) {
        super(email, password, dni, names, lastNames, AsignRol.PACIENTE);
        this._telefono = telefono;
        this._direccion = direccion;
        this._fechaNacimiento = fechaNacimiento;
        this._biografia = biografia;
        this._fotoUrl = fotourl;
    }

    public Paciente(String email, String password, String dni,
            String names, String lastNames, String telefono,
            String direccion, String fechaNacimiento, CustomID id) {
        super(email, password, dni, names, lastNames, AsignRol.PACIENTE, id);
        this._telefono = telefono;
        this._direccion = direccion;
        this._fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return _telefono;
    }

    public String getDireccion() {
        return _direccion;
    }

    public String getFechaNacimiento() {
        return _fechaNacimiento;
    }

    public String getBiografia() {
        return _biografia;
    }

    public String getFotoUrl() {
        return _fotoUrl;
    }

    public void setTelefono(String _telefono) {
        this._telefono = _telefono;
    }

    public void setDireccion(String _direccion) {
        this._direccion = _direccion;
    }

    public void setFechaNacimiento(String _fechaNacimiento) {
        this._fechaNacimiento = _fechaNacimiento;
    }

    public void setBiografia(String _biografia) {
        this._biografia = _biografia;
    }

    public void setFotoUrl(String _fotoUrl) {
        this._fotoUrl = _fotoUrl;
    }
}
