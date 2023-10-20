package culinart.service.funcionario;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioCriacaoDTO;
import culinart.domain.fornecedor.dto.FuncionarioExibicaoDTO;
import culinart.domain.fornecedor.mapper.FuncionarioMapper;
import culinart.domain.fornecedor.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;


    public List<FuncionarioExibicaoDTO> listarFunc(){
        List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(FuncionarioMapper::of)
                .collect(Collectors.toList());
    }

    public FuncionarioExibicaoDTO cadastrarFuncionario(FuncionarioCriacaoDTO func){
        if (buscarFuncionarioPorBuscaBinaria(func.getEmail())) {
            throw new IllegalArgumentException("Usuario já cadastrado");
        }
        Funcionario novoFunc = FuncionarioMapper.of(func);
        String senhaCriptografada = passwordEncoder.encode(func.getSenha());
        novoFunc.setSenha(senhaCriptografada);
        return FuncionarioMapper.of(funcionarioRepository.save(novoFunc));
    }

    public FuncionarioExibicaoDTO atualizarFuncionario(Integer id, Funcionario funcionario) {
        Optional<Funcionario> func = funcionarioRepository.findById(id);
        if (func.isPresent()) {
            Funcionario funcionarioExistente = func.get();
            funcionarioExistente.setNome(funcionario.getNome());
            funcionarioExistente.setEmail(funcionario.getEmail());
            funcionarioExistente.setSenha(passwordEncoder.encode(funcionario.getSenha()));
            funcionarioExistente.setPermissao(funcionario.getPermissao());
            funcionarioExistente.setCpf(funcionario.getCpf());
            funcionarioExistente.setTel(funcionario.getTel());
            funcionarioExistente.setArea(funcionario.getArea());
            funcionarioExistente.setCargo(funcionario.getCargo());
            funcionarioExistente.setTurno(funcionario.getTurno());
            funcionarioExistente.setDataNascimento(funcionario.getDataNascimento());
            funcionarioExistente.setIsAtivo(funcionario.getIsAtivo());
            Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionarioExistente);

            return FuncionarioMapper.of(funcionarioAtualizado);
        } else {
            System.out.println("Funcionário não encontrado");
            return null;
        }
    }

    public String deletarFuncionario(Integer id){
        this.funcionarioRepository.deleteById(id);
        return "Deletado com sucesso";
    }

    public Boolean buscarFuncionarioPorBuscaBinaria(String email) {
        List<Funcionario> vetor = funcionarioRepository.findAll();
        for (int i = 0; i < vetor.toArray().length; i++) {
            if (vetor.get(i).getEmail().equals(email)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    public Boolean funcionarioIsEmpty(Integer id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.isEmpty();
    }

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
