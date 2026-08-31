package pl.cisowski.domain.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String clazz, String params) {
        super(String.format("'%s' was not found with given params: %s", clazz, params));
    }
}
