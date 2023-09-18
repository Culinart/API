package culinart.service.usuario;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioDTO;
import culinart.domain.usuario.mapper.UsuarioMapper;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.service.usuario.Utils.UsuarioUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<UsuarioDTO> criarUsuario(UsuarioDTO usuarioDTO) {

        if (!UsuarioUtils.validaEmailUsuarioDTO(usuarioDTO) ||
                !UsuarioUtils.validaNomeUsuarioDTO(usuarioDTO) ||
                !UsuarioUtils.validaSenhaUsuarioDTO(usuarioDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        final Usuario novoUsuario = UsuarioMapper.of(usuarioDTO);
        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    public ResponseEntity<UsuarioDTO> obterUsuarioPorId(Long id) {

        if(UsuarioUtils.validaIdUsuario(id,usuarioRepository)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.of(usuario.get()));
    }

    public ResponseEntity<UsuarioDTO> atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        return null;
    }

    public ResponseEntity<Void> deletarUsuario(Long id) {
        return null;
    }




}
