package de.bripkens.i18n.builder;

import de.bripkens.i18n.I18nException;

/**
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class I18nBuilderException extends I18nException {
  private static final long serialVersionUID = 1L;

  public I18nBuilderException(String message) {
    super(message);
  }

  public I18nBuilderException(String message, Throwable cause) {
    super(message, cause);
  }

  public I18nBuilderException(Throwable cause) {
    super(cause);
  }
}
