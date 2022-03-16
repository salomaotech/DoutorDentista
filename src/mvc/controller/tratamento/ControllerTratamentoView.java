package mvc.controller.tratamento;

import br.com.taimber.algoritmos.Datas;
import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import sistema.model.Swap;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerTratamentoView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jDdataInicioTratamento.setDate(Datas.converterStringParaDate(""));
        view.jDdataConcluidoTratamento.setDate(Datas.converterStringParaDate(""));
        view.jCsituacaoTratamento.setSelectedItem("");
        view.jTdentesTratamento.setText("");
        view.jTprocedimentoTratamento.setText("");
        view.jTvalorTratamento.setText("");
        view.jTvalorEntradaTratamento.setText("");
        view.jCpagoTratamento.setSelected(false);
        view.jCsituacaoTratamento.setSelectedIndex(1);
        view.jLcodigoTratamento.setText("###");
        view.jCtratamentoPaginador.removeAllItems();

    }

    /**
     * Limpa os resultados antigos
     *
     * @param view
     */
    public static void limpaListaResultadosAntigos(ViewSistema view) {

        /* default model */
        DefaultTableModel model = (DefaultTableModel) view.jTresultadosTratamento.getModel();
        model.setNumRows(0);

    }

    /**
     * Habilita os controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBexcluirTratamento.setEnabled(!isNull(Swap.getIdTratamento()));
        view.jBsalvarTratamento.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBnovoTratamento.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBtratamentoPaginador.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBatalhoParcelarTratamento.setEnabled(!isNull(Swap.getIdTratamento()));

    }

}
