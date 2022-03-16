package mvc.controller.parcelamento;

import br.com.taimber.algoritmos.Datas;
import static java.util.Objects.isNull;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerParcelamentoView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jDpagamentoDataVencimentoParcela.setDate(Datas.converterStringParaDate(""));
        view.jTpagamentoValorParcela.setText("");
        view.jTpagamentoHistoricoParcela.setText("");
        view.jCpagamentoNumeroParcelas.setSelectedIndex(0);
        view.jTpagamentoCodigoTratamentoHistoricoParcela.setText("");
        view.jCpagamentoFormaParcela.removeAllItems();

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBpagamentoParcelaGravar.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBpagamentoParcelaNovo.setEnabled(!isNull(Swap.getIdPaciente()));

    }

}
