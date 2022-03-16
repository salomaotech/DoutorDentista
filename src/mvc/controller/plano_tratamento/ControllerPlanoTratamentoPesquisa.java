package mvc.controller.plano_tratamento;

import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.swings.MudaCorLinhaJtable;
import br.com.taimber.swings.Paginador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import mvc.model.plano_tratamento.DaoPlanoTratamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.PesquisaRegistro;
import sistema.model.Swap;

/**
 * Pesquisa os dados e apresenta os resultados na view
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerPlanoTratamentoPesquisa {

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void pesquisar(ViewSistema view) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("idPaciente", Swap.getIdPaciente());

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, view.jCplanoTratamentoPaginador.getSelectedItem(), new DaoPlanoTratamento().getTabela(), false);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("procedimento");
        colunasTabela.add("valor");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoPlanoTratamento().getTabela(), completaQuery, colunasTabela, null, "order by id asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTplanoTratamentoResultados.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* list com cores */
        List cores = new ArrayList();

        /* saldo */
        BigDecimal saldo = new BigDecimal("0");

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* calcula o saldo */
            saldo = saldo.add(FormataParaBigDecimal.formatar(dadosMap.get("valor")));
            dadosMap.put("saldo", FormataParaMoedaBrasileira.cifrar(saldo));

            /* trata os dados */
            dadosMap.put("valor", FormataParaMoedaBrasileira.cifrar(dadosMap.get("valor")));

            /* objeto linha */
            Object[] linhaJtable = new Object[]{
                dadosMap.get("id"),
                dadosMap.get("procedimento"),
                dadosMap.get("valor"),
                dadosMap.get("saldo")

            };

            /* insere a linha */
            model.insertRow(contador, linhaJtable);

            /* atualiza o contador */
            contador++;

        }

        /* muda a cor da jtable */
        MudaCorLinhaJtable.mudar(view.jTplanoTratamentoResultados, cores);

        /* paginador */
        Paginador paginador = new Paginador(view.jCplanoTratamentoPaginador, new DaoPlanoTratamento().getTabela(), completaQuery.completar(false, null, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
