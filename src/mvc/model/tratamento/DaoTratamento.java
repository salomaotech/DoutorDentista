package mvc.model.tratamento;

import br.com.taimber.persistencia.sql.SqlTrataEntidades;
import br.com.taimber.arquivos.LeitorDePropriedades;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static java.util.Objects.isNull;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class DaoTratamento {

    private final String tabela;

    /**
     * Construtor
     */
    public DaoTratamento() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabelaTratamento");

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
        entidades.put("dataInicio", "text");
        entidades.put("dataConclusao", "text");
        entidades.put("situacao", "text");
        entidades.put("dentes", "text");
        entidades.put("procedimento", "text");
        entidades.put("valor", "text");
        entidades.put("valorEntrada", "text");
        entidades.put("isPago", "text");

        /* cria a tabela */
        banco.getBanco().criarTabela(this.tabela, entidades);

    }

    /**
     * Grava os dados
     *
     * @param cadastro Cadastro
     * @return Retorna true em caso de sucesso ao gravar dados
     */
    public boolean gravar(BeanTratamento cadastro) {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        LinkedHashMap entidades = new LinkedHashMap();
        entidades.put("idPaciente", cadastro.getIdPaciente());
        entidades.put("dataInicio", cadastro.getDataInicio());
        entidades.put("dataConclusao", cadastro.getDataConclusao());
        entidades.put("situacao", cadastro.getSituacao());
        entidades.put("dentes", cadastro.getDentes());
        entidades.put("procedimento", cadastro.getProcedimento());
        entidades.put("valor", cadastro.getValor());
        entidades.put("valorEntrada", cadastro.getValorEntrada());
        entidades.put("isPago", cadastro.isIsPago());

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
        entidades.add("id");
        entidades.add("idPaciente");
        entidades.add("dataInicio");
        entidades.add("dataConclusao");
        entidades.add("situacao");
        entidades.add("dentes");
        entidades.add("procedimento");
        entidades.add("valor");
        entidades.add("valorEntrada");
        entidades.add("isPago");

        /* retorno */
        return banco.getBanco().consultarRegistro(this.tabela, entidades, "where id='" + idRegistro + "'");

    }

}
