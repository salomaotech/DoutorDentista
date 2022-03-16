package mvc.controller.plano_tratamento;

import br.com.taimber.swings.ValidaCamposNumericos;
import javax.swing.JOptionPane;
import mvc.model.plano_tratamento.BeanPlanoTratamento;
import mvc.model.plano_tratamento.DaoPlanoTratamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

/**
 * Pega os dados da view e grava no banco de dados
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerPlanoTratamentoGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return True conseguiu salvar os dados
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanPlanoTratamento cadastro = new BeanPlanoTratamento();
        cadastro.setId(id);
        cadastro.setIdPaciente(Swap.getIdPaciente());
        cadastro.setProcedimento(view.jTplanoTratamentoProcedimento.getText());
        cadastro.setValor(view.jTplanoTratamentoValor.getText());

        /* cadastro */
        DaoPlanoTratamento cadastroDao = new DaoPlanoTratamento();

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

        /* retorno */
        return ValidaCamposNumericos.isCamposNumericosValidados(view.jTplanoTratamentoValor);

    }

}
