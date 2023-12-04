package culinart.api.avaliacao;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.avaliacao.dto.AvaliacaoCadastroDTO;
import culinart.domain.avaliacao.dto.AvaliacaoResponseDTO;
import culinart.domain.avaliacao.mapper.AvaliacaoMapper;
import culinart.service.avaliacao.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<Void> cadastrarAvaliacao(@Valid @RequestBody List<AvaliacaoCadastroDTO> listAvaliacaoCadastroDTO){
        avaliacaoService.cadastrarAvaliacao(listAvaliacaoCadastroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/download/txt")
    public ResponseEntity<FileSystemResource> downloadTxt() {
        File txt = avaliacaoService.exportTxt();

        Path arquivoPath = Paths.get(txt.getAbsolutePath());
        FileSystemResource fileSystemResource = new FileSystemResource(arquivoPath.toFile());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-avaliacoes.txt");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(txt.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileSystemResource);
    }

}
