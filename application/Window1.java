/**
 * Title: Milk's Friend
 * 
 * This file implements the pop-up window of the GUI that displays a farm report.
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

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Filename: Window1.java Project: Milk's Friend Authors: ateam88
 * 
 * class that implements the windows of report results
 */
public class Window1 extends Application {

 /**
  * display the result of the report
  */
 @SuppressWarnings({ "unchecked", "rawtypes" })
 public static void display(DataManager dm, String id, int year) {
  Stage popupwindow = new Stage();

  // window title
  popupwindow.initModality(Modality.APPLICATION_MODAL);
  popupwindow.setTitle("Farm Report");

  // create a bar plot
  CategoryAxis xAxis = new CategoryAxis();
  xAxis.setLabel("Month#");
  NumberAxis yAxis = new NumberAxis();
  yAxis.setLabel("Weights");
  XYChart.Series dataSeries1 = new XYChart.Series();

  dataSeries1.setName("Farm Report for " + id);
  // add data to the dataSeries;
  for (int i = 1; i <= 12; i++) {
   dataSeries1.getData().add(new XYChart.Data(Integer.toString(i), dm.get(id, i, year)));
  }
  // add the dataseries to the bar plot
  BarChart barChart = new BarChart(xAxis, yAxis);
  barChart.getData().add(dataSeries1);
  VBox vbox = new VBox(barChart);

  // create a sorted bar plot
  CategoryAxis xAxis2 = new CategoryAxis();
  xAxis.setLabel("Month#");
  NumberAxis yAxis2 = new NumberAxis();
  yAxis.setLabel("Weights");
  XYChart.Series dataSeries2 = new XYChart.Series();
  dataSeries2.setName("Sorted Farm Report for " + id);
  ArrayList<Integer> weights = new ArrayList<Integer>();
  ArrayList<Integer> weights2 = new ArrayList<Integer>();
  ArrayList<Integer> months = new ArrayList<Integer>();
  for (int i = 1; i <= 12; i++) {
   weights.add(dm.get(id, i, year));
   weights2.add(dm.get(id, i, year));
  }
  Collections.sort(weights);
  for (int i = 0; i < 12; i++) {
   if (weights2.get(i) == 0) {
    months.add(i + 1);
   }
  }
  for (int i = 0; i < 12; i++) {
   if (weights.get(i) != 0) {
    months.add(weights2.indexOf(weights.get(i))+1);
   }
  }
  for (int i = 0; i < 12; i++) {
   dataSeries2.getData().add(new XYChart.Data(Integer.toString(months.get(i)), weights.get(i)));
  }

  BarChart barChart2 = new BarChart(xAxis2, yAxis2);
  barChart2.getData().add(dataSeries2);
  VBox sorted = new VBox(barChart2);

  // create a table that contains the weight for each month for the given year in
  // the farm
  GridPane gridPane = new GridPane();
  gridPane.add(new Label("Month      "), 0, 0);
  gridPane.add(new Label("Weight      "), 2, 0);
  gridPane.add(new Label("Percent of the total"), 4, 0);
  for (int i = 1; i <= 12; i++) {
   Label l1 = new Label(Integer.toString(i) + "      ");
   Label l2 = new Label(Integer.toString(dm.get(id, i, year)) + "     ");
   Label l3 = new Label(
     Double.toString((double) dm.get(id, i, year) / dm.get(i, year) * 100).substring(0, 3) + "%       ");
   gridPane.add(l1, 0, i);
   gridPane.add(l2, 2, i);
   gridPane.add(l3, 4, i);
  }
  ScrollPane scrollPane = new ScrollPane();
  scrollPane.setContent(gridPane);
  scrollPane.fitToHeightProperty().set(true);

  // create a table that contains the toal, average, max, min weight for the given
  // year in the farm
  GridPane avgMaxMin = new GridPane();
  Label total = new Label("Total Weight: " + dm.get(id, year));
  Label avg = new Label("Yearly Average: " + dm.getYearlyAverageForFarm(id, year));
  Label max = new Label("Yearly Max: " + dm.getYearlyMaxForFarm(id, year) + ", Month: "
    + dm.getYearlyMaxForFarm_Month(id, year));
  Label min = new Label("Yearly Min: " + dm.getYearlyMinForFarm(id, year) + ", Month: "
    + dm.getYearlyMinForFarm_Month(id, year));
  avgMaxMin.add(total, 0, 0);
  avgMaxMin.add(avg, 0, 1);
  avgMaxMin.add(max, 0, 2);
  avgMaxMin.add(min, 0, 3);
  ScrollPane scrollPane2 = new ScrollPane();
  scrollPane2.setContent(avgMaxMin);
  scrollPane2.fitToHeightProperty().set(true);

  // create a button that closes the popup window
  Button button1 = new Button("Close this report");
  button1.setOnAction(e -> popupwindow.close());

  // add all the parts together
  VBox layout = new VBox(10);
  layout.getChildren().addAll(vbox, sorted, scrollPane, scrollPane2, button1);
  layout.setAlignment(Pos.CENTER);
  Scene scene1 = new Scene(layout, 800, 600);
  popupwindow.setScene(scene1);
  popupwindow.showAndWait();

 }

 /**
  * This method will get this program started.
  */
 @Override
 public void start(Stage arg0) throws Exception {
  // TODO Auto-generated method stub

 }

}