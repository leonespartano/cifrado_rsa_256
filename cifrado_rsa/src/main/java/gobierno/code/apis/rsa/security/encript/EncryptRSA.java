package gobierno.code.apis.rsa.security.encript;

import gobierno.code.apis.rsa.security.utils.Utils;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.StandardCharsets;
import java.security.spec.MGF1ParameterSpec;



public class EncryptRSA {


    public EncryptRSA(String algorithm){
        super();
        this.algorithm = algorithm;
    }

    private String algorithm;
    Logger logger = Logger.getLogger( EncryptRSA.class.getName());

    /**
     * @param data |-	Texto a cifrar.
     * @param publicKey |- Llave publica obtenida del api de seguridad.
     * @return
     */

    public String encryptRSA(String data, String publicKey) {
        String textoCifrado=null;
        try {

            Cipher rsaCipher = Cipher.getInstance(algorithm);
            //rsaCipher.init(Cipher.ENCRYPT_MODE, Utils.publicKeyParser(publicKey));
            rsaCipher.init(Cipher.ENCRYPT_MODE, Utils.publicKeyParser(publicKey), new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
            textoCifrado = Base64.getEncoder().encodeToString(rsaCipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));

        }catch(Exception e) {
            logger.log(Level.WARNING, "Ocurrio un problema!"+ e.getMessage());
        }

        return textoCifrado;
    }

    /*
     *
     * @param valorCifrado |- Valor a descifrar
     * @param privateKey |- llave privada obtenida del api de seguridad.
     * @return
     */

    public String dencrypRSA(String valorCifrado, String privateKey) {

        String textoDescifrado=null;

        try {

            Cipher rsaCipher = Cipher.getInstance(algorithm);
            //rsaCipher.init(Cipher.DECRYPT_MODE, Utils.privateKeyParser(privateKey));
            rsaCipher.init(Cipher.DECRYPT_MODE, Utils.privateKeyParser(privateKey), new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
            textoDescifrado = new String(rsaCipher.doFinal(Base64.getDecoder().decode(valorCifrado)), StandardCharsets.UTF_8);

        }catch(Exception e) {
            logger.log(Level.WARNING, "Ocurrio un problema!"+ e.getMessage());
        }

        return textoDescifrado;
    }



}

