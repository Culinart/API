package culinart.api.email;

import culinart.domain.email.ReceitaEmail;
import culinart.service.email.ReceitaEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class ReceitaEmailController {

    @Autowired
    private ReceitaEmailService receitaEmailService;


    @GetMapping
    public ResponseEntity<List<ReceitaEmail>> listar() {
        List<ReceitaEmail> conteudos = this.receitaEmailService.listar();

        if (conteudos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(conteudos);
    }

    @GetMapping("/{idNewsletter}")
    public ResponseEntity<ReceitaEmail> buscarPorId(@PathVariable UUID idNewsletter) {
        return ResponseEntity.status(200).body(this.receitaEmailService.buscarPorId(idNewsletter));
    }

    @PostMapping
    public ResponseEntity<ReceitaEmail> criar(@RequestBody ReceitaEmail receitaEmail) {
        this.receitaEmailService.criar(receitaEmail);
        return ResponseEntity.status(201).body(receitaEmail);
    }

    @PostMapping("/publicar/receitas/{idReceita}")
    public ResponseEntity<Void> publicarReceita(@PathVariable int idReceita){
        this.receitaEmailService.publicarReceita(idReceita);
        return ResponseEntity.ok().build();
    }
}
