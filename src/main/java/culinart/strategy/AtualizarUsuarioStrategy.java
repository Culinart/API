package culinart.strategy;

import culinart.context.IOperacao;
import culinart.controller.UsuarioController;
import culinart.model.Usuario;
import org.springframework.http.ResponseEntity;

public class AtualizarUsuarioStrategy implements IOperacao {
    @Override
    public ResponseEntity executarOperacao(Usuario usuario) {
        if(verificarSeUsuarioJaExiste(usuario)){
            int index = usuario.getId().intValue() - 1;
            UsuarioController.listaDeUsuarios.set(index,usuario);
            return ResponseEntity.status(200).body(usuario);
        }
        return ResponseEntity.status(404).build();
    }
}
