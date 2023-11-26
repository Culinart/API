package culinart.domain.pedido.mapper;

import culinart.domain.categoria.dto.CategoriaCardDto;
import culinart.domain.pedido.dto.PedidoByDataDto;
import culinart.domain.preferencia.dto.PreferenciaCardDto;
import culinart.domain.receita.dto.ReceitaExibicaoPedidoDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoByDataMapper {

    public static PedidoByDataDto toPedidoByDataDto(List<Object[]>resultSet) {
        PedidoByDataDto pedido = new PedidoByDataDto();
        pedido.setId((Integer) resultSet.get(0)[0]);
        pedido.setValor((Double) resultSet.get(0)[1]);
        java.sql.Date dataSql = (java.sql.Date) resultSet.get(0)[2];
        pedido.setDataEntrega(dataSql.toLocalDate());

        List<ReceitaExibicaoPedidoDto> receitas = resultSet.stream()
                .map(PedidoByDataMapper::mapToReceitaDto)
                .collect(Collectors.toList());

        pedido.setListaReceitas(receitas);
        return pedido;
    }

    private static ReceitaExibicaoPedidoDto mapToReceitaDto(Object[] row) {
        ReceitaExibicaoPedidoDto receita = new ReceitaExibicaoPedidoDto();
        receita.setId((Integer) row[3]);
        receita.setNome((String) row[4]);
        receita.setHoras((int) row[5]);
        receita.setMinutos((int) row[6]);
        receita.setQtd_porcoes(((Number) row[7]).intValue());

        List<PreferenciaCardDto> preferencias = mapToPreferenciaDto(row);
        receita.setPreferencias(preferencias);

        List<CategoriaCardDto> categorias = mapToCategoriaDto(row);
        receita.setCategorias(categorias);

        return receita;
    }

    private static List<PreferenciaCardDto> mapToPreferenciaDto(Object[] row) {
        String[] preferenciasNome = ((String) row[8]).split(",");
        String[] coresFundo = ((String) row[9]).split(",");
        String[] coresTexto = ((String) row[10]).split(",");

        List<PreferenciaCardDto> preferencias = new ArrayList<>();
        for (int i = 0; i < preferenciasNome.length; i++) {
            PreferenciaCardDto preferencia = new PreferenciaCardDto();
            preferencia.setNome(preferenciasNome[i]);
            preferencia.setCorFundo(coresFundo[i]);
            preferencia.setCorTexto(coresTexto[i]);
            preferencias.add(preferencia);
        }
        return preferencias;
    }

    private static List<CategoriaCardDto> mapToCategoriaDto(Object[] row) {
        String[] categoriasNome = ((String) row[11]).split(",");
        List<CategoriaCardDto> categorias = new ArrayList<>();
        for (String nome : categoriasNome) {
            CategoriaCardDto categoria = new CategoriaCardDto();
            categoria.setNome(nome);
            categorias.add(categoria);
        }
        return categorias;
    }
}
