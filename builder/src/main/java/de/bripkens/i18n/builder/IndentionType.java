package de.bripkens.i18n.builder;

/**
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public enum IndentionType {
  SPACE(' '), TAB('\t');

  private final char character;

  private IndentionType(char character) {
    this.character = character;
  }

  public char getCharacter() {
    return character;
  }
}
