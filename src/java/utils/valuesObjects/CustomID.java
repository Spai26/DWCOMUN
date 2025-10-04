package utils.valuesObjects;
import java.util.Objects;

public class CustomID {
    private final String value;

    public CustomID(String value) {
        if (value != null && !isValidUUID(value)) {
            throw new IllegalArgumentException("Invalid UUID format: " + value);
        }
        this.value = (value != null) ? value : generateID();
    }
    
    /**
     * Creates a new UUID with a generated value
     */
    public CustomID() {
        this.value = generateID();
    }
    
    
    private String generateID(){
        return java.util.UUID.randomUUID().toString();
    }
    
    
    public String id() {
        return this.value;
    }

    public boolean equals(CustomID other) {
        if (this == other) return true;
        if (other == null) return false;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    private boolean isValidUUID(String uuid) {
        if (uuid == null) return false;
        try {
            java.util.UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
