package mvc.model.upload;

import br.com.taimber.algoritmos.RemoveCaracteresEspeciaisString;
import br.com.taimber.arquivos.CopiaArquivos;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import sistema.model.Swap;
import sistema.model.Propriedades;

public class UploadNovoArquivo {

    /**
     * Faz o upload para uma pasta
     *
     * @param nomePastaUpload Nome da pasta de upload
     * @return True para conseguiu fazer o upload
     */
    public static boolean executar(String nomePastaUpload) {

        /* endereco da pasta de uploads */
        String endereco = Propriedades.ENDERECO_PASTA_UPLOADS;

        /* valida o nome da pasta de upload */
        if (nomePastaUpload.length() == 0) {

            /* aviso */
            JOptionPane.showMessageDialog(null, "Informe o nome da pasta do upload!");

            /* retorno */
            return false;

        }

        /* valida id de paciente */
        if (!isNull(Swap.getIdPaciente())) {

            /* completa com o id do paciente */
            endereco += Swap.getIdPaciente() + "/" + RemoveCaracteresEspeciaisString.executa(nomePastaUpload);

            /* copia as imagens para a pasta informada */
            CopiaArquivos copiarArquivos = new CopiaArquivos();

            /* retorno */
            return copiarArquivos.copiarComDialogoParaSelecionarOrigem(endereco);

        }

        /* retorno */
        return false;

    }

}
