package mvc.controller.paciente;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.persistencia.banco.BancoDados;
import static java.util.Objects.isNull;
import javax.swing.JOptionPane;
import mvc.model.paciente.BeanPaciente;
import mvc.model.paciente.DaoPaciente;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.BancoFactory;

public final class ControllerPacienteGrava {

    /**
     * Grava os dados
     *
     * @param view View
     * @param id ID do cadastro
     * @return True gravou os dados
     */
    public static boolean gravar(ViewSistema view, Object id) {

        /* cadastro */
        BeanPaciente cadastro = new BeanPaciente();
        cadastro.setId(id);
        cadastro.setNome(view.jTpessoalNomePaciente.getText());
        cadastro.setCpf(view.jTpessoalCpfPaciente.getText());
        cadastro.setRg(view.jTpessoalRgPaciente.getText());
        cadastro.setSexo(view.jCpessoalSexoPaciente.getSelectedItem());
        cadastro.setNascimento(Datas.converterDateParaString(view.jDpessoalNascimentoPaciente.getDate()));
        cadastro.setNacionalidade(view.jCpessoalNacionalidadePaciente.getSelectedItem());
        cadastro.setNomePai(view.jTpessoalNomePaiPaciente.getText());
        cadastro.setNomeMae(view.jTpessoalNomeMaePaciente.getText());
        cadastro.setCep(view.jTenderecoCepPaciente.getText());
        cadastro.setRua(view.jTenderecoRuaPaciente.getText());
        cadastro.setQuadra(view.jTenderecoQuadraPaciente.getText());
        cadastro.setLote(view.jTenderecoLotePaciente.getText());
        cadastro.setNumero(view.jTenderecoNumeroPaciente.getText());
        cadastro.setBairro(view.jTenderecoBairroPaciente.getText());
        cadastro.setCidade(view.jTenderecoCidadePaciente.getText());
        cadastro.setUf(view.jCenderecoUfPaciente.getSelectedItem());
        cadastro.setComplemento(view.jTenderecoComplementoPaciente.getText());
        cadastro.setTelefone(view.jTcontatoTelefonePaciente.getText());
        cadastro.setCelular(view.jTcontatoCelularPaciente.getText());
        cadastro.setEmail(view.jTcontatoEmailPaciente.getText());
        cadastro.setAdicionais(view.jTcontatoAdicionaisPaciente.getText());

        /* cadastro */
        DaoPaciente cadastroDao = new DaoPaciente();

        /* validad dados */
        if (isDadosValidados(view, id)) {

            /* grava */
            if (cadastroDao.gravar(cadastro)) {

                /* Informa que o cadastro foi realizado */
                JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");

                /* retorno */
                return true;

            }

        }

        /* retorno */
        return false;

    }

    /* valida os dados */
    private static boolean isDadosValidados(ViewSistema view, Object id) {

        /* valida nome do paciente */
        if (view.jTpessoalNomePaciente.getText().length() == 0) {

            /* move o foco */
            view.jTpessoalNomePaciente.requestFocus();

            /* diálogo */
            JOptionPane.showMessageDialog(null, "Informe o nome do paciente.");

            /* retorno */
            return false;

        }

        /* valida CPF */
        if (view.jTpessoalCpfPaciente.getText().length() > 0) {

            /* valida se o CPF já está cadastrado */
            if (isDocumentoValidado(id, "cpf", view.jTpessoalCpfPaciente.getText())) {

                /* move o foco */
                view.jTpessoalCpfPaciente.requestFocus();

                /* diálogo */
                JOptionPane.showMessageDialog(null, "CPF já em uso!");

                /* retorno */
                return false;

            }

        }

        /* valida RG */
        if (view.jTpessoalRgPaciente.getText().length() > 0) {

            /* valida se o rg já está cadastrado */
            if (isDocumentoValidado(id, "rg", view.jTpessoalRgPaciente.getText())) {

                /* move o foco */
                view.jTpessoalRgPaciente.requestFocus();

                /* diálogo */
                JOptionPane.showMessageDialog(null, "RG já em uso!");

                /* retorno */
                return false;

            }

        }

        /* retorno */
        return true;

    }

    /**
     * Valida um documento
     *
     * @param idPaciente ID do paciente
     * @param chave Chave RG, CPF etc
     * @param valor Valor da chave
     * @return True documento validado
     */
    private static boolean isDocumentoValidado(Object idPaciente, Object chave, Object valor) {

        /* banco */
        BancoDados banco = new BancoFactory(true).getBanco();

        /* condição anterior */
        String condicaoAnterior;

        /* valida ID do paciente */
        if (!isNull(idPaciente)) {

            /* condição anterior */
            condicaoAnterior = " and id!='" + idPaciente + "'";

        } else {

            /* condição anterior */
            condicaoAnterior = "";

        }

        /* instrução query */
        String instrucaoQuery = "select id from " + new DaoPaciente().getTabela() + " where " + chave + "='" + valor + "'" + condicaoAnterior;

        /* retorno */
        return !banco.consultarRegistro(instrucaoQuery).isEmpty();

    }

}
