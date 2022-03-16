package mvc.model.paciente;

import static java.util.Objects.isNull;

public class BeanPaciente {

    private Object id;
    private Object nome;
    private Object cpf;
    private Object rg;
    private Object sexo;
    private Object nascimento;
    private Object nacionalidade;
    private Object nomePai;
    private Object nomeMae;
    private Object cep;
    private Object rua;
    private Object quadra;
    private Object lote;
    private Object numero;
    private Object bairro;
    private Object cidade;
    private Object uf;
    private Object complemento;
    private Object telefone;
    private Object celular;
    private Object email;
    private Object adicionais;

    public BeanPaciente() {

    }

    public void setId(Object id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setSexo(Object sexo) {

        /* valida parametro */
        if (isNull(sexo)) {

            this.sexo = "";

        } else {

            this.sexo = (String) sexo;

        }

    }

    public void setNascimento(String nascimento) {

        this.nascimento = nascimento;

    }

    public void setNacionalidade(Object nacionalidade) {

        /* valida parametro */
        if (isNull(nacionalidade)) {

            this.nacionalidade = "";

        } else {

            this.nacionalidade = (String) nacionalidade;

        }

    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(Object uf) {
        this.uf = (String) uf;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdicionais(String adicionais) {
        this.adicionais = adicionais;
    }

    public Object getId() {
        return id;
    }

    public Object getNome() {
        return nome;
    }

    public Object getCpf() {
        return cpf;
    }

    public Object getRg() {
        return rg;
    }

    public Object getSexo() {
        return sexo;
    }

    public Object getNascimento() {
        return nascimento;
    }

    public Object getNacionalidade() {
        return nacionalidade;
    }

    public Object getNomePai() {
        return nomePai;
    }

    public Object getNomeMae() {
        return nomeMae;
    }

    public Object getCep() {
        return cep;
    }

    public Object getRua() {
        return rua;
    }

    public Object getQuadra() {
        return quadra;
    }

    public Object getLote() {
        return lote;
    }

    public Object getNumero() {
        return numero;
    }

    public Object getBairro() {
        return bairro;
    }

    public Object getCidade() {
        return cidade;
    }

    public Object getUf() {
        return uf;
    }

    public Object getComplemento() {
        return complemento;
    }

    public Object getTelefone() {
        return telefone;
    }

    public Object getCelular() {
        return celular;
    }

    public Object getEmail() {
        return email;
    }

    public Object getAdicionais() {
        return adicionais;
    }

}
