package hackaton.com.br.hackatonapp.ui.core.alerta;

/**
 * Created by hipolito on 21/12/2016.
 */
public class Alerta {
    private String descricao;
    private String prioridade;
    private String tipo;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
