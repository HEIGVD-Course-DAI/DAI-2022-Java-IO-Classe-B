package ch.heigvd.api.labio.impl.transformers;

/**
 * This class applies a transformation to the input character (a string with a single character):
 *   1. Convert all line endings to Unix-style line endings, i.e. only '\n'.
 *   2. Add a line number at the beginning of each line.
 * Example:
 *   Using this character transformer, the following file :
 *      This is the first line.\r\n
 *      This is the second line.
 *   will be transformed to:
 *      1. This is the first line.\n
 *      2. This is the second line.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class LineNumberingCharTransformer {
  private int line = 1;

  public String transform(String c) {
    String result = "";

    if (line == 1) {
      result = line++ + ". ";
    }

    switch (c) {
      case "\n":
        result += c + line++ + ". ";
        break;
      case "\r":
        result += "";
        break;
      default:
        result += c;
        break;
    }
    return result;
  }
}
