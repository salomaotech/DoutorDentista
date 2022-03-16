package mvc.controller.agenda;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.swings.MudaCorLinhaJtable;
import br.com.taimber.swings.Paginador;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import mvc.model.agenda.DaoAgenda;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.PesquisaRegistro;

public class ControllerAgendaPesquisa {

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void pesquisar(ViewSistema view) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();
        entidadesPesquisa.put("nome", view.jTagendaPesquisaNome.getText());

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, view.jCagendaPaginador.getSelectedItem(), new DaoAgenda().getTabela(), true);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("nome");
        colunasTabela.add("data");
        colunasTabela.add("hora");
        colunasTabela.add("minuto");
        colunasTabela.add("historico");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoAgenda().getTabela(), completaQuery, colunasTabela, null, "order by str_to_date(data, '%d/%m/%Y') asc, hora asc, minuto asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTagendaLista.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* list com cores */
        List cores = new ArrayList();

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* data hora */
            String dataHora = dadosMap.get("data") + " " + dadosMap.get("hora") + ":" + dadosMap.get("minuto");

            /* valida */
            if (Datas.isDataInicialMenorDataFinalComHoraMinuto(Datas.getDataAtualDiaMesAnoComHoraMinuto(), dataHora)) {

                /* verde */
                cores.add(Color.decode("#ace0c6"));

            } else {

                /* vermelho */
                cores.add(Color.decode("#f49093"));

            }

            /* hora e minuto */
            dadosMap.put("horaMinuto", dadosMap.get("hora") + ":" + dadosMap.get("minuto"));

            /* objeto linha */
            Object[] linhaJtable = new Object[]{
                dadosMap.get("id"),
                dadosMap.get("data"),
                dadosMap.get("horaMinuto"),
                dadosMap.get("nome"),
                dadosMap.get("historico")

            };

            /* insere a linha */
            model.insertRow(contador, linhaJtable);

            /* atualiza o contador */
            contador++;

        }

        /* muda a cor da jtable */
        MudaCorLinhaJtable.mudar(view.jTagendaLista, cores);

        /* paginador */
        Paginador paginador = new Paginador(view.jCagendaPaginador, new DaoAgenda().getTabela(), completaQuery.completar(false, null, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
