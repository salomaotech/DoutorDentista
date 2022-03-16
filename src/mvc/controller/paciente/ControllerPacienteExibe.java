package mvc.controller.paciente;

import br.com.taimber.algoritmos.Datas;
import java.util.Map;
import mvc.model.paciente.DaoPaciente;
import mvc.view.telas.sistema.ViewSistema;

public class ControllerPacienteExibe {

    /**
     * Exibe os dados
     *
     * @param view View
     * @param id Id do cadastro
     */
    public static void exibir(ViewSistema view, Object id) {

        /* excessão */
        try {

            /* cadastro */
            DaoPaciente cadastro = new DaoPaciente();

            /* dados */
            Map dados = (Map) cadastro.getDadosCadastro((String) id).get(0);

            /* popula */
            view.jTpessoalNomePaciente.setText((String) dados.get("nome"));
            view.jTpessoalCpfPaciente.setText((String) dados.get("cpf"));
            view.jTpessoalRgPaciente.setText((String) dados.get("rg"));
            view.jCpessoalSexoPaciente.setSelectedItem(dados.get("sexo"));
            view.jDpessoalNascimentoPaciente.setDate(Datas.converterStringParaDate((String) dados.get("nascimento")));
            view.jCpessoalNacionalidadePaciente.setSelectedItem(dados.get("nacionalidade"));
            view.jTpessoalNomePaiPaciente.setText((String) dados.get("nomePai"));
            view.jTpessoalNomeMaePaciente.setText((String) dados.get("nomeMae"));
            view.jTenderecoCepPaciente.setText((String) dados.get("cep"));
            view.jTenderecoRuaPaciente.setText((String) dados.get("rua"));
            view.jTenderecoQuadraPaciente.setText((String) dados.get("quadra"));
            view.jTenderecoLotePaciente.setText((String) dados.get("lote"));
            view.jTenderecoNumeroPaciente.setText((String) dados.get("numero"));
            view.jTenderecoBairroPaciente.setText((String) dados.get("bairro"));
            view.jTenderecoCidadePaciente.setText((String) dados.get("cidade"));
            view.jTenderecoComplementoPaciente.setText((String) dados.get("complemento"));
            view.jTcontatoTelefonePaciente.setText((String) dados.get("telefone"));
            view.jTcontatoCelularPaciente.setText((String) dados.get("celular"));
            view.jTcontatoEmailPaciente.setText((String) dados.get("email"));
            view.jTcontatoAdicionaisPaciente.setText((String) dados.get("adicionais"));
            view.jCenderecoUfPaciente.setSelectedItem(dados.get("uf"));

        } catch (java.lang.IndexOutOfBoundsException ex) {

        }

    }

}
