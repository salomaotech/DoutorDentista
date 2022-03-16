package mvc.controller.paciente;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.util.Objects.isNull;
import mvc.controller.anamnese.ControllerAnamneseExibe;
import mvc.controller.anamnese.ControllerAnamneseView;
import mvc.controller.pagamento.ControllerPagamentoPesquisa;
import mvc.controller.pagamento.ControllerPagamentoView;
import mvc.controller.tratamento.ControllerTratamentoPesquisa;
import mvc.controller.tratamento.ControllerTratamentoView;
import mvc.controller.upload.ControllerUploadExibe;
import mvc.controller.paciente_imagem.ControllerPacienteImagemExibe;
import mvc.controller.parcelamento.ControllerParcelamentoView;
import mvc.controller.plano_tratamento.ControllerPlanoTratamentoPesquisa;
import mvc.controller.plano_tratamento.ControllerPlanoTratamentoView;
import mvc.controller.upload.ControllerUploadView;
import mvc.model.anamnese.RecuperaDadosAnamnesePaciente;
import mvc.model.paciente.RecuperaDadosPaciente;
import sistema.model.Swap;
import mvc.view.telas.sistema.ViewSistema;
import sistema.controller.ControllerViewSistema;

public class ControllerPacienteEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão salvar cadastro do usuário */
        view.jBsalvarCadastroPaciente.addActionListener((ActionEvent e) -> {

            /* grava os dados do paciente */
            if (ControllerPacienteGrava.gravar(view, Swap.getIdPaciente())) {

                /* valida swap */
                if (isNull(Swap.getIdPaciente())) {

                    /* reseta os controles */
                    ControllerViewSistema.resete(view);

                    /* atualiza a swap */
                    Swap.setIdPaciente(RecuperaDadosPaciente.getUltimoIdCadastrado());

                }

                /* executar após uma ação */
                executarPosAcao(view);

            }

        });

        /* add evento mouse sobre jtable de resultados de pesquisa de paciente */
        view.jTresultadosPesquisaPaciente.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* valida duplo clique */
                if (e.getClickCount() == 2) {

                    /* reseta os controles */
                    ControllerViewSistema.resete(view);

                    /* seta o id do paciente na swap */
                    Swap.setIdPaciente(view.jTresultadosPesquisaPaciente.getModel().getValueAt(view.jTresultadosPesquisaPaciente.getSelectedRow(), 0));

                    /* executar após uma ação */
                    executarPosAcao(view);

                }

            }

        });

        /* botão adicionar novo cadastro de paciente */
        view.jBnovoPaciente.addActionListener((ActionEvent e) -> {

            /* limpa o swap */
            Swap.setIdPaciente(null);

            /* reseta os controles */
            ControllerViewSistema.resete(view);

            /* seleciona opcao */
            view.jTabaPrincipal.setSelectedIndex(2);
            view.jTabaDadosCadastroPaciente.setSelectedIndex(0);
            view.jTabaCadastroPaciente.setSelectedIndex(0);
            view.jTpessoalNomePaciente.requestFocus();

            /* habilita os controles */
            habilitarControles(view);

        });

        /* botão de pesquisa */
        view.jBpesquisarPaciente.addActionListener((ActionEvent e) -> {

            /* pesquisa por registro */
            ControllerPacientePesquisa.pesquisar(view);

        });

        /* botao paginador pesquisa */
        view.jBpacientePaginador.addActionListener((ActionEvent e) -> {

            /* pesquisa por registro */
            ControllerPacientePesquisa.pesquisar(view);

        });

        /* pesquisa */
        view.jTpesquisaNomePaciente.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                /* enter */
                if (e.getKeyCode() == 10) {

                    /* pesquisa por registro */
                    ControllerPacientePesquisa.pesquisar(view);

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* pesquisa */
        view.jTpesquisaCpfPaciente.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                /* enter */
                if (e.getKeyCode() == 10) {

                    /* pesquisa por registro */
                    ControllerPacientePesquisa.pesquisar(view);

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }

    /**
     * Habilita os controles
     *
     * @param view View
     */
    private static void habilitarControles(ViewSistema view) {

        /* habilita controles */
        ControllerPacienteView.habilitaControles(view);
        ControllerTratamentoView.habilitaControles(view);
        ControllerAnamneseView.habilitaControles(view);
        ControllerPagamentoView.habilitaControles(view);
        ControllerParcelamentoView.habilitaControles(view);
        ControllerUploadView.habilitaControles(view);
        ControllerPlanoTratamentoView.habilitaControles(view);

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    public static void executarPosAcao(ViewSistema view) {

        /* habilita os controles */
        habilitarControles(view);

        /* exibe os dados do cadastro */
        ControllerPacienteExibe.exibir(view, Swap.getIdPaciente());

        /* exibe a imagem do perfil profile */
        ControllerPacienteImagemExibe.exibir(view);

        /* exibe os dados da anamnese */
        ControllerAnamneseExibe.exibir(view, RecuperaDadosAnamnesePaciente.getDados(Swap.getIdPaciente()).get("id"));

        /* exibe os dados do tratamento */
        ControllerTratamentoPesquisa.pesquisar(view, view.jCtratamentoPaginador, view.jTresultadosTratamento, false, Swap.getIdPaciente(), false);

        /* pesquisa o pagamento do paciente */
        ControllerPagamentoPesquisa.pesquisar(view, view.jCpagamentoPaginador, view.jTpagamentoResultados, false, Swap.getIdPaciente(), false);

        /* pesquisa por pacientes */
        ControllerPacientePesquisa.pesquisar(view);

        /* exibe as pastas de uploads */
        ControllerUploadExibe.exibir(view.jTreepastasUpload);

        /* pesquisa os controles de tratamentos */
        ControllerPlanoTratamentoPesquisa.pesquisar(view);

        /* abre as abas */
        view.jTabaPrincipal.setSelectedIndex(2);
        view.jTabaCadastroPaciente.setSelectedIndex(0);
        view.jTabaDadosCadastroPaciente.setSelectedIndex(0);

        /* move o foco */
        view.jTpessoalNomePaciente.requestFocus();

    }

}
