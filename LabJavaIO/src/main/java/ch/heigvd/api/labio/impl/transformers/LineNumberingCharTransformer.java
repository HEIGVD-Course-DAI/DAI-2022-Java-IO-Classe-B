package ch.heigvd.api.labio.impl.transformers;

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
  private boolean firstLineSet = false;
  private int lineNumber = 1;

  public String transform(String c) {
    /* '\r' is a char that we want to remove,
     * so it become the first condition to pass */
    if (c.equals("\r"))
      return c.replace("\r", "");

    /* Then we analyse if it is the first time we make a transformation
    * to number the first line */
    if (!firstLineSet) {
      firstLineSet = true;
      String oldC = c;
      c = String.valueOf(lineNumber++) + ". " + c;
      /* If the first line start with a '\n' we update
       * directly the second line with its lineNumber */
      if (oldC.equals("\n"))
        return c += String.valueOf(lineNumber++) + ". ";
    }

    /* Update following line after a '\n' */
    if (c.equals("\n")) {
      return c + String.valueOf(lineNumber++) + ". ";
    }

    /* Otherwise, simply return the char */
    return c;
  }

  public void resetAttributs() {
    firstLineSet = false;
    lineNumber = 1;
  }
}
