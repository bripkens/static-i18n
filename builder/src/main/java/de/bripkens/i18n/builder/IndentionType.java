package de.bripkens.i18n.builder;

/**
 * @author Ben Ripkens <ben.ripkens@codecentric.de>
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
