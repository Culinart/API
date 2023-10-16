package culinart.api.funcionario;

import culinart.domain.funcionario.Funcionario;
import culinart.domain.funcionario.dto.FuncionarioCriacaoDTO;
import culinart.domain.funcionario.dto.FuncionarioExibicaoDTO;
import culinart.domain.funcionario.repository.FuncionarioRepository;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.service.funcionario.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Funcionario")
public class FuncionarioController {

    private final FuncionarioService service;

    @GetMapping
    public ResponseEntity<List<FuncionarioExibicaoDTO>> listagemGeral() {
        List<FuncionarioExibicaoDTO> funcionarios = this.service.listarFunc();

        if (funcionarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<FuncionarioExibicaoDTO> cadastrarFuncionario(@RequestBody FuncionarioCriacaoDTO funcionarioCriacao) {
        try {

            return ResponseEntity.status(201).body(service.cadastrarFuncionario(funcionarioCriacao));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
