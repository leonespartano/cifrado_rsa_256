package gobierno.code.apis.rsa.service;

import gobierno.code.apis.rsa.security.encript.EncryptRSA;
import org.springframework.stereotype.Service;

@Service
public class RsaService {

    public String encriptar(String texto){
        EncryptRSA rsa = new EncryptRSA("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw6Z2QFN89lEp/ku8nSbzPMglbz405EwuhZhnJPyRKiDmq/4uemdxv2JcPHLuU4xCHER3KDJba156mo8PcscYuhrc6PYz4ePyIOV+h86GUxG/rmKpwlqOBfRrKdMNRwTGgCDCEMJQa0tTmvD0kkhMAvMftrsPRYIQNYi3I2kYq3z6e5VDWa+jjwojMSTLhMx81BegLUHWQyz7vP93u+k0kFOAPqg4u/zJ2C2Yp+dN2NHe/J4+mjO0O7UU7Cr+a3VH1Rl9XP8PgnrpCzW6Ts1x1brs9EZvmgTxl4HRorqyOiioFJnCrp6ip6vX3C4RDN/gmoU8mbXC77aIvPfreBgEsQIDAQAB";
        String rsaData= rsa.encryptRSA(texto, publicKey);

        return rsaData;
    }
}
