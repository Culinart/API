package culinart.service.receita;

import culinart.domain.receita.Receita;
import culinart.domain.receita.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaService {
    private final ReceitaRepository receitaRepository;

    public List<Receita> exibirTodasReceitas() {
        return receitaRepository.findAll();
    }

    public Receita exibirReceitaPorId(int id) {
        return receitaRepository.findById(id).orElseThrow();
    }

    public Receita cadastrarReceita(Receita receita) {
        if(receitaRepository.existsByNome(receita.getNome())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Receita já cadastrada");
        }

        return receitaRepository.save(receita);
    }

    public Receita atualizarReceita(int id, Receita receita) {
        if(receitaRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Receita não encontrada");
        }
        receita.setId(id);
        return receitaRepository.save(receita);
    }

    public void deletarReceita(int id) {
        if(receitaRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Receita não encontrada");
        }

        receitaRepository.delete(receitaRepository.findById(id).get());
    }
}
