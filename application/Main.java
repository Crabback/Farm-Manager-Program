/**
 * Title: Milk's Friend
 * 
 * This project implements the GUI to the users and tell them the main interface and functions.
 * 
 * 
 * Author: ateam88
 * 
 * Email: //
 * 
 * Lecture Number: LEC002, LEC001
 *
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Filename: Main.java Project: Milk's Friend Authors: ateam88
 * 
 * Main method to implement the GUI for Milk"s Friend
 * @author ateam88
 */
public class Main extends Application {
  // store any command-line arguments that were entered.
  // NOTE: this.getParameters().getRaw() will get these also
  @SuppressWarnings("unused")
private List<String> args;

  private static final int WINDOW_WIDTH = 2000;
  private static final int WINDOW_HEIGHT = 800;
  private static final String APP_TITLE = "Milk's Friend";
  FileManager fm = new FileManager();
  ArrayList<String> allData = new ArrayList<String>();
  CheeseFactory cf = new CheeseFactory();
  DataManager dm = new DataManager(cf);

  /**
   * This method will set the primary stage and get this GUI started.
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    // save args example
    args = this.getParameters().getRaw();
    
    BorderPane root = new BorderPane();
    BackgroundFill background_fill =
        new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY);
    Background background = new Background(background_fill);
    root.setBackground(background);
    
    root.setTop(getTop());
    root.setLeft(getLeft());
    root.setCenter(getCenter());
    root.setRight(getRight());
    root.setBottom(getBot());
    
    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.FLORALWHITE);
    
    // Add all the stuff and set the primary stage
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();
  }

  /**
   * Build up the top part of the window.
   * 
   * @return vb - the VBox representing the top part of the window 
   */
  private VBox getTop() {
    Label title = new Label(APP_TITLE);
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
    VBox vb = new VBox();
    HBox hb = new HBox();
    HBox hSpace = new HBox();
    hb.getChildren().addAll(title);
    hb.setSpacing(20);
    vb.getChildren().addAll(hb, hSpace);
    vb.setSpacing(20);
    return vb;
  }

  /**
   * Build up the bottom part of the window 
   * 
   * @return layout - the HBox representing the bottom part of the window 
   */
  private HBox getBot() {
    VBox vb = new VBox();
    VBox vSpace = new VBox();
    HBox hb = new HBox();
    HBox hSpace = new HBox();
    HBox layout = new HBox();
    Button Exit = new Button();
    
    Exit.setText("Exit");
    Exit.setOnAction(e -> Platform.exit());
    
    vb.getChildren().addAll(Exit, hSpace);
    vb.setSpacing(20);
    
    Label ateam = new Label("Ateam 88");
    ateam.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
    ateam.setTextFill(Color.PINK);
    
    hb.getChildren().addAll(vSpace, vb);
    hb.setSpacing(20);
    layout.getChildren().addAll(hb, ateam);
    layout.setSpacing(1250);
    
    return layout;
  }

  /**
   * Build up the left part of the window.
   * 
   * @return total - the VBox representing the left part of the window 
   */
  private VBox getLeft() {
    VBox total = new VBox();
    VBox vSpace3 = new VBox();
    VBox vSpace4 = new VBox();
    VBox vSpace5 = new VBox();
    HBox hb1 = new HBox();
    HBox hb2 = new HBox();
    HBox hb3 = new HBox();
    HBox hb4 = new HBox();
    HBox hb5 = new HBox();
    VBox vbr1 = new VBox();
    VBox vbr2 = new VBox();
    VBox vbr3 = new VBox();
    VBox vbr4 = new VBox();

    vbr1 = this.getFarmReport();
    vbr2 = this.getAnnualReport();
    vbr3 = this.getMonthlyReport();
    vbr4 = this.getDateRangeReport();
    
    hb3.getChildren().addAll(vSpace3, Add());
    hb4.getChildren().addAll(vSpace4, Remove());
    hb5.getChildren().addAll(vSpace5, Edit());
    hb1.setSpacing(20);
    hb2.setSpacing(20);
    hb3.setSpacing(6);
    hb4.setSpacing(6);
    hb5.setSpacing(6);
    
    total.getChildren().addAll(vbr1, vbr2, vbr3, vbr4, hb3, hb4, hb5);
    total.setSpacing(10);

    return total;
  }

