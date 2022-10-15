package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.impl.transformers.LineNumberingCharTransformer;
import ch.heigvd.api.labio.impl.transformers.UpperCaseCharTransformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
    LineNumberingCharTransformer lineTransformer = new LineNumberingCharTransformer();
    UpperCaseCharTransformer upperTransformer = new UpperCaseCharTransformer();

    try {

      //new reader and writer
      FileReader fr = new FileReader(inputFile.getAbsolutePath(), StandardCharsets.UTF_8);
      FileWriter fw = new FileWriter(inputFile.getAbsolutePath() + ".out", StandardCharsets.UTF_8);

      BufferedReader br = new BufferedReader(fr);

      String actualChar;
      int readChar;
      StringBuilder result = new StringBuilder();


      while ((readChar = br.read()) != -1){ //while end of stream is not reached
        actualChar = String.valueOf((char)readChar); //get the char in a string

        //char transformation
        actualChar = upperTransformer.transform(actualChar);
        actualChar = lineTransformer.transform(actualChar);

        result.append(actualChar); //append the transformed char to a string builder
      }

      fw.write(result.toString()); //write the string in the file

      fw.flush();
      fw.close();
      fr.close();

    } catch (Exception ex) {
      LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
    }
  }
}