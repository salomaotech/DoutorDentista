package mvc.controller.paciente;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import sistema.model.PesquisaRegistro;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.swings.Paginador;
import java.util.ArrayList;
import mvc.model.paciente.DaoPaciente;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;

public final class ControllerPacientePesquisa {

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void pesquisar(ViewSistema view) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("nome", view.jTpesquisaNomePaciente.getText());
        entidadesPesquisa.put("cpf", view.jTpesquisaCpfPaciente.getText());

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, view.jCpacientePaginador.getSelectedItem(), new DaoPaciente().getTabela(), true);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("nome");
        colunasTabela.add("cpf");
        colunasTabela.add("rg");
        colunasTabela.add("celular");
        colunasTabela.add("telefone");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoPaciente().getTabela(), completaQuery, colunasTabela, null, "order by nome asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTresultadosPesquisaPaciente.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* objeto linha */
            Object[] linhaJtable = new Object[]{
                dadosMap.get("id"),
                dadosMap.get("nome"),
                dadosMap.get("cpf"),
                dadosMap.get("rg"),
                dadosMap.get("celular"),
                dadosMap.get("telefone")

            };

            /* insere a linha */
            model.insertRow(contador, linhaJtable);

            /* atualiza o contador */
            contador++;

        }

        /* paginador */
        Paginador paginador = new Paginador(view.jCpacientePaginador, new DaoPaciente().getTabela(), completaQuery.completar(false, null, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
