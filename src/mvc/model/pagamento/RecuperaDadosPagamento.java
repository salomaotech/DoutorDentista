package mvc.model.pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sistema.model.BancoFactory;

public class RecuperaDadosPagamento {

    /**
     * Retorna o ID do paciente associado ao pagamento
     *
     * @param id ID do registro
     * @return ID de paciente associado ao pagamento informado na ID
     */
    public static String getIdPacienteAssociadoPagamento(String id) {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("idPaciente");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa = "where id='" + id + "'";

        /* dados */
        Map dados = (Map) banco.getBanco().consultarRegistro(new DaoPagamento().getTabela(), entidades, condicaoPesquisa).get(0);

        /* retorno */
        return (String) dados.get("idPaciente");

    }

}
