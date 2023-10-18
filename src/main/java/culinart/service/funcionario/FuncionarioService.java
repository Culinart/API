package culinart.service.funcionario;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.domain.fornecedor.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioDTO> funcionariosOrdenadosDTO() {
        List<FuncionarioDTO> funcionariosDTO = buscarFuncionariosDTO();

        FuncionarioDTO[] funcionariosArray = funcionariosDTO.toArray(new FuncionarioDTO[0]);

        for (int i = 0; i < funcionariosArray.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < funcionariosArray.length; j++) {
                if (funcionariosArray[j].getNome().compareTo(funcionariosArray[indiceMenor].getNome()) < 0) {
                    indiceMenor = j;
                }
            }
            FuncionarioDTO aux = funcionariosArray[i];
            funcionariosArray[i] = funcionariosArray[indiceMenor];
            funcionariosArray[indiceMenor] = aux;
        }
        return List.of(funcionariosArray);
    }

    public List<FuncionarioDTO> buscarFuncionariosDTO() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        // Converter Funcionario em FuncionarioDTO
        List<FuncionarioDTO> funcionarioDTOs = funcionarios.stream()
                .map(funcionario -> {
                    FuncionarioDTO dto = new FuncionarioDTO();
                    dto.setNome(funcionario.getNome());
                    dto.setEmail(funcionario.getEmail());
                    return dto;
                })
                .collect(Collectors.toList());

        return funcionarioDTOs;
    }

}
