package culinart.controller;

import culinart.context.IOperacao;
import culinart.context.OperacaoEnum;
import culinart.model.Usuario;
import culinart.service.UsuarioService;
import culinart.strategy.AtualizarUsuarioStrategy;
import culinart.strategy.CriarUsuarioStrategy;
import culinart.strategy.ExcluirUsuarioStrategy;
import culinart.strategy.LerUsuarioStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    public static List<Usuario> listaDeUsuarios = new ArrayList<>();
    private final UsuarioService usuarioService;

    private static final CriarUsuarioStrategy criar = new CriarUsuarioStrategy();
    private static final LerUsuarioStrategy ler = new LerUsuarioStrategy();
    private static final AtualizarUsuarioStrategy atualizar = new AtualizarUsuarioStrategy();
    private static final ExcluirUsuarioStrategy excluir = new ExcluirUsuarioStrategy();

    @Autowired
    public UsuarioController() {
        List<IOperacao> listDeOperacoes = Arrays.asList(criar, ler, atualizar, excluir);
        this.usuarioService = new UsuarioService(listDeOperacoes);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.executarOperacao(OperacaoEnum.CRIAR, usuario);
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> lerUsuario() {
        return usuarioService.executarOperacao(OperacaoEnum.LER);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> lerUsuario(@PathVariable Long idUsuario) {
        int index = idUsuario.intValue() - 1;
        if (index < 0 || index >= listaDeUsuarios.size()) {
            return ResponseEntity.status(404).build(); // Índice inválido
        }
        Usuario usuario = listaDeUsuarios.get(index);
        return ResponseEntity.status(200).body(usuario);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long idUsuario) {
        return usuarioService.executarOperacao(OperacaoEnum.ATUALIZAR, usuario, idUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Usuario> excluirUsuario(@PathVariable Long idUsuario) {
        return usuarioService.executarOperacao(OperacaoEnum.EXCLUIR, retornaUsuarioPorId(idUsuario).getBody());
    }

    private ResponseEntity<Usuario> retornaUsuarioPorId(Long idUsuario) {
        int index = idUsuario.intValue() - 1;
        if (index < 0 || index >= listaDeUsuarios.size()) {
            return ResponseEntity.status(404).build(); // Índice inválido
        }
        return ResponseEntity.status(200).body(listaDeUsuarios.get(index));
    }
}
