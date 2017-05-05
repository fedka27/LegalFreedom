package legalFreedom.java.model.data.exception;

public class UnauthorizedException extends RuntimeException {
    private static final String ERROR_UNAUTHORIZED = "Unauthorized session was used.";

    public UnauthorizedException() {
        super(ERROR_UNAUTHORIZED);
    }
}
