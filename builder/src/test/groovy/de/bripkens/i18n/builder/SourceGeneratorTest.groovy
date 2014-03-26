package de.bripkens.i18n.builder

import org.apache.commons.io.IOUtils;
import spock.lang.Specification

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
class SourceGeneratorTest extends Specification {

  def 'should add comment in interface for empty bundles'() {
    given:
    def builder = MessageInterface.builder()
      .name('TestInterface')
      .pckg('com.exmple.i18n')
      .bundle('empty')

    when:
    def s = builder.build()

    then:
    s == slurp('/empty.java.txt')
  }

  def 'should handle simple cases'(name, _) {
    given:
    def builder = MessageInterface.builder()
      .name(name)
      .pckg("com.example.$name")
      .bundle(name)

    when:
    def s = builder.build()

    then:
    s == slurp("/${name}.java.txt")

    where:
    name                 | _
    'singleMethod'       | _
    'twoMethods'         | _
    'parameterized'      | _
  }

  def slurp(String filename) {
    def stream = SourceGeneratorTest.class.getResourceAsStream(filename)
    assert stream != null
    try {
      return IOUtils.toString(stream, 'UTF-8')
    } finally {
      IOUtils.closeQuietly(stream)
    }
  }
}
