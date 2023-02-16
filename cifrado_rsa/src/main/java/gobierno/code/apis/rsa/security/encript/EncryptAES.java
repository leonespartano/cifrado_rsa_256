package gobierno.code.apis.rsa.security.encript;

import gobierno.code.apis.rsa.security.utils.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class EncryptAES {

    public EncryptAES(String algorithm, String algorithmHMAC){
        super();
        this.algorithm = algorithm;
        this.algorithmHMAC = algorithmHMAC;
    }

    private String algorithm;
    private String algorithmHMAC;
    Logger logger = Logger.getLogger( EncryptAES.class.getName());

    /**
     * @param valorCampo |-	Valor a cifrar.
     * @param aesKeyBase64 |- accesoSimetrico obtenida del api de Seguridad.
     * @param hmacKeyBase64 |- codigoAutenticacionHash obtenida del api de Seguridad.
     * @return
     */
    public String encryptAES(String valorCampo, String aesKeyBase64, String hmacKeyBase64) {

        String respuesta=null;
        try {


            SecretKeySpec aesKey = new SecretKeySpec(Base64.getDecoder().decode(aesKeyBase64.getBytes(StandardCharsets.UTF_8)), "AES");
            SecretKeySpec hmacKey = new SecretKeySpec(Base64.getDecoder().decode(hmacKeyBase64.getBytes(StandardCharsets.UTF_8)), algorithmHMAC);

            byte[] iv = Utils.generarInitializationVector();

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, aesKey, new IvParameterSpec(iv));

            byte[] plainText= valorCampo.getBytes(StandardCharsets.UTF_8);

            byte[] cipherText = cipher.doFinal(plainText);
            byte[] ivCipherText = Utils.concatenateBytes(iv, cipherText);
            byte[] hmac = Utils.generarHMAC(hmacKey, ivCipherText);
            byte[] ivCipherTextHmac = Utils.concatenateBytes(ivCipherText, hmac);

            byte[] ivCipherTextHmacBase64 = Base64.getEncoder().encode(ivCipherTextHmac);
            respuesta= new String(ivCipherTextHmacBase64, StandardCharsets.UTF_8);

        } catch (Exception e) {
            logger.log(Level.WARNING, "Ocurrio un problema!");
        }
        return respuesta;
    }


    /**
     * @param valorCifrado |- Valor a descifrar
     * @param aesKeyBase64 |- accesoSimetrico obtenida del api de seguridad.
     * @param hmacKeyBase64 |- codigoAutenticaciónHash obtenida del api de seguridad.
     * @return
     */
    public String decryptAES(String valorCifrado, String aesKeyBase64, String hmacKeyBase64){

        String respuesta=null;

        try {
            SecretKeySpec aesKey = new SecretKeySpec(Base64.getDecoder().decode(aesKeyBase64.getBytes(StandardCharsets.UTF_8)), "AES");
            SecretKeySpec hmacKey = new SecretKeySpec(Base64.getDecoder().decode(hmacKeyBase64.getBytes(StandardCharsets.UTF_8)), algorithmHMAC);

            int macLength = Utils.obtenerHMACLength(hmacKey);

            byte[] ivCipherTextHmac = Base64.getDecoder().decode(valorCifrado.getBytes(StandardCharsets.UTF_8));
            int cipherTextLength = ivCipherTextHmac.length - macLength;

            byte[] iv = Arrays.copyOf(ivCipherTextHmac, 16);
            byte[] cipherText = Arrays.copyOfRange(ivCipherTextHmac, 16, cipherTextLength);
            byte[] ivCipherText = Utils.concatenateBytes(iv, cipherText);
            byte[] receivedHMAC = Arrays.copyOfRange(ivCipherTextHmac, cipherTextLength, ivCipherTextHmac.length);
            byte[] calculatedHMAC = Utils.generarHMAC(hmacKey, ivCipherText);

            if(Arrays.equals(receivedHMAC, calculatedHMAC)) {
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
                byte[] plainText = cipher.doFinal(cipherText);
                respuesta= new String(plainText,StandardCharsets.UTF_8);
            } else {
                respuesta= valorCifrado;
            }

        } catch (Exception e) {
            logger.log(Level.WARNING, "Ocurrio un problema!");
        }
        return respuesta;
    }



}