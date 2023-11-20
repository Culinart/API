package culinart.domain.receita.dto.mapper;

import culinart.domain.avaliacao.dto.AvaliacaoResponseDTO;
import culinart.domain.avaliacao.mapper.AvaliacaoMapper;
import culinart.domain.email.ReceitaEmail;
import culinart.domain.ingrediente.dto.IngredienteExibicaoDTO;
import culinart.domain.ingrediente.dto.mapper.IngredienteMapper;
import culinart.domain.modoPreparo.dto.ModoPreparoExibicaoDTO;
import culinart.domain.modoPreparo.dto.mapper.ModoPreparoMapper;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaExibicaoDTO;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReceitaMapper {
    public static ReceitaExibicaoDTO toDTO(Receita receita) {
        if (receita == null) {
            return null; 
        }

        List<IngredienteExibicaoDTO> ingredientesDTO = receita.getIngredientes() != null
                ? receita.getIngredientes()
                .stream()
                .filter(Objects::nonNull)
                .map(IngredienteMapper::toDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();

        List<ModoPreparoExibicaoDTO> modoPreparosDTO = receita.getModoPreparos() != null
                ? receita.getModoPreparos()
                .stream()
                .filter(Objects::nonNull)
                .map(ModoPreparoMapper::toDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();

        List<AvaliacaoResponseDTO> avaliacaoDTO = receita.getAvaliacoes() != null
                ? receita.getAvaliacoes()
                .stream()
                .filter(Objects::nonNull)
                .map(AvaliacaoMapper::toDTO)
                .toList()
                : Collections.emptyList();

        Integer qtdAvaliacoes = avaliacaoDTO.size();

        Double mediaAvaliacoes = avaliacaoDTO.stream()
                .mapToDouble(AvaliacaoResponseDTO::getNota)
                .average()
                .orElse(0.0);

        return ReceitaExibicaoDTO.builder()
                .id(receita.getId())
                .nome(receita.getNome())
                .horas(receita.getHoras())
                .minutos(receita.getMinutos())
                .descricao(receita.getDescricao())
                .ingredientes(ingredientesDTO)
                .modoPreparos(modoPreparosDTO)
                .avaliacoes(avaliacaoDTO)
                .qtdAvaliacoes(qtdAvaliacoes)
                .mediaAvaliacoes(mediaAvaliacoes)
                .build();
    }

    public static ReceitaEmail toEmailDTO (Receita receita){
        return ReceitaEmail.builder()
                .titulo("Nova Receita Adicionada no sistema: " + receita.getNome())
                .conteudo(receita.getDescricao() + "\n Venha Conferir!!")
                .receita(receita)
                .build();
    }
}
