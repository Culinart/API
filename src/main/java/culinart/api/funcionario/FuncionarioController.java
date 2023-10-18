package culinart.api.funcionario;

import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.service.funcionario.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
