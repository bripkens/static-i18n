package de.bripkens.i18n.builder;

/**
 * @author Ben Ripkens <ben.ripkens@codecentric.de>
 */
public class SourceGenerator {

  private final Config config;

  public SourceGenerator(Config config) {
    config.validate();
    this.config = config;
  }

  public String run() {
    return null;
  }
}
