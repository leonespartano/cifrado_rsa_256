package gobierno.code.apis.rsa.security.utils;

import javax.crypto.Mac;
import java.util.Base64;
import javax.crypto.SecretKey;
import java.util.logging.Level;
import java.security.PublicKey;
import java.util.logging.Logger;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.InvalidKeyException;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.NoSuchAlgorithmException;


public class Utils {

    private Utils() {
        super();
    }


    private static final String ALGORITHM_HMAC = "HmacSHA256";
    private static final int IV_SIZE = 16;
    static Logger logger = Logger.getLogger( Utils.class.getName());

    /**
     *
     * @param publicKey
     * @return
     */
    public static PublicKey publicKeyParser(String publicKey){
        PublicKey publicKeyResponse =null;
        try {
            byte[] decode = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decode);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            publicKeyResponse = kf.generatePublic(spec);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Ocurrio un problema!");
        }
        return publicKeyResponse;
    }


    /**
     *
     * @param privateKey
     * @return
     */
    public static PrivateKey privateKeyParser(String privateKey){

        PrivateKey privateKeyResponse=null;

        try {
            byte[] decode = Base64.getDecoder().decode(privateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            privateKeyResponse= kf.generatePrivate(spec);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Ocurrio un problema!");
        }
        return privateKeyResponse;
    }

    /**
     * @return
     */
    public static byte[] generarInitializationVector() {
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        return iv;
    }

    /**
     *
     * @param first
     * @param second
     * @return
     */

    public static byte[] concatenateBytes(byte[]first, byte[] second) {
        byte [] concatBytes = new byte[first.length + second.length];
        System.arraycopy(first, 0, concatBytes, 0, first.length);
        System.arraycopy(second, 0, concatBytes, first.length, second.length);
        return concatBytes;
    }

    /**
     *
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static int obtenerHMACLength(SecretKey key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance(ALGORITHM_HMAC);
        hmac.init(key);
        return hmac.getMacLength();
    }

    /**
     *
     * @param key
     * @param hmacInput
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static byte[] generarHMAC(SecretKey key, byte[] hmacInput) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac = Mac.getInstance(ALGORITHM_HMAC);
        hmac.init(key);
        return hmac.doFinal(hmacInput);
    }


}