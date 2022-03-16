package mvc.controller.anamnese;

import javax.swing.JOptionPane;
import mvc.model.anamnese.BeanAnamnese;
import mvc.model.anamnese.DaoAnamnese;
import sistema.model.Swap;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerAnamneseGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     */
    public static void gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanAnamnese cadastro = new BeanAnamnese();
        cadastro.setId(id);
        cadastro.setIdPaciente(Swap.getIdPaciente());
        cadastro.setResposta1(view.jCanamneseR1.isSelected());
        cadastro.setResposta2(view.jCanamneseR2.isSelected());
        cadastro.setResposta3(view.jCanamneseR3.isSelected());
        cadastro.setResposta4(view.jCanamneseR4.isSelected());
        cadastro.setResposta5(view.jCanamneseR5.isSelected());
        cadastro.setResposta6(view.jCanamneseR6.isSelected());
        cadastro.setResposta7(view.jCanamneseR7.isSelected());
        cadastro.setResposta8(view.jCanamneseR8.isSelected());
        cadastro.setResposta9(view.jCanamneseR9.isSelected());
        cadastro.setResposta10(view.jCanamneseR10.isSelected());
        cadastro.setResposta11(view.jCanamneseR11.isSelected());
        cadastro.setResposta12(view.jCanamneseR12.isSelected());
        cadastro.setResposta13(view.jCanamneseR13.isSelected());
        cadastro.setResposta14(view.jCanamneseR14.isSelected());
        cadastro.setResposta15(view.jCanamneseR15.isSelected());
        cadastro.setResposta16(view.jCanamneseR16.getSelectedItem());
        cadastro.setResposta17(view.jTanamneseR17.getText());
        cadastro.setResposta18(view.jTanamneseR18.getText());
        cadastro.setResposta19(view.jTanamneseR19.getText());

        /* cadastro */
        DaoAnamnese cadastroDao = new DaoAnamnese();

        /* validad dados */
        if (isDadosValidados(view)) {

            /* grava */
            if (cadastroDao.gravar(cadastro)) {

                /* Informa que o cadastro foi realizado */
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

            }

        }

    }

    /* valida os dados */
    private static boolean isDadosValidados(ViewSistema view) {

        /* retorno */
        return true;

    }

}
