package culinart.service.assinatura.pagamento;

import culinart.domain.assinatura.Assinatura;
import culinart.domain.assinatura.pagamento.Pagamento;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.integration.gerencianet.mapper.PagamentoMapper;
import culinart.integration.gerencianet.repository.PagamentoRepository;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import culinart.integration.gerencianet.subscription.dto.PlanoDataDTO;
import culinart.integration.gerencianet.subscription.map.CancelSubscription;
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

    public void cancelarAssinatura(int idAssinatura) {
        CancelSubscription cancelSubscription = new CancelSubscription();
        cancelSubscription.cancelarAssinatura(idAssinatura);
    }

    public void cancelarPlano(int idPlano) {
        DeletePlan deletePlan = new DeletePlan();
        deletePlan.cancelarPlano(idPlano);
    }


//    public PagamentoDTO atualizarStatusPagamento(int idUsuario) {
        //TODO: Implementar atualização de status de pagamento
//        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
//
//        Pagamento pagamento = pagamentoRepository.findByUsuario_Id(usuario.getId()).orElseThrow(() ->
//                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado"));
//
//        DetailSubscription detailSubscription = new DetailSubscription();
//        StatusPagamentoDTO statusPagamentoDTO = detailSubscription.atualizarStatusSubscricao(pagamento.getIdAssinatura());
//
//        if(statusPagamentoDTO.getStatusTransacao().equalsIgnoreCase("approved")
//                || statusPagamentoDTO.getStatusTransacao().equalsIgnoreCase("settled")){
//            pagamento.setStatusAssinatura(StatusAtivoEnum.ATIVO.getStatus());
//            pagamentoRepository.save(pagamento);
//        }else{
//            pagamento.setStatusAssinatura(StatusAtivoEnum.INATIVO.getStatus());
//            pagamentoRepository.save(pagamento);
//        }
//        return PagamentoMapper.toDTO(pagamento);
//    }

    public void criarPagamento(PagamentoDTO pagamento, Assinatura assinatura) {
        Pagamento pagamentoEntity = PagamentoMapper.toEntity(pagamento, assinatura);
        pagamentoRepository.save(pagamentoEntity);
    }
}
