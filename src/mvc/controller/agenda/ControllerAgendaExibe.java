package mvc.controller.agenda;

import br.com.taimber.algoritmos.Datas;
import java.util.Map;
import mvc.model.agenda.DaoAgenda;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerAgendaExibe {

    /**
     * Exibe os dados
     *
     * @param view View
     * @param id Id do cadastro
     */
    public static void exibir(ViewSistema view, Object id) {

        /* excessão */
        try {

            /* cadastro */
            DaoAgenda cadastro = new DaoAgenda();

            /* dados */
            Map dados = (Map) cadastro.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jTagendaNome.setText((String) dados.get("nome"));
            view.jDagendaData.setDate(Datas.converterStringParaDate((String) dados.get("data")));
            view.jCagendaHora.setSelectedItem(dados.get("hora"));
            view.jCagendaMinuto.setSelectedItem(dados.get("minuto"));
            view.jTagendaHistorico.setText((String) dados.get("historico"));

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

}
