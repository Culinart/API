package culinart.service.assinatura;

import culinart.domain.assinatura.Assinatura;
import culinart.domain.assinatura.dto.AssinaturaComPagamentoDTO;
import culinart.domain.assinatura.mapper.AssinaturaMapper;
import culinart.domain.assinatura.repository.AssinaturaRepository;
import culinart.domain.plano.Plano;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import culinart.integration.gerencianet.subscription.dto.PlanoDataDTO;
import culinart.integration.gerencianet.subscription.map.CancelSubscription;
import culinart.integration.gerencianet.subscription.map.CreateOneStepBilletSubscription;
import culinart.integration.gerencianet.subscription.map.CreatePlan;
import culinart.integration.gerencianet.subscription.map.DeletePlan;
import culinart.service.assinatura.pagamento.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AssinaturaService {
    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;
    private final PagamentoService pagamentoService;

    public PlanoDataDTO criarPlano(){
        CreatePlan createPlan = new CreatePlan();
        return createPlan.criarPlano();
    }

    public void cancelarAssinatura(int idAssinatura) {
        CancelSubscription cancelSubscription = new CancelSubscription();
        cancelSubscription.cancelarAssinatura(idAssinatura);
    }

    public void cancelarPlano(int idPlano) {
        DeletePlan deletePlan = new DeletePlan();
        deletePlan.cancelarPlano(idPlano);
    }

    public PagamentoDTO criarAssinatura(int idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado para cobrar pagamento"));

        Plano plano = planoRepository.findByUsuario_Id(usuario.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado para cobrar pagamento"));

        CreateOneStepBilletSubscription createOneStepBilletSubscription = new CreateOneStepBilletSubscription();
        AssinaturaComPagamentoDTO debit = createOneStepBilletSubscription.criarSubscricao(usuario, plano);

        Assinatura assinatura = assinaturaRepository.save(AssinaturaMapper.toEntity(debit.getAssinatura(), usuario));
        pagamentoService.criarPagamento(debit.getPagamento(), assinatura);

        return debit.getPagamento();
    }
}
