package culinart.strategy;

import culinart.context.IOperacao;
import culinart.controller.UsuarioController;
import culinart.model.Usuario;
import org.springframework.http.ResponseEntity;

public class AtualizarUsuarioStrategy implements IOperacao {
    public ResponseEntity<Usuario> executarOperacaoPorId(Usuario usuario, Long idUsuario) {
        int index = idUsuario.intValue() - 1;

        if (index < 0 || index >= UsuarioController.listaDeUsuarios.size()) {
            return ResponseEntity.status(404).build(); // Índice inválido
        }

        UsuarioController.listaDeUsuarios.set(index, usuario);
        return ResponseEntity.status(200).body(usuario);
    }

    @Override
    public ResponseEntity executarOperacao(Usuario usuario) {
        if (verificarSeUsuarioJaExiste(usuario)) {
            int index = usuario.getId().intValue() - 1;
            UsuarioController.listaDeUsuarios.set(index, usuario);
            return ResponseEntity.status(200).body(usuario);
        }
        return ResponseEntity.status(404).build();
    }
}
