package de.bripkens.i18n.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ResourceBundle;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
class MethodExtractor {

  List<MethodDefinition> extract(String bundleName) {
    ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
    List<MethodDefinition> methods = new ArrayList<MethodDefinition>();

    Enumeration<String> keys = bundle.getKeys();
    while (keys.hasMoreElements()) {
      String key = keys.nextElement();
      methods.add(toMethodDefinition(key, bundle.getString(key)));
    }

    Collections.sort(methods, MethodDefinition.COMPARATOR_NAME);
    return methods;
  }

  private MethodDefinition toMethodDefinition(String key, String value) {
    return new MethodDefinition(camelify(key),
      String.class,
      getParameterTypes(key, value),
      makeJavaDocSafe(value));
  }

  private String camelify(String name) {
    // TODO Ben actual implementation
    return name;
  }

  private List<Class<?>> getParameterTypes(String key, String value) {
    List<Class<?>> returnTypes = new ArrayList<Class<?>>();

    int paramCount = getParameterCount(value);
    for (int i = 0; i < paramCount; i++) {
      returnTypes.add(String.class);
    }

    return returnTypes;
  }

  private int getParameterCount(String formatString) {
    Matcher matcher = PLACEHOLDER.matcher(formatString);
    Set<String> formatStringIndices = new HashSet<String>();

    while (matcher.find()) {
      formatStringIndices.add(matcher.group(1));
    }

    return formatStringIndices.size();
  }

  private String makeJavaDocSafe(String s) {
    // TOOD Ben handle line breaks, */ content and more
    return s;
  }

  // TODO Ben move to top afer in-train dev
  private static final Pattern PLACEHOLDER = Pattern.compile("\\{(\\d+)[^}]*?\\}");
}
