package de.bripkens.i18n.builder;

/**
 * @author Ben Ripkens <ben.ripkens@codecentric.de>
 */
public class Builder {
  private Config config = new Config();

  public Builder name(String name) {
    config.name = name;
    return this;
  }

  public Builder pckg(String pckg) {
    config.pckg = pckg;
    return this;
  }

  public Builder bundle(String bundle) {
    config.bundle = bundle;
    return this;
  }

  public Builder indention(int indention) {
    config.indention = indention;
    return this;
  }

  public Builder indentionType(IndentionType indentionType) {
    config.indentionType = indentionType;
    return this;
  }

  public Builder lineBreak(String lineBreak) {
    config.lineBreak = lineBreak;
    return this;
  }

  public String build() {
    return new SourceGenerator(config).run();
  }
}
