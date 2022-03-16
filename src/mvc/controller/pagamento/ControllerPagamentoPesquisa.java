package mvc.controller.pagamento;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.swings.MudaCorLinhaJtable;
import br.com.taimber.swings.Paginador;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mvc.model.paciente.RecuperaDadosPaciente;
import mvc.model.pagamento.DaoPagamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;
import sistema.model.PesquisaRegistro;

public class ControllerPagamentoPesquisa {

    /**
     * Exibe os dados
     *
     * @param view View
     * @param jcPaginador Paginador
     * @param jtResultados Resultados
     * @param isTodosOsPacientes True pesquisa por todos os pacientes
     * @param idPaciente Id do paciente
     * @param isPago True lista o que ja foi pago
     */
    public static void pesquisar(ViewSistema view, JComboBox jcPaginador, JTable jtResultados, boolean isTodosOsPacientes, Object idPaciente, boolean isPago) {

        /* entidades */
        Map entidadesPesquisa = new LinkedHashMap();

        /* valida se foi pago */
        if (isPago) {

            /* popula */
            entidadesPesquisa.put("isPago", "True");

        } else {

            /* popula */
            entidadesPesquisa.put("isPago", "False");

        }

        /* valida id de paciente */
        if (!isNull(idPaciente)) {

            /* popula */
            entidadesPesquisa.put("idPaciente", idPaciente);

        }

        /* se não forem todos os paciententes pesquise por pagos e não pagos */
        if (!isTodosOsPacientes) {

            /* remove */
            entidadesPesquisa.remove("isPago");

        }

        /* completa query */
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, jcPaginador.getSelectedItem(), new DaoPagamento().getTabela(), false);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("idPaciente");
        colunasTabela.add("data");
        colunasTabela.add("valor");
        colunasTabela.add("historico");
        colunasTabela.add("isPago");
        colunasTabela.add("codigoTratamento");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoPagamento().getTabela(), completaQuery, colunasTabela, null, "order by str_to_date(data, '%d/%m/%Y') asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) jtResultados.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* saldo */
        BigDecimal saldo = new BigDecimal("0");

        /* list com cores */
        List cores = new ArrayList();

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* linhas da jtable */
            Object[] linhaJtable;

            /* valida */
            if (Datas.isDataInicialMenorDataFinal(Datas.getDataAtualDiaMesAno(), dadosMap.get("data")) | dadosMap.get("isPago").equals("True")) {

                /* verde */
                cores.add(Color.decode("#ace0c6"));

            } else {

                /* vermelho */
                cores.add(Color.decode("#f49093"));

            }

            /* calcula o saldo */
            saldo = saldo.add(FormataParaBigDecimal.formatar(dadosMap.get("valor")));
            dadosMap.put("saldo", FormataParaMoedaBrasileira.cifrar(saldo));

            /* trata os dados */
            dadosMap.put("valor", FormataParaMoedaBrasileira.cifrar(dadosMap.get("valor")));

            /* valida se foi pago */
            if (dadosMap.get("isPago").equals("True")) {

                dadosMap.put("isPago", "Sim");

            } else {

                dadosMap.put("isPago", "Nao");

            }

            /* valida todos */
            if (isTodosOsPacientes) {

                /* popula */
                dadosMap.put("paciente", RecuperaDadosPaciente.getNomePacientePorId(dadosMap.get("idPaciente")));

                /* objeto linha */
                linhaJtable = new Object[]{
                    dadosMap.get("id"),
                    dadosMap.get("data"),
                    dadosMap.get("paciente"),
                    dadosMap.get("isPago"),
                    dadosMap.get("valor"),
                    dadosMap.get("saldo"),
                    dadosMap.get("codigoTratamento")

                };

            } else {

                /* objeto linha */
                linhaJtable = new Object[]{
                    dadosMap.get("id"),
                    dadosMap.get("data"),
                    dadosMap.get("historico"),
                    dadosMap.get("isPago"),
                    dadosMap.get("valor"),
                    dadosMap.get("saldo"),
                    dadosMap.get("codigoTratamento")

                };

            }

            /* insere a linha */
            model.insertRow(contador, linhaJtable);

            /* atualiza o contador */
            contador++;

        }

        /* muda a cor da jtable */
        MudaCorLinhaJtable.mudar(jtResultados, cores);

        /* paginador */
        Paginador paginador = new Paginador(jcPaginador, new DaoPagamento().getTabela(), completaQuery.completar(false, null, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
