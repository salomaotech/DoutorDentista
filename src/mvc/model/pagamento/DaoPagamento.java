package mvc.model.pagamento;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.persistencia.sql.SqlTrataEntidades;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static java.util.Objects.isNull;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class DaoPagamento {

    private final String tabela;

    /**
     * Construtor
     */
    public DaoPagamento() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabelaPagamentosPaciente");

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
        entidades.put("idPaciente", "text");
        entidades.put("data", "text");
        entidades.put("valor", "text");
        entidades.put("historico", "text");
        entidades.put("isPago", "text");
        entidades.put("codigoTratamento", "text");
        entidades.put("formaPagamento", "text");

        /* cria a tabela */
        banco.getBanco().criarTabela(this.tabela, entidades);

    }

    /**
     * Grava os dados
     *
     * @param cadastro Cadastro
     * @return Retorna true em caso de sucesso ao gravar dados
     */
    public boolean gravar(BeanPagamento cadastro) {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        LinkedHashMap entidades = new LinkedHashMap();
        entidades.put("idPaciente", cadastro.getIdPaciente());
        entidades.put("data", cadastro.getData());
        entidades.put("valor", cadastro.getValor());
        entidades.put("historico", cadastro.getHistorico());
        entidades.put("isPago", cadastro.isIsPago());
        entidades.put("codigoTratamento", cadastro.getCodigoTratamento());
        entidades.put("formaPagamento", cadastro.getFormaPagamento());

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
        entidades.add("idPaciente");
        entidades.add("data");
        entidades.add("valor");
        entidades.add("historico");
        entidades.add("isPago");
        entidades.add("codigoTratamento");
        entidades.add("formaPagamento");

        /* retorno */
        return banco.getBanco().consultarRegistro(this.tabela, entidades, "where id='" + idRegistro + "'");

    }

}
