package culinart.api.usuario;

import culinart.domain.usuario.dto.EnderecoExibicaoDTO;
import culinart.service.usuario.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoExibicaoDTO>> listarEnderecos() {
        List<EnderecoExibicaoDTO> lista = enderecoService.listarTodosOsEnderecos();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<EnderecoExibicaoDTO> cadastrarEnderecoAoUsuarioPorId(@RequestBody String cep, @PathVariable Long idUsuario){
        return ResponseEntity.ok(enderecoService.cadastrarEnderecoAoUsuarioPorId(cep, idUsuario));
    }

//    public ResponseEntity<EnderecoExibicaoDTO> atualizarEndereco(){
//        return ResponseEntity.ok(enderecoService.atualizarEndereco());
//    }
}
