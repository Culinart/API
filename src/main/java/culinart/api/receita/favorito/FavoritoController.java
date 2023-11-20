package culinart.api.receita.favorito;

import culinart.domain.favorito.Favorito;
import culinart.domain.favorito.dto.FavoritoExibicaoDTO;
import culinart.domain.favorito.mapper.FavoritoMapper;
import culinart.service.receita.favorito.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {
    private final FavoritoService favoritoService;

    @GetMapping
    public ResponseEntity<List<FavoritoExibicaoDTO>> listarFavoritos(){
        return ResponseEntity.ok(favoritoService.listarFavoritos().stream().map(FavoritoMapper::toDTO).toList());
    }

    @PostMapping("/{idReceita}/{idUsuario}")
    public ResponseEntity<Favorito> adcionarFavorito(@PathVariable int idReceita, @PathVariable int idUsuario){
        return ResponseEntity.ok(favoritoService.adcionarFavorito(idReceita,idUsuario));
    }
}
