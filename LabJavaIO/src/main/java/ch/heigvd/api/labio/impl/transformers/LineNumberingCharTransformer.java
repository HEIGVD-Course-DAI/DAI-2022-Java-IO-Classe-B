package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;
import java.util.List;

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
  private static final Logger LOG = Logger.getLogger(UpperCaseCharTransformer.class.getName());
  static boolean isFirst = true;
  private int line = 1;

  public String transform(String c) {
    String result = "";
    if(c.equals("\n")){

    }
    if (line == 1) {
      result = line++ + ". ";
    }

    if (c.equals("\r")){
      result += "";
    } else if (c.equals("\n")) {
      result +=  c + line++ + ". ";
    } else {
      result += c;
    }
    return result;
  }
}