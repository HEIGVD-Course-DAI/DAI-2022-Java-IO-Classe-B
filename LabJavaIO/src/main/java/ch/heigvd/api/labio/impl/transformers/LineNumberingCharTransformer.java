package ch.heigvd.api.labio.impl.transformers;

import java.util.Objects;
import java.util.logging.Logger;

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
  private static final Logger LOG = Logger.getLogger(LineNumberingCharTransformer.class.getName());

  private int currentLineNumber = 1;
  private boolean isFirstChar = true;

  public String transform(String c) {
    // Remove carriage return
    if (Objects.equals(c, "\r")) {
      c = "";
    }

    // Add a line number before the first char
    if (isFirstChar) {
      c = (currentLineNumber++) + ". " + c;
      isFirstChar = false;
    }

    // Add line number after line return.
    // Since there could have been a line number added before (in case of first char)
    // we check the last char to see if it is a line return
    if (c.endsWith("\n")) {
      c += (currentLineNumber++) + ". ";
    }

    return c;
  }
}
