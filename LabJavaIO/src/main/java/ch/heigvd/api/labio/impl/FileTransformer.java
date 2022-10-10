package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.StandardCharsets;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
 */
public class FileTransformer {
  private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());

  public void transform(File inputFile) {
    /*
     * This method opens the given inputFile and copies the
     * content to an output file.
     * The output file has a file name <inputFile-Name>.out, for example:
     *   quote-2.utf --> quote-2.utf.out
     * Both files must be opened (read or write) with encoding "UTF-8".
     * Before writing each character to the output file, the transformer calls
     * a character transformer to transform the character before writing it to the output.
     */

    /* TODO: first start with the NoOpCharTransformer which does nothing.
     *  Later, replace it by a combination of the UpperCaseCharTransformer
     *  and the LineNumberCharTransformer.
     */
    // ... transformer = ...

    UpperCaseCharTransformer upperCaseCharTransformer = new UpperCaseCharTransformer();

    LineNumberingCharTransformer lineNumberingCharTransformer = new LineNumberingCharTransformer();


    /* TODO: implement the following logic here:
     *  - open the inputFile and an outputFile
     *    Use UTF-8 encoding for both.
     *    Filename of the output file: <inputFile-Name>.out (that is add ".out" at the end)
     *  - Copy all characters from the input file to the output file.
     *  - For each character, apply a transformation: start with NoOpCharTransformer,
     *    then later replace it with a combination of UpperCaseFCharTransformer and LineNumberCharTransformer.
     */
    try {
      if (inputFile.exists() && inputFile.isFile()) {

        FileReader fr = new FileReader(inputFile, StandardCharsets.UTF_8);

        String contents = new String(java.nio.file.Files.readAllBytes(inputFile.toPath()));

        int c = fr.read();

        char[] chars = new char[c];

        fr.read(chars);

        fr.close();

        String s = new String(chars);

        s = upperCaseCharTransformer.transform(s);

        s = lineNumberingCharTransformer.transform(s);

        FileWriter fw = new FileWriter(inputFile.getName() + ".out", StandardCharsets.UTF_8);

        fw.write(s);

        fw.close();

      } else {
        LOG.log(Level.SEVERE, "File {0} does not exist", inputFile.getAbsolutePath());
      }
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}