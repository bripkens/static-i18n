package de.bripkens.i18n.builder;

import java.util.List;

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

  SourceGenerator(Config config) {
    config.validate();
    this.config = config;
  }

  public String run() {
    MethodExtractor extractor = new MethodExtractor();
    final List<MethodDefinition> methods = extractor.extract(config.bundle);
    // TODO imports for MethodDefinitions
    builder = new StringBuilder();

    packageStatement(config.pckg);
    importStatements(MessagesSource.class);
    annotation(MessagesSource.class, config.bundle);
    classDefinition(config.name, new IndentAwareBlock() {
      @Override
      public void call(int level) {
        methodDefinitions(methods, level);
      }
    });

    return builder.toString();
  }

  private void w(int level, String s) {
    indent(level);
    builder.append(s);
    nl();
  }

  private void nl() { builder.append(config.lineBreak); }

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

  private void methodDefinitions(List<MethodDefinition> methods,
      int level) {
    if (methods.isEmpty()) {
      indent(level);
      builder.append("// empty resource bundle");
      return;
    }

    for (MethodDefinition method : methods) {
      methodDefinition(method, level);
    }
  }

  private void methodDefinition(MethodDefinition method, int level) {
    nl();
    w(level, "/**");
    w(level, " * Text from resource bundle:");
    w(level, " * <pre>");
    w(level, " * " + method.comment);
    w(level, " * </pre>");
    w(level, " */");

    indent(level);
    builder.append(method.returnType.getSimpleName())
      .append(" ")
      .append(method.name)
      .append("(");

    int paramIndex = 0;
    for (Class<?> type : method.paramTypes) {
      if (paramIndex > 0) {
        builder.append(",");
        nl();
        indent(level + 1);
      }

      builder.append(type.getSimpleName())
          .append(" p" + paramIndex);
      paramIndex++;
    }

    builder.append(");");
    nl();
  }

  private static interface IndentAwareBlock {
    void call(int level);
  }
}
