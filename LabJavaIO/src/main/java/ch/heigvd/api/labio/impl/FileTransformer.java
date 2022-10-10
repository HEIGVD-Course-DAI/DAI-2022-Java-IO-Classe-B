package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.NoOpCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import javax.sound.sampled.Line;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.nio.charset.StandardCharsets;
import java.io.*;

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
    InputStreamReader reader = null;
    OutputStreamWriter writer = null;
    try {

      File outputFile = new File(inputFile + ".out");

      FileInputStream fis = new FileInputStream(inputFile);
      FileOutputStream fos = new FileOutputStream(outputFile);

       reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
       writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

      LineNumberingCharTransformer tr1 = new LineNumberingCharTransformer();
      UpperCaseCharTransformer tr2 = new UpperCaseCharTransformer();

      int c;
      while((c = reader.read()) != -1){
        String str = Character.toString(c);
        str = tr1.transform(str);
        str = tr2.transform(str);
        writer.write(str);
      }
    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    } finally {
      try {
        writer.close();
      } catch (Exception ex) {
        LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
      }
      try {
        reader.close();
      } catch (Exception ex){
        LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
      }

    }
  }
}