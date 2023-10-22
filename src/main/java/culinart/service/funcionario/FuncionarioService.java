package culinart.service.funcionario;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioCriacaoDTO;
import culinart.domain.fornecedor.dto.FuncionarioExibicaoDTO;
import culinart.domain.fornecedor.mapper.FuncionarioMapper;
import culinart.domain.fornecedor.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.utils.ListaObj;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;


    public List<FuncionarioExibicaoDTO> listarFunc() {
        List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(FuncionarioMapper::of)
                .collect(Collectors.toList());
    }

    public FuncionarioExibicaoDTO cadastrarFuncionario(FuncionarioCriacaoDTO func) {
        if (buscarFuncionarioPorBuscaBinaria(func.getEmail())) {
            throw new IllegalArgumentException("Usuario já cadastrado");
        }
        Funcionario novoFunc = FuncionarioMapper.of(func);
        String senhaCriptografada = passwordEncoder.encode(func.getSenha());
        novoFunc.setSenha(senhaCriptografada);
        return FuncionarioMapper.of(funcionarioRepository.save(novoFunc));
    }

    public FuncionarioExibicaoDTO atualizarFuncionario(Integer id, Funcionario funcionario) {
        Optional<Funcionario> func = funcionarioRepository.findById(id);
        if (func.isPresent()) {
            Funcionario funcionarioExistente = func.get();
            funcionarioExistente.setNome(funcionario.getNome());
            funcionarioExistente.setEmail(funcionario.getEmail());
            funcionarioExistente.setSenha(passwordEncoder.encode(funcionario.getSenha()));
            funcionarioExistente.setPermissao(funcionario.getPermissao());
            funcionarioExistente.setCpf(funcionario.getCpf());
            funcionarioExistente.setTel(funcionario.getTel());
            funcionarioExistente.setArea(funcionario.getArea());
            funcionarioExistente.setCargo(funcionario.getCargo());
            funcionarioExistente.setTurno(funcionario.getTurno());
            funcionarioExistente.setDataNascimento(funcionario.getDataNascimento());
            funcionarioExistente.setIsAtivo(funcionario.getIsAtivo());
            Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionarioExistente);

            return FuncionarioMapper.of(funcionarioAtualizado);
        } else {
            System.out.println("Funcionário não encontrado");
            return null;
        }
    }

    public String deletarFuncionario(Integer id) {
        this.funcionarioRepository.deleteById(id);
        return "Deletado com sucesso";
    }

    public Boolean buscarFuncionarioPorBuscaBinaria(String email) {
        List<Funcionario> vetor = funcionarioRepository.findAll();
        for (int i = 0; i < vetor.toArray().length; i++) {
            if (vetor.get(i).getEmail().equals(email)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean funcionarioIsEmpty(Integer id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.isEmpty();
    }


    public List<FuncionarioDTO> funcionariosOrdenadosDTO() {
        List<FuncionarioDTO> funcionariosDTO = buscarFuncionariosDTO();
        ListaObj<FuncionarioDTO> vetorFuncionarios = new ListaObj<>(funcionariosDTO.size());

        for (FuncionarioDTO funcionarioDTO : funcionariosDTO) {
            vetorFuncionarios.adiciona(funcionarioDTO);
        }

        ordenarVetor(vetorFuncionarios);

        return vetorFuncionarios.toList();
    }

    private void ordenarVetor(ListaObj<FuncionarioDTO> vetorFuncionarios) {
        for (int i = 0; i < vetorFuncionarios.getTamanho() - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < vetorFuncionarios.getTamanho(); j++) {
                if (vetorFuncionarios.getElemento(j).getNome().compareTo(vetorFuncionarios.getElemento(indiceMenor).getNome()) < 0) {
                    indiceMenor = j;
                }
            }
            FuncionarioDTO aux = vetorFuncionarios.getElemento(i);
            vetorFuncionarios.setElemento(i, vetorFuncionarios.getElemento(indiceMenor));
            vetorFuncionarios.setElemento(indiceMenor, aux);
        }
    }

    public List<FuncionarioDTO> buscarFuncionariosDTO() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();

        List<FuncionarioDTO> funcionarioDTOs = funcionarios.stream()
                .map(funcionario -> {
                    FuncionarioDTO dto = new FuncionarioDTO();
                    dto.setNome(funcionario.getNome());
                    dto.setEmail(funcionario.getEmail());
                    return dto;
                })
                .collect(Collectors.toList());

        return funcionarioDTOs;
    }

    public String gerarArquivoCsv(List<FuncionarioDTO> listaOrdenadaFunc) {
        String nomeArquivo = "funcionarios.csv";
        gravaArquivoCsv(listaOrdenadaFunc, nomeArquivo);
        leExibeArquivoCsv(nomeArquivo);
        return nomeArquivo;
    }


    public void gravaArquivoCsv(List<FuncionarioDTO> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            saida.format("%s;%s\n","Nome", "Email");
            for (int i = 0; i < lista.size(); i++) {
                FuncionarioDTO func = lista.get(i);
                saida.format("%s;%s\n",func.getNome(), func.getEmail());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);

            }
        }

    }

    public void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado!");
            System.exit(1);
        }

        try {
            while (entrada.hasNext()) {


                String nome = entrada.next();
                String email = entrada.next();


                System.out.printf("%-15s %-9s\n",nome, email);
            }
        }
        catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            erro.printStackTrace();
            deuRuim = true;
        }
        catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }


    }
}

