package de.bripkens.i18n

import spock.lang.*

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
class MessagesTest extends Specification {

  def 'should create proxies'(locale, _) {
    given:
    Class<?> type = TestMessages.class
    def messages = Messages.get(type, locale)

    expect:
    messages != null
    type.isAssignableFrom(messages.getClass())

    where:
    locale         | _
    Locale.GERMAN  | _
    Locale.ENGLISH | _
  }

  def 'should support multiple locales'(locale, hello, world) {
    given:
    def messages = Messages.get(TestMessages.class, locale)

    expect:
    messages.hello() == hello
    messages.world() == world

    where:
    locale         | hello   | world
    Locale.GERMAN  | 'Hallo' | 'Welt'
    Locale.ENGLISH | 'Hello' | 'World'
  }

  def 'should report missing translations'() {
    given:
    def proxy = Messages.get(TestMessages.class, Locale.GERMAN)

    expect:
    proxy.missing().startsWith('<<Missing resource bundle entry for \'missing\'')
  }

  def 'should support format parameters'(locale, name, msg) {
    given:
    def proxy = Messages.get(TestMessages.class, locale)

    expect:
    proxy.greetings(name) == msg

    where:
    locale         | name          | msg
    Locale.GERMAN  | 'Hans Müller' | 'Hallo Hans Müller! Wie geht es Ihnen?'
    Locale.ENGLISH | 'Jane Doe'    | 'Hello Jane Doe! How are you doing?'
  }

  def 'should cache proxy instances'() {
    given:
    def deProxy1 = Messages.get(TestMessages.class, Locale.GERMAN)
    def deProxy2 = Messages.get(TestMessages.class, Locale.GERMAN)

    expect:
    deProxy1.is(deProxy2)
  }

  def 'should return different proxies for different locales'() {
    given:
    def deProxy = Messages.get(TestMessages.class, Locale.GERMAN)
    def enProxy = Messages.get(TestMessages.class, Locale.ENGLISH)

    expect:
    !enProxy.is(deProxy)
  }

}
