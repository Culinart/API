package culinart.domain.fornecedor.mapper;

import culinart.domain.fornecedor.Funcionario;

import culinart.domain.fornecedor.dto.FuncionarioCriacaoDTO;
import culinart.domain.fornecedor.dto.FuncionarioExibicaoDTO;

import java.time.LocalDate;


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
        func.setDataCriacao(LocalDate.now());

        return func;
    }

    public static FuncionarioExibicaoDTO of(Funcionario funcionario) {
        FuncionarioExibicaoDTO FuncionarioExibicaoDTO = new FuncionarioExibicaoDTO();
        FuncionarioExibicaoDTO.setId(funcionario.getId());
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
