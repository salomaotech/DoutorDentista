package mvc.controller.tratamento;

import br.com.taimber.algoritmos.Datas;
import java.util.Map;
import mvc.model.tratamento.DaoTratamento;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerTratamentoExibe {

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
            DaoTratamento cadastroDao = new DaoTratamento();

            /* dados */
            Map dados = (Map) cadastroDao.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jDdataInicioTratamento.setDate(Datas.converterStringParaDate((String) dados.get("dataInicio")));
            view.jDdataConcluidoTratamento.setDate(Datas.converterStringParaDate((String) dados.get("dataConclusao")));
            view.jCsituacaoTratamento.setSelectedItem(dados.get("situacao"));
            view.jTdentesTratamento.setText((String) dados.get("dentes"));
            view.jTprocedimentoTratamento.setText((String) dados.get("procedimento"));
            view.jTvalorTratamento.setText((String) dados.get("valor"));
            view.jTvalorEntradaTratamento.setText((String) dados.get("valorEntrada"));
            view.jCpagoTratamento.setSelected(dados.get("isPago").equals("True"));
            view.jLcodigoTratamento.setText((String) id);

        } catch (java.lang.IndexOutOfBoundsException | java.lang.NullPointerException ex) {

        }

    }

}
