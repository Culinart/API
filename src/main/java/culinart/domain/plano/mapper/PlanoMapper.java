package culinart.domain.plano.mapper;

import culinart.domain.plano.Plano;
import culinart.domain.plano.dto.PlanoCadastroDTO;
import culinart.domain.plano.dto.PlanoExibicaoDTO;
import culinart.domain.usuario.dto.mapper.UsuarioMapper;
import culinart.utils.enums.DiaSemanaEnum;

import java.util.ArrayList;

public class PlanoMapper {
    public static PlanoExibicaoDTO toDTO(Plano plano) {
        return new PlanoExibicaoDTO(
                plano.getId(),
                plano.getPreferences(),
                plano.getQtdPessoas(),
                plano.getQtdRefeicoesDia(),
                plano.getQtdDiasSemana(),
                plano.getHoraEntrega(),
                plano.getDiaSemana(),
                plano.getIsAtivo(),
                UsuarioMapper.toDTO(plano.getUsuario())
        );
    }

    public static Plano toEntity(PlanoCadastroDTO planoDTO) {
        return new Plano(
                planoDTO.getId(),
                new ArrayList<>(),
                planoDTO.getQtdPessoas(),
                planoDTO.getQtdRefeicoesDia(),
                planoDTO.getValorPlano(),
                planoDTO.getValorAjuste(),
                planoDTO.getQtdDiasSemana(),
                planoDTO.getHoraEntrega(),
                DiaSemanaEnum.valueOf(planoDTO.getDiaSemana().toUpperCase()),
                planoDTO.getIsAtivo(),
                planoDTO.getUsuario()
        );
    }
}
