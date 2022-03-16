package mvc.model.pagamento;

public class BeanPagamento {

    private Object id;
    private Object idPaciente;
    private Object data;
    private Object valor;
    private Object historico;
    private boolean isPago;
    private Object codigoTratamento;
    private Object formaPagamento;

    public BeanPagamento() {

    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Object idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Object getHistorico() {
        return historico;
    }

    public void setHistorico(Object historico) {
        this.historico = historico;
    }

    public boolean isIsPago() {
        return isPago;
    }

    public void setIsPago(boolean isPago) {
        this.isPago = isPago;
    }

    public Object getCodigoTratamento() {
        return codigoTratamento;
    }

    public void setCodigoTratamento(Object codigoTratamento) {
        this.codigoTratamento = codigoTratamento;
    }

    public Object getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Object formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
