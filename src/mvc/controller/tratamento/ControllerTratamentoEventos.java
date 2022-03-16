package mvc.controller.tratamento;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.algoritmos.FormataParaBigDecimal;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import static java.util.Objects.isNull;
import mvc.controller.paciente.ControllerPacienteEventos;
import mvc.model.paciente.RecuperaDadosPaciente;
import sistema.model.RemoveRegistro;
import sistema.model.Swap;
import mvc.model.tratamento.DaoTratamento;
import mvc.model.tratamento.RecuperaDadosTratamento;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerTratamentoEventos {

    /**
     * Adiciona os eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBsalvarTratamento.addActionListener((ActionEvent e) -> {

            /* grava os dados */
            if (ControllerTratamentoGrava.gravar(view, Swap.getIdTratamento())) {

                /* valida swap */
                if (isNull(Swap.getIdTratamento())) {

                    /* atualiza o swap */
                    Swap.setIdTratamento(RecuperaDadosTratamento.getUltimoIdCadastrado());

                }

                /* executar após ação */
                executarPosAcao2(view);

            }

        });

        /* botão excluir */
        view.jBexcluirTratamento.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu remover o registro */
            if (RemoveRegistro.executar(new DaoTratamento().getTabela(), Swap.getIdTratamento())) {

                /* executar após ação */
                executarPosAcao1(view);

            }

        });

        /* add evento mouse sobre jtable de resultados de pesquisa de paciente */
        view.jTresultadosTratamento.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* atualiza o swap */
                    Swap.setIdTratamento(view.jTresultadosTratamento.getModel().getValueAt(view.jTresultadosTratamento.getSelectedRow(), 0));

                    /* executar após ação */
                    executarPosAcao2(view);

                }

            }

        });

        /* botão novo tratamento */
        view.jBnovoTratamento.addActionListener((ActionEvent e) -> {

            /* limpa o swap */
            Swap.setIdTratamento(null);

            /* reseta os controles */
            ControllerTratamentoView.resetaControles(view);

            /* habilita controles */
            ControllerTratamentoView.habilitaControles(view);

            /* move o foco */
            view.jDdataInicioTratamento.requestFocus();

        });

        /* botao paginador pesquisa */
        view.jBtratamentoPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerTratamentoPesquisa.pesquisar(view, view.jCtratamentoPaginador, view.jTresultadosTratamento, false, Swap.getIdPaciente(), false);

        });

        /* botão para pesquisar devedores */
        view.jBpagamentoDevedoresTratamentoPesquisar.addActionListener((ActionEvent e) -> {

            /* pesquisa devedores */
            ControllerTratamentoPesquisa.pesquisar(view, view.jCtratamentoPaginador, view.jTresultadosDevedoresTratamentos, true, RecuperaDadosPaciente.getIdPacientePorNome(view.jCpagamentoDevedoresTratamentoPaciente.getSelectedItem()), view.jCpagamentoDevedoresTratamentoPago.isSelected());

        });

        /* botão para pesquisar devedores paginador */
        view.jBpagamentoDevedoresTratamento.addActionListener((ActionEvent e) -> {

            /* pesquisa devedores */
            ControllerTratamentoPesquisa.pesquisar(view, view.jCtratamentoPaginador, view.jTresultadosDevedoresTratamentos, true, RecuperaDadosPaciente.getIdPacientePorNome(view.jCpagamentoDevedoresTratamentoPaciente.getSelectedItem()), view.jCpagamentoDevedoresTratamentoPago.isSelected());

        });

        /* botão de atalho para gerar parcelamento do tratamento */
        view.jBatalhoParcelarTratamento.addActionListener((ActionEvent e) -> {

            /* valores */
            BigDecimal valorTratamento = FormataParaBigDecimal.formatar(view.jTvalorTratamento.getText());
            BigDecimal valorEntrada = FormataParaBigDecimal.formatar(view.jTvalorEntradaTratamento.getText());
            BigDecimal valorRestante = valorTratamento.subtract(valorEntrada);
            String data = Datas.adicionaMesData(Datas.converterDateParaString(view.jDdataConcluidoTratamento.getDate()), 1);

            /* popula */
            view.jTpagamentoCodigoTratamentoHistoricoParcela.setText((String) Swap.getIdTratamento());
            view.jTpagamentoValorParcela.setText(String.valueOf(valorRestante));
            view.jTpagamentoHistoricoParcela.setText("Dente: " + view.jTdentesTratamento.getText() + " - " + "Tratamento: " + view.jTprocedimentoTratamento.getText());
            view.jDpagamentoDataVencimentoParcela.setDate(Datas.converterStringParaDate(data));
            view.jTabaCadastroPaciente.setSelectedIndex(4);
            view.jTabaCadastroFinanceiroPaciente.setSelectedIndex(1);
            view.jTpagamentoHistoricoParcela.requestFocus();

        });

        /* add evento mouse sobre jtable de resultados de pagamento de paciente */
        view.jTresultadosDevedoresTratamentos.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* id do tratamento */
                    String idTratamento = (String) view.jTresultadosDevedoresTratamentos.getModel().getValueAt(view.jTresultadosDevedoresTratamentos.getSelectedRow(), 0);

                    /* atualiza o swap */
                    Swap.setIdPaciente(RecuperaDadosTratamento.getIdPacienteAssociadoTratamento(idTratamento));
                    Swap.setIdTratamento(idTratamento);

                    /* abre a ficha do paciente */
                    ControllerPacienteEventos.executarPosAcao(view);

                    /* exibe os dados do tratamento */
                    ControllerTratamentoExibe.exibir(view, Swap.getIdTratamento());

                    /* seleciona aba */
                    view.jTabaPrincipal.setSelectedIndex(2);
                    view.jTabaCadastroPaciente.setSelectedIndex(2);
                    view.jTabaCadastroTratamentoPaciente.setSelectedIndex(0);

                    /* habilita os controles */
                    ControllerTratamentoView.habilitaControles(view);

                }

            }

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao1(ViewSistema view) {

        /* limpa a swap */
        Swap.setIdTratamento(null);

        /* reseta os controles */
        ControllerTratamentoView.resetaControles(view);

        /* habilita controles */
        ControllerTratamentoView.habilitaControles(view);

        /* pesquisa */
        ControllerTratamentoPesquisa.pesquisar(view, view.jCtratamentoPaginador, view.jTresultadosTratamento, false, Swap.getIdPaciente(), false);

        /* seleciona aba */
        view.jTabaCadastroTratamentoPaciente.setSelectedIndex(1);

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao2(ViewSistema view) {

        /* exibe os dados */
        ControllerTratamentoExibe.exibir(view, Swap.getIdTratamento());

        /* habilita controles */
        ControllerTratamentoView.habilitaControles(view);

        /* pesquisa */
        ControllerTratamentoPesquisa.pesquisar(view, view.jCtratamentoPaginador, view.jTresultadosTratamento, false, Swap.getIdPaciente(), false);

        /* seleciona aba */
        view.jTabaCadastroTratamentoPaciente.setSelectedIndex(0);

    }

}
