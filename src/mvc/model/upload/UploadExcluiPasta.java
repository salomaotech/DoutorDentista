package mvc.model.upload;

import br.com.taimber.algoritmos.RemoveCaracteresEspeciaisString;
import br.com.taimber.arquivos.RemovePastaNaoRecursivo;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import sistema.model.Swap;
import sistema.model.Propriedades;

public class UploadExcluiPasta {

    /**
     * Exclui a pasta do paciente
     *
     * @param nomePasta Nome da pasta
     * @return True se conseguiu excluir a pasta
     */
    public static boolean excluir(String nomePasta) {

        /* confirma se deseja remover a pasta */
        if (JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir esta pasta?") == 0) {

            /* endereco da pasta de uploads */
            String endereco = Propriedades.ENDERECO_PASTA_UPLOADS;

            /* valida nome de pasta */
            if (nomePasta.length() == 0) {

                /* retorno */
                return false;

            }

            /* valida id de paciente */
            if (!isNull(Swap.getIdPaciente())) {

                /* completa com o id do paciente */
                endereco += Swap.getIdPaciente() + "/" + RemoveCaracteresEspeciaisString.executa(nomePasta);

                /* retorno */
                return RemovePastaNaoRecursivo.remover(endereco);

            }

        }

        /* retorno */
        return false;

    }

}
