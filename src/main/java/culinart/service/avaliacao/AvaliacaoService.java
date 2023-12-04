package culinart.service.avaliacao;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.avaliacao.dto.AvaliacaoCadastroDTO;
import culinart.domain.avaliacao.dto.AvaliacaoTxtDto;
import culinart.domain.avaliacao.mapper.AvaliacaoMapper;
import culinart.domain.avaliacao.mapper.AvaliacaoTxtMapper;
import culinart.domain.avaliacao.repository.AvaliacaoRepository;
import culinart.domain.pedido.mapper.PedidoMapper;
import culinart.domain.receita.Receita;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReceitaRepository receitaRepository;

    public List<Avaliacao> exibirTodasAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao exibirAvaliacaoPorId(int id) {
        return avaliacaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));
    }

    public void cadastrarAvaliacao(List<AvaliacaoCadastroDTO> listAvaliacaoCadastroDTO) {
        for (AvaliacaoCadastroDTO avaliacaoCadastroDTO : listAvaliacaoCadastroDTO) {
            Usuario usuario = usuarioRepository.findById(avaliacaoCadastroDTO.getIdUsuario())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

            Receita receita = receitaRepository.findById(avaliacaoCadastroDTO.getIdReceita())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

            Avaliacao entity = AvaliacaoMapper.toEntity(avaliacaoCadastroDTO, usuario, receita);
            avaliacaoRepository.save(entity);
            receita.getAvaliacoes().add(entity);
            receitaRepository.save(receita);
        }
    }

    public File exportTxt() {
        List<Object[]> listaAvaliacoes = avaliacaoRepository.avaliacoesTxt();
        List<AvaliacaoTxtDto> listaAvaliacao = listaAvaliacoes.stream()
                .map(AvaliacaoTxtMapper::toTxtDto)
                .toList();
        return gravaArquivoTxt(listaAvaliacao, "relatorio-avaliacoes");
    }

    public File gravaArquivoTxt(List<AvaliacaoTxtDto> lista, String nomeArq) {
        File arquivo = new File(nomeArq + ".txt");

        try (BufferedWriter saida = new BufferedWriter(new FileWriter(arquivo))) {
            int contaRegDados = 0;

            // Monta o registro de header
            String header = "0002";
            header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            header += "01";

            // Grava o registro de header
            gravaRegistro(saida, header);

            // Grava os registros de dados (ou registros de corpo)
            for (AvaliacaoTxtDto a : lista) {
                String corpo = "02";
                corpo += String.format("%-45.45s", a.getNomeReceita());
                // Grava o registro de corpo
                gravaRegistro(saida, corpo);
                // Incrementa o contador de registros de dados gravados
                String corpo2 = "03";
                corpo2 += String.format("%02d", a.getQtd_avaliacoes());
                corpo2 += String.format("%03.1f", a.getNotaMedia());
                gravaRegistro(saida, corpo2);
                contaRegDados++;
            }

            // Monta e grava o registro de trailer
            String trailer = "01";
            trailer += String.format("%05d", contaRegDados);

            gravaRegistro(saida, trailer);
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }

        return arquivo;
    }

    public void gravaRegistro(BufferedWriter saida, String registro) throws IOException {
        saida.append(registro).append("\n");
    }

}
