package gobierno.code.apis.rsa.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestCifrado {
    private String texto;
    private String llavePublica;

    public RequestCifrado(
            @JsonProperty("texto")String texto,
            @JsonProperty("llavePublica")String llavePublica
    ) {
        this.texto = texto;
        this.llavePublica = llavePublica;
    }

    public String getTexto() {
        return texto;
    }

    public String getLlavePublica() {
        return llavePublica;
    }
}
