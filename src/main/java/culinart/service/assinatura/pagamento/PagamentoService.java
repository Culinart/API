package culinart.service.assinatura.pagamento;

import culinart.domain.assinatura.Assinatura;
import culinart.domain.assinatura.pagamento.Pagamento;
import culinart.domain.assinatura.pagamento.dto.HistoryResponseDTO;
import culinart.domain.assinatura.pagamento.dto.StatusPagamentoDTO;
import culinart.domain.assinatura.repository.AssinaturaRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.integration.gerencianet.mapper.PagamentoMapper;
import culinart.integration.gerencianet.repository.PagamentoRepository;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import culinart.integration.gerencianet.subscription.map.DetailSubscription;
import culinart.utils.enums.StatusTransacao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {
    private final UsuarioRepository usuarioRepository;
    private final PagamentoRepository pagamentoRepository;
    private final AssinaturaRepository assinaturaRepository;

    public List<Pagamento> atualizarStatusPagamento(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        Assinatura assinatura = assinaturaRepository.findByUsuario_Id(usuario.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Assinatura não encontrada"));

        List<Pagamento> pagamentoList = pagamentoRepository.findByAssinatura_Id(assinatura.getId());

        DetailSubscription detailSubscription = new DetailSubscription();
        List<HistoryResponseDTO> historyResponseDTOList =
                detailSubscription.exibirListaDePagamentos(assinatura.getAssinaturaId());

        for (HistoryResponseDTO historyResponseDTO : historyResponseDTOList) {
            for (Pagamento pagamento : pagamentoList) {
                if (historyResponseDTO.getCharge_id().equals(pagamento.getTransacaoId())) {

                    //faça um switch case para setar o status do pagamento com todos os enum da casse StatusTransacao de acordo com o status da transação
                    switch (historyResponseDTO.getStatus()) {
                        case "new":
                            pagamento.setStatusTransacao(StatusTransacao.NOVO.obterStatus());
                            break;
                        case "approved":
                            pagamento.setStatusTransacao(StatusTransacao.APROVADO.obterStatus());
                            break;
                        case "paid":
                            pagamento.setStatusTransacao(StatusTransacao.PAGO.obterStatus());
                            break;
                        case "unpaid":
                            pagamento.setStatusTransacao(StatusTransacao.NAO_PAGO.obterStatus());
                            break;
                        case "canceled":
                            pagamento.setStatusTransacao(StatusTransacao.CANCELADO.obterStatus());
                            break;
                        case "expired":
                            pagamento.setStatusTransacao(StatusTransacao.EXPIRADO.obterStatus());
                            break;
                        case "waiting":
                            pagamento.setStatusTransacao(StatusTransacao.AGUARDANDO.obterStatus());
                            break;
                        case "contested":
                            pagamento.setStatusTransacao(StatusTransacao.CONTESTADO.obterStatus());
                            break;
                        case "settled":
                            pagamento.setStatusTransacao(StatusTransacao.MARCAR_COMO_PAGO.obterStatus());
                            break;
                        case "link":
                            pagamento.setStatusTransacao(StatusTransacao.LINK.obterStatus());
                            break;
                        case "refunded":
                            pagamento.setStatusTransacao(StatusTransacao.DEVOLVIDO.obterStatus());
                            break;
                        case "identified":
                            pagamento.setStatusTransacao(StatusTransacao.IDENTIFICADO.obterStatus());
                            break;
                        default:
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status de pagamento inválido");
                    }

                    pagamentoRepository.save(pagamento);
                }
            }
        }
        return pagamentoList;
    }

    public void criarPagamento(PagamentoDTO pagamento, Assinatura assinatura) {
        Pagamento pagamentoEntity = PagamentoMapper.toEntity(pagamento, assinatura);
        pagamentoRepository.save(pagamentoEntity);
    }

    public Boolean verificarSeExisteDoisPagamentosPendentes(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        Assinatura assinatura = assinaturaRepository.findByUsuario_Id(usuario.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Assinatura não encontrada"));

        List<Pagamento> pagamentoList = pagamentoRepository.findByAssinatura_Id(assinatura.getId());

        if (pagamentoList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado");
        }

        List<Pagamento> pagamentosPendentes = pagamentoRepository.findByStatusTransacao(StatusTransacao.NAO_PAGO.obterStatus());
        if (pagamentosPendentes.size() > 1) {
            return true;
        }
        return false;
    }
}
