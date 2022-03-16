package sistema.controller;

import mvc.controller.anamnese.ControllerAnamneseView;
import mvc.controller.pagamento.ControllerPagamentoView;
import mvc.controller.paciente.ControllerPacienteView;
import mvc.controller.tratamento.ControllerTratamentoView;
import mvc.controller.upload.ControllerUploadView;
import mvc.controller.paciente_imagem.ControllerPacienteImagemView;
import mvc.controller.parcelamento.ControllerParcelamentoView;
import mvc.controller.plano_tratamento.ControllerPlanoTratamentoView;
import sistema.model.Swap;

public class ControllerViewSistema {

    /**
     * Reseta os controles da view
     *
     * @param view View
     */
    public static void resete(mvc.view.telas.sistema.ViewSistema view) {

        /* limpa swap */
        Swap.setIdPaciente(null);
        Swap.setIdPagamento(null);
        Swap.setIdTratamento(null);
        Swap.setIdPlanoTratamento(null);

        /* tratamentos */
        ControllerTratamentoView.resetaControles(view);
        ControllerTratamentoView.limpaListaResultadosAntigos(view);

        /* anamnese */
        ControllerAnamneseView.resetaControles(view);

        /* paciente */
        ControllerPacienteView.resetaControles(view);
        ControllerPacienteImagemView.resetaControles(view);

        /* uploads */
        ControllerUploadView.resetaControles(view);
        ControllerUploadView.habilitaControles(view);

        /* pagamentos */
        ControllerPagamentoView.resetaControles(view);
        ControllerPagamentoView.limpaListaResultadosAntigos(view);

        /* parcelas */
        ControllerParcelamentoView.resetaControles(view);
        ControllerParcelamentoView.habilitaControles(view);

        /* plano de tratamentos */
        ControllerPlanoTratamentoView.resetaControles(view);
        ControllerPlanoTratamentoView.habilitaControles(view);
        ControllerPlanoTratamentoView.limpaListaResultadosAntigos(view);

        /* reseta abas */
        view.jTabaPrincipal.setSelectedIndex(0);
        view.jTabaCadastroTratamentoPaciente.setSelectedIndex(0);
        view.jTabaCadastroAgenda.setSelectedIndex(0);
        view.jTabaCadastroFinanceiroPaciente.setSelectedIndex(0);
        view.jTabaCadastroPaciente.setSelectedIndex(0);
        view.jTabaDadosCadastroPaciente.setSelectedIndex(0);
        view.jTabaPlanoTratamento.setSelectedIndex(0);

        /* repaint */
        view.repaint();

    }

}
