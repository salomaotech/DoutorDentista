package mvc.controller.agenda;

import br.com.taimber.algoritmos.Datas;
import static java.util.Objects.isNull;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerAgendaView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jTagendaNome.setText("");
        view.jDagendaData.setDate(Datas.converterStringParaDate(""));
        view.jCagendaHora.setSelectedIndex(0);
        view.jCagendaMinuto.setSelectedIndex(0);
        view.jTagendaHistorico.setText("");
        view.jTagendaPesquisaNome.setText("");

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBagendaExcluir.setEnabled(!isNull(Swap.getIdAgenda()));

    }

}
