package mvc.controller.tratamento;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.swings.ValidaCamposNumericos;
import javax.swing.JOptionPane;
import mvc.model.tratamento.BeanTratamento;
import mvc.model.tratamento.DaoTratamento;
import static java.util.Objects.isNull;
import sistema.model.Swap;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerTratamentoGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return True para sucesso
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanTratamento cadastro = new BeanTratamento();
        cadastro.setId(id);
        cadastro.setIdPaciente(Swap.getIdPaciente());
        cadastro.setDataInicio(Datas.converterDateParaString(view.jDdataInicioTratamento.getDate()));
        cadastro.setDataConclusao(Datas.converterDateParaString(view.jDdataConcluidoTratamento.getDate()));
        cadastro.setSituacao(view.jCsituacaoTratamento.getSelectedItem());
        cadastro.setDentes(view.jTdentesTratamento.getText());
        cadastro.setProcedimento(view.jTprocedimentoTratamento.getText());
        cadastro.setValor(view.jTvalorTratamento.getText());
        cadastro.setValorEntrada(view.jTvalorEntradaTratamento.getText());
        cadastro.setIsPago(view.jCpagoTratamento.isSelected());

        /* cadastro */
        DaoTratamento cadastroDao = new DaoTratamento();

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

        /* valida id de paciente */
        if (isNull(Swap.getIdPaciente())) {

            /* diálogo */
            JOptionPane.showMessageDialog(null, "Informe um paciente!");

            /* retorno */
            return false;

        }

        /* valida valor de entrada */
        if (view.jTvalorEntradaTratamento.getText().equals("")) {

            /* popula valor padrão */
            view.jTvalorEntradaTratamento.setText("0");

        }

        /* valida campo */
        if (!ValidaCamposNumericos.isCamposNumericosValidados(view.jTvalorTratamento)) {

            /* retorno */
            return false;

        }

        /* retorno */
        return ValidaCamposNumericos.isCamposNumericosValidados(view.jTvalorEntradaTratamento);

    }

}
