package culinart.service.usuario;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioDTO;
import culinart.domain.usuario.dto.UsuarioDetalhesDto;
import culinart.domain.usuario.mapper.UsuarioMapper;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.service.usuario.Utils.UsuarioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean buscaNaoPossuiResultado(){
        return usuarioRepository.findAll().isEmpty();
    }

    public boolean filmeIsPresent(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.isPresent();
    }

    public Usuario cadastrarFilme(Usuario usuario){
        if(buscarFilmePorBuscaBinaria(usuario.getEmail())){
            throw new IllegalArgumentException("Usuario já cadastrado");
        }
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioDetalhesDto> listarTodosOsFilmes() {
        return UsuarioMapper.of(usuarioRepository.findAll());
    }

    public Usuario listarFilmePorId(Long id){
        return usuarioRepository.findById(id).get();
    }

    public boolean buscarFilmePorBuscaBinaria(String nomeDoFilme) {
        List<Usuario> vetor = usuarioRepository.findAll();
        for (int i = 0; i < vetor.toArray().length; i++) {
            if (vetor.get(i).getEmail().equals(nomeDoFilme)) {   // se encontrou
                return true;                     // retorna seu índice
            }
        }
        return false;
    }

    public Usuario atualizarFilme(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deletarFilme(Long id) {
        usuarioRepository.delete(usuarioRepository.findById(id).get());
    }
}
