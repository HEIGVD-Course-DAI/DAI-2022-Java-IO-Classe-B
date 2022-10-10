package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies a simple transformation to the input character (a string with a single character):
 * it converts the character to upper case.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class UpperCaseCharTransformer {
  private static final Logger LOG = Logger.getLogger(UpperCaseCharTransformer.class.getName());

  public String transform(String c) {
    /* TODO: implement the transformation here.
     */

    char ch;

    StringBuilder str = new StringBuilder();

    for (int i = 0; i < c.length(); i++) {
      ch = c.charAt(i);
      if (Character.isLowerCase(ch)) {
        ch = Character.toUpperCase(ch);
      }

      str.append(Character.toString(ch));

    }

    return str.toString();

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
}
