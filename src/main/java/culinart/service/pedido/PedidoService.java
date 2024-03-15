package culinart.service.pedido;


import culinart.domain.endereco.Endereco;
import culinart.domain.endereco.usuario.EnderecoUsuario;
import culinart.domain.endereco.usuario.dto.EnderecoResponseToUsuarioDTO;
import culinart.domain.endereco.usuario.dto.mapper.EnderecoUsuarioMapper;
import culinart.domain.endereco.usuario.repository.EnderecoUsuarioRepository;
import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.dto.DeletarReceitaPedidoDto;
import culinart.domain.pedido.repository.PedidoRepository;
import culinart.domain.plano.Plano;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.planoCategoria.PlanoCategoria;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.service.endereco.EnderecoService;
import culinart.service.plano.categoria.PlanoCategoriaService;
import culinart.utils.DiaSemanaIngles;
import culinart.utils.enums.DiaSemanaEnum;
import culinart.utils.enums.StatusAtivoEnum;
import culinart.utils.enums.StatusPedidoEnum;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
@Lazy
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PlanoRepository planoRepository;
    private final PlanoCategoriaService planoCategoriaService;
    private final ReceitaRepository receitaRepository;
    private final EnderecoUsuarioRepository enderecoUsuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, PlanoRepository planoRepository, PlanoCategoriaService planoCategoriaService, ReceitaRepository receitaRepository, EnderecoUsuarioRepository enderecoUsuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.planoRepository = planoRepository;
        this.planoCategoriaService = planoCategoriaService;
        this.receitaRepository = receitaRepository;
        this.enderecoUsuarioRepository = enderecoUsuarioRepository;
    }

    public List<Object[]> nextPedido(Integer idUser, LocalDate dataEntrega){
        return pedidoRepository.acharProximoPedidoUser(idUser, dataEntrega);

    }


    public List<Pedido> getDatas(Integer idUser){

        return pedidoRepository.findAllByPlanoUsuarioId(idUser);
    }
    @Transactional
    public Optional<Pedido> pularEntrega(Integer idPedido){
        pedidoRepository.pularEntrega(idPedido);
        return pedidoRepository.findById(idPedido);
    }

    @Transactional
    public Optional<Pedido> pedidoEntregue(Integer idPedido){
        pedidoRepository.atualizarStatusParaEntregue(idPedido);
        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        Optional<Plano> plano = planoRepository.findById(pedido.get().getPlano().getId());
        criarPedido(plano.get().getUsuario().getId(), plano.get(), "Pedido", pedido.get().getDataEntrega());
        return pedido;
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

    @Transactional
    public void deletarReceitaPedido(DeletarReceitaPedidoDto receitaPedidoDto){
        pedidoRepository.deleteReceitaPedido(receitaPedidoDto.getPedido_id(), receitaPedidoDto.getReceita_id());
    }

    @Transactional
    public void addReceitaPedido(DeletarReceitaPedidoDto receitaPedidoDto){
        pedidoRepository.addReceitaPedido(receitaPedidoDto.getPedido_id(), receitaPedidoDto.getReceita_id());
    }

    public  List<Object[]> proximasEntregas(){
        LocalDate sevenDaysAfter = LocalDate.now().plusDays(7);
        return pedidoRepository.findProximosPedidos(sevenDaysAfter);

    }

    public void criarPedido(int userId, Plano plano, String tipoCriacao, LocalDate dataUltimoPedido){
        List<PlanoCategoria> categorias = planoCategoriaService.exibirPlanoCategoriaPorUserId(userId);
        Set<Integer> receitasAdicionadas = new HashSet<>(); // Conjunto para armazenar IDs das receitas adicionadas
        List<Receita> listaReceitasBanco = new ArrayList<>();

        for (PlanoCategoria categoriaDaVez : categorias) {
            List<Receita> receitasCategoria = receitaRepository.receitasPorCategoria(categoriaDaVez.getCategoria().getId());

            for (Receita receita : receitasCategoria) {
                if (!receitasAdicionadas.contains(receita.getId())) {
                    listaReceitasBanco.add(receita);
                    receitasAdicionadas.add(receita.getId()); // Adiciona o ID da receita ao conjunto
                }
            }
        }

        Collections.shuffle(listaReceitasBanco);

        List<Receita> tresReceitasAleatorias = listaReceitasBanco.subList(0, Math.min(listaReceitasBanco.size(), 3));
        Optional<EnderecoUsuario> enderecoUser = enderecoUsuarioRepository.findEnderecoUsuarioAtivo(StatusAtivoEnum.ATIVO , userId);
        Endereco endereco = null;
        if (enderecoUser.isPresent()) {
            endereco = enderecoUser.get().getEndereco();
        }
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedidoEnum.ATIVO);
        pedido.setPlano(plano);
        pedido.setValor(plano.getValorPlano().doubleValue() / 4.0);
        pedido.setDataCriacao(LocalDate.now());
        pedido.setListaReceitas(tresReceitasAleatorias);
        pedido.setEndereco(endereco);
        if (tipoCriacao.equalsIgnoreCase("Pedido")){
            pedido.setDataEntrega(dataUltimoPedido.plusDays(7));
        }else if (tipoCriacao.equalsIgnoreCase("Plano")){
            String diaSemanaPlano = String.valueOf(plano.getDiaSemana());
            DiaSemanaIngles diaSemanaIngles = new DiaSemanaIngles();
            DayOfWeek dia = diaSemanaIngles.obterDayOfWeek(diaSemanaPlano);
            LocalDate hoje = LocalDate.now();
            DayOfWeek diaSemanaEnum = DayOfWeek.valueOf(String.valueOf(dia));
            LocalDate proximoDia = hoje.with(diaSemanaEnum);
            if (proximoDia.isBefore(hoje) || proximoDia.isEqual(hoje)) {
                proximoDia = proximoDia.plusWeeks(1);
            }
            pedido.setDataEntrega(proximoDia);
        }
        pedidoRepository.save(pedido);
    }



}
