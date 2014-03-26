package de.bripkens.i18n.testing;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import de.bripkens.i18n.Messages;

public class MessagesTester {

  public void test(Locale locale) {
    Set<Class<?>> types = findAnnotatedClasses();
    for (Class<?> type : types) {
      test(type, locale);
    }
  }

  private Set<Class<?>> findAnnotatedClasses() {
    // TODO use org.reflections to find annotated classes
    return new HashSet<Class<?>>();
  }

  public <T> void test(Class<T> type, Locale locale) {
    T t = Messages.get(type, locale);

    for (Method method : type.getMethods()) {
      if (method.getDeclaringClass().equals(type)) {
        // TODO actually invoke method with number of required parameters
        // verify return value
      }
    }
  }
}
