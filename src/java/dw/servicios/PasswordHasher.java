package dw.servicios;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    public static String hash(String passwordPlana) {
        return BCrypt.hashpw(passwordPlana, BCrypt.gensalt());
    }

    public static boolean compare(String plainPass, String hash) {
        return BCrypt.checkpw(plainPass, hash);
    }

}
