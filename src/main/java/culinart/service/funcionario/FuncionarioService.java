package culinart.service.funcionario;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.domain.fornecedor.repository.FuncionarioRepository;
import culinart.utils.ListaObj;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
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
