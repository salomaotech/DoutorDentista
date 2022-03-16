package sistema.app;

import javax.swing.JOptionPane;
import mvc.controller.agenda.ControllerAgendaEventos;
import mvc.controller.agenda.ControllerAgendaPesquisa;
import mvc.controller.anamnese.ControllerAnamneseEventos;
import mvc.controller.configuracoes.ControllerConfiguracoesEventos;
import mvc.controller.configuracoes.ControllerConfiguracoesExibe;
import mvc.controller.pagamento.ControllerPagamentoEventos;
import mvc.controller.paciente.ControllerPacienteEventos;
import mvc.controller.paciente.ControllerPacientePesquisa;
import mvc.controller.tratamento.ControllerTratamentoEventos;
import mvc.controller.upload.ControllerUploadEventos;
import mvc.controller.paciente_imagem.ControllerPacienteImagemEventos;
import mvc.controller.pagamento.ControllerPagamentoExibe;
import mvc.controller.pagamento.ControllerPagamentoPesquisa;
import mvc.controller.parcelamento.ControllerParcelamentoEventos;
import mvc.controller.plano_tratamento.ControllerPlanoTratamentoEventos;
import mvc.controller.tratamento.ControllerTratamentoPesquisa;
import mvc.model.agenda.DaoAgenda;
import mvc.model.anamnese.DaoAnamnese;
import mvc.model.configuracoes.CarregaConfiguracoes;
import mvc.model.pagamento.DaoPagamento;
import mvc.model.paciente.DaoPaciente;
import mvc.model.plano_tratamento.DaoPlanoTratamento;
import mvc.model.tratamento.DaoTratamento;
import mvc.view.telas.sistema.ViewSistema;
import sistema.model.CriaBancoDados;

public class App {

    /**
     * Main
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {

        /* configurações */
        CarregaConfiguracoes configuracoes = new CarregaConfiguracoes();

        /* view */
        ViewSistema view = new ViewSistema();

        /* valida se as configurações funcionaram */
        if (configuracoes.isConfiguracoesFuncionaram()) {

            /* 1 cria o banco de dados */
            new CriaBancoDados().criarBanco();

            /* 2 cria as tabelas */
            new DaoPaciente().criaEntidade();
            new DaoTratamento().criaEntidade();
            new DaoAnamnese().criaEntidade();
            new DaoPagamento().criaEntidade();
            new DaoAgenda().criaEntidade();
            new DaoPlanoTratamento().criaEntidade();

            /* 3 pesquisa os dados */
            ControllerAgendaPesquisa.pesquisar(view);
            ControllerPacientePesquisa.pesquisar(view);
            ControllerPagamentoPesquisa.pesquisar(view, view.jCpagamentoDevedoresPaginador, view.jTpagamentoDevedoresResultados, true, null, false);
            ControllerTratamentoPesquisa.pesquisar(view, view.jCpagamentoDevedoresTratamentoPaginador, view.jTresultadosDevedoresTratamentos, true, null, false);

            /* 4 adiciona eventos */
            ControllerPacienteEventos.addEventos(view);
            ControllerTratamentoEventos.addEventos(view);
            ControllerAnamneseEventos.addEventos(view);
            ControllerUploadEventos.addEventos(view);
            ControllerPacienteImagemEventos.addEventos(view);
            ControllerPagamentoEventos.addEventos(view);
            ControllerParcelamentoEventos.addEventos(view);
            ControllerAgendaEventos.addEventos(view);
            ControllerPlanoTratamentoEventos.addEventos(view);

            /* 5 carrega view necessaria */
            ControllerPagamentoExibe.exibir(view);

        } else {

            /* mensagem */
            JOptionPane.showMessageDialog(null, "O sistema não conseguiu carregar as configurações.");

        }

        /* adiciona eventos */
        ControllerConfiguracoesEventos.addEventos(view);

        /* carrega a view das configurações */
        ControllerConfiguracoesExibe.exibir(view);

        /* seleciona a aba */
        view.jTabaPrincipal.setSelectedIndex(0);

        /* exibe a view */
        view.setVisible(true);

    }

}
