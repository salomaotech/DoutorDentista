package mvc.model.anamnese;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import sistema.model.PesquisaRegistro;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import java.util.ArrayList;
import java.util.HashMap;

public final class RecuperaDadosAnamnesePaciente {

    /**
     * Retorna os dados
     *
     * @param idPaciente Id do paciente
     * @return Dados de anamnese no formato Map
     */
    public static Map getDados(Object idPaciente) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("idPaciente", idPaciente);

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, null, new DaoAnamnese().getTabela(), false);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("idPaciente");
        colunasTabela.add("resposta1");
        colunasTabela.add("resposta2");
        colunasTabela.add("resposta3");
        colunasTabela.add("resposta4");
        colunasTabela.add("resposta5");
        colunasTabela.add("resposta6");
        colunasTabela.add("resposta7");
        colunasTabela.add("resposta8");
        colunasTabela.add("resposta9");
        colunasTabela.add("resposta10");
        colunasTabela.add("resposta11");
        colunasTabela.add("resposta12");
        colunasTabela.add("resposta13");
        colunasTabela.add("resposta14");
        colunasTabela.add("resposta15");
        colunasTabela.add("resposta16");
        colunasTabela.add("resposta17");
        colunasTabela.add("resposta18");
        colunasTabela.add("resposta19");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoAnamnese().getTabela(), completaQuery, colunasTabela, null, "");

        /* excessao */
        try {

            /* retorno */
            return (Map) dados.get(0);

        } catch (java.lang.IndexOutOfBoundsException ex) {

            /* retorno */
            return new HashMap();

        }

    }

}
