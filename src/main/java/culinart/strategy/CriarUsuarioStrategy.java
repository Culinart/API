package culinart.strategy;

import culinart.context.IOperacao;
import culinart.controller.UsuarioController;
import culinart.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class CriarUsuarioStrategy implements IOperacao {
private Long idContador = 0L;
    @Override
    public ResponseEntity executarOperacao(Usuario usuario) {
        if (verificarSeUsuarioJaExiste(usuario) || !verificaLoginUsuarioEhValido(usuario)) {
            return ResponseEntity.status(400).build();
        }
        idContador++;
        usuario.setId(idContador);
        UsuarioController.listaDeUsuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

    private boolean verificaLoginUsuarioEhValido(Usuario usuario) {
        if (usuario.getLogin() != null && usuario.getLogin().length() >= 10) {
            if (!UsuarioController.listaDeUsuarios.isEmpty()) {
                for (Usuario usuarioDaVez : UsuarioController.listaDeUsuarios) {
                    if (usuario.getLogin().equals(usuarioDaVez.getLogin())) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
