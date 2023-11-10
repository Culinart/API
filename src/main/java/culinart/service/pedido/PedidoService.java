package culinart.service.pedido;


import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Optional<Pedido> nextPedido(Integer idUser){
        return pedidoRepository.acharPorId(idUser);

    }
}
