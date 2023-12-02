package culinart.service.receita.receitaPreferencia;

import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.receitaPreferencia.ReceitaPreferencia;
import culinart.domain.receitaPreferencia.repository.ReceitaPreferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaPreferenciaService {

    private final ReceitaPreferenciaRepository receitaPreferenciaRepository;

    public List<ReceitaPreferencia> saveAll(List<ReceitaPreferencia> receitaPreferencias) {
        return this.receitaPreferenciaRepository.saveAll(receitaPreferencias);
    }

}
