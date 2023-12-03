package culinart.domain.dashboard.mapper;

import culinart.domain.dashboard.DashCategoria;
import culinart.domain.dashboard.DashPreferencia;

import java.util.ArrayList;
import java.util.List;

public class DashMapper {

    public static List<DashCategoria> mapToDashCategoriaList(List<Object[]> resultSet) {
        List<DashCategoria> categorias = new ArrayList<>();

        for (Object[] row : resultSet) {
            DashCategoria categoria = new DashCategoria();
            categoria.setCategoria((String) row[0]); // Assumindo que a categoria está na primeira posição do array
            Long totalLong = (Long) row[1];
            categoria.setQtd_escolhas(totalLong.intValue()); // Assumindo que a quantidade de escolhas está na segunda posição do array
            categorias.add(categoria);
        }

        return categorias;
    }

    public static List<DashPreferencia> mapToDashPreferenciaList(List<Object[]> resultSet) {
        List<DashPreferencia> listaPreferencia = new ArrayList<>();


        for (Object[] row : resultSet) {
            DashPreferencia preferencia = new DashPreferencia();
            preferencia.setPreferencia((String) row[0]); // Assumindo que a categoria está na primeira posição do array
            Long totalLong = (Long) row[1];
            preferencia.setQtd_escolhas(totalLong.intValue()); // Assumindo que a quantidade de escolhas está na segunda posição do array
            listaPreferencia.add(preferencia);
        }

        return listaPreferencia;
    }
}
