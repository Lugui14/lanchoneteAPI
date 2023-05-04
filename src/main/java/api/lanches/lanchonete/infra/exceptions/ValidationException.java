package api.lanches.lanchonete.infra.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }

}
