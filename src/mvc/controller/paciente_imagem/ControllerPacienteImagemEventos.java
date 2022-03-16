package mvc.controller.paciente_imagem;

import java.awt.event.ActionEvent;
import mvc.controller.upload.ControllerUploadExibe;
import mvc.model.paciente_imagem.UploadPacienteImagem;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerPacienteImagemEventos {

    /**
     * Eventos
     *
     * @param view View
     */
    public static void addEventos(ViewSistema view) {

        /* botão para atualizar imagem de perfil */
        view.jBatualizaImagemPaciente.addActionListener((ActionEvent e) -> {

            /* realiza o upload */
            if (UploadPacienteImagem.executar()) {

                /* executar apos acao */
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

        /* exibe o profile */
        ControllerPacienteImagemExibe.exibir(view);

        /* exibe as pastas de uploads */
        ControllerUploadExibe.exibir(view.jTreepastasUpload);

    }

}
