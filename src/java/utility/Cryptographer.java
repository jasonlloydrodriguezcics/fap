package utility;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;

public class Cryptographer {

    public static String encrypt(ServletContext context, String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(context.getInitParameter("key").getBytes(), context.getInitParameter("cipher")));

        return Base64.getEncoder().encodeToString((cipher.doFinal(text.getBytes())));
    }

    public static String decrypt(ServletContext context, String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(context.getInitParameter("key").getBytes(), context.getInitParameter("cipher")));

        return new String(cipher.doFinal(Base64.getDecoder().decode(text)));
    }
}
