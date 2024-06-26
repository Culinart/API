package culinart.api.funcionario;

import culinart.domain.fornecedor.dto.*;
import culinart.service.funcionario.FuncionarioService;
import culinart.service.usuario.autenticacao.dto.UsuarioLoginDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import culinart.utils.FilaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import culinart.domain.fornecedor.Funcionario;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

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

    @PostMapping
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

    @PutMapping("/perfil/{id}")
    public ResponseEntity<FuncionarioExibicaoDTO> atualizarPerfilFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario){
        FuncionarioExibicaoDTO funcAtt = this.funcionarioService.atualizarPerfilFuncionario(id, funcionario);
        if (funcAtt != null){
            return ResponseEntity.status(200).body(funcAtt);
        }else{
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/senha/{id}")
    public ResponseEntity atualizarSenha(@PathVariable Integer id, @RequestBody FuncionarioNovaSenha senha){
        String senhaNova = senha.getSenhaNova();
        funcionarioService.atualizarSenha(id, senhaNova);
        return ResponseEntity.ok().build();
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

    @PostMapping("/txt")
    public ResponseEntity<List<FuncionarioExibicaoDTO>> cadastroTxt(@RequestParam("files") List<MultipartFile> files){
        List<FuncionarioExibicaoDTO> listaUsersCadastrados = funcionarioService.toFila(files);
        if (listaUsersCadastrados.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(listaUsersCadastrados);
    }

    @PostMapping("/login")
    public ResponseEntity<FuncionarioTokenDto> login(@RequestBody FuncionarioLoginDto funcionarioLoginDto) {
        FuncionarioTokenDto funcionarioTokenDto = this.funcionarioService.autenticar(funcionarioLoginDto);
        return ResponseEntity.ok(funcionarioTokenDto);
    }

}
