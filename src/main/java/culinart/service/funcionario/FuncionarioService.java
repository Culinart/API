package culinart.service.funcionario;

import culinart.domain.funcionario.Funcionario;
import culinart.domain.funcionario.dto.FuncionarioCriacaoDTO;
import culinart.domain.funcionario.dto.FuncionarioExibicaoDTO;
import culinart.domain.funcionario.mapper.FuncionarioMapper;
import culinart.domain.funcionario.repository.FuncionarioRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FuncionarioService {
    private final FuncionarioRepository repository;
    private final PasswordEncoder passwordEncoder;


    public List<FuncionarioExibicaoDTO> listarFunc(){
        List<Funcionario> funcionarios = this.repository.findAll();
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

        return FuncionarioMapper.of(novoFunc);
    }













    public Boolean buscarFuncionarioPorBuscaBinaria(String email) {
        List<Funcionario> vetor = repository.findAll();
        for (int i = 0; i < vetor.toArray().length; i++) {
            if (vetor.get(i).getEmail().equals(email)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
