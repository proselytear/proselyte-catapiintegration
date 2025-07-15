package net.proselyte.catapiintegration.exceptionhandler;

public class CatApiUnauthorizedException extends RuntimeException {
    public CatApiUnauthorizedException(String message) {
        super(message);
    }
}
