package mvc.model.paciente_imagem;

import br.com.taimber.arquivos.CopiaArquivos;
import br.com.taimber.arquivos.RemovePastaNaoRecursivo;
import static java.util.Objects.isNull;
import sistema.model.Swap;
import sistema.model.Propriedades;

public class UploadPacienteImagem {

    /**
     * Faz o upload para a pasta da imagem de profile
     *
     * @return True para conseguiu fazer o upload
     */
    public static boolean executar() {

        /* endereco da pasta de uploads */
        String endereco = Propriedades.ENDERECO_PASTA_UPLOADS;

        /* valida id de paciente */
        if (!isNull(Swap.getIdPaciente())) {

            /* completa com o id do paciente */
            endereco += Swap.getIdPaciente() + "/Profile";

            /* exclui pasta */
            RemovePastaNaoRecursivo.remover(endereco);

            /* copia as imagens para a pasta informada */
            CopiaArquivos copiarArquivos = new CopiaArquivos();

            /* retorno */
            return copiarArquivos.copiarComDialogoParaSelecionarOrigem(endereco);

        }

        /* retorno */
        return false;

    }

}