  /**
   * Takes user input and generates a window with farm report
   * 
   * @return  the VBox containing user interfaces for generation of farm report 
   */
  private VBox getFarmReport() {
    VBox vb = new VBox();
    Label label = new Label();
    HBox hb = new HBox();
    TextField id = new TextField();
    TextField year = new TextField();
    Button report = new Button("Get Farm Report");
    
    id.setPrefWidth(50.0);
    year.setPrefWidth(50.0);
    id.setPromptText("ID");
    year.setPromptText("Year");
    
    report.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        try {
          label.setText("");
          Window1.display(dm, id.getText(), Integer.parseInt(year.getText()));
        } catch (NumberFormatException e) {
          label.setText("Error, please type in a valid year");
        }
      }
    });
    
    VBox space = new VBox();
    hb.setSpacing(6);
    hb.getChildren().addAll(space, id, year, report);
    vb.setSpacing(6);
    vb.getChildren().addAll(hb, label);
    
    return vb;
  }

  /**
   * Takes user input and generates a window with annual report
   * 
   * @return the VBox containing user interfaces to generate annual report
   */
  private VBox getAnnualReport() {
    VBox vb = new VBox();
    HBox hb = new HBox();
    Label label = new Label();
    TextField year = new TextField();
    Button report = new Button("Get Annual Report");
    
    year.setPrefWidth(50.0);
    year.setPromptText("Year");
    
    report.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        try {
          label.setText("");
          Window2.display(dm, Integer.parseInt(year.getText()));
        } catch (NumberFormatException e) {
          label.setText("Error, please type in a valid year");
        }
      }
    });
    
    VBox space = new VBox();
    hb.setSpacing(6);
    hb.getChildren().addAll(space, year, report);
    vb.setSpacing(6);
    vb.getChildren().addAll(hb, label);
    
    return vb;
  }

  /**
   * Takes user input and generates a window with monthly report
   * 
   * @return the VBox containing user interfaces to generate monthly reports
   */
  private VBox getMonthlyReport() {
    VBox vb = new VBox();
    HBox hb = new HBox();
    Label label = new Label();
    TextField month = new TextField();
    TextField year = new TextField();
    Button report = new Button("Get Monthly Report");
    
    year.setPrefWidth(50.0);
    month.setPrefWidth(50.0);
    year.setPromptText("Year");
    month.setPromptText("Month");
    
    report.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        try {
          label.setText("");
          Window3.display(dm, Integer.parseInt(year.getText()), Integer.parseInt(month.getText()));
        } catch (NumberFormatException e) {
          label.setText("Error, please type in a valid month/year");
        }
      }
    });
    
    VBox space = new VBox();
    hb.setSpacing(6);
    hb.getChildren().addAll(space, month, year, report);
    vb.setSpacing(6);
    vb.getChildren().addAll(hb, label);
    
    return vb;
  }

  /**
   * Take user input and generates a window with date range reports
   * 
   * @return the VBox containing user interfaces to generate date range reports
   */
  private VBox getDateRangeReport() {
    VBox vb = new VBox();
    HBox hb = new HBox();
    Label label = new Label();
    TextField year = new TextField();
    TextField month1 = new TextField();
    TextField day1 = new TextField();
    TextField month2 = new TextField();
    TextField day2 = new TextField();
    Button report = new Button("Get Date Range Report");
    
    year.setPrefWidth(50.0);
    day1.setPrefWidth(40.0);
    month1.setPrefWidth(100.0);
    year.setPromptText("Year");
    day1.setPromptText("Day");
    month1.setPromptText("From: Month");
    day2.setPrefWidth(40.0);
    month2.setPrefWidth(80.0);
    day2.setPromptText("Day");
    month2.setPromptText("To: Month");
    
    report.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        try {
          label.setText("");
          int pday1 = Integer.parseInt(day1.getText());
          int pday2 = Integer.parseInt(day2.getText());
          int pmonth1 = Integer.parseInt(month1.getText());
          int pmonth2 = Integer.parseInt(month2.getText());
          int pyear = Integer.parseInt(year.getText());
          Window4.display(dm, pyear, pmonth1, pday1, pmonth2, pday2);
        } catch (NumberFormatException e) {
          label.setText("Error, please type in a valid month/year");
        }
      }
    });
    
    VBox space = new VBox();
    hb.setSpacing(6);
    hb.getChildren().addAll(space, year, month1, day1, month2, day2, report);
    vb.setSpacing(6);
    vb.getChildren().addAll(hb, label);
    
    return vb;
  }

  /**
   * Build up the right part of the window.
   * 
   * @return vb2 - the VBox representing the right part of the window 
   */
  private VBox getRight() {
    Label label1 = new Label();
    Button button = new Button("Done");
    TextField textField = new TextField();
    Label corMessage = new Label();
    HBox hb = new HBox();
    VBox vb1 = new VBox();
    VBox vb2 = new VBox();
    VBox vSpace = new VBox();
    
    hb.getChildren().addAll(textField, button, vSpace);
    hb.setSpacing(20);
    vb1.getChildren().addAll(hb, label1, corMessage);
    vb1.setSpacing(20);
    vb2.getChildren().addAll(vb1, fileOutput());
    vb2.setSpacing(50);
    textField.setPrefWidth(250);
    textField.setPromptText("Upload csv File (Please type in full name)");
    
    
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String text = textField.getText();
        if (text == null || text.isEmpty()) {
          label1.setText("Please type in something");
        } else {
          FileManager fm2 = new FileManager();
          try {
            fm.readFile(text);
            allData = fm.getInputData();
            boolean correctness = fm2.readFile(text);
            cf.insertData(fm2.getInputData());
            label1.setText(text + " received");
            if (correctness)
              corMessage.setText("This is a dataset with error/missing information");
            else
              corMessage.setText("This is a correct dataset");

          } catch (FileNotFoundException e1) {
            label1.setText("Error! File not found!");
          }
        }
      }
    });
    
    return vb2;
  }

  /**
   * Build up the center part of the window.
   * 
   * @return total - the VBox
   */
  private ImageView getCenter() {
    Image img = new Image("milk.png");
    ImageView imgView = new ImageView();
    imgView.setImage(img);
    imgView.setFitHeight(600);
    imgView.setFitWidth(530);
    
    return imgView;
  }

  /**
   * Implement the add button's function and display it.
   * 
   * @return total - the VBox representing the add button interface
   */
  private VBox Add() {
    VBox total = new VBox();
    Label labelAdd = new Label();
    Button buttonAdd = new Button("Add");
    TextField day = new TextField();
    TextField month = new TextField();
    TextField year = new TextField();
    TextField farm = new TextField();
    TextField weight = new TextField();
    
    day.setPrefWidth(50.0);
    month.setPrefWidth(50.0);
    year.setPrefWidth(50.0);
    farm.setPrefWidth(70.0);
    weight.setPrefWidth(100.0);
    day.setPromptText("Day");
    year.setPromptText("Year");
    month.setPromptText("Month");
    farm.setPromptText("Farm");
    weight.setPromptText("Weight");
    
    HBox hbAdd = new HBox();
    hbAdd.getChildren().addAll(year, month, day, farm, weight, buttonAdd);
    hbAdd.setSpacing(6);
    total.getChildren().addAll(hbAdd, labelAdd);
    total.setSpacing(10);
    
    buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String dayT = day.getText();
        String monthT = month.getText();
        String yearT = year.getText();
        String farmT = farm.getText();
        String weightT = weight.getText();
        FileManager fm = new FileManager();
        if (dayT.equals("") || monthT.equals("") || yearT.equals("") || farmT.equals("")
            || weightT.equals(""))
          labelAdd.setText("Input not complete, Please re-enter");
        else if (fm.detectDataFormat(
            yearT + "-" + monthT + "-" + dayT + ",Farm " + farmT + "," + weightT) == 0) {
          labelAdd.setText("Successfully added " + weightT + " milk for Farm " + farmT + " on "
              + yearT + "-" + monthT + "-" + dayT);
          cf.insertSingleData(yearT + "-" + monthT + "-" + dayT + ",Farm " + farmT + "," + weightT);
          allData.add(yearT + "-" + monthT + "-" + dayT + ",Farm " + farmT + "," + weightT);
        } else
          labelAdd.setText("Invalid input, Please re-enter");
      }
    });

    return total;
  }

  /**
   * Implement the remove button's function and display it.
   * 
   * @return total - the VBox representing the remove button interface
   */
  private VBox Remove() {
    VBox total = new VBox();
    Label labelRemove = new Label();
    Button buttonRemove = new Button("Remove");
    TextField rday = new TextField();
    TextField rmonth = new TextField();
    TextField ryear = new TextField();
    TextField rfarm = new TextField();
    
    rday.setPrefWidth(50.0);
    rmonth.setPrefWidth(50.0);
    ryear.setPrefWidth(50.0);
    rfarm.setPrefWidth(70.0);
    rday.setPromptText("Day");
    ryear.setPromptText("Year");
    rmonth.setPromptText("Month");
    rfarm.setPromptText("Farm");
    
    HBox hbRemove = new HBox();
    hbRemove.getChildren().addAll(ryear, rmonth, rday, rfarm, buttonRemove);
    hbRemove.setSpacing(6);
    total.getChildren().addAll(hbRemove, labelRemove);
    total.setSpacing(10);
    
    buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String dayR = rday.getText();
        String monthR = rmonth.getText();
        String yearR = ryear.getText();
        String farmR = rfarm.getText();
        FileManager fm = new FileManager();
        boolean removed = false;
        System.out.println(
            fm.detectDataFormat(yearR + "-" + monthR + "-" + dayR + ",Farm " + farmR + ", "));
        if (dayR.equals("") || monthR.equals("") || yearR.equals("") || farmR.equals(""))
          labelRemove.setText("Input not complete, Please re-enter");
        else if (fm
            .detectDataFormat(yearR + "-" + monthR + "-" + dayR + ",Farm " + farmR + ", ") == 0) {

          cf.removeSingleData(yearR + "-" + monthR + "-" + dayR + ",Farm " + farmR + ", ");
          for (int i = 0; i < allData.size(); i++) {
            String[] dataSplit = allData.get(i).split(",", 0);
            String date = dataSplit[0];
            int id = Integer.parseInt(dataSplit[1].split(" ")[1]);
            System.out.println(id + "id");
            String[] dateSplit = date.split("-", 0);
            int iYear = Integer.parseInt(dateSplit[0]);
            int iMonth = Integer.parseInt(dateSplit[1]);
            int iDate = Integer.parseInt(dateSplit[2]);
            System.out.println(iYear + "year");
            System.out.println(iMonth + "month");
            System.out.println(iDate + "day");
            if (Integer.parseInt(yearR) == iYear && Integer.parseInt(monthR) == iMonth
                && Integer.parseInt(dayR) == iDate && Integer.parseInt(farmR) == id) {
              allData.remove(i);
              removed = true;
              labelRemove.setText("Successfully removed milk for Farm " + farmR + " on " + yearR
                  + "-" + monthR + "-" + dayR);
            }
          }
          if (removed == false)
            labelRemove.setText("Data entered to be removed does not exist");

        } else
          labelRemove.setText("Invalid input, Please re-enter");
      }
    });
    
    return total;
  }

  /**
   * Implement the Edit button's function and display it.
   * 
   * @return total - the VBox representing the edit button interface
   */
  private VBox Edit() {
    VBox total = new VBox();
    Label labelRemove = new Label();
    Button buttonRemove = new Button("Edit");
    TextField eday = new TextField();
    TextField emonth = new TextField();
    TextField eyear = new TextField();
    TextField efarm = new TextField();
    TextField eweight = new TextField();
    
    eday.setPrefWidth(50.0);
    emonth.setPrefWidth(50.0);
    eyear.setPrefWidth(50.0);
    efarm.setPrefWidth(70.0);
    eweight.setPrefWidth(100.0);
    eday.setPromptText("Day");
    eyear.setPromptText("Year");
    emonth.setPromptText("Month");
    efarm.setPromptText("Farm");
    eweight.setPromptText("Weight");
    
    HBox hbRemove = new HBox();
    hbRemove.getChildren().addAll(eyear, emonth, eday, efarm, eweight, buttonRemove);
    hbRemove.setSpacing(6);
    total.getChildren().addAll(hbRemove, labelRemove);
    total.setSpacing(10);
    
    buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        String dayR = eday.getText();
        String monthR = emonth.getText();
        String yearR = eyear.getText();
        String farmR = efarm.getText();
        String weightR = eweight.getText();
        FileManager fm = new FileManager();
        boolean changed = false;
        if (dayR.equals("") || monthR.equals("") || yearR.equals("") || farmR.equals("")
            || weightR.equals(""))
          labelRemove.setText("Input not complete, Please re-enter");
        else if (fm.detectDataFormat(
            yearR + "-" + monthR + "-" + dayR + ",Farm " + farmR + "," + weightR) == 0) {
          labelRemove.setText("Successfully changed to " + weightR + " milk for Farm " + farmR
              + " on " + yearR + "-" + monthR + "-" + dayR);
          cf.editSingleData(yearR + "-" + monthR + "-" + dayR + ",Farm " + farmR + "," + weightR);
          for (int i = 0; i < allData.size(); i++) {
            String[] data = allData.get(i).split(",");
            if (data[0].equals(yearR + "-" + monthR + "-" + dayR)
                && data[1].equals("Farm " + farmR)) {
              allData.set(i, yearR + "-" + monthR + "-" + dayR + ",Farm " + farmR + "," + weightR);
              changed = true;
            }
          }
          if (changed == false)
            allData.add(yearR + "-" + monthR + "-" + dayR + ",Farm " + farmR + "," + weightR);
        } else
          labelRemove.setText("Invalid input, Please re-enter");
      }
    });
    
    return total;
  }

  /**
   * Implement the csv file output function and display it
   * @return total - VBox representing the file output interface
   */
  private VBox fileOutput() {
    VBox total = new VBox();
    HBox hb = new HBox();
    Label title = new Label();
    Label title2 = new Label();
    Label response = new Label();
    
    title.setText("Download latest dataset to local csv file");
    title2.setText("Enter csv file name to download to:");
    title.setFont(Font.font(20));
    title2.setFont(Font.font(20));
    
    TextField ename = new TextField();
    ename.setPrefWidth(200);
    ename.setPrefHeight(50);
    ename.setPromptText("File Name");
    
    Button downloadB = new Button("Download");
    hb.getChildren().addAll(ename, downloadB);
    hb.setSpacing(20);
    total.getChildren().addAll(title, title2, hb, response);
    total.setSpacing(30);
    
    downloadB.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        File file = new File(ename.getText());
        try {
          FileWriter fw = new FileWriter(file);
          for (int i = 0; i < allData.size(); i++) {
            fw.write(allData.get(i));
            fw.write("\n");
          }
          fw.flush();
          fw.close();
          response.setText("File succesfully downloaded!");
        } catch (IOException e1) {
          response.setText("File not succesfully downloaded, please try again!");
        }
      }
    });

    return total;
  }

  /**
   * Launches the GUI
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}
