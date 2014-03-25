package de.bripkens.i18n.builder;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
class MethodDefinition {

  final String name;
  final Class<?> returnType;
  final List<Class<?>> paramTypes;
  final String comment;

  MethodDefinition(String name,
      Class<?> returnType,
      List<Class<?>> paramTypes,
      String comment) {
    this.name = name;
    this.returnType = returnType;
    this.paramTypes = paramTypes;
    this.comment = comment;
  }

  public static final Comparator<MethodDefinition> COMPARATOR_NAME =
      new Comparator<MethodDefinition>() {
        public int compare(MethodDefinition m1, MethodDefinition m2) {
          return m1.name.compareTo(m2.name);
        }
      };
}
