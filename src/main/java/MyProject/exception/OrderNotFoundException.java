package MyProject.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Pedido nao encontrado");
    }
}
