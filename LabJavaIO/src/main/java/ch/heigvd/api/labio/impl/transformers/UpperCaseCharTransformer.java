package ch.heigvd.api.labio.impl.transformers;

/**
 * This class applies a simple transformation to the input character (a string with a single character):
 * it converts the character to upper case.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class UpperCaseCharTransformer {
  public String transform(String c) {
    return c.toUpperCase();
  }
}
