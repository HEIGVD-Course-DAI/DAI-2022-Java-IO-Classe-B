package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies a transformation to the input character (a string with a single character):
 * 1. Convert all line endings to Unix-style line endings, i.e. only '\n'.
 * 2. Add a line number at the beginning of each line.
 * Example:
 * Using this character transformer, the following file :
 * This is the first line.\r\n
 * This is the second line.
 * will be transformed to:
 * 1. This is the first line.\n
 * 2. This is the second line.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class LineNumberingCharTransformer {
    private static final Logger LOG = Logger.getLogger(UpperCaseCharTransformer.class.getName());

    public String transform(String c) {
        /* TODO: implement the transformation here.
         */
        c = c.replace("\r\n", "\n");
        StringBuilder sb = new StringBuilder(c);
        int lineNbr = 1;
        sb.insert(0, String.format("%d. ", lineNbr));
        lineNbr++;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '\n') {
                sb.insert(i + 1, String.format("%d. ", lineNbr));
                lineNbr++;
            }
        }
        return sb.toString();
    }
}