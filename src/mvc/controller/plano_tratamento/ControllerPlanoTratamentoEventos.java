package mvc.controller.plano_tratamento;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.util.Objects.isNull;
import mvc.model.plano_tratamento.DaoPlanoTratamento;
import mvc.model.plano_tratamento.RecuperaDadosPlanoTratamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.RemoveRegistro;
import sistema.model.Swap;

/**
 * Eventos da view
 *
 * @author E-mail(salomao@taimber.com)
 * @version 1.0
 */
public class ControllerPlanoTratamentoEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBsalvarPlanoTratamento.addActionListener((ActionEvent e) -> {

            /* grava */
            if (ControllerPlanoTratamentoGrava.gravar(view, Swap.getIdPlanoTratamento())) {

                /* valida id do plano de tratamento */
                if (isNull(Swap.getIdPlanoTratamento())) {

                    /* atualiza o swap */
                    Swap.setIdPlanoTratamento(RecuperaDadosPlanoTratamento.getUltimoIdCadastrado());

                }

                /* executar após ação */
                executarPosAcao2(view);

            }

        });

        /* botão excluir */
        view.jBplanoTratamentoExcluir.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu remover o registro */
            if (RemoveRegistro.executar(new DaoPlanoTratamento().getTabela(), Swap.getIdPlanoTratamento())) {

                /* executar após ação */
                executarPosAcao1(view);

            }

        });

        /* botão novo */
        view.jBplanoTratamentoNovo.addActionListener((ActionEvent e) -> {

            /* limpa swap */
            Swap.setIdPlanoTratamento(null);

            /* reseta e habilita controles */
            ControllerPlanoTratamentoView.resetaControles(view);
            ControllerPlanoTratamentoView.habilitaControles(view);

            /* move o foco */
            view.jTplanoTratamentoProcedimento.requestFocus();

        });

        /* botao paginador pesquisa */
        view.jBplanoTratamentoPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerPlanoTratamentoPesquisa.pesquisar(view);

        });

        /* resultados */
        view.jTplanoTratamentoResultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* atualiza o swap */
                    Swap.setIdPlanoTratamento(view.jTplanoTratamentoResultados.getModel().getValueAt(view.jTplanoTratamentoResultados.getSelectedRow(), 0));

                    /* exibe os dados */
                    ControllerPlanoTratamentoExibe.exibir(view, Swap.getIdPlanoTratamento());

                    /* seleciona aba */
                    view.jTabaPlanoTratamento.setSelectedIndex(0);

                    /* exibe os controles */
                    ControllerPlanoTratamentoView.habilitaControles(view);

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

        /* limpa swap */
        Swap.setIdPlanoTratamento(null);

        /* reseta e habilita controles */
        ControllerPlanoTratamentoView.resetaControles(view);
        ControllerPlanoTratamentoView.habilitaControles(view);

        /* pesquisa */
        ControllerPlanoTratamentoPesquisa.pesquisar(view);

        /* seleciona a aba */
        view.jTabaPlanoTratamento.setSelectedIndex(1);

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao2(ViewSistema view) {

        /* exibe os dados */
        ControllerPlanoTratamentoExibe.exibir(view, Swap.getIdPlanoTratamento());

        /* habilita controles */
        ControllerPlanoTratamentoView.habilitaControles(view);

        /* pesquisa */
        ControllerPlanoTratamentoPesquisa.pesquisar(view);

        /* seleciona a aba */
        view.jTabaPlanoTratamento.setSelectedIndex(0);

    }

}
