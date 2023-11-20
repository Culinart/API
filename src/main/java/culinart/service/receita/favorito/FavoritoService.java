package culinart.service.receita.favorito;

import culinart.domain.favorito.Favorito;
import culinart.domain.favorito.repository.FavoritoRepository;
import culinart.domain.receita.Receita;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final ReceitaRepository receitaRepository;
    private final UsuarioRepository usuarioRepository;

    public Favorito adcionarFavorito(int idReceita, int idUsuario) {

        Receita receita = receitaRepository.findById(idReceita).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrada"));

        if(favoritoRepository.existsByUsuario_IdAndReceita_Id(idUsuario, idReceita)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receita já favoritada pelo usuário");
        }

        Favorito favorito = new Favorito();
        favorito.setReceita(receita);
        favorito.setUsuario(usuario);
        return favoritoRepository.save(favorito);
    }

    public List<Favorito> listarFavoritos() {
        return favoritoRepository.findAll();
    }
}
