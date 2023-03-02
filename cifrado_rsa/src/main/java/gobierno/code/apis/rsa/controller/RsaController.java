package gobierno.code.apis.rsa.controller;

import gobierno.code.apis.rsa.dao.RequestCifrado;
import gobierno.code.apis.rsa.dao.RequestDesencriptado;
import gobierno.code.apis.rsa.service.RsaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RsaController {

   private RsaService rsaService;

    public RsaController(RsaService rsaService) {
        this.rsaService = rsaService;
    }

    @PostMapping("/ejemplo/rsa")
    public ResponseEntity<?> ejemploRSA(@RequestBody RequestCifrado request){

        String respuesta = this.rsaService.encriptar(request.getTexto(), request.getLlavePublica());

        return ResponseEntity.ok().body(respuesta);

    }

    @PostMapping("/ejemplo/desencriptar/rsa")
    public ResponseEntity<?> ejemploDesencriptarRSA(@RequestBody RequestDesencriptado request){

        String respuesta = this.rsaService.desencriptar(request.getTexto(), request.getLlavePrivada());

        return ResponseEntity.ok().body(respuesta);

    }
}
