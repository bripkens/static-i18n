package de.bripkens.i18n.builder;

/**
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public abstract class MessageInterface {

  private MessageInterface() {
    // only the builder() method is necessary to get a nice API
  }

  public static Builder builder() {
    return new Builder();
  }

}
