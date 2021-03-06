package de.bripkens.i18n;

/**
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class I18nException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public I18nException(String message) {
    super(message);
  }

  public I18nException(String message, Throwable cause) {
    super(message, cause);
  }

  public I18nException(Throwable cause) {
    super(cause);
  }

}
