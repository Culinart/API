package culinart.api.endereco;

import culinart.domain.endereco.Endereco;
import culinart.domain.endereco.dto.EnderecoExibicaoDTO;
import culinart.domain.endereco.usuario.dto.EnderecoResponseToUsuarioDTO;
import culinart.integration.ViaCep.ViaCepIntegrationService;
import culinart.integration.ViaCep.dto.ViaCepResponse;
import culinart.service.endereco.EnderecoService;
import culinart.service.endereco.usuario.EnderecoUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final ViaCepIntegrationService viaCepIntegrationService;
    private final EnderecoUsuarioService enderecoUsuarioService;

    public EnderecoController(EnderecoService enderecoService, ViaCepIntegrationService viaCepIntegrationService, EnderecoUsuarioService enderecoUsuarioService) {
        this.enderecoService = enderecoService;
        this.viaCepIntegrationService = viaCepIntegrationService;
        this.enderecoUsuarioService = enderecoUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoExibicaoDTO>> listarEnderecos() {
        List<EnderecoExibicaoDTO> lista = enderecoService.listarTodosOsEnderecos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<EnderecoResponseToUsuarioDTO>> getEnderecoUsuarios(){
        List<EnderecoResponseToUsuarioDTO> enderecoResponseToUsuarioDTOS = enderecoUsuarioService.mostrarTodosEnderecosUsuarios();
        if(enderecoResponseToUsuarioDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(enderecoResponseToUsuarioDTOS);
    }

    @GetMapping("/usuarios/{idUsuario}")
    public ResponseEntity<EnderecoResponseToUsuarioDTO> getEnderecoUsuarioPorId(@PathVariable int idUsuario){
        return ResponseEntity.ok(enderecoUsuarioService.mostrarEnderecoUsuarioPorIdUsuario(idUsuario));
    }

    @GetMapping("/buscarCEP")
    public ResponseEntity<ViaCepResponse> getCEP(@RequestParam String cep) {
        return ResponseEntity.ok(viaCepIntegrationService.getCEP(cep));
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<EnderecoExibicaoDTO> cadastrarEnderecoAoUsuarioPorId(
            @PathVariable int idUsuario,
            @RequestParam String cep,
            @RequestParam int numero,
            @RequestParam String complemento
    ) {
        return ResponseEntity.ok(enderecoService.cadastrarEnderecoAoUsuarioPorId(cep, complemento ,idUsuario, numero));
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
