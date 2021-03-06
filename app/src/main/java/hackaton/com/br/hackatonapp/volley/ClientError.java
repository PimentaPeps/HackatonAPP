package hackaton.com.br.hackatonapp.volley;

/**
 * Indicates that the server responded with an error response indicating that the client has erred.
 *
 * For backwards compatibility, extends ServerError which used to be thrown for all server errors,
 * including 4xx error codes indicating a client error.
 */
@SuppressWarnings("serial")
public class ClientError extends ServerError {
    public ClientError(NetworkResponse networkResponse) {
        super(networkResponse);
    }
    public ClientError() {
        super();
    }
}
