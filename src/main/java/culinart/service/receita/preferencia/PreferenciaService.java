package culinart.service.receita.preferencia;

import culinart.domain.preferencia.Preferencia;
import culinart.domain.preferencia.repository.PreferenciaRepository;
import culinart.domain.receita.Receita;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferenciaService {
    private final PreferenciaRepository preferenciaRepository;

    public List<Preferencia> exibirTodasPreferencia() {
        return preferenciaRepository.findAll();
    }

    public Preferencia exibirPreferenciaPorId(int id) {
        return preferenciaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada"));
    }

    public Preferencia cadastrarPreferencia(Preferencia preferencia) {
        if (preferenciaRepository.existsByNome(preferencia.getNome())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita já cadastrada");
        }

        return preferenciaRepository.save(preferencia);
    }

    public Preferencia atualizarPreferencia(int id, Preferencia preferencia) {
        if (preferenciaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada");
        }
        preferencia.setId(id);
        return preferenciaRepository.save(preferencia);
    }

    public void deletarPreferencia(int id) {
        if (preferenciaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada");
        }

        preferenciaRepository.delete(preferenciaRepository.findById(id).get());
    }
}
