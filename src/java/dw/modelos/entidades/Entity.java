package dw.modelos.entidades;

import utils.valuesObjects.CustomID;

public abstract class Entity {
    protected final CustomID _id;

    protected Entity() {
        this._id = new CustomID();
    }
    
    protected Entity(CustomID id) {
        this._id = (id != null) ? id : new CustomID();
    }
    
     public CustomID getId() {
        return this._id;
    }
}
