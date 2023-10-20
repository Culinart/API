package culinart.api.funcionario;

import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.service.funcionario.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioDTO>> buscarFuncionariosOrdenadosDTO() {
        List<FuncionarioDTO> funcionariosOrdenadosPorNomeDTO = funcionarioService.funcionariosOrdenadosDTO();
        if(funcionariosOrdenadosPorNomeDTO.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionariosOrdenadosPorNomeDTO);
    }

    @GetMapping("/download/csv")
    public ResponseEntity<byte[]> download(){
        List<FuncionarioDTO> funcionariosOrdenadosPorNomeDTO = funcionarioService.funcionariosOrdenadosDTO();
        String nomeArquivo = funcionarioService.gerarArquivoCsv(funcionariosOrdenadosPorNomeDTO);
        try {
            File file = new File(nomeArquivo);
            byte[] csvContent = Files.readAllBytes(file.toPath());

            // Configura a resposta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=funcionarios.csv");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(csvContent);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(500, "Erro ao ler ou enviar o arquivo CSV", e);
        }
    }

}
