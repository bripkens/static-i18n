package de.bripkens.i18n.builder

import de.bripkens.i18n.I18nException
import spock.lang.Specification

/**
 *
 * @author Ben Ripkens <ben.ripkens@codecentric.de>
 */
class MessageInterfaceTest extends Specification {

  def 'should fail for missing name'() {
    given:
    def builder = getValidBuilder()
        .name(null)

    when:
    builder.build()

    then:
    thrown(I18nException)
  }

  def 'should fail for missing package'() {
    given:
    def builder = getValidBuilder()
        .pckg(null)

    when:
    builder.build()

    then:
    thrown(I18nException)
  }

  def 'should fail for missing bundle'() {
    given:
    def builder = getValidBuilder()
        .bundle(null)

    when:
    builder.build()

    then:
    thrown(I18nException)
  }

  def 'should fail for non existing bundle'() {
    given:
    def builder = getValidBuilder()
        .bundle('notExisting')

    when:
    builder.build()

    then:
    thrown(I18nException)
  }

  def Builder getValidBuilder() {
    return MessageInterface.builder()
        .name('BigBangTheory')
        .pckg('de.bripkens.i18n')
        .bundle('bigbangmessages')
  }

}
