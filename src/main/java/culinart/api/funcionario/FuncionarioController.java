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
<<<<<<< HEAD
import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioCriacaoDTO;
import culinart.domain.fornecedor.dto.FuncionarioExibicaoDTO;
import culinart.service.funcionario.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.server.ResponseStatusException;
>>>>>>> 19738591b282af4c11e8247bd6bd716d70a3ac55

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioExibicaoDTO>> listagemGeral() {
        List<FuncionarioExibicaoDTO> funcionarios = this.funcionarioService.listarFunc();

        if (funcionarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<FuncionarioExibicaoDTO> cadastrarFuncionario(@RequestBody FuncionarioCriacaoDTO funcionarioCriacao) {
        try {
            return ResponseEntity.status(201).body(funcionarioService.cadastrarFuncionario(funcionarioCriacao));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioExibicaoDTO> atualizarFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario){
        FuncionarioExibicaoDTO funcAtt = this.funcionarioService.atualizarFuncionario(id, funcionario);
        if (funcAtt != null){
            return ResponseEntity.status(200).body(funcAtt);
        }else{
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DesativarFuncionario(@PathVariable Integer id){
        return ResponseEntity.status(200).body(funcionarioService.deletarFuncionario(id));
    }

    @GetMapping("/nome/email")
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
