package mvc.controller.tratamento;

import br.com.taimber.algoritmos.FormataParaBigDecimal;
import br.com.taimber.algoritmos.FormataParaMoedaBrasileira;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import sistema.model.PesquisaRegistro;
import br.com.taimber.persistencia.sql.SqlCompletaQuery;
import br.com.taimber.swings.MudaCorLinhaJtable;
import br.com.taimber.swings.Paginador;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;
import javax.swing.JTable;
import mvc.model.paciente.RecuperaDadosPaciente;
import mvc.model.tratamento.DaoTratamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;

public final class ControllerTratamentoPesquisa {

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
        SqlCompletaQuery completaQuery = new SqlCompletaQuery(entidadesPesquisa, jcPaginador.getSelectedItem(), new DaoTratamento().getTabela(), false);

        /* colunas a serem pesquisadas */
        List colunasTabela = new ArrayList();
        colunasTabela.add("id");
        colunasTabela.add("idPaciente");
        colunasTabela.add("dataInicio");
        colunasTabela.add("dataConclusao");
        colunasTabela.add("procedimento");
        colunasTabela.add("dentes");
        colunasTabela.add("situacao");
        colunasTabela.add("valor");
        colunasTabela.add("valorEntrada");
        colunasTabela.add("isPago");

        /* dados */
        List dados = new PesquisaRegistro().executar(new DaoTratamento().getTabela(), completaQuery, colunasTabela, null, "order by str_to_date(dataInicio, '%d/%m/%Y') asc");

        /* default model */
        DefaultTableModel model = (DefaultTableModel) jtResultados.getModel();
        model.setNumRows(0);

        /* contador */
        int contador = 0;

        /* list com cores */
        List cores = new ArrayList();

        /* listando os dados */
        for (Object linha : dados) {

            /* map de dados */
            Map dadosMap = (Map) linha;

            /* linhas da jtable */
            Object[] linhaJtable;

            /* valida */
            if (dadosMap.get("situacao").equals("Concluido") & dadosMap.get("isPago").equals("False")) {

                /* vermelho */
                cores.add(Color.decode("#f49093"));

            } else {

                /* verde */
                cores.add(Color.decode("#ace0c6"));

            }

            /* calcula a diferença */
            BigDecimal diferenca = FormataParaBigDecimal.formatar(dadosMap.get("valor")).subtract(FormataParaBigDecimal.formatar(dadosMap.get("valorEntrada")));

            /* trata os dados */
            dadosMap.put("valor", FormataParaMoedaBrasileira.cifrar(dadosMap.get("valor")));
            dadosMap.put("valorEntrada", FormataParaMoedaBrasileira.cifrar(dadosMap.get("valorEntrada")));
            dadosMap.put("valorDiferenca", FormataParaMoedaBrasileira.cifrar(diferenca));

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
                    dadosMap.get("dataInicio"),
                    dadosMap.get("dataConclusao"),
                    dadosMap.get("paciente"),
                    dadosMap.get("valor"),
                    dadosMap.get("valorEntrada"),
                    dadosMap.get("valorDiferenca"),
                    dadosMap.get("isPago")

                };

            } else {

                /* objeto linha */
                linhaJtable = new Object[]{
                    dadosMap.get("id"),
                    dadosMap.get("dataInicio"),
                    dadosMap.get("dataConclusao"),
                    dadosMap.get("procedimento"),
                    dadosMap.get("dentes"),
                    dadosMap.get("situacao"),
                    dadosMap.get("valor"),
                    dadosMap.get("valorEntrada"),
                    dadosMap.get("valorDiferenca"),
                    dadosMap.get("isPago")

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
        Paginador paginador = new Paginador(jcPaginador, new DaoTratamento().getTabela(), completaQuery.completar(false, null, null));
        paginador.popular(new BancoFactory(true).getBanco());

    }

}
