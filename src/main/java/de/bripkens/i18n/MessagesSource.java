package de.bripkens.i18n;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessagesSource {
    /**
     * The base name of the resource bundle, a fully qualified class name in accordance
     * with the documentation of {@link java.util.ResourceBundle#getBundle(String)}.
     */
    String value();
}
