package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.*;
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

    UpperCaseCharTransformer upperCaseCharTransformer =
            new UpperCaseCharTransformer();

    LineNumberingCharTransformer lineNumberingCharTransformer =
            new LineNumberingCharTransformer();

    try {
      if (inputFile.exists() && inputFile.isFile()) {

        // Creates the input stream using utf-8 encoding
        // we are not using buffers as they're small files
        InputStreamReader in = new InputStreamReader(new FileInputStream(inputFile),
                StandardCharsets.UTF_8);

        int c = in.read();

        // Puts the every character of the file in a string builder
        // we are using string builder to use the append method
        StringBuilder contents = new StringBuilder();
        while (c != -1) {
         contents.append((char) c);
          c = in.read();
        }

        in.close();


        // Transforms every character and puts it in a string builder
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < contents.length(); i++) {
          String tempString = contents.substring(i, i + 1);
          tempString = upperCaseCharTransformer.transform(tempString);
          tempString = lineNumberingCharTransformer.transform(tempString);
          result.append(tempString);
        }

        // Creates the file writer using ut8-8 encoding
        FileWriter fw = new FileWriter(inputFile.getAbsoluteFile() + ".out",
                StandardCharsets.UTF_8);

        // Writes every character in the file
        for (int i = 0; i < result.length(); i++) {
          fw.write(result.charAt(i));
        }
        fw.flush();
        fw.close();

      } else {
        LOG.log(Level.SEVERE, "File {0} does not exist", inputFile.getAbsolutePath());
      }
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}