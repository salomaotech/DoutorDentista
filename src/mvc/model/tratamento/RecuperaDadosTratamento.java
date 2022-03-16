package mvc.model.tratamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sistema.model.BancoFactory;

public class RecuperaDadosTratamento {

    /**
     * Retorna o ID do último cadastrado
     *
     * @return String contendo último ID do paciente cadastrado
     */
    public static String getUltimoIdCadastrado() {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("id");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa = "order by id desc limit 1";

        /* dados */
        Map dados = (Map) banco.getBanco().consultarRegistro(new DaoTratamento().getTabela(), entidades, condicaoPesquisa).get(0);

        /* retorno */
        return (String) dados.get("id");

    }

    /**
     * Retorna ID do paciente associado ao tratamento
     *
     * @param id ID de tratamento
     * @return ID do paciente associado ao tratamento
     */
    public static String getIdPacienteAssociadoTratamento(String id) {

        /* entidades */
        List entidades = new ArrayList();
        entidades.add("idPaciente");

        /* banco */
        BancoFactory banco = new BancoFactory(true);

        /* condição de pesquisa */
        String condicaoPesquisa = "where id='" + id + "'";

        /* dados */
        Map dados = (Map) banco.getBanco().consultarRegistro(new DaoTratamento().getTabela(), entidades, condicaoPesquisa).get(0);

        /* retorno */
        return (String) dados.get("idPaciente");

    }

}
