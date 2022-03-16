package mvc.controller.plano_tratamento;

import java.util.Map;
import mvc.model.plano_tratamento.DaoPlanoTratamento;
import mvc.view.telas.sistema.ViewSistema;

/**
 * Exibe os dados cadastrados de um registro na view
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerPlanoTratamentoExibe {

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
            DaoPlanoTratamento cadastro = new DaoPlanoTratamento();

            /* dados */
            Map dados = (Map) cadastro.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jTplanoTratamentoProcedimento.setText((String) dados.get("procedimento"));
            view.jTplanoTratamentoValor.setText((String) dados.get("valor"));

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

}
