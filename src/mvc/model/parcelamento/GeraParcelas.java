package mvc.model.parcelamento;

import br.com.taimber.algoritmos.Datas;
import br.com.taimber.algoritmos.FormataParaBigDecimal;
import java.math.BigDecimal;
import mvc.model.pagamento.BeanPagamento;
import mvc.model.pagamento.DaoPagamento;

public class GeraParcelas {

    private Object data;
    private Object valor;
    private Object historico;
    private int numeroParcelas;
    private Object idPaciente;
    private Object codigoTratamento;
    private Object formaPagamento;

    public void setData(Object data) {
        this.data = data;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public void setHistorico(Object historico) {
        this.historico = historico;
    }

    public void setNumeroParcelas(String numeroParcelas) {
        this.numeroParcelas = Integer.valueOf(numeroParcelas);
    }

    public void setIdPaciente(Object idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setCodigoTratamento(Object codigoTratamento) {
        this.codigoTratamento = codigoTratamento;
    }

    public void setFormaPagamento(Object formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * Gera as parcelas
     */
    public void gerar() {

        /* calculos */
        BigDecimal valorTotal = FormataParaBigDecimal.formatar(this.valor);
        BigDecimal valorParcela = valorTotal.divide(FormataParaBigDecimal.formatar(numeroParcelas), 2, BigDecimal.ROUND_HALF_UP);

        /* calcula a diferença de centavos */
        BigDecimal diferenca = valorParcela.multiply(FormataParaBigDecimal.formatar(numeroParcelas));
        diferenca = valorTotal.subtract(diferenca);

        /* itera as parcelas */
        for (int i = 1; i <= this.numeroParcelas; i++) {

            /* valida iterador com o número de parcelas */
            if (i == this.numeroParcelas) {

                /* adiciona a diferença */
                valorParcela = valorParcela.add(diferenca);

            }

            /* nova data */
            String novaData = (String) this.data;

            /* valida o contador */
            if (i > 1) {

                novaData = Datas.adicionaMesData((String) this.data, (i - 1));

            }

            /* informação da parcela */
            BeanPagamento bean = new BeanPagamento();
            bean.setIdPaciente(this.idPaciente);
            bean.setData(novaData);
            bean.setValor(valorParcela);
            bean.setHistorico(this.historico);
            bean.setIsPago(false);
            bean.setCodigoTratamento(this.codigoTratamento);
            bean.setFormaPagamento(this.formaPagamento);

            /* gera a parcela */
            DaoPagamento cadastro = new DaoPagamento();
            cadastro.gravar(bean);

        }

    }

}
