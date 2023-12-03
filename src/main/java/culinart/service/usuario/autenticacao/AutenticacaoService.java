package culinart.service.usuario.autenticacao;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.repository.FuncionarioRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOPT = usuarioRepository.findByEmail(email);
        if(usuarioOPT.isEmpty()){
            UserDetails userDetails = loadFuncByUsername(email);
            if (userDetails == null){
                throw new UsernameNotFoundException(String.format("usuario com o email: %s não encontrado", email));
            }
            return userDetails;
        }
        return new UsuarioDetalhesDto(usuarioOPT.get());
    }
    public UserDetails loadFuncByUsername(String email) throws UsernameNotFoundException {
        Optional<Funcionario> funcionarioOPT = funcionarioRepository.findByEmail(email);
            if(funcionarioOPT.isEmpty()){
                throw new UsernameNotFoundException(String.format("FUNCIONARIO com o email: %s não encontrado", email));
            }
        return new UsuarioDetalhesDto(funcionarioOPT.get());
    }

}
