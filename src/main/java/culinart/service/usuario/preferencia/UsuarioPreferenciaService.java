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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioPreferenciaService {

    private final UsuarioPreferenciaRepository usuarioPreferenciaRepository;
    private final PreferenciaRepository preferenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioPreferencia exibirTodasPreferenciasDeUsuario(int idUsuario) {
        return usuarioPreferenciaRepository.findByUsuario_Id(idUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencias de usuario não encontrada")
        );
    }

    public UsuarioPreferencia cadastrarPreferenciasDoUsuario(int idUsuario, int idUsuarioPreferencia) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
        Preferencia preferencia = preferenciaRepository.findById(idUsuarioPreferencia)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada no sistema"));

        Optional<UsuarioPreferencia> usuarioPreferenciaOptional = usuarioPreferenciaRepository.findByUsuario_Id(idUsuario);

        if (usuarioPreferenciaOptional.isEmpty()) {
            UsuarioPreferencia usuarioPreferencia = new UsuarioPreferencia();
            usuarioPreferencia.setPreferencias(new ArrayList<>());
            usuarioPreferencia.getPreferencias().add(preferencia);
            usuarioPreferencia.setUsuario(usuario);
            return usuarioPreferenciaRepository.save((usuarioPreferencia));
        }

        for (Preferencia preferenciaDaVez : usuarioPreferenciaOptional.get().getPreferencias()) {
            if (preferenciaDaVez.getNome().equals(preferencia.getNome())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preferencia já está associada ao usuario");
            }
        }

        UsuarioPreferencia usuarioPreferencia = usuarioPreferenciaOptional.get();
        usuarioPreferencia.getPreferencias().add(preferencia);
        return usuarioPreferenciaRepository.save((usuarioPreferencia));
    }

    public void deletarPreferenciasDoUsuario(int idUsuario, int idUsuarioPreferencia) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
        Preferencia preferencia = preferenciaRepository.findById(idUsuarioPreferencia )
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada no sistema"));

        Optional<UsuarioPreferencia> usuarioPreferenciaOptional = usuarioPreferenciaRepository.findByUsuario_Id(idUsuario);

        if (usuarioPreferenciaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia de usuario não encontrado para deleção");
        }

        UsuarioPreferencia usuarioPreferencia = usuarioPreferenciaOptional.get();
        usuarioPreferencia.getPreferencias().add(preferencia);
        usuarioPreferenciaRepository.delete((usuarioPreferencia));
    }
}
