package hackaton.com.br.hackatonapp.ui.core.alerta;

/**
 * Created by hipolito on 21/12/2016.
 */
public class Alerta {
    private String mensagem;
    private String tipo;
    private Integer Prioridade;
    private String direcionamento;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPrioridade() {
        return Prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        Prioridade = prioridade;
    }

    public String getDirecionamento() {
        return direcionamento;
    }

    public void setDirecionamento(String direcionamento) {
        this.direcionamento = direcionamento;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "mensagem='" + mensagem + '\'' +
                ", tipo='" + tipo + '\'' +
                ", Prioridade=" + Prioridade +
                ", direcionamento='" + direcionamento + '\'' +
                '}';
    }
}
