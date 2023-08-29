package culinart.context;

import culinart.controller.UsuarioController;
import culinart.model.Usuario;
import org.springframework.http.ResponseEntity;

public interface IOperacao {
    ResponseEntity<Usuario> executarOperacao(Usuario usuario);
    default boolean verificarSeUsuarioJaExiste(Usuario usuario) {
        try {
            return (UsuarioController.listaDeUsuarios.get(usuario.getId().intValue() - 1) != null);
        }catch (NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
    }
}
