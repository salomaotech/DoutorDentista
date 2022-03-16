package mvc.model.paciente;

import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import sistema.model.BancoFactory;
import sistema.model.PesquisaRegistro;

public class RecuperaDadosPaciente {

    /**
     * Retorna o nome do paciente por seu Id
     *
     * @param idPaciente Id do paciente
     * @return Nome do paciente
     */
    public static String getNomePacientePorId(Object idPaciente) {

        /* valida */
        if (idPaciente.equals("")) {

            /* retorno */
            return null;

        }

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("id", idPaciente);

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, null, new DaoPaciente().getTabela(), false);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("nome");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoPaciente().getTabela(), completaQuery, colunasTabela, null, "");

        /* excessao */
        try {

            /* dados de retorno */
            Map dadosRetorno = (Map) dados.get(0);

            /* retorno */
            return (String) dadosRetorno.get("nome");

        } catch (java.lang.IndexOutOfBoundsException | java.lang.NullPointerException ex) {

            /* retorno */
            return null;

        }

    }

    /**
     * Recupera o ID do paciente por seu nome
     *
     * @param nome Nome do paciente
     * @return Id do paciente
     */
    public static String getIdPacientePorNome(Object nome) {

        /* valida */
        if (nome.equals("")) {

            /* retorno */
            return null;

        }

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("nome", nome);

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, null, new DaoPaciente().getTabela(), false);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoPaciente().getTabela(), completaQuery, colunasTabela, null, "");

        /* excessao */
        try {

            /* dados de retorno */
            Map dadosRetorno = (Map) dados.get(0);

            /* retorno */
            return (String) dadosRetorno.get("id");

        } catch (java.lang.IndexOutOfBoundsException | java.lang.NullPointerException ex) {

            /* retorno */
            return null;

        }

    }

    /**
     * Retorna o ID do último cadastrado
     *
     * @return String contendo último ID do paciente cadastrado
     */
    public static String getUltimoIdCadastrado() {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("id");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa = "order by id desc limit 1";

        /* dados */
        Map dados = (Map) banco.getBanco().consultarRegistro(new DaoPaciente().getTabela(), entidades, condicaoPesquisa).get(0);

        /* retorno */
        return (String) dados.get("id");

    }

}
