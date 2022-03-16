package mvc.controller.plano_tratamento;

import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

/**
 * Realiza operações na view como (Resetar os controles, Limpar, habilitar
 * campos etc).
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerPlanoTratamentoView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jTplanoTratamentoProcedimento.setText("");
        view.jTplanoTratamentoValor.setText("");
        view.jCplanoTratamentoPaginador.removeAllItems();

    }

    /**
     * Limpa resultados antigos
     *
     * @param view View
     */
    public static void limpaListaResultadosAntigos(ViewSistema view) {

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTplanoTratamentoResultados.getModel();
        model.setNumRows(0);

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBsalvarPlanoTratamento.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBplanoTratamentoNovo.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBplanoTratamentoExcluir.setEnabled(!isNull(Swap.getIdPlanoTratamento()));
        view.jBplanoTratamentoPaginador.setEnabled(!isNull(Swap.getIdPaciente()));

    }

}
