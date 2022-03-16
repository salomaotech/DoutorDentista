package mvc.controller.upload;

import br.com.taimber.swings.GridPastas;
import static java.util.Objects.isNull;
import javax.swing.tree.DefaultMutableTreeNode;
import sistema.model.Swap;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerUploadView {

    /**
     * Reseta os controles
     *
     * @param view View
     */
    public static void resetaControles(ViewSistema view) {

        /* limpa campo de imagem */
        view.jPimagemUpload.removeAll();

        /* limpa campos */
        view.jTnomeUpload.setText("");

        /* reseta a gride antiga */
        GridPastas gride = new GridPastas();
        gride.exibir(null, view.jTreepastasUpload);

        /* repaint */
        view.repaint();

    }

    /**
     * Habilita controles
     *
     * @param view View
     */
    public static void habilitaControles(ViewSistema view) {

        /* habilita */
        view.jBtratamentoNovaPasta.setEnabled(!isNull(Swap.getIdPaciente()));

        /* excessão */
        try {

            /* dados da seleção atual */
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) view.jTreepastasUpload.getLastSelectedPathComponent();

            /* habilita controles */
            view.jBuploadArquivo.setEnabled(isNull(node.getParent().getParent()));
            view.jBexcluirPastaUpload.setEnabled(isNull(node.getParent().getParent()));
            view.jBexcluirArquivoUpload.setEnabled(!isNull(node.getParent().getParent()));

        } catch (java.lang.NullPointerException ex) {

            /* desabilita controles */
            view.jBuploadArquivo.setEnabled(false);
            view.jBexcluirPastaUpload.setEnabled(false);
            view.jBexcluirArquivoUpload.setEnabled(false);

        }

    }

}
