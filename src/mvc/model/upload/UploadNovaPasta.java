package mvc.model.upload;

import br.com.taimber.algoritmos.RemoveCaracteresEspeciaisString;
import br.com.taimber.arquivos.CriaPastaLocal;
import static java.util.Objects.isNull;
import sistema.model.Swap;
import sistema.model.Propriedades;

public class UploadNovaPasta {

    /**
     * Cria a pasta do paciente
     *
     * @param nomePasta Nome da pasta
     * @return True se conseguiu criar a pasta
     */
    public static boolean criar(String nomePasta) {

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
            return CriaPastaLocal.criar(endereco);

        }

        /* retorno */
        return false;

    }

}
