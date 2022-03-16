package mvc.controller.upload;

import br.com.taimber.swings.GridPastas;
import javax.swing.JTree;
import sistema.model.Swap;
import sistema.model.Propriedades;

public class ControllerUploadExibe {

    /**
     * Exibe as pastas de upload
     *
     * @param jPrincipal JPanel onde serão visiveis as pastas
     */
    public static void exibir(JTree jPrincipal) {

        /* endereco da pasta de uploads */
        String endereco = Propriedades.ENDERECO_PASTA_UPLOADS + Swap.getIdPaciente();

        /* exibe grid de pastas */
        GridPastas grid = new GridPastas();
        grid.exibir(endereco, jPrincipal);

    }

}
