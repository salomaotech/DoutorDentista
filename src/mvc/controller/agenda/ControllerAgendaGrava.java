package mvc.controller.agenda;

import br.com.taimber.algoritmos.Datas;
import javax.swing.JOptionPane;
import mvc.model.agenda.BeanAgenda;
import mvc.model.agenda.DaoAgenda;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerAgendaGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return true conseguiu gravar os dados
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanAgenda cadastro = new BeanAgenda();
        cadastro.setId(id);
        cadastro.setNome(view.jTagendaNome.getText());
        cadastro.setData(Datas.converterDateParaString(view.jDagendaData.getDate()));
        cadastro.setHora(view.jCagendaHora.getSelectedItem());
        cadastro.setMinuto(view.jCagendaMinuto.getSelectedItem());
        cadastro.setHistorico(view.jTagendaHistorico.getText());

        /* cadastro */
        DaoAgenda cadastroDao = new DaoAgenda();

        /* validad dados */
        if (isDadosValidados(view)) {

            /* grava */
            if (cadastroDao.gravar(cadastro)) {

                /* Informa que o cadastro foi realizado */
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

                /* retorno */
                return true;

            }

        }

        /* retorno */
        return false;

    }

    /* valida os dados */
    private static boolean isDadosValidados(ViewSistema view) {

        /* valida nome */
        if (view.jTagendaNome.getText().length() == 0) {

            /* diálogo */
            JOptionPane.showMessageDialog(null, "Informe o nome da agenda.");

            /* move o foco */
            view.jTagendaNome.requestFocus();

            /* retorno */
            return false;

        }

        /* retorno */
        return true;

    }

}
