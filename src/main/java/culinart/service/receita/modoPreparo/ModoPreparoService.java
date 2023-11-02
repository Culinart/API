package culinart.service.receita.modoPreparo;

import culinart.domain.receita.modoPreparo.repository.ModoPreparoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModoPreparoService {
    private final ModoPreparoRepository modoPreparoRepository;
}
