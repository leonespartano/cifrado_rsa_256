package gobierno.code.apis.rsa.service;

import gobierno.code.apis.rsa.security.encript.EncryptRSA;
import org.springframework.stereotype.Service;

@Service
public class RsaService {

    public String encriptar(String texto, String llavePublica){
        EncryptRSA rsa = new EncryptRSA("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        String publicKey= llavePublica;
        //String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5sFzhsi2mbUkdDuIUtaXonGPu836SXWvgP9H53bu6xPXNxkSHTtc0UQ/o4oribkmpBpqhqz58BN88YlsfS/yC9YxvUiFwBMy1pzCv5RocoesCzGqm0jcyG7sioR53c3Wj1NBWD8fKB3MRBzFGOCHJsb/lP30T4nVSdVFQk5rrkwIDAQAB";
        String rsaData= rsa.encryptRSA(texto, publicKey);

        return rsaData;
    }

    public String desencriptar(String texto, String llavePrivada){
        EncryptRSA rsa = new EncryptRSA("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        String privatekey= llavePrivada;
        //String privatekey="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALmwXOGyLaZtSR0O4hS1peicY+7zfpJda+A/0fndu7rE9c3GRIdO1zRRD+jiiuJuSakGmqGrPnwE3zxiWx9L/IL1jG9SIXAEzLWnMK/lGhyh6wLMaqbSNzIbuyKhHndzdaPU0FYPx8oHcxEHMUY4Icmxv+U/fRPidVJ1UVCTmuuTAgMBAAECgYB8iW4h6NB5GD67AGpcnV+CXLEhZ6lyHLnnEqaPQElSABsjQWBgp13qNpvHA3lGV9WLSWSYHTOxFctqwkR+/wWA4xOf0x5J4Jubgrzs/id2q9FZgrzfO+qB1qPSMwPymvGjnPVvLnuLNbw/ysmHQib+LgSkCO/sTdYsxnqTRU3x8QJBAN2lQFfN6ARgF3Jl9kN3LE3wPUqhKQ4qV0VaCa6/gU3eRdt58hJN4+70JP5yqvGKsLJFuNtO0E0gTu/xnR1dGacCQQDWeGCYpxGcwC8WuXarVklcrlB8N4MMtx98u+2KF6PDXo3MpcZK7FAFI5mmToMqRWqIKA0GNlFLiSkkWGMfYgQ1AkEAuJJ3TWcjchlPDS3JcI8aLuEDOVWRacFqoU8ERygklmKli8o2AnYofIGFGPsGTVRwJlSXRtGP9toTwg5TBvPTewJBAI9OakU2RVd3Nz+492lvQ5/xPghyU1fYuOpw7D08pIl0Ec3v6zq/Ao0ch5uPlKdXMOL/2AwRmWqeoOYPRVR/+w0CQQCAZfR6IhHgBzq6UKg+gI+J6Hx07V0VsQ6hbkiPn+uHPMjwUPA7uvOM+xrRxPA2W8pECJk+xxmO0OZpLs/eDMyd";
        String rsaData= rsa.dencrypRSA(texto, privatekey);

        return rsaData;
    }
}
