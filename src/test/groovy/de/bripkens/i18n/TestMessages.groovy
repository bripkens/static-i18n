package de.bripkens.i18n

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
@MessagesSource("testmessages")
public interface TestMessages {

    String hello();

    String world();

    String missing();

    String greetings(String s1);

}