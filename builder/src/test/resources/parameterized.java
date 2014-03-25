package com.example.parameterized;

import de.bripkens.i18n.MessagesSource;

@MessagesSource("parameterized")
public class parameterized {

    /**
     * Text from resource bundle:
     * <pre>
     * Tag
     * </pre>
     */
    String day();

    /**
     * Text from resource bundle:
     * <pre>
     * Guten {0} {1}
     * </pre>
     */
    String greetings(String p0,
        String p1);

    /**
     * Text from resource bundle:
     * <pre>
     * Morgen
     * </pre>
     */
    String morning();

}
