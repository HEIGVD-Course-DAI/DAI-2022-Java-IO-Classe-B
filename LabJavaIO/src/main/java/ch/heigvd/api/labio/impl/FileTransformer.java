package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;
import ch.heigvd.api.labio.impl.transformers.CombinedTransformer;

import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //NoOpCharTransformer transformer = new NoOpCharTransformer();
    //UpperCaseCharTransformer transformer1 = new UpperCaseCharTransformer();
    //LineNumberingCharTransformer transformer2 = new LineNumberingCharTransformer();
    CombinedTransformer transformer = new CombinedTransformer();

    try {
      java.nio.charset.Charset encoding = java.nio.charset.StandardCharsets.UTF_8;
      FileReader fileReader = new FileReader(inputFile, encoding);
      String contentTransformed = "";

      /* RECOVERING CONTENT + APPLYING TRANSFORMATIONS */
      /* Work with both approach */
      int c;
      while ((c = fileReader.read()) != -1)
        contentTransformed += transformer.transform(Character.toString(c));
        //contentTransformed += transformer1.transform(
        //        transformer2.transform(
        //                Character.toString(c)));

      /* WRITING CONTENT TO WRITER */
      FileOutputStream fos = new FileOutputStream(inputFile + ".out");
      fos.write(contentTransformed.getBytes(encoding));

      /* CLOSING FILES */
      fileReader.close();
      /* /!\ For WRITING file, don't forget to flush them before closing /!\ */
      fos.flush();
      fos.close();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}