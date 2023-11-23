package culinart.integration.gerencianet;

import culinart.domain.plano.Plano;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.integration.gerencianet.mapper.PagamentoMapper;
import culinart.integration.gerencianet.repository.PagamentoRepository;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import culinart.integration.gerencianet.subscription.dto.PlanoDataDTO;
import culinart.integration.gerencianet.subscription.map.CancelSubscription;
import culinart.integration.gerencianet.subscription.map.CreateOneStepBilletSubscription;
import culinart.integration.gerencianet.subscription.map.CreatePlan;
import culinart.integration.gerencianet.subscription.map.DeletePlan;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;
    private final PagamentoRepository pagamentoRepository;

    public PlanoDataDTO criarPlano(){
        CreatePlan createPlan = new CreatePlan();
        return createPlan.criarPlano();
    }

    public PagamentoDTO criarPagamentoSubscricao(int idUsuario){


        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado para cobrar pagamento"));

        Plano plano = planoRepository.findByUsuario_Id(usuario.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado para cobrar pagamento"));

        CreateOneStepBilletSubscription createOneStepBilletSubscription = new CreateOneStepBilletSubscription();
        PagamentoDTO pagamentoDTO = createOneStepBilletSubscription.cobrarPagamento(usuario, plano);
        pagamentoRepository.save(PagamentoMapper.toEntity(pagamentoDTO,usuario));
        return pagamentoDTO;
    }

    public void cancelarAssinatura(int idAssinatura) {
        CancelSubscription cancelSubscription = new CancelSubscription();
        cancelSubscription.cancelarAssinatura(idAssinatura);
    }

    public void cancelarPlano(int idPlano) {
        DeletePlan deletePlan = new DeletePlan();
        deletePlan.cancelarPlano(idPlano);
    }
}
