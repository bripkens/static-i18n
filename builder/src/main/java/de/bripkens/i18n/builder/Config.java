package de.bripkens.i18n.builder;

import de.bripkens.i18n.I18nException;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Ben Ripkens <ben.ripkens@codecentric.de>
 */
class Config {
  String name;
  String pckg;

  String bundle;

  int indention = 4;
  IndentionType indentionType = IndentionType.SPACE;

  private void invariant(boolean val, String msg) {
    if (!val) {
      throw new I18nBuilderException(msg);
    }
  }

  void validate() {
    invariant(name != null, "interface name missing");
    invariant(pckg != null, "package configuration missing");
    invariant(bundle != null, "bundle missing");
    invariant(indention > 0, "indention must be larger than 0");
    invariant(indentionType != null, "indention type missing");

    try {
      ResourceBundle.getBundle(bundle);
    } catch(MissingResourceException ex) {
      throw new I18nException("Bundle cannot be found", ex);
    }
  }

}
