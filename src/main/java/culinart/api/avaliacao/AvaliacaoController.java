package culinart.api.avaliacao;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.avaliacao.dto.AvaliacaoCadastroDTO;
import culinart.domain.avaliacao.dto.AvaliacaoResponseDTO;
import culinart.domain.avaliacao.mapper.AvaliacaoMapper;
import culinart.service.avaliacao.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {
    private final AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> exibirTodasAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoService.exibirTodasAvaliacoes();
        return ResponseEntity.ok(avaliacoes.stream()
                .map(AvaliacaoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> exibirAvaliacaoPorId(@PathVariable int id){
        return ResponseEntity.ok(AvaliacaoMapper.toDTO(avaliacaoService.exibirAvaliacaoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> cadastrarAvaliacao(@Valid @RequestBody AvaliacaoCadastroDTO avaliacaoCadastroDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AvaliacaoMapper.toDTO(avaliacaoService.cadastrarAvaliacao(avaliacaoCadastroDTO)));
    }

    @DeleteMapping
    public ResponseEntity<Void> desfazerAvaliacao(){
        avaliacaoService.desfazer();
        return ResponseEntity.noContent().build();
    }
}
