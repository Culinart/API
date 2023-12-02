package culinart.domain.receita.dto.mapper;

import culinart.domain.avaliacao.dto.AvaliacaoResponseDTO;
import culinart.domain.avaliacao.mapper.AvaliacaoMapper;
import culinart.domain.email.ReceitaEmail;
import culinart.domain.ingrediente.dto.IngredienteExibicaoDTO;
import culinart.domain.ingrediente.dto.mapper.IngredienteMapper;
import culinart.domain.modoPreparo.dto.ModoPreparoExibicaoDTO;
import culinart.domain.modoPreparo.dto.mapper.ModoPreparoMapper;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaCadastroDTO;
import culinart.domain.receita.dto.ReceitaExibicaoDTO;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaExibicaoDTO;
import culinart.domain.receitaCategoria.mapper.ReceitaCategoriaMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReceitaMapper {

    public static ReceitaExibicaoDTO toDTO(Receita receita) {
        if (receita == null) {
            return null;
        }

        List<IngredienteExibicaoDTO> ingredientesDTO = getIngredientesDTO(receita);
        List<ModoPreparoExibicaoDTO> modoPreparosDTO = getModoPreparosDTO(receita);
        List<AvaliacaoResponseDTO> avaliacaoDTO = getAvaliacaoDTO(receita);

        Integer qtdAvaliacoes = avaliacaoDTO.size();
        Double mediaAvaliacoes = avaliacaoDTO.isEmpty() ? 0.0 : avaliacaoDTO.stream()
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
                .categorias(getCategoriasDTO(receita))
                .modoPreparos(modoPreparosDTO)
                .avaliacoes(avaliacaoDTO)
                .qtdAvaliacoes(qtdAvaliacoes)
                .mediaAvaliacoes(mediaAvaliacoes)
                .build();
    }

    private static List<IngredienteExibicaoDTO> getIngredientesDTO(Receita receita) {
        return receita.getIngredientes() != null
                ? receita.getIngredientes()
                .stream()
                .filter(Objects::nonNull)
                .map(IngredienteMapper::toDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<ModoPreparoExibicaoDTO> getModoPreparosDTO(Receita receita) {
        return receita.getModoPreparos() != null
                ? receita.getModoPreparos()
                .stream()
                .filter(Objects::nonNull)
                .map(ModoPreparoMapper::toDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    private static List<AvaliacaoResponseDTO> getAvaliacaoDTO(Receita receita) {
        return receita.getAvaliacoes() != null
                ? receita.getAvaliacoes()
                .stream()
                .filter(Objects::nonNull)
                .map(AvaliacaoMapper::toDTO)
                .toList()
                : Collections.emptyList();
    }

    private static List<ReceitaCategoriaExibicaoDTO> getCategoriasDTO(Receita receita) {
        return receita.getReceitaCategorias() != null
                ? receita.getReceitaCategorias()
                .stream()
                .filter(Objects::nonNull)
                .map(ReceitaCategoriaMapper::toDTO)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    public static Receita toEntity(ReceitaCadastroDTO receitaCadastroDTO) {
        if (receitaCadastroDTO == null) {
            return null;
        }

        return Receita.builder()
                .nome(receitaCadastroDTO.getNome())
                .horas(receitaCadastroDTO.getHoras())
                .minutos(receitaCadastroDTO.getMinutos())
                .descricao(receitaCadastroDTO.getDescricao())
                .qtdPorcoes(receitaCadastroDTO.getQtdPorcoes())
                .imagem(receitaCadastroDTO.getImagem())
                .ingredientes(receitaCadastroDTO.getIngredientes())
                .modoPreparos(receitaCadastroDTO.getModoPreparos())
                .receitaCategorias(new ArrayList<>())
                .avaliacoes(new ArrayList<>())
                .receitaPreferencias(new ArrayList<>())
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
