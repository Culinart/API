package culinart.domain.fornecedor.mapper;

import culinart.domain.fornecedor.Funcionario;

import culinart.domain.fornecedor.dto.FuncionarioCriacaoDTO;
import culinart.domain.fornecedor.dto.FuncionarioExibicaoDTO;
import culinart.domain.fornecedor.dto.FuncionarioTokenDto;
import culinart.utils.enums.StatusAtivoEnum;

import java.time.LocalDate;


public class FuncionarioMapper {
    public static Funcionario of(FuncionarioCriacaoDTO funcionarioCriacaoDTO) {
        Funcionario func = new Funcionario();

        func.setNome(funcionarioCriacaoDTO.getNome());
        func.setEmail(funcionarioCriacaoDTO.getEmail());
        func.setDataContratacao(funcionarioCriacaoDTO.getDataContratacao());
        func.setCpf(funcionarioCriacaoDTO.getCpf());
        func.setTel(funcionarioCriacaoDTO.getTel());
        func.setDataNascimento(funcionarioCriacaoDTO.getDataNascimento());
        func.setRg(funcionarioCriacaoDTO.getRg());
        func.setCargo(funcionarioCriacaoDTO.getCargo());
        func.setTurno(funcionarioCriacaoDTO.getTurno());
        func.setPermissao(funcionarioCriacaoDTO.getPermissao());
        func.setSenha("sptech123");
        func.setIsAtivo(StatusAtivoEnum.ATIVO);
        func.setDataCriacao(LocalDate.now());

        return func;
    }

    public static FuncionarioExibicaoDTO of(Funcionario funcionario) {
        FuncionarioExibicaoDTO FuncionarioExibicaoDTO = new FuncionarioExibicaoDTO();
        FuncionarioExibicaoDTO.setId(funcionario.getId());
        FuncionarioExibicaoDTO.setNome(funcionario.getNome());
        FuncionarioExibicaoDTO.setEmail(funcionario.getEmail());
        FuncionarioExibicaoDTO.setCpf(funcionario.getCpf());
        FuncionarioExibicaoDTO.setTel(funcionario.getTel());
        FuncionarioExibicaoDTO.setCargo(funcionario.getCargo());
        FuncionarioExibicaoDTO.setTurno(funcionario.getTurno());
        FuncionarioExibicaoDTO.setPermissao(funcionario.getPermissao());

        return FuncionarioExibicaoDTO;
    }

    public static FuncionarioTokenDto toFuncionarioTokenDto(Funcionario func, String token){
        FuncionarioTokenDto funcToken = new FuncionarioTokenDto();
        funcToken.setFuncId(func.getId());
        funcToken.setNome(func.getNome());
        funcToken.setTelefone(func.getTel());
        funcToken.setPermissao(func.getPermissao());
        funcToken.setEmail(func.getEmail());
        funcToken.setToken(token);
        return funcToken;
    }
}
