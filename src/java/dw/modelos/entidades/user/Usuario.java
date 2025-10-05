package dw.modelos.entidades.user;

import dw.modelos.entidades.Entity;
import utils.valuesObjects.AsignRol;
import utils.valuesObjects.CustomID;

public class Usuario extends Entity {

    private String _email;
    private String _password;
    private String _dni;
    private String _names;
    private String _lastNames;
    private Boolean _isActive;
    private AsignRol _rol;

    public Usuario() {
    }
    
    // Constructor con rol específico    
    public Usuario(String email, String password, String dni,
            String names, String lasNames, AsignRol rol) {
        super(null);
        this._email = email;
        this._password = password;
        this._dni = dni;
        this._names = names;
        this._lastNames = lasNames;
        this._isActive = true;
        this._rol = rol;
    }

    // cuando el usuario tiene un id generado
    public Usuario(String email, String password, String dni,
            String names, String lasNames, AsignRol rol, CustomID id) {
        super(id != null ? id : new CustomID());
        this._email = email;
        this._password = password;
        this._names = names;
        this._lastNames = lasNames;
        this._isActive = true;
        this._rol = rol;
    }

    public String dni() {
        return _dni;
    }

    public AsignRol rol() {
        return _rol;
    }

    public String email() {
        return _email;
    }

    public String password() {
        return _password;
    }

    public String names() {
        return _names;
    }

    public String lastNames() {
        return _lastNames;
    }

    public Boolean isActive() {
        return _isActive;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public void setNames(String _names) {
        this._names = _names;
    }

    public void setLastNames(String _lastNames) {
        this._lastNames = _lastNames;
    }

    public void setIsActive(Boolean _isActive) {
        this._isActive = _isActive;
    }

    public void setDni(String _dni) {
        this._dni = _dni;
    }

    public void setRol(AsignRol _rol) {
        this._rol = _rol;
    }

}
