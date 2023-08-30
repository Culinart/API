package culinart.service;

import culinart.context.IOperacao;
import culinart.context.OperacaoEnum;
import culinart.model.Usuario;
import culinart.strategy.AtualizarUsuarioStrategy;
import culinart.strategy.CriarUsuarioStrategy;
import culinart.strategy.ExcluirUsuarioStrategy;
import culinart.strategy.LerUsuarioStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final Map<OperacaoEnum, IOperacao> estrategias;

    public UsuarioService(List<IOperacao> estrategias) {
        this.estrategias = estrategias.stream()
                .collect(Collectors.toMap(this::determinarOperacao, Function.identity()));
    }

    public ResponseEntity<Usuario> executarOperacao(OperacaoEnum operacao, Usuario usuario) throws IllegalArgumentException {
        IOperacao estrategia = estrategias.get(operacao);
        if (estrategia != null) {
            return estrategia.executarOperacao(usuario);
        }
        throw new IllegalArgumentException("Operação não suportada: " + operacao);
    }

    public ResponseEntity<Usuario> executarOperacao(OperacaoEnum operacao, Usuario usuario,Long idUsuario) throws IllegalArgumentException {
        IOperacao estrategia = estrategias.get(operacao);
        if (estrategia != null) {
            AtualizarUsuarioStrategy atualizarUsuarioStrategy = new AtualizarUsuarioStrategy();
            return atualizarUsuarioStrategy.executarOperacaoPorId(usuario, idUsuario);
        }
        throw new IllegalArgumentException("Operação não suportada: " + operacao);
    }

    public ResponseEntity<List<Usuario>> executarOperacao(OperacaoEnum operacao) throws IllegalArgumentException {
        IOperacao estrategia = estrategias.get(operacao);
        if (estrategia != null) {
            return new LerUsuarioStrategy().executarOperacaoLeituraTotal();
        }
        throw new IllegalArgumentException("Operação não suportada: " + operacao);
    }

    private OperacaoEnum determinarOperacao(IOperacao estrategia) {
        if (estrategia instanceof CriarUsuarioStrategy) {
            return OperacaoEnum.CRIAR;
        } else if (estrategia instanceof LerUsuarioStrategy) {
            return OperacaoEnum.LER;
        } else if (estrategia instanceof AtualizarUsuarioStrategy) {
            return OperacaoEnum.ATUALIZAR;
        } else if (estrategia instanceof ExcluirUsuarioStrategy) {
            return OperacaoEnum.EXCLUIR;
        }
        throw new IllegalArgumentException("Estratégia de operação desconhecida: " + estrategia.getClass().getName());
    }
}
