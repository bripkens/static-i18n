package de.bripkens.i18n.builder;

import de.bripkens.i18n.MessagesSource;

/**
 * This class uses a very simple source code generation mechanism instead of
 * CodeModel or something similar as the generated Java code is simple
 * and repetitive.
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class SourceGenerator {

  private final Config config;
  private StringBuilder builder;

  public SourceGenerator(Config config) {
    config.validate();
    this.config = config;
  }

  public String run() {
    builder = new StringBuilder();

    packageStatement(config.pckg);
    importStatements(MessagesSource.class);
    annotation(MessagesSource.class, config.bundle);

    return builder.toString();
  }

  private void nl() { builder.append(config.lineBreak); }

  private void nl(int amount) {

  }

  private void indent(int level) {
    int totalAmount = level * config.indention;
    for (int i = 0; i < totalAmount; i++) {
      builder.append(config.indentionType.getCharacter());
    }
  }

  private void packageStatement(String pckg) {
    builder.append("package ")
        .append(pckg)
        .append(";");
    nl();
    nl();
  }

  private void importStatements(Class<?>... types) {
    for (Class<?> type : types) {
      builder.append("import ")
          .append(type.getName())
          .append(";");
      nl();
    }
    nl();
  }

  private void annotation(Class<?> type, String value) {
    builder.append("@")
        .append(type.getSimpleName())
        .append("(\"")
        .append(value)
        .append("\")");
    nl();
  }

  private void classDefinition(String name, IndentAwareBlock body) {
    builder.append("public class ")
        .append(name)
        .append(" {");
    nl();
    body.call(1);
    nl();
    builder.append("}");
    nl();
  }

  private static interface IndentAwareBlock {
    void call(int level);
  }
}
