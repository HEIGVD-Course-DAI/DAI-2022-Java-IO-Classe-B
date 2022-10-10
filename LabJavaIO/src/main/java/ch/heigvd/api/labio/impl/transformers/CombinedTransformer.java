package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies multiple transformations to the input character (a string with a single character):
 * it converts the character to upper case & add line numbering
 * LineNumberingCharTransformer need to be reset between each file that the transformation is used on
 * Otherwise, the line numbering will be wrong
 * Except if you start the transformation after the following char
 * where you stopped your iteration
 *
 * @author Kévin BOUGNON-PEIGNE
 */
public class CombinedTransformer {
    private static final Logger LOG = Logger.getLogger(CombinedTransformer.class.getName());
    private final LineNumberingCharTransformer lineTransformer
            = new LineNumberingCharTransformer();
    private final UpperCaseCharTransformer upperCaseTransformer
            = new UpperCaseCharTransformer();

    public String transform(String c) {
        return lineTransformer.transform(
                upperCaseTransformer.transform(
                        c
                )
        );
    }

    public void resetLineTransformer() {
        lineTransformer.resetAttributs();
    }
}

