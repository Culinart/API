package culinart.api.endereco;

import culinart.domain.endereco.Endereco;
import culinart.domain.endereco.dto.EnderecoExibicaoDTO;
import culinart.domain.endereco.repository.EnderecoRepository;
import culinart.service.endereco.EnderecoService;
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
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<EnderecoExibicaoDTO> cadastrarEnderecoAoUsuarioPorId(
            @PathVariable int idUsuario,
            @RequestParam String cep,
            @RequestParam int numero
    ) {
        return ResponseEntity.ok(enderecoService.cadastrarEnderecoAoUsuarioPorId(cep, idUsuario, numero));
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoExibicaoDTO> atualizarEnderecoDoUsuarioPorId(
            @PathVariable int idEndereco,
            @RequestBody Endereco endereco
    ) {
        return ResponseEntity.ok(enderecoService.atualizarEnderecoDoUsuarioPorId(idEndereco, endereco));
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<Void> deletarEnderecoDoUsuarioPorId(@PathVariable int idEndereco) {
        enderecoService.deletarEnderecoDoUsuarioPorId(idEndereco);
        return ResponseEntity.noContent().build();
    }

}
