package mvc.model.paciente;

import br.com.taimber.persistencia.sql.SqlTrataEntidades;
import br.com.taimber.arquivos.LeitorDePropriedades;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import sistema.model.BancoFactory;
import static java.util.Objects.isNull;
import sistema.model.Propriedades;

public class DaoPaciente {

    private final String tabela;

    /**
     * Construtor
     */
    public DaoPaciente() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabelaPaciente");

    }

    /**
     * Retorna a tabela
     *
     * @return Nome da tabela
     */
    public String getTabela() {

        return tabela;

    }

    /**
     * Cria a entidade, ou seja a tabela
     */
    public void criaEntidade() {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        LinkedHashMap entidades = new LinkedHashMap();
        entidades.put("id", "int not null auto_increment primary key");
        entidades.put("nome", "text");
        entidades.put("cpf", "text");
        entidades.put("rg", "text");
        entidades.put("sexo", "text");
        entidades.put("nascimento", "text");
        entidades.put("nacionalidade", "text");
        entidades.put("nomePai", "text");
        entidades.put("nomeMae", "text");
        entidades.put("cep", "text");
        entidades.put("rua", "text");
        entidades.put("quadra", "text");
        entidades.put("lote", "text");
        entidades.put("numero", "text");
        entidades.put("bairro", "text");
        entidades.put("cidade", "text");
        entidades.put("uf", "text");
        entidades.put("complemento", "text");
        entidades.put("telefone", "text");
        entidades.put("celular", "text");
        entidades.put("email", "text");
        entidades.put("adicionais", "text");

        /* cria a tabela */
        banco.getBanco().criarTabela(this.tabela, entidades);

    }

    /**
     * Grava os dados
     *
     * @param cadastro Cadastro
     * @return Retorna true em caso de sucesso ao gravar dados
     */
    public boolean gravar(BeanPaciente cadastro) {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        LinkedHashMap entidades = new LinkedHashMap();
        entidades.put("nome", cadastro.getNome());
        entidades.put("cpf", cadastro.getCpf());
        entidades.put("rg", cadastro.getRg());
        entidades.put("sexo", cadastro.getSexo());
        entidades.put("nascimento", cadastro.getNascimento());
        entidades.put("nacionalidade", cadastro.getNacionalidade());
        entidades.put("nomePai", cadastro.getNomePai());
        entidades.put("nomeMae", cadastro.getNomeMae());
        entidades.put("cep", cadastro.getCep());
        entidades.put("rua", cadastro.getRua());
        entidades.put("quadra", cadastro.getQuadra());
        entidades.put("lote", cadastro.getLote());
        entidades.put("numero", cadastro.getNumero());
        entidades.put("bairro", cadastro.getBairro());
        entidades.put("cidade", cadastro.getCidade());
        entidades.put("uf", cadastro.getUf());
        entidades.put("complemento", cadastro.getComplemento());
        entidades.put("telefone", cadastro.getTelefone());
        entidades.put("celular", cadastro.getCelular());
        entidades.put("email", cadastro.getEmail());
        entidades.put("adicionais", cadastro.getAdicionais());

        /* trata as entidades */
        entidades = SqlTrataEntidades.tratar(entidades);

        /* valida id */
        if (isNull(cadastro.getId())) {

            /* grava e retorna */
            return banco.getBanco().inserirRegistro(this.tabela, entidades);

        } else {

            /* condições */
            LinkedHashMap condicoes = new LinkedHashMap();
            condicoes.put("id", cadastro.getId());

            /* atualiza e retorna */
            return banco.getBanco().atualizarRegistro(this.tabela, entidades, condicoes);

        }

    }

    /**
     * Retorna os dados cadastrados
     *
     * @param idRegistro Id do cadastro
     * @return List com dados
     */
    public List getDadosCadastro(String idRegistro) {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("nome");
        entidades.add("cpf");
        entidades.add("rg");
        entidades.add("sexo");
        entidades.add("nascimento");
        entidades.add("nacionalidade");
        entidades.add("nomePai");
        entidades.add("nomeMae");
        entidades.add("cep");
        entidades.add("rua");
        entidades.add("quadra");
        entidades.add("lote");
        entidades.add("numero");
        entidades.add("bairro");
        entidades.add("cidade");
        entidades.add("uf");
        entidades.add("complemento");
        entidades.add("telefone");
        entidades.add("celular");
        entidades.add("email");
        entidades.add("adicionais");

        /* retorno */
        return banco.getBanco().consultarRegistro(this.tabela, entidades, "where id='" + idRegistro + "'");

    }

}
