package ch.heigvd.api.labio.impl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import ch.heigvd.api.labio.impl.transformers.*;

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

    var lineNumberingCharTrans = new LineNumberingCharTransformer();
    var upperCaseCharTrans = new UpperCaseCharTransformer();

    try {
      // Instantiates reader and writer
      var fileReader = new FileReader(inputFile.getAbsoluteFile(), StandardCharsets.UTF_8);
      var bufferedReader = new BufferedReader(fileReader);
      var fileWriter = new FileWriter(inputFile.getAbsoluteFile() + ".out", StandardCharsets.UTF_8);
      var bufferedWriter = new BufferedWriter(fileWriter);
      int charRead;
      String currentChar;

      // Copy file with transformations
      while ((charRead = bufferedReader.read()) != -1) {
        currentChar = String.valueOf((char)charRead);
        currentChar = upperCaseCharTrans.transform(currentChar);
        currentChar = lineNumberingCharTrans.transform(currentChar);
        bufferedWriter.append(currentChar);
      }

      // Close readers and writers
      bufferedReader.close();
      bufferedWriter.flush();
      bufferedWriter.close();
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}