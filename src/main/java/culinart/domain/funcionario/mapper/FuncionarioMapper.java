package culinart.domain.funcionario.mapper;

import culinart.domain.funcionario.Funcionario;

import culinart.domain.funcionario.dto.FuncionarioCriacaoDTO;
import culinart.domain.funcionario.dto.FuncionarioExibicaoDTO;
import culinart.domain.usuario.Usuario;


public class FuncionarioMapper {
    public static Funcionario of(FuncionarioCriacaoDTO funcionarioCriacaoDTO) {
        Funcionario func = new Funcionario();

        func.setNome(funcionarioCriacaoDTO.getNome());
        func.setEmail(funcionarioCriacaoDTO.getEmail());
        func.setSenha(funcionarioCriacaoDTO.getSenha());
        func.setPermissao(funcionarioCriacaoDTO.getPermissao());
        func.setCpf(funcionarioCriacaoDTO.getCpf());
        func.setTel(funcionarioCriacaoDTO.getTel());
        func.setDataNascimento(funcionarioCriacaoDTO.getDataNascimento());
        func.setArea(funcionarioCriacaoDTO.getArea());
        func.setCargo(funcionarioCriacaoDTO.getCargo());
        func.setTurno(funcionarioCriacaoDTO.getTurno());
        func.setIsAtivo(1);

        return func;
    }

    public static FuncionarioExibicaoDTO of(Funcionario funcionario) {
        FuncionarioExibicaoDTO FuncionarioExibicaoDTO = new FuncionarioExibicaoDTO();

        FuncionarioExibicaoDTO.setNome(funcionario.getNome());
        FuncionarioExibicaoDTO.setEmail(funcionario.getEmail());
        FuncionarioExibicaoDTO.setPermissao(funcionario.getPermissao());
        FuncionarioExibicaoDTO.setCpf(funcionario.getCpf());
        FuncionarioExibicaoDTO.setTel(funcionario.getTel());
        FuncionarioExibicaoDTO.setArea(funcionario.getArea());
        FuncionarioExibicaoDTO.setCargo(funcionario.getCargo());
        FuncionarioExibicaoDTO.setTurno(funcionario.getTurno());

        return FuncionarioExibicaoDTO;
    }
}
