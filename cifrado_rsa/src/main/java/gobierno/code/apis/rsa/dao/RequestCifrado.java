package gobierno.code.apis.rsa.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCifrado {
    private String texto;

    public RequestCifrado(@JsonProperty("texto")String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}
