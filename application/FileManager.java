package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Filename: Main.java Project: Milk's Friend Authors: ateam88
 * Defines required operations to read/write input/output files
 * 
 * @author ateam88
 *
 */
public class FileManager {
  private ArrayList<String> inputData;

  /**
   * Create a new instance of FileManager
   */
  public FileManager() {
    this.inputData = new ArrayList<String>();
  }

  /**
   * Read data from the inputFile
   * 
   * @param inputFile2
   * 
   * @return true if the data has errors and false otherwise
   * @throws FileNotFoundException - if file cannot be found
   */
  public boolean readFile(String inputFile2) throws FileNotFoundException {
    boolean error = false;

    File input = new File(inputFile2);
    Scanner scnr = new Scanner(input);
    while (scnr.hasNextLine()) {
      String line = scnr.nextLine();
      if (detectDataFormat(line) == 0)
        inputData.add(line);
      else {
        if (line.equals("date,farm_id,weight"))
          continue;
        error = true;
      }
    }
    scnr.close();
    return error;
  }


  /**
   * Detects error with each line of data input 
   * @param input - line of data input for detection of error
   * @return 0 if data is correct, 1 if data is missing, and 2 if data is in a wrong format
   */
  public int detectDataFormat(String input) {
    String[] dataSplit = input.split(",", 0);
    String date = dataSplit[0];
    String id = dataSplit[1];
    String weight = dataSplit[2];
    if (date.equals("-") || id.equals("-") || weight.equals("-")) {
      return 1;
    } else if (!date.contains("-") || detectLetter(date) || detectInvalidDate(date)
        || detectLetter(weight) || detectFarm(id)) {
      return 2;
    } else
      return 0;
  }

  /**
   * Private helper method for detection of letters
   * @param string - string for detection of letters
   * @return true if the string contains letters and false otherwise
   */
  private boolean detectLetter(String string) {

    for (int i = 0; i < string.length(); i++) {
      char x = string.charAt(i);
      if ((x > 'a' && x < 'z') || (x > 'A' && x < 'Z'))
        return true;
    }
    return false;
  }

  /**
   * Private helper method for detection of an invalid date
   * @param string - string for detection of invalid date
   * @return true if string is invalid and false otherwise
   */
  private boolean detectInvalidDate(String string) {

    if (string.charAt(4) != '-' || (string.charAt(6) != '-' && string.charAt(7) != '-'))
      return true;

    String[] splitDate = string.split("-", 0);
    String month = splitDate[1];
    String day = splitDate[2];
    if (month.length() < 1 || month.length() > 2)
      return true;
    if (day.length() < 1 || day.length() > 2)
      return true;

    return false;
  }

  /**
   * Private helper method for detection of invalid farm id 
   * @param string - string for detection of invalid farm id 
   * @return true if farm id is invalid and false otherwise
   */
  private boolean detectFarm(String string) {
    if (!string.contains(" "))
      return true;
    else {
      String[] dataSplit = string.split(" ", 0);
      String farm = dataSplit[0];
      String id = dataSplit[1];
      if (farm.equals("Farm") || !detectLetter(id)) {
        return false;
      } else
        return true;
    }
  }

  /**
   * Gets the input data that was obtained from the inputFile
   * 
   * @return - an ArrayList of String containing the data obtained from the CSV file
   */
  public ArrayList<String> getInputData() {
    return this.inputData;
  }

}
