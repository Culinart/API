package culinart.service.receita.modoPreparo;

import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.modoPreparo.repository.ModoPreparoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModoPreparoService {
    private final ModoPreparoRepository modoPreparoRepository;

    public List<ModoPreparo> exibirTodosModosPreparos() {
        return modoPreparoRepository.findAll();
    }

    public ModoPreparo exibirModoPreparoPorId(int id) {
        return modoPreparoRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modo de Preparo não encontrado"));
    }


    public ModoPreparo cadastrarModoPreparo(ModoPreparo modoPreparo) {
        return modoPreparoRepository.save(modoPreparo);
    }

    public ModoPreparo atualizarModoPreparo(int id, ModoPreparo modoPreparoNovo) {
        if (modoPreparoRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passo não existe");
        }
        modoPreparoNovo.setId(id);
        return modoPreparoRepository.save(modoPreparoNovo);
    }

    public void deletarModoPreparo(int id) {
        Optional<ModoPreparo> modoPreparoOptional = modoPreparoRepository.findById(id);
        if (modoPreparoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Passo não existe");
        }

        modoPreparoRepository.delete(modoPreparoOptional.get());
    }

    public List<ModoPreparo> saveAll(List<ModoPreparo> modoPreparos) {
        return modoPreparoRepository.saveAll(modoPreparos);
    }
}

