package culinart.service.usuario.preferencia;

import culinart.domain.preferencia.Preferencia;
import culinart.domain.preferencia.dto.PreferenciaDelecaoDTO;
import culinart.domain.preferencia.repository.PreferenciaRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.domain.usuarioPreferencia.UsuarioPreferencia;
import culinart.domain.usuarioPreferencia.repository.UsuarioPreferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioPreferenciaService {

    private final UsuarioPreferenciaRepository usuarioPreferenciaRepository;
    private final PreferenciaRepository preferenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioPreferencia> exibirTodasPreferenciasDeUsuario(int idUsuario) {
        List<UsuarioPreferencia> usuarioPreferencias = usuarioPreferenciaRepository.findByUsuario_Id(idUsuario);
        if (usuarioPreferencias.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não possui preferencias");
        }
        return usuarioPreferencias;
    }

    public UsuarioPreferencia cadastrarPreferenciasDoUsuario(int idUsuario, int idPreferencia) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
        Preferencia preferencia = preferenciaRepository.findById(idPreferencia)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada no sistema"));

        UsuarioPreferencia usuarioPreferencia = new UsuarioPreferencia();
        usuarioPreferencia.setPreferencia(preferencia);
        usuarioPreferencia.setUsuario(usuario);
        return usuarioPreferenciaRepository.save(usuarioPreferencia);
    }

    public void deletarPreferenciasDoUsuario(int idUsuarioPreferencia) {
        Optional<UsuarioPreferencia> usuarioPreferencia = usuarioPreferenciaRepository.findById(idUsuarioPreferencia);

        if (usuarioPreferencia.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada no sistema");
        }

        usuarioPreferenciaRepository.deleteById(idUsuarioPreferencia);

    }

}
