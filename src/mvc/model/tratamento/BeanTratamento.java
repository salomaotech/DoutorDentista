package mvc.model.tratamento;

public class BeanTratamento {

    private Object id;
    private Object idPaciente;
    private Object dataInicio;
    private Object dataConclusao;
    private Object situacao;
    private Object dentes;
    private Object procedimento;
    private Object valor;
    private Object valorEntrada;
    private boolean isPago;

    public BeanTratamento() {

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

    public Object getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Object dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Object getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Object dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Object getSituacao() {
        return situacao;
    }

    public void setSituacao(Object situacao) {
        this.situacao = situacao;
    }

    public Object getDentes() {
        return dentes;
    }

    public void setDentes(Object dentes) {
        this.dentes = dentes;
    }

    public Object getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Object procedimento) {
        this.procedimento = procedimento;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Object getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(Object valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public boolean isIsPago() {
        return isPago;
    }

    public void setIsPago(boolean isPago) {
        this.isPago = isPago;
    }

}
