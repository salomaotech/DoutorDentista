package mvc.model.upload;

import br.com.taimber.arquivos.RemoveArquivo;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import sistema.model.Swap;
import sistema.model.Propriedades;

public class UploadExcluiArquivo {

    /**
     * Exclui arquivo de upload
     *
     * @param pathArquivo Path de arquivo
     * @return True se conseguiu excluir a pasta
     */
    public static boolean excluir(String pathArquivo) {

        /* confirma se deseja remover a pasta */
        if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir este arquivo?") == 0) {

            /* endereco da pasta de uploads */
            String endereco = Propriedades.ENDERECO_PASTA_UPLOADS;

            /* valida id de paciente */
            if (!isNull(Swap.getIdPaciente())) {

                /* completa com o id do paciente */
                endereco += Swap.getIdPaciente() + "/" + pathArquivo;

                /* retorno */
                return RemoveArquivo.remover(endereco);

            }

        }

        /* retorno */
        return false;

    }

}
