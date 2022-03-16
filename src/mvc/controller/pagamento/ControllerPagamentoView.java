package mvc.controller.pagamento;

import br.com.taimber.algoritmos.Datas;
import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerPagamentoView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jDpagamentoDataVencimento.setDate(Datas.converterStringParaDate(""));
        view.jTpagamentoValor.setText("");
        view.jTpagamentoHistorico.setText("");
        view.jCpagamentoPago.setSelected(false);
        view.jTpagamentoCodigoTratamento.setText("");
        view.jCpagamentoPaginador.removeAllItems();
        view.jCpagamentoForma.removeAllItems();

    }

    /**
     * Limpa os resultados antigos
     *
     * @param view
     */
    public static void limpaListaResultadosAntigos(ViewSistema view) {

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTpagamentoResultados.getModel();
        model.setNumRows(0);

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBpagamentoGravar.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBpagamentoExcluir.setEnabled(!isNull(Swap.getIdPagamento()));
        view.jBpagamentoNovo.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBpagamentoPaginador.setEnabled(!isNull(Swap.getIdPaciente()));

    }

}
