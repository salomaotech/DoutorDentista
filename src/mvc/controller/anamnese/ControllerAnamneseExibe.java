package mvc.controller.anamnese;

import java.util.Map;
import mvc.model.anamnese.DaoAnamnese;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerAnamneseExibe {

    /**
     * Exibe os dados
     *
     * @param view View
     * @param id Id do cadastro
     */
    public static void exibir(ViewSistema view, Object id) {

        /* excessão */
        try {

            /* cadastro */
            DaoAnamnese cadastroDao = new DaoAnamnese();

            /* dados */
            Map dados = (Map) cadastroDao.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jCanamneseR1.setSelected(dados.get("resposta1").equals("True"));
            view.jCanamneseR2.setSelected(dados.get("resposta2").equals("True"));
            view.jCanamneseR3.setSelected(dados.get("resposta3").equals("True"));
            view.jCanamneseR4.setSelected(dados.get("resposta4").equals("True"));
            view.jCanamneseR5.setSelected(dados.get("resposta5").equals("True"));
            view.jCanamneseR6.setSelected(dados.get("resposta6").equals("True"));
            view.jCanamneseR7.setSelected(dados.get("resposta7").equals("True"));
            view.jCanamneseR8.setSelected(dados.get("resposta8").equals("True"));
            view.jCanamneseR9.setSelected(dados.get("resposta9").equals("True"));
            view.jCanamneseR10.setSelected(dados.get("resposta10").equals("True"));
            view.jCanamneseR11.setSelected(dados.get("resposta11").equals("True"));
            view.jCanamneseR12.setSelected(dados.get("resposta12").equals("True"));
            view.jCanamneseR13.setSelected(dados.get("resposta13").equals("True"));
            view.jCanamneseR14.setSelected(dados.get("resposta14").equals("True"));
            view.jCanamneseR15.setSelected(dados.get("resposta15").equals("True"));
            view.jCanamneseR16.setSelectedItem(dados.get("resposta16"));
            view.jTanamneseR17.setText((String) dados.get("resposta17"));
            view.jTanamneseR18.setText((String) dados.get("resposta18"));
            view.jTanamneseR19.setText((String) dados.get("resposta19"));

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

}
