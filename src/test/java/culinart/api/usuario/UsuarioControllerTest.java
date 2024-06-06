package culinart.api.usuario;

import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.domain.usuario.dto.UsuarioInfoPessoalDTO;
import culinart.service.usuario.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarTodosUsuarios_NoContent() {
        when(usuarioService.buscaNaoPossuiResultado()).thenReturn(true);

        ResponseEntity<List<UsuarioExibicaoDTO>> response = usuarioController.listarTodosUsuarios();

        assertEquals(204, response.getStatusCodeValue());
        verify(usuarioService, times(1)).buscaNaoPossuiResultado();
    }

    @Test
    public void testListarTodosUsuarios_Ok() {
        List<UsuarioExibicaoDTO> usuarios = List.of(new UsuarioExibicaoDTO());
        when(usuarioService.buscaNaoPossuiResultado()).thenReturn(false);
        when(usuarioService.listarTodosOsUsuarios()).thenReturn(usuarios);

        ResponseEntity<List<UsuarioExibicaoDTO>> response = usuarioController.listarTodosUsuarios();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuarios, response.getBody());
        verify(usuarioService, times(1)).buscaNaoPossuiResultado();
        verify(usuarioService, times(1)).listarTodosOsUsuarios();
    }

    @Test
    public void testListarUsuarioPorID_NotFound() {
        int userId = 1;
        when(usuarioService.usuarioIsEmpty(userId)).thenReturn(true);

        ResponseEntity<UsuarioExibicaoDTO> response = usuarioController.listarUsuarioPorID(userId);

        assertEquals(404, response.getStatusCodeValue());
        verify(usuarioService, times(1)).usuarioIsEmpty(userId);
    }

    @Test
    public void testListarUsuarioPorID_Ok() {
        int userId = 1;
        UsuarioExibicaoDTO usuario = new UsuarioExibicaoDTO();
        when(usuarioService.usuarioIsEmpty(userId)).thenReturn(false);
        when(usuarioService.listarUsuarioPorId(userId)).thenReturn(usuario);

        ResponseEntity<UsuarioExibicaoDTO> response = usuarioController.listarUsuarioPorID(userId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuario, response.getBody());
        verify(usuarioService, times(1)).usuarioIsEmpty(userId);
        verify(usuarioService, times(1)).listarUsuarioPorId(userId);
    }

    @Test
    public void testCadastrarUsuario_Created() {
        UsuarioCriacaoDTO usuarioCriacao = new UsuarioCriacaoDTO();
        UsuarioExibicaoDTO usuarioExibicao = new UsuarioExibicaoDTO();
        when(usuarioService.cadastrarUsuario(usuarioCriacao)).thenReturn(usuarioExibicao);

        ResponseEntity<UsuarioExibicaoDTO> response = usuarioController.cadastrarUsuario(usuarioCriacao);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(usuarioExibicao, response.getBody());
        verify(usuarioService, times(1)).cadastrarUsuario(usuarioCriacao);
    }

    @Test
    public void testAtualizarUsuario_Ok() {
        int userId = 1;
        UsuarioInfoPessoalDTO usuarioInfoPessoalDTO = new UsuarioInfoPessoalDTO();
        UsuarioExibicaoDTO usuarioExibicao = new UsuarioExibicaoDTO();
        when(usuarioService.atualizarUsuario(userId, usuarioInfoPessoalDTO)).thenReturn(usuarioExibicao);

        ResponseEntity<UsuarioExibicaoDTO> response = usuarioController.atualizarUsuario(userId, usuarioInfoPessoalDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(usuarioExibicao, response.getBody());
        verify(usuarioService, times(1)).atualizarUsuario(userId, usuarioInfoPessoalDTO);
    }

    @Test
    public void testDesativarUsuario_NotFound() {
        int userId = 1;
        when(usuarioService.usuarioIsEmpty(userId)).thenReturn(true);

        ResponseEntity<Void> response = usuarioController.DesativarUsuario(userId);

        assertEquals(404, response.getStatusCodeValue());
        verify(usuarioService, times(1)).usuarioIsEmpty(userId);
    }

    @Test
    public void testDesativarUsuario_NoContent() {
        int userId = 1;
        when(usuarioService.usuarioIsEmpty(userId)).thenReturn(false);
        doNothing().when(usuarioService).desativarUsuario(userId);

        ResponseEntity<Void> response = usuarioController.DesativarUsuario(userId);

        assertEquals(204, response.getStatusCodeValue());
        verify(usuarioService, times(1)).usuarioIsEmpty(userId);
        verify(usuarioService, times(1)).desativarUsuario(userId);
    }

}