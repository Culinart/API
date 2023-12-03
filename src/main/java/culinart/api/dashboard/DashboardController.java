package culinart.api.dashboard;

import culinart.domain.avaliacao.dto.AvaliacaoTxtDto;
import culinart.domain.dashboard.DashCategoria;
import culinart.domain.dashboard.DashPreferencia;
import culinart.domain.dashboard.mapper.DashMapper;
import culinart.service.dashboard.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashBoardService service;

    @GetMapping("/categorias")
    public ResponseEntity<List<DashCategoria>> listarCategorias(){
        List<Object[]> resultCategoria = service.getCategorias();
        List<DashCategoria> listaCategorias = DashMapper.mapToDashCategoriaList(resultCategoria);
        return ResponseEntity.ok().body(listaCategorias);
    }
    @GetMapping("/preferencias")
    public ResponseEntity<List<DashPreferencia>> listarPreferencias(){
        List<Object[]> resultPreferencia = service.getPreferencia();
        List<DashPreferencia> listaPreferencias = DashMapper.mapToDashPreferenciaList(resultPreferencia);
        return ResponseEntity.ok().body(listaPreferencias);
    }

    @GetMapping("/receitas")
    public ResponseEntity<List<AvaliacaoTxtDto>> listarReceitas(){
        List<Object[]> resultPreferencia = service.getReceitas();
        List<AvaliacaoTxtDto> listaPreferencias = DashMapper.mapToDashPreferenciaList(resultPreferencia);
        return ResponseEntity.ok().body(listaPreferencias);
    }
}
