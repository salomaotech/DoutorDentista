package mvc.controller.agenda;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mvc.model.agenda.DaoAgenda;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.RemoveRegistro;
import sistema.model.Swap;

public class ControllerAgendaEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar */
        view.jBagendaSalvar.addActionListener((ActionEvent e) -> {

            /* grava */
            if (ControllerAgendaGrava.gravar(view, Swap.getIdAgenda())) {

                /* executar após ação */
                executarPosAcao(view);

            }

        });

        /* botão excluir */
        view.jBagendaExcluir.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu remover o registro */
            if (RemoveRegistro.executar(new DaoAgenda().getTabela(), Swap.getIdAgenda())) {

                /* executar após ação */
                executarPosAcao(view);

            }

        });

        /* botão novo */
        view.jBagendaNovo.addActionListener((ActionEvent e) -> {

            /* limpa swap */
            Swap.setIdAgenda(null);

            /* reseta e habilita controles */
            ControllerAgendaView.resetaControles(view);
            ControllerAgendaView.habilitaControles(view);

            /* move o foco */
            view.jTagendaNome.requestFocus();

        });

        /* botão pesquisa */
        view.jBagendaPesquisa.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerAgendaPesquisa.pesquisar(view);

        });

        /* botao paginador pesquisa */
        view.jBagendaPaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa */
            ControllerAgendaPesquisa.pesquisar(view);

        });

        /* resultados */
        view.jTagendaLista.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* atualiza o swap */
                    Swap.setIdAgenda(view.jTagendaLista.getModel().getValueAt(view.jTagendaLista.getSelectedRow(), 0));

                    /* exibe os dados */
                    ControllerAgendaExibe.exibir(view, Swap.getIdAgenda());

                    /* seleciona aba */
                    view.jTabaPrincipal.setSelectedIndex(0);
                    view.jTabaCadastroAgenda.setSelectedIndex(1);

                    /* exibe os controles */
                    ControllerAgendaView.habilitaControles(view);

                }
            }

        });

        /* pesquisa */
        view.jTagendaPesquisaNome.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                /* enter */
                if (e.getKeyCode() == 10) {

                    /* pesquisa */
                    ControllerAgendaPesquisa.pesquisar(view);

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao(ViewSistema view) {

        /* limpa swap */
        Swap.setIdAgenda(null);

        /* reseta e habilita controles */
        ControllerAgendaView.resetaControles(view);
        ControllerAgendaView.habilitaControles(view);

        /* pesquisa */
        ControllerAgendaPesquisa.pesquisar(view);

        /* seleciona a aba */
        view.jTabaPrincipal.setSelectedIndex(0);
        view.jTabaCadastroAgenda.setSelectedIndex(1);

    }

}
