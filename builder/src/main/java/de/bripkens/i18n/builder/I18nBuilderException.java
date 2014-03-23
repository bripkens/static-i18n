package de.bripkens.i18n.builder;

import de.bripkens.i18n.I18nException;

/**
 * @author Ben Ripkens <ben.ripkens@codecentric.de>
 */
public class I18nBuilderException extends I18nException {

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
