package dw.modelos.entidades.user;

import dw.modelos.entidades.Entity;
import utils.valuesObjects.CustomID;

public class Usuario extends Entity {
    private String _email;
    private String _password;
    private String _names;
    private String _lastNames;
    private Boolean _isActive;
    
    public Usuario(){
    }

    public Usuario(String email, String password,
            String names, String lasNames) {
        super(null);
        this._email = email;
        this._password = password;
        this._names = names;
        this._lastNames = lasNames;
        this._isActive = true;
    }
    
     public Usuario(String email, String password,
            String names, String lasNames, CustomID id) {
        super(id !=null ? id: new CustomID());
        this._email = email;
        this._password = password;
        this._names = names;
        this._lastNames = lasNames;
        this._isActive = true;
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
}
