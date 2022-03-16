package mvc.model.plano_tratamento;

public class BeanPlanoTratamento {

    private Object id;
    private Object idPaciente;
    private Object procedimento;
    private Object valor;

    public BeanPlanoTratamento() {
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

}
