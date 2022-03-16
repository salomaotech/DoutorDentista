package mvc.controller.paciente_imagem;

import br.com.taimber.swings.Imagem;
import java.io.File;
import static java.util.Objects.isNull;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.Propriedades;
import sistema.model.Swap;

public class ControllerPacienteImagemExibe {

    /**
     * Exibe a imagem do perfil profile
     *
     * @param view
     */
    public static void exibir(ViewSistema view) {

        /* reseta a imagem antiga de profile */
        ControllerPacienteImagemView.resetaControles(view);

        /* excessão */
        try {

            /* endereco da pasta de uploads */
            String endereco = Propriedades.ENDERECO_PASTA_UPLOADS;

            /* valida id de paciente */
            if (!isNull(Swap.getIdPaciente())) {

                /* completa com o id do paciente */
                endereco += Swap.getIdPaciente() + "/Profile";

                /* arquivos */
                File arquivos = new File(endereco);

                /* carrega a imagem */
                Imagem imagem = new Imagem();
                imagem.exibir(arquivos.listFiles()[0].getAbsolutePath(), view.jPimagemProfile, 170, 170);

                /* repinta a view */
                view.repaint();

            }

        } catch (java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException ex) {

        }

    }

}
