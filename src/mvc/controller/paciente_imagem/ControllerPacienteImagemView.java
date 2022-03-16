package mvc.controller.paciente_imagem;

import mvc.view.telas.sistema.ViewSistema;

public class ControllerPacienteImagemView {

    /**
     * Reseta os controles
     *
     * @param view View
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campo de imagem */
        view.jPimagemProfile.removeAll();

        /* repaint */
        view.repaint();

    }

}
