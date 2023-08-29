package culinart.strategy;

import culinart.context.IOperacao;
import culinart.controller.UsuarioController;
import culinart.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CriarUsuarioStrategy implements IOperacao {

    @Override
    public ResponseEntity executarOperacao(Usuario usuario) {
        if (verificarSeUsuarioJaExiste(usuario) || usuario.getId() <= 0) {
            return ResponseEntity.status(400).build();
        }
        UsuarioController.listaDeUsuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

}
