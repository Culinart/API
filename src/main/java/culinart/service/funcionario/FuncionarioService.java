package culinart.service.funcionario;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.domain.fornecedor.repository.FuncionarioRepository;
import culinart.utils.ListaObj;
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
        ListaObj<FuncionarioDTO> vetorFuncionarios = new ListaObj<>(funcionariosDTO.size());

        for (FuncionarioDTO funcionarioDTO : funcionariosDTO) {
            vetorFuncionarios.adiciona(funcionarioDTO);
        }

        ordenarVetor(vetorFuncionarios);

        return vetorFuncionarios.toList();
    }

    private void ordenarVetor(ListaObj<FuncionarioDTO> vetorFuncionarios) {
        for (int i = 0; i < vetorFuncionarios.getTamanho() - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < vetorFuncionarios.getTamanho(); j++) {
                if (vetorFuncionarios.getElemento(j).getNome().compareTo(vetorFuncionarios.getElemento(indiceMenor).getNome()) < 0) {
                    indiceMenor = j;
                }
            }
            FuncionarioDTO aux = vetorFuncionarios.getElemento(i);
            vetorFuncionarios.setElemento(i, vetorFuncionarios.getElemento(indiceMenor));
            vetorFuncionarios.setElemento(indiceMenor, aux);
        }
    }

    public List<FuncionarioDTO> buscarFuncionariosDTO() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

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
