package hackaton.com.br.hackatonapp.model;

/**
 * Created by gusta on 22/12/2016.
 */

public class ChatMessage {
    private String message;
    private Type type;

    public ChatMessage(Type type, String message){
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        send(1), received(2);
        private int valor;

        Type(int valor){
            this.valor = valor;
        }

        public double getValor(){
            return this.valor;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
