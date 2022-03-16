package mvc.controller.agenda;

import br.com.taimber.patterns.ObjetoComposite;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerScheduler implements ObjetoComposite {

    private final ViewSistema view;

    /**
     * Construtor
     *
     * @param view View
     */
    public ControllerScheduler(ViewSistema view) {

        /* view */
        this.view = view;

    }

    @Override
    public void execute() {

        /* valida o refresh automático */
        if (view.jCagendaRefreshAutomatico.isSelected()) {

            /* pesquisa */
            ControllerAgendaPesquisa.pesquisar(this.view);

        }

    }

}
