package mvc.controller.parcelamento;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.swings.ValidaCamposNumericos;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import mvc.model.parcelamento.GeraParcelas;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Swap;

public class ControllerParcelamentoGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @return True conseguiu gravar
     */
    public static boolean gravar(ViewSistema view) {

        /* gerador de parcelas */
        GeraParcelas parcela = new GeraParcelas();
        parcela.setData(Datas.converterDateParaString(view.jDpagamentoDataVencimentoParcela.getDate()));
        parcela.setValor(view.jTpagamentoValorParcela.getText());
        parcela.setHistorico(view.jTpagamentoHistoricoParcela.getText());
        parcela.setNumeroParcelas((String) view.jCpagamentoNumeroParcelas.getSelectedItem());
        parcela.setIdPaciente(Swap.getIdPaciente());
        parcela.setCodigoTratamento(view.jTpagamentoCodigoTratamentoHistoricoParcela.getText());
        parcela.setFormaPagamento(view.jCpagamentoFormaParcela.getEditor().getItem());

        /* valida os dados */
        if (isDadosValidados(view)) {

            /* gera as parcelas */
            parcela.gerar();

            /* Informa que o cadastro foi realizado */
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

            /* retorno */
            return true;

        }

        /* retorno */
        return false;

    }

    /* valida os dados */
    private static boolean isDadosValidados(ViewSistema view) {

        /* valida campos númericos */
        if (!ValidaCamposNumericos.isCamposNumericosValidados(view.jTpagamentoValorParcela)) {

            /* retorno */
            return false;

        }

        /* valida o número de parcelas */
        if (view.jCpagamentoNumeroParcelas.getSelectedItem().equals("0")) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Número de parcelas inválido.");

            /* retorno */
            return false;

        }

        /* valida data */
        if (isNull(Datas.converterDateParaString(view.jDpagamentoDataVencimentoParcela.getDate()))) {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "Data inválida.");

            /* move o foco */
            view.jDpagamentoDataVencimentoParcela.requestFocus();

            /* retorno */
            return false;

        }

        /* retorno */
        return true;

    }

}
