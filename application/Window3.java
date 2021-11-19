/**
 * Title: Milk's Friend
 * 
 * This project implements the GUI to the users and 
 * tell them the main interface and functions.
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
 * Filename:  Window3.java
 * Project:    Milk's Friend
 * Authors:    ateam88
 * 
 * class that implements the windows of report results
 */
public class Window3 extends Application {
  /**
    * display the result of the report
 * @param dm 
    */
    private static LinkedList<String> id=new LinkedList<String>();
    private static LinkedList<Integer> weight=new LinkedList<Integer>();
    private static int totalWeight ;
    /**
     * display report to a new window
     * @param dm -the DataManger instance storing data
     * @param year -the year to get report for
     * @param month -the month to get report for
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void display(DataManager dm,int year,int month) {
    	ArrayList<String> farmNameList = dm.getFarmNames();
		totalWeight = 0;
		sort(dm, month, year);
		for(int i=0;i<id.size();i++)
	          System.out.println(id.get(i));
		
		
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Monthly Report");
        
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Farm#");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Weights");

        @SuppressWarnings({ })
		BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(  "month " + month + "year " + year);
        int farmNum = dm.getFarmNums();
        for(int i=0; i<farmNum;i++) {
        	dataSeries1.getData().add(new XYChart.Data("Farm "+farmNameList.get(i), weight.get(i), month));
        }

        barChart.getData().add(dataSeries1);
        
       
        
        
        //set a hbox in the window2
        HBox hbox = new  HBox();
        Label label1 = new  Label();
        label1.setText("total weight:");
        Label label2 = new  Label();   
        //compute total weight
        totalWeight = dm.get(month, year);
        label2.setText(Integer.toString(totalWeight));
        hbox.getChildren().addAll(label1, label2);
        
       
        GridPane gridPane = new GridPane();
        for(int i=0; i<farmNum;i++) {
            Label l1 = new  Label(farmNameList.get(i) + "    ");
             Label l2 = new  Label(Integer.toString(weight.get(i))+"     ");
             Label l3 = new  Label(Double.toString((double)weight.get(i)/totalWeight*100).substring(0,3)+"%       ");
        	gridPane.add(l1, 0, i);
        	gridPane.add(l2, 2 , i);
        	gridPane.add(l3, 4 , i);
        }
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        scrollPane.fitToHeightProperty().set(true);
        
        
        //set a minbox in the window2
        HBox minbox = new  HBox();
        Label label3 = new  Label();
        label3.setText("min weight:");
        Label label4 = new  Label();   
        //compute min weight
        label4.setText(Integer.toString(weight.get(0))+"  ");
        Label id1 = new  Label();
        id1.setText(" Farm"+id.get(0));
        minbox.getChildren().addAll(label3, label4, id1);  
      //set a maxbox in the window2
        HBox maxbox = new  HBox();
        Label label5 = new  Label();
        label5.setText("max weight:");
        Label label6 = new  Label();   
        //compute max weight
        label6.setText(Integer.toString(weight.get(id.size()-1))+"  ");
        Label id2 = new  Label();
        id1.setText(" Farm"+id.get(id.size()-1));
        maxbox.getChildren().addAll(label5, label6,id2);            
        //set a avebox in the window2
        HBox avebox = new  HBox();
        Label label7 = new  Label();
        label7.setText("average weight:");
        Label label8 = new  Label();   
        //compute averager weight
        label8.setText(Integer.toString(totalWeight/id.size())+"  ");
        avebox.getChildren().addAll(label7, label8);
        
        
        VBox v = new  VBox();
        v.getChildren().addAll(minbox,  maxbox, avebox);
        ScrollPane scrollPane1 = new ScrollPane();
        scrollPane1.setContent(v);
        scrollPane1.fitToHeightProperty().set(true);
        
        
        VBox vbox = new VBox(barChart);
        Button button1 = new Button("Close this report");

        button1.setOnAction(e -> popupwindow.close());

        VBox layout = new VBox(10);

        layout.getChildren().addAll(hbox, vbox, scrollPane, scrollPane1, button1);

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
     * @param month - the month to be be displayed
     */
    
    private static void sort(DataManager dm, int month, int year) {
    	ArrayList<String> farmNameList = dm.getFarmNames();
    	weight.clear();
    	id.clear();
      for(int i=0;i<dm.getFarmNums();i++) {
        weight.add(dm.get(farmNameList.get(i),month, year));
        id.add(farmNameList.get(i));
      }
      
      for(int i=0;i<id.size();i++) {
        for(int j=0;j<id.size()-1-i;j++) {
          if(weight.get(j)>weight.get(j+1)) {
            int temp1=weight.get(j);
            weight.set(j, weight.get(j+1));
            weight.set(j+1, temp1);
            String temp2=id.get(j);
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

