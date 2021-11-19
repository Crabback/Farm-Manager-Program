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

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Filename:  Window2.java
 * Project:    Milk's Friend
 * Authors:    ateam88
 * 
 * class that implements the windows of report results
 */
public class Window2 extends Application {
    //Create LinkedLists to store id and weight in order.
    private static LinkedList<String> id=new LinkedList<String>();
    private static LinkedList<Integer> weight=new LinkedList<Integer>();
    private static int totalWeight ;
    
    /**
     * display the result of the report
     * @param dm - an instance of DataManager
     * @param year - the year to be displayed
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display(DataManager dm,int year) {
    	ArrayList<String> farmNameList = dm.getFarmNames();
    	totalWeight = 0;
        //sort the data in ascending order.
        sort(dm, year);
        
        //Set up the popupwindow.         
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Annual Report");
        
        //Set up the xAxis and yAxis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Farm#");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Weights");

        @SuppressWarnings({ })
		BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(Integer.toString(year));
        int farmNum = dm.getFarmNums();
        //Add data into dataSeries1.
        for(int i=0; i<farmNum;i++) {
            dataSeries1.getData().add(new XYChart.Data("Farm "+farmNameList.get(i), weight.get(i)));
        }

        barChart.getData().add(dataSeries1);  
        
        //set a hbox in the window2
        HBox hbox = new  HBox();
        Label label1 = new  Label();
        label1.setText("total weight:");
        Label label2 = new  Label();
        
        //compute total weight
        for(int i=1; i<=12;i++) {
            if(dm.get(i, 2019)>0) {
                totalWeight = totalWeight + dm.get(i, 2019);
            }
        }
        
        label2.setText(Integer.toString(totalWeight));
        hbox.getChildren().addAll(label1, label2);
        
        //Create a gridPane.
        GridPane gridPane = new GridPane();
        //Set up the gridPane.
        for(int i=0; i<farmNum;i++) {
        	//System.out.println(farmNameList.get(i));
             Label l1 = new  Label("Farm:"+farmNameList.get(i) + "    ");
             Label l2 = new  Label(Integer.toString(weight.get(i))+"     ");
             Label l3 = new  Label(Double.toString((double)weight.get(i)/totalWeight*100).substring(0,3)+"%       ");
            gridPane.add(l1, 0, i);
            gridPane.add(l2, 2 , i);
            gridPane.add(l3, 4 , i);
        }
        //Create and set up a ScrollPane.
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.fitToHeightProperty().set(true);
        
        VBox vbox = new VBox(barChart);
        //Create a button to close the popupwindow.
        Button button1 = new Button("Close this report");
        button1.setOnAction(e -> popupwindow.close());

        //Set up the layout.
        VBox layout = new VBox(10);
        layout.getChildren().addAll(hbox, vbox, scrollPane,  button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 800, 600);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    /**
     * This method get the function started.
     * @param primaryStage
     * @exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
    }
    
    /**
     * This method sort the data in DataManager to make it in ascending order.
     * @param dm - an instance of DataManager
     * @param year - the year to be displayed
     */
    private static void sort(DataManager dm, int year) {
    	ArrayList<String> farmNameList = dm.getFarmNames();
    	id.clear();
    	weight.clear();
      //Store id in order.
      for(int i=0;i<dm.getFarmNums();i++) {
        weight.add(dm.get(farmNameList.get(i), year));
        id.add(farmNameList.get(i));
      }
    // Store weights in order using bubble sort.
      for(int i=0;i<id.size();i++) {
        for(int j=0;j<id.size()-1-i;j++) {
          if(weight.get(j)>weight.get(j+1)) {
            //Swap the the number in j and j+1.
            int temp1=weight.get(j);
            weight.set(j, weight.get(j+1));
            weight.set(j+1, temp1);
            String temp2= id.get(j);
            id.set(j, id.get(j+1));
            id.set(j+1,temp2);
          }  
        }
      }
    }

    
    public static void main(String[] args) {
      launch(args);
  }


} 

