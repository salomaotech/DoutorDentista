package mvc.controller.anamnese;

import static java.util.Objects.isNull;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerAnamneseView {

    /**
     * Reseta os controles
     *
     * @param view
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campos */
        view.jCanamneseR1.setSelected(false);
        view.jCanamneseR2.setSelected(false);
        view.jCanamneseR3.setSelected(false);
        view.jCanamneseR4.setSelected(false);
        view.jCanamneseR5.setSelected(false);
        view.jCanamneseR6.setSelected(false);
        view.jCanamneseR7.setSelected(false);
        view.jCanamneseR8.setSelected(false);
        view.jCanamneseR9.setSelected(false);
        view.jCanamneseR10.setSelected(false);
        view.jCanamneseR11.setSelected(false);
        view.jCanamneseR12.setSelected(false);
        view.jCanamneseR13.setSelected(false);
        view.jCanamneseR14.setSelected(false);
        view.jCanamneseR15.setSelected(false);
        view.jCanamneseR16.setSelectedIndex(0);
        view.jTanamneseR17.setText("");
        view.jTanamneseR18.setText("");
        view.jTanamneseR19.setText("");

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBsalvarAnamnese.setEnabled(!isNull(Swap.getIdPaciente()));

    }

}
