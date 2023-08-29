package culinart.strategy;

import culinart.context.IOperacao;
import culinart.controller.UsuarioController;
import culinart.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class LerUsuarioStrategy implements IOperacao {
    @Override
    public ResponseEntity executarOperacao(Usuario usuario) {
        int index = usuario.getId().intValue() - 1;
        if (verificarSeUsuarioJaExiste(usuario)) {
            return ResponseEntity.status(200).body(UsuarioController.listaDeUsuarios.get(index));
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<List<Usuario>> executarOperacaoLeituraTotal() {
        return ResponseEntity.status(200).body(UsuarioController.listaDeUsuarios);
    }
}
