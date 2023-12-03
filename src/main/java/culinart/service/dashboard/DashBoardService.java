package culinart.service.dashboard;

import culinart.domain.avaliacao.repository.AvaliacaoRepository;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.utils.PilhaObj;
import lombok.RequiredArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final PlanoRepository planoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    public List<Object[]> getCategorias(){
        List<Object[]> result = planoRepository.findCategoriasMaisPresentes();
        if (result.isEmpty()){
            throw new IllegalArgumentException("CATEGORIAS VAZIAS");
        }
        return result;
    }

    public List<Object[]> getPreferencia(){
        List<Object[]> result = usuarioRepository.findPreferenciasMaisPresentes();
        if (result.isEmpty()){
            throw new IllegalArgumentException("PREFERENCIAS VAZIAS");
        }
        return result;
    }

    public List<Object[]> getReceitas(){
        List<Object[]> result = avaliacaoRepository.avaliacoesTxt();
        if (result.isEmpty()){
            throw new IllegalArgumentException("AVALIAÇÕES VAZIAS");
        }
        PilhaObj pilha = new PilhaObj(result.size());
        List<Object[]> resultOredenado = new ArrayList<>();
        for (int i = 0; i < result.size(); i++){
            pilha.push(i);
        }
        for (int i = pilha.getTopo(); i > -1; i--){
            resultOredenado.add(result.get(pilha.pop()));
        }
        return resultOredenado;
    }
}
