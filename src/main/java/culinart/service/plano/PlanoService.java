package culinart.service.plano;

import culinart.domain.plano.Plano;
import culinart.domain.plano.dto.PlanoCadastroDTO;
import culinart.domain.plano.dto.PlanoExibicaoDTO;
import culinart.domain.plano.mapper.PlanoMapper;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.utils.enums.PreferenciaEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<PlanoExibicaoDTO> listarTodosOsPlanos() {
        List<Plano> lista = this.planoRepository.findAll();
        List<PlanoExibicaoDTO> listaDTO = new ArrayList<>();
        for (Plano plano : lista){
            listaDTO.add(PlanoMapper.toDTO(plano));
        }
        return listaDTO;
    }

    public PlanoExibicaoDTO listarPorId(int idUsuario) {
        Optional<Plano> planoOptional = planoRepository.findByUsuario_Id(idUsuario);
        if(planoOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }

        return PlanoMapper.toDTO(planoOptional.get());
    }

    public PlanoExibicaoDTO cadastrarPlano(PlanoCadastroDTO planoCadastroDTO, int idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(usuarioOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inválido para cadastro de plano");
        }

        if (this.planoRepository.existsByUsuario_Id(idUsuario)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario já possui plano cadastrado");
        }

        planoCadastroDTO.setUsuario(usuarioOptional.get());

        List<PreferenciaEnum> preferenciaEnums = atribuirPreferencia(planoCadastroDTO.getPreferencias());
        Plano plano = PlanoMapper.toEntity(planoCadastroDTO);

        plano.setPreferences(preferenciaEnums);

        return PlanoMapper.toDTO( this.planoRepository.save(plano));
    }

    public PlanoExibicaoDTO atualizarPlano(PlanoCadastroDTO novoPlano, int idUsuario) {
        Optional<Plano> planoOptional = planoRepository.findByUsuario_Id(idUsuario);
        if(planoOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado");
        }
        novoPlano.setUsuario(planoOptional.get().getUsuario());
        novoPlano.setId(planoOptional.get().getId());

        List<PreferenciaEnum> preferenciaEnums = atribuirPreferencia(novoPlano.getPreferencias());
        Plano plano = PlanoMapper.toEntity(novoPlano);

        plano.setPreferences(preferenciaEnums);

        planoRepository.save(plano);
        return PlanoMapper.toDTO(plano);
    }

    public void deletarPlano(int idUsuario) {
        Optional<Plano> planoOptional = planoRepository.findByUsuario_Id(idUsuario);
        if(planoOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado");
        }
        planoRepository.delete(planoOptional.get());
    }

    private List<PreferenciaEnum> atribuirPreferencia(List<String> preferencias){
        List<PreferenciaEnum> listaEnum = new ArrayList<>();

        for (String preferenciaDaVez : preferencias) {
            for (PreferenciaEnum enumValue : PreferenciaEnum.values()) {
                if (enumValue.getNome().equalsIgnoreCase(preferenciaDaVez)) {
                    listaEnum.add(enumValue);
                    break;
                }
            }
        }

        return listaEnum;
    }


}
