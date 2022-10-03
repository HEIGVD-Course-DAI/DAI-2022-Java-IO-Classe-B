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
        //We check if it's a letter and add it to the output string after changing it
        //https://intellipaat.com/community/35416/how-do-i-apply-the-for-each-loop-to-every-character-in-a-string
        //https://stackoverflow.com/questions/4047808/what-is-the-best-way-to-tell-if-a-character-is-a-letter-or-number-in-java-withou
        //https://stackoverflow.com/questions/3696441/converting-a-char-to-uppercase
        StringBuilder str = new StringBuilder();
        if (Character.isLetter(c.toCharArray()[0])) {
            //We transform it if it's a letter
            str.append(Character.toUpperCase(c.toCharArray()[0]));
        } else {
            //We just add it otherwise
            str.append(c.toCharArray()[0]);
        }
        return str.toString();
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
}
