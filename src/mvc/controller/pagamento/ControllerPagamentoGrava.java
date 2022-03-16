package mvc.controller.pagamento;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.swings.ValidaCamposNumericos;
import javax.swing.JOptionPane;
import mvc.model.pagamento.BeanPagamento;
import mvc.model.pagamento.DaoPagamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerPagamentoGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return True sucesso
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanPagamento cadastro = new BeanPagamento();
        cadastro.setId(id);
        cadastro.setIdPaciente(Swap.getIdPaciente());
        cadastro.setData(Datas.converterDateParaString(view.jDpagamentoDataVencimento.getDate()));
        cadastro.setValor(view.jTpagamentoValor.getText());
        cadastro.setHistorico(view.jTpagamentoHistorico.getText());
        cadastro.setIsPago(view.jCpagamentoPago.isSelected());
        cadastro.setCodigoTratamento(view.jTpagamentoCodigoTratamento.getText());
        cadastro.setFormaPagamento(view.jCpagamentoForma.getEditor().getItem());

        /* cadastro */
        DaoPagamento cadastroDao = new DaoPagamento();

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
        return ValidaCamposNumericos.isCamposNumericosValidados(view.jTpagamentoValor);

    }

}
