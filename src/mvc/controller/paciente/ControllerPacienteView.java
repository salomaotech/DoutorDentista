package mvc.controller.paciente;

import br.com.taimber.algoritmos.Datas;
import static java.util.Objects.isNull;
import sistema.model.Swap;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerPacienteView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jTpessoalNomePaciente.setText("");
        view.jTpessoalCpfPaciente.setText("");
        view.jTpessoalRgPaciente.setText("");
        view.jCpessoalSexoPaciente.setSelectedItem("");
        view.jDpessoalNascimentoPaciente.setDate(Datas.converterStringParaDate(""));
        view.jCpessoalNacionalidadePaciente.setSelectedItem("");
        view.jTpessoalNomePaiPaciente.setText("");
        view.jTpessoalNomeMaePaciente.setText("");
        view.jTenderecoCepPaciente.setText("");
        view.jTenderecoRuaPaciente.setText("");
        view.jTenderecoQuadraPaciente.setText("");
        view.jTenderecoLotePaciente.setText("");
        view.jTenderecoNumeroPaciente.setText("");
        view.jTenderecoBairroPaciente.setText("");
        view.jTenderecoCidadePaciente.setText("");
        view.jTenderecoComplementoPaciente.setText("");
        view.jTcontatoTelefonePaciente.setText("");
        view.jTcontatoCelularPaciente.setText("");
        view.jTcontatoEmailPaciente.setText("");
        view.jTcontatoAdicionaisPaciente.setText("");
        view.jCenderecoUfPaciente.setSelectedItem("");

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBatualizaImagemPaciente.setEnabled(!isNull(Swap.getIdPaciente()));
        view.jBpacientePaginador.setEnabled(!isNull(Swap.getIdPaciente()));

    }

}
