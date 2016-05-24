package co.herdy.manager.domain.exception;

/**
 * Interface to represent a wrapper around an {@link Exception} to manage errors.
 */
public interface IErrorBundle {
    Exception getException();

    String getErrorMessage();
}
