package culinart.service.pedido;


import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.repository.PedidoRepository;
import culinart.domain.plano.Plano;
import culinart.domain.plano.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private PlanoRepository planoRepository;

    public PedidoService(PedidoRepository pedidoRepository, PlanoRepository planoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.planoRepository = planoRepository;
    }

    public Optional<Pedido> nextPedido(Integer idUser){
        return pedidoRepository.acharProximoPedidoUser(idUser);

    }
    @Transactional
    public Optional<Pedido> pularEntrega(Integer idPedido){
        pedidoRepository.pularEntrega(idPedido);
        return pedidoRepository.findById(idPedido);
    }


    public void setDescontoPlano(Double valorDesconto, Integer planoId) {
        Optional<Plano> optionalPlano = planoRepository.findById(planoId);

        if (optionalPlano.isPresent()) {
            Plano plano = optionalPlano.get();

            BigDecimal valorReajuste = plano.getValorAjuste().add(BigDecimal.valueOf(valorDesconto));
            plano.setValorAjuste(valorReajuste);

            planoRepository.save(plano);
        } else {
            throw new NoSuchElementException("Plano não encontrado para o ID: " + planoId);
        }
    }

    public Pedido updatePedido(Pedido pedidoatt){
        return pedidoRepository.save(pedidoatt);
    }

}
