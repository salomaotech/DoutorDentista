package mvc.controller.parcelamento;

import java.awt.event.ActionEvent;
import mvc.controller.pagamento.ControllerPagamentoPesquisa;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerParcelamentoEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão para gravar */
        view.jBpagamentoParcelaGravar.addActionListener((ActionEvent e) -> {

            /* grava */
            if (ControllerParcelamentoGrava.gravar(view)) {

                /* executar após uma ação */
                executarPosAcao(view);

            }

        });

        /* botão novo tratamento */
        view.jBpagamentoParcelaNovo.addActionListener((ActionEvent e) -> {

            /* reseta os campos */
            ControllerParcelamentoView.resetaControles(view);

            /* move o foco */
            view.jDpagamentoDataVencimentoParcela.requestFocus();

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao(ViewSistema view) {

        /* seleciona aba */
        view.jTabaCadastroFinanceiroPaciente.setSelectedIndex(2);

        /* habilita controles */
        ControllerParcelamentoView.resetaControles(view);
        ControllerParcelamentoView.habilitaControles(view);

        /* exibe os dados */
        ControllerPagamentoPesquisa.pesquisar(view, view.jCpagamentoPaginador, view.jTpagamentoResultados, false, Swap.getIdPaciente(), false);

    }

}
