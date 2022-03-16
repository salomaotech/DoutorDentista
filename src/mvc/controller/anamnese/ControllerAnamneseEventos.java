package mvc.controller.anamnese;

import java.awt.event.ActionEvent;
import mvc.model.anamnese.RecuperaDadosAnamnesePaciente;
import sistema.model.Swap;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerAnamneseEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBsalvarAnamnese.addActionListener((ActionEvent e) -> {

            /* grava os dados */
            ControllerAnamneseGrava.gravar(view, RecuperaDadosAnamnesePaciente.getDados(Swap.getIdPaciente()).get("id"));

            /* executar após ação */
            executarPosAcao(view);

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao(ViewSistema view) {

        /* limpa os controles */
        ControllerAnamneseView.resetaControles(view);

        /* exibe os dados */
        ControllerAnamneseExibe.exibir(view, RecuperaDadosAnamnesePaciente.getDados(Swap.getIdPaciente()).get("id"));

    }

}
