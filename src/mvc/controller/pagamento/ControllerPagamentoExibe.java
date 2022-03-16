package mvc.controller.pagamento;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.arquivos.LeitorDePropriedades;
import br.com.taimber.swings.PopulaCombobox;
import java.util.Map;
import java.util.Properties;
import mvc.model.pagamento.DaoPagamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.Propriedades;

public class ControllerPagamentoExibe {

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
            DaoPagamento cadastro = new DaoPagamento();

            /* dados */
            Map dados = (Map) cadastro.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jDpagamentoDataVencimento.setDate(Datas.converterStringParaDate((String) dados.get("data")));
            view.jTpagamentoValor.setText((String) dados.get("valor"));
            view.jTpagamentoHistorico.setText((String) dados.get("historico"));
            view.jCpagamentoPago.setSelected(dados.get("isPago").equals("True"));
            view.jTpagamentoCodigoTratamento.setText((String) dados.get("codigoTratamento"));
            view.jCpagamentoForma.setSelectedItem(dados.get("formaPagamento"));

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

    /**
     * Exibe os dados
     *
     * @param view View
     */
    public static void exibir(ViewSistema view) {

        /* propriedades */
        Properties propriedades = new LeitorDePropriedades(Propriedades.ENDERECO_ARQUIVO_CONFIGURACOES).getPropriedades();

        /* popula */
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCpagamentoDevedoresPaciente, propriedades.getProperty("prop.server.tabelaPaciente"), "nome");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCpagamentoDevedoresTratamentoPaciente, propriedades.getProperty("prop.server.tabelaPaciente"), "nome");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCpagamentoForma, propriedades.getProperty("prop.server.tabelaPagamentosPaciente"), "formaPagamento");
        PopulaCombobox.executa(new BancoFactory(true).getBanco(), view.jCpagamentoFormaParcela, propriedades.getProperty("prop.server.tabelaPagamentosPaciente"), "formaPagamento");

    }

}
