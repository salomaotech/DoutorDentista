package mvc.controller.upload;

import br.com.taimber.arquivos.ExecutaProgramaExterno;
import br.com.taimber.swings.Imagem;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import mvc.controller.paciente_imagem.ControllerPacienteImagemExibe;
import mvc.model.upload.UploadExcluiArquivo;
import sistema.model.Swap;
import mvc.model.upload.UploadExcluiPasta;
import mvc.model.upload.UploadNovaPasta;
import mvc.model.upload.UploadNovoArquivo;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Propriedades;

public class ControllerUploadEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão para criar pasta */
        view.jBtratamentoNovaPasta.addActionListener((ActionEvent e) -> {

            /* valida se conseguiu criar a pasta */
            if (UploadNovaPasta.criar(view.jTnomeUpload.getText())) {

                /* executar após ação */
                executarPosAcao(view);

            } else {

                /* mensagem */
                JOptionPane.showMessageDialog(null, "A pasta '" + view.jTnomeUpload.getText() + "' não foi criada!");

            }

        });

        /* jtree de pastas de upload evento */
        view.jTreepastasUpload.addPropertyChangeListener((PropertyChangeEvent evt) -> {

            /* excessão */
            try {

                /* dados da seleção atual */
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) view.jTreepastasUpload.getLastSelectedPathComponent();
                Object nodeInfo = node.getUserObject();

                /* habilita controles */
                ControllerUploadView.habilitaControles(view);

                /* valida se é uma folha */
                if (!isNull(node.getParent().getParent())) {

                    /* endereco da pasta de uploads */
                    String endereco = Propriedades.ENDERECO_PASTA_UPLOADS + Swap.getIdPaciente() + "/" + node.getParent().toString() + "/" + nodeInfo;

                    /* exibe a gride de pastas */
                    Imagem gride = new Imagem();
                    gride.exibir(endereco, view.jPimagemUpload, 350, 350);

                    /* recarrega a view */
                    view.repaint();

                }

            } catch (java.lang.NullPointerException ex) {

            }

        });

        /* jtree de pastas de upload evento */
        view.jTreepastasUpload.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                /* excessão */
                try {

                    /* dados da seleção atual */
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) view.jTreepastasUpload.getLastSelectedPathComponent();
                    Object nodeInfo = node.getUserObject();

                    /* valida se é uma folha */
                    if (!isNull(node.getParent().getParent())) {

                        /* endereco da pasta de uploads */
                        String endereco = Propriedades.ENDERECO_PASTA_UPLOADS + Swap.getIdPaciente() + "/" + node.getParent().toString() + "/" + nodeInfo;

                        /* dois cliques */
                        if (e.getClickCount() == 2 && !e.isConsumed()) {

                            /* executa o programa */
                            ExecutaProgramaExterno.executarModoDesktop(endereco);

                        }

                    }

                } catch (java.lang.NullPointerException ex) {

                }

            }

        });

        /* botão para criar pasta */
        view.jBuploadArquivo.addActionListener((ActionEvent e) -> {

            /* excessão */
            try {

                /* dados da seleção atual */
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) view.jTreepastasUpload.getLastSelectedPathComponent();
                Object nodeInfo = node.getUserObject();

                /* faz o upload */
                UploadNovoArquivo.executar((String) nodeInfo);

                /* executar após uma ação */
                executarPosAcao(view);

            } catch (java.lang.NullPointerException ex) {

            }

        });

        /* botão para excluir pasta */
        view.jBexcluirPastaUpload.addActionListener((ActionEvent e) -> {

            /* excessão */
            try {

                /* dados da seleção atual */
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) view.jTreepastasUpload.getLastSelectedPathComponent();
                Object nodeInfo = node.getUserObject();

                /* valida se removeu */
                if (UploadExcluiPasta.excluir((String) nodeInfo)) {

                    /* executar após uma ação */
                    executarPosAcao(view);

                }

            } catch (java.lang.NullPointerException ex) {

            }

        });

        /* botão para excluir arquivo de upload */
        view.jBexcluirArquivoUpload.addActionListener((ActionEvent e) -> {

            /* dados da seleção atual */
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) view.jTreepastasUpload.getLastSelectedPathComponent();
            Object nodeInfo = node.getUserObject();

            /* path do arquivo */
            String pathArquivo = node.getParent() + "/" + nodeInfo;

            /* valida se removeu */
            if (UploadExcluiArquivo.excluir(pathArquivo)) {

                /* executar após uma ação */
                executarPosAcao(view);

            }

        });

    }

    /**
     * Executar após uma ação
     *
     * @param view View
     */
    private static void executarPosAcao(ViewSistema view) {

        /* reseta controles da view do upload */
        ControllerUploadView.resetaControles(view);

        /* exibe as pastas de uploads */
        ControllerUploadExibe.exibir(view.jTreepastasUpload);

        /* habilita controles */
        ControllerUploadView.habilitaControles(view);

        /* exibe a imagem do perfil profile */
        ControllerPacienteImagemExibe.exibir(view);

        /* limpa o texto */
        view.jTnomeUpload.setText("");

        /* foco */
        view.jTnomeUpload.requestFocus();

    }

}
