package de.bripkens.i18n.builder;

/**
 * @author Ben Ripkens <ben.ripkens@codecentric.de>
 */
public abstract class MessageInterface {

  private MessageInterface() {
    // only the builder() method is necessary to get a nice API
  }

  public static Builder builder() {
    return new Builder();
  }

}
