package culinart.domain.pedido.mapper;

import culinart.domain.categoria.dto.CategoriaCardDto;
import culinart.domain.pedido.dto.PedidoByDataDto;
import culinart.domain.preferencia.dto.PreferenciaCardDto;
import culinart.domain.receita.dto.ReceitaExibicaoPedidoDto;
import culinart.utils.enums.StatusPedidoEnum;

import java.math.BigDecimal;
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
        String statusString = (String) resultSet.get(0)[3];
        pedido.setStatus(StatusPedidoEnum.valueOf((String) statusString));
        pedido.setLogradouro((String) resultSet.get(0)[4]);
        pedido.setNumero((Integer) resultSet.get(0)[5]);
        List<ReceitaExibicaoPedidoDto> receitas = resultSet.stream()
                .map(PedidoByDataMapper::mapToReceitaDto)
                .collect(Collectors.toList());

        pedido.setListaReceitas(receitas);
        return pedido;
    }

    private static ReceitaExibicaoPedidoDto mapToReceitaDto(Object[] row) {
        ReceitaExibicaoPedidoDto receita = new ReceitaExibicaoPedidoDto();
        receita.setId((Integer) row[6]);
        receita.setNome((String) row[7]);
        receita.setHoras((int) row[8]);
        receita.setMinutos((int) row[9]);
        receita.setQtd_porcoes(((Number) row[10]).intValue());
        receita.setImagem((String) row[11]);

        List<PreferenciaCardDto> preferencias = mapToPreferenciaDto(row);
        receita.setPreferencias(preferencias);

        List<CategoriaCardDto> categorias = mapToCategoriaDto(row);
        receita.setCategorias(categorias);
        BigDecimal bigDecimal = (BigDecimal) row[16];
        Double valorDouble = bigDecimal.doubleValue();
        receita.setMedia_notas(valorDouble);
        long valorLong = (long) row[17];
        receita.setQtd_avaliacoes(Long.valueOf(valorLong).intValue() );

        return receita;
    }

    private static List<PreferenciaCardDto> mapToPreferenciaDto(Object[] row) {
        String[] preferenciasNome = ((String) row[12]).split(",");
        String[] coresFundo = ((String) row[13]).split(",");
        String[] coresTexto = ((String) row[14]).split(",");

        List<PreferenciaCardDto> preferencias = new ArrayList<>();
        for (int i = 0; i < preferenciasNome.length; i++) {
            PreferenciaCardDto preferencia = new PreferenciaCardDto();
            preferencia.setNome(preferenciasNome[i]);
            preferencia.setCorFundo(coresFundo[i]);
            if (i < coresTexto.length) {
                preferencia.setCorTexto(coresTexto[i]);
            } else {
                preferencia.setCorTexto(coresTexto[0]);
            }
            preferencias.add(preferencia);
        }
        return preferencias;
    }

    private static List<CategoriaCardDto> mapToCategoriaDto(Object[] row) {
        String[] categoriasNome = ((String) row[15]).split(",");
        List<CategoriaCardDto> categorias = new ArrayList<>();
        for (String nome : categoriasNome) {
            CategoriaCardDto categoria = new CategoriaCardDto();
            categoria.setNome(nome);
            categorias.add(categoria);
        }
        return categorias;
    }
}
