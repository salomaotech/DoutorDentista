package mvc.model.anamnese;

import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.persistencia.sql.SqlTrataEntidades;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static java.util.Objects.isNull;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class DaoAnamnese {

    private final String tabela;

    /**
     * Construtor
     */
    public DaoAnamnese() {

        /* tabela */
        this.tabela = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades().getProperty("prop.server.tabelaAnamnese");

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
        entidades.put("resposta1", "text");
        entidades.put("resposta2", "text");
        entidades.put("resposta3", "text");
        entidades.put("resposta4", "text");
        entidades.put("resposta5", "text");
        entidades.put("resposta6", "text");
        entidades.put("resposta7", "text");
        entidades.put("resposta8", "text");
        entidades.put("resposta9", "text");
        entidades.put("resposta10", "text");
        entidades.put("resposta11", "text");
        entidades.put("resposta12", "text");
        entidades.put("resposta13", "text");
        entidades.put("resposta14", "text");
        entidades.put("resposta15", "text");
        entidades.put("resposta16", "text");
        entidades.put("resposta17", "text");
        entidades.put("resposta18", "text");
        entidades.put("resposta19", "text");

        /* cria a tabela */
        banco.getBanco().criarTabela(this.tabela, entidades);

    }

    /**
     * Grava os dados
     *
     * @param cadastro Cadastro
     * @return Retorna true em caso de sucesso ao gravar dados
     */
    public boolean gravar(BeanAnamnese cadastro) {

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* entidades */
        LinkedHashMap entidades = new LinkedHashMap();
        entidades.put("idPaciente", cadastro.getIdPaciente());
        entidades.put("resposta1", cadastro.getResposta1());
        entidades.put("resposta2", cadastro.getResposta2());
        entidades.put("resposta3", cadastro.getResposta3());
        entidades.put("resposta4", cadastro.getResposta4());
        entidades.put("resposta5", cadastro.getResposta5());
        entidades.put("resposta6", cadastro.getResposta6());
        entidades.put("resposta7", cadastro.getResposta7());
        entidades.put("resposta8", cadastro.getResposta8());
        entidades.put("resposta9", cadastro.getResposta9());
        entidades.put("resposta10", cadastro.getResposta10());
        entidades.put("resposta11", cadastro.getResposta11());
        entidades.put("resposta12", cadastro.getResposta12());
        entidades.put("resposta13", cadastro.getResposta13());
        entidades.put("resposta14", cadastro.getResposta14());
        entidades.put("resposta15", cadastro.getResposta15());
        entidades.put("resposta16", cadastro.getResposta16());
        entidades.put("resposta17", cadastro.getResposta17());
        entidades.put("resposta18", cadastro.getResposta18());
        entidades.put("resposta19", cadastro.getResposta19());

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
        entidades.add("resposta1");
        entidades.add("resposta2");
        entidades.add("resposta3");
        entidades.add("resposta4");
        entidades.add("resposta5");
        entidades.add("resposta6");
        entidades.add("resposta7");
        entidades.add("resposta8");
        entidades.add("resposta9");
        entidades.add("resposta10");
        entidades.add("resposta11");
        entidades.add("resposta12");
        entidades.add("resposta13");
        entidades.add("resposta14");
        entidades.add("resposta15");
        entidades.add("resposta16");
        entidades.add("resposta17");
        entidades.add("resposta18");
        entidades.add("resposta19");

        /* retorno */
        return banco.getBanco().consultarRegistro(this.tabela, entidades, "where id='" + idRegistro + "'");

    }

}
