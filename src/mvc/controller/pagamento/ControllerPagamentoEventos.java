package mvc.controller.pagamento;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mvc.controller.paciente.ControllerPacienteEventos;
import mvc.model.paciente.RecuperaDadosPaciente;
import mvc.model.pagamento.DaoPagamento;
import mvc.model.pagamento.RecuperaDadosPagamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.RemoveRegistro;
import sistema.model.Swap;

public class ControllerPagamentoEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBpagamentoGravar.addActionListener((ActionEvent e) -> {

            /* grava os dados */
            if (ControllerPagamentoGrava.gravar(view, Swap.getIdPagamento())) {

                /* executar após ação */
                executarPosAcao(view);

            }

        });

        /* botão excluir */
        view.jBpagamentoExcluir.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu remover o registro */
            if (RemoveRegistro.executar(new DaoPagamento().getTabela(), Swap.getIdPagamento())) {

                /* executar após ação */
                executarPosAcao(view);

            }

        });

        /* botão novo */
        view.jBpagamentoNovo.addActionListener((ActionEvent e) -> {

            /* limpa o swap */
            Swap.setIdPagamento(null);

            /* reseta os controles */
            ControllerPagamentoView.resetaControles(view);

            /* habilita os controles */
            ControllerPagamentoView.habilitaControles(view);

            /* move o foco */
            view.jDpagamentoDataVencimento.requestFocus();

        });

        /* botao paginador pesquisa */
        view.jBpagamentoPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerPagamentoPesquisa.pesquisar(view, view.jCpagamentoPaginador, view.jTpagamentoResultados, false, Swap.getIdPaciente(), false);

        });

        /* add evento mouse sobre jtable de resultados de pagamento de paciente */
        view.jTpagamentoResultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* atualiza o swap */
                    Swap.setIdPagamento(view.jTpagamentoResultados.getModel().getValueAt(view.jTpagamentoResultados.getSelectedRow(), 0));

                    /* exibe os dados */
                    ControllerPagamentoExibe.exibir(view, Swap.getIdPagamento());

                    /* seleciona aba */
                    view.jTabaCadastroFinanceiroPaciente.setSelectedIndex(0);

                    /* habilita controles */
                    ControllerPagamentoView.habilitaControles(view);

                }

            }

        });

        /* botão para pesquisar devedores */
        view.jBpagamentoDevedores.addActionListener((ActionEvent e) -> {

            /* pesquisa devedores */
            ControllerPagamentoPesquisa.pesquisar(view, view.jCpagamentoDevedoresPaginador, view.jTpagamentoDevedoresResultados, true, RecuperaDadosPaciente.getIdPacientePorNome(view.jCpagamentoDevedoresPaciente.getSelectedItem()), view.jCpagamentoDevedoresPacientePago.isSelected());

        });

        /* botão paginador para pesquisar devedores */
        view.jBpagamentoDevedoresPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa devedores */
            ControllerPagamentoPesquisa.pesquisar(view, view.jCpagamentoDevedoresPaginador, view.jTpagamentoDevedoresResultados, true, RecuperaDadosPaciente.getIdPacientePorNome(view.jCpagamentoDevedoresPaciente.getSelectedItem()), view.jCpagamentoDevedoresPacientePago.isSelected());

        });

        /* add evento mouse sobre jtable de resultados de pagamento de paciente */
        view.jTpagamentoDevedoresResultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* id do pagamento */
                    String idPagamento = (String) view.jTpagamentoDevedoresResultados.getModel().getValueAt(view.jTpagamentoDevedoresResultados.getSelectedRow(), 0);

                    /* atualiza o swap */
                    Swap.setIdPaciente(RecuperaDadosPagamento.getIdPacienteAssociadoPagamento(idPagamento));
                    Swap.setIdPagamento(idPagamento);

                    /* abre a ficha do paciente */
                    ControllerPacienteEventos.executarPosAcao(view);

                    /* exibe os dados */
                    ControllerPagamentoExibe.exibir(view, Swap.getIdPagamento());

                    /* seleciona aba */
                    view.jTabaPrincipal.setSelectedIndex(2);
                    view.jTabaCadastroPaciente.setSelectedIndex(4);
                    view.jTabaCadastroFinanceiroPaciente.setSelectedIndex(0);

                    /* habilita controles */
                    ControllerPagamentoView.habilitaControles(view);

                }

            }

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao(ViewSistema view) {

        /* reseta id de pagamento */
        Swap.setIdPagamento(null);

        /* seleciona aba */
        view.jTabaCadastroFinanceiroPaciente.setSelectedIndex(2);

        /* limpa os controles */
        ControllerPagamentoView.resetaControles(view);
        ControllerPagamentoView.habilitaControles(view);

        /* exibe os dados */
        ControllerPagamentoPesquisa.pesquisar(view, view.jCpagamentoPaginador, view.jTpagamentoResultados, false, Swap.getIdPaciente(), false);

    }

}
