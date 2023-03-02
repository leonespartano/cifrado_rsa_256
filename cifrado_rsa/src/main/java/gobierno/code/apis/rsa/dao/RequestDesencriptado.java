package gobierno.code.apis.rsa.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDesencriptado {

    private String texto;
    private String llavePrivada;

    public RequestDesencriptado(
            @JsonProperty("texto")String texto,
            @JsonProperty("llavePrivada")String llavePrivada
    ) {
        this.texto = texto;
        this.llavePrivada = llavePrivada;
    }

    public String getTexto() {
        return texto;
    }

    public String getLlavePrivada() {
        return llavePrivada;
    }
}
