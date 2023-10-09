package culinart.api.viaCEP;

import culinart.service.ViaCep.ViaCepService;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/viaCep")
    public class ViaCepController {

    private final ViaCepService service;

    public ViaCepController(ViaCepService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<String> consultarCEP(@PathVariable String cep) {
        String resposta = service.consultarCEP(cep);

        if (resposta != null) {
            return ResponseEntity.ok(resposta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    }
