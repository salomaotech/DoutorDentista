package mvc.model.agenda;

public class BeanAgenda {

    private Object id;
    private Object nome;
    private Object data;
    private Object hora;
    private Object minuto;
    private Object historico;

    public BeanAgenda() {

    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getNome() {
        return nome;
    }

    public void setNome(Object nome) {
        this.nome = nome;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getHora() {
        return hora;
    }

    public void setHora(Object hora) {
        this.hora = hora;
    }

    public Object getMinuto() {
        return minuto;
    }

    public void setMinuto(Object minuto) {
        this.minuto = minuto;
    }

    public Object getHistorico() {
        return historico;
    }

    public void setHistorico(Object historico) {
        this.historico = historico;
    }

}
