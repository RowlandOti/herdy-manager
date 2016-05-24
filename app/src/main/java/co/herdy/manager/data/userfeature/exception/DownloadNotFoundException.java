package co.herdy.manager.data.userfeature.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */

public class DownloadNotFoundException extends Exception {

  public DownloadNotFoundException() {
    super();
  }

  public DownloadNotFoundException(final String message) {
    super(message);
  }

  public DownloadNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DownloadNotFoundException(final Throwable cause) {
    super(cause);
  }
}
