/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phocoslink.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import jssc.SerialPortException;
/**
 * FXML Controller class
 *
 * @author Dell-N7110
 */
public class CXNsolidPageController implements Initializable, ControlledScreen {
            
    /*List of Buttons by fx:id*/
    @FXML private Button currentValuesButton; 
    @FXML private Button dataLoggerButton;
    @FXML private Button newSettingsButton;
    @FXML private Button systemReadingsButton;
    @FXML private Button systemSettingsButton;
    @FXML private Button currentRefresh;
    @FXML private Button dataDayButton;
    @FXML private Button dataWeekButton;
    @FXML private Button dataMonthButton;
    @FXML private Button dataYearButton;
    @FXML private Button settingsControllerButton;
    @FXML private Button settingsLoadButton;
    @FXML private Button batteryVoltageButton;
    @FXML private Button ampHoursButton;
    @FXML private Button pVVoltageButton;
    @FXML private Button systemCurrentsButton;
    @FXML private Button morningSOCButton;
    @FXML private Button temperatureButton;
    
    /*List of TextFields by fx:id*/
    @FXML private TextField currentBatteryVoltage;
    @FXML private TextField currentStateOfCharge;
    @FXML private TextField currentChargeCurrent;
    @FXML private TextField currentLoadCurrent;
    @FXML private TextField currentTodaysEnergy;
    @FXML private TextField currentBatteryChargingState;
    @FXML private TextField currentLoadState;
    @FXML private TextField currentTemperature;
    
    /*List of Labels by fx:id*/
    @FXML private Label batteryVoltageLabel;
    @FXML private Label stateOfChargeLabel;
    @FXML private Label chargeCurrentLabel;
    @FXML private Label loadCurrentLabel;
    @FXML private Label todaysEnergyLabel;
    @FXML private Label batteryChargingStateLabel;
    @FXML private Label loadStateLabel;
    @FXML private Label temperatureLabel;
    /*List of Labels by fx:id*/
    @FXML private Label batteryVoltageUnits;
    @FXML private Label stateOfChargeUnits;
    @FXML private Label chargeCurrentUnits;
    @FXML private Label loadCurrentUnits;
    @FXML private Label todaysEnergyUnits;
    @FXML private Label batteryChargingStateUnits;
    @FXML private Label loadStateUnits;
    @FXML private Label temperatureUnits;
    
    /*List of GridPanes by fx:id*/
    @FXML private GridPane currentValuesGridPane;
    @FXML private GridPane currentValuesSideGridPane;
    @FXML private GridPane systemReadingsGridPane;
    @FXML private GridPane systemSettingsGridPane;
    
    /*List of BorderPanes by fx:id, 3 main buttons control the BorderPanes*/
    @FXML private BorderPane currentValuesBorderPane;
    @FXML private BorderPane dataLoggerBorderPane;
    @FXML private BorderPane newSettingsBorderPane;
    
    /*List of StackPane by fx:id*/
    @FXML private StackPane cxPageStackPane;
    @FXML private StackPane barChartStackPane;
    @FXML private StackPane currentValuesStackPane;
    
    /*List of ScrollPane by fx:id*/
    @FXML private ScrollPane barChartScrollPane;
    
    /*List of Line Charts by fx:id*/    
    @FXML private final BarChart<String, Number> batteryVoltages;
    
    ScreensController myController;
    
    private CXNsolidDataDecryptor solidDecryptor;
    
    //List of the series' needed for the charts
    private final XYChart.Series displayedSeries1 = new XYChart.Series();
    private final XYChart.Series displayedSeries2 = new XYChart.Series();
    private final XYChart.Series batteryMinSeries = new XYChart.Series();
    private final XYChart.Series batteryMaxSeries = new XYChart.Series();
    private final XYChart.Series loadAmpHoursSeries = new XYChart.Series();
    private final XYChart.Series chargeAmpHoursSeries = new XYChart.Series();
    private final XYChart.Series pVMinSeries = new XYChart.Series();
    private final XYChart.Series pVMaxSeries = new XYChart.Series();
    private final XYChart.Series loadMaxCurrentSeries = new XYChart.Series();
    private final XYChart.Series chargeMaxCurrentSeries = new XYChart.Series();
    private final XYChart.Series morningSOCSeries = new XYChart.Series();
    private final XYChart.Series minExternalTempSeries = new XYChart.Series();
    private final XYChart.Series maxExternalTempSeries = new XYChart.Series();
    private final XYChart.Series maxLoadCurrentSeries = new XYChart.Series();
    private final XYChart.Series maxChargeCurrentSeries = new XYChart.Series();
    private final XYChart.Series displayedMonthSeries1 = new XYChart.Series();
    private final XYChart.Series displayedMonthSeries2 = new XYChart.Series();
    private final XYChart.Series batteryMinMonthSeries = new XYChart.Series();
    private final XYChart.Series batteryMaxMonthSeries = new XYChart.Series();
    private final XYChart.Series loadAmpHoursMonthSeries = new XYChart.Series();
    private final XYChart.Series chargeAmpHoursMonthSeries = new XYChart.Series();
    private final XYChart.Series pVMinMonthSeries = new XYChart.Series();
    private final XYChart.Series pVMaxMonthSeries = new XYChart.Series();
    private final XYChart.Series loadMaxCurrentMonthSeries = new XYChart.Series();
    private final XYChart.Series chargeMaxCurrentMonthSeries = new XYChart.Series();
    private final XYChart.Series morningSOCMonthSeries = new XYChart.Series();
    private final XYChart.Series minExternalTempMonthSeries = new XYChart.Series();
    private final XYChart.Series maxExternalTempMonthSeries = new XYChart.Series();
    private final XYChart.Series maxLoadCurrentMonthSeries = new XYChart.Series();
    private final XYChart.Series maxChargeCurrentMonthSeries = new XYChart.Series();
    
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    
    private int systemVoltage = 12;
    private String barChartScale = "day";

    public CXNsolidPageController() {        
        
        //Set up the chart        
        this.batteryVoltages = new BarChart<String, Number>(xAxis,yAxis);
        this.xAxis.setLabel("Date");
        System.out.println("initialized CXNsolid page controller");
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {        
        this.currentValuesButton.setStyle("-fx-font-weight:bold");
        this.systemReadingsButton.setStyle("-fx-font-weight:bold");
        this.dataLoggerBorderPane.setOpacity(0);
        this.newSettingsBorderPane.setOpacity(0);
        this.systemSettingsGridPane.setOpacity(0); 
    }
    
    /**
     *
     * @param screenParent
     */
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;        
    }
    
    
    @FXML
    private void goToScreen1(ActionEvent event){
       myController.setScreen(PhocosLink.screen1ID);
    }
    
    @FXML
    private void loadCurrentValues(ActionEvent event){
       myController.setScreen(PhocosLink.screen1ID);   
    }
    
    @FXML
    private void handleMainMenuSelect(ActionEvent event) throws IOException, SerialPortException {
        myController.setScreen(PhocosLink.screen1ID);
    }
    
    @FXML
    private void handleSystemReadingsSelect(ActionEvent event) throws SerialPortException, UnsupportedEncodingException  {
        this.currentValuesButton.setStyle("-fx-font-weight:bold");
        this.dataLoggerButton.setStyle("-fx-font-weight:normal");
        this.newSettingsButton.setStyle("-fx-font-weight:normal");        
        this.systemReadingsButton.setStyle("-fx-font-weight:bold");
        this.systemSettingsButton.setStyle("-fx-font-weight:normal");
        
        //Set each borderPane to proper opacity, then remove and reload in the proper order
        //Optimize in the future 
        this.systemReadingsGridPane.setOpacity(1);
        this.systemSettingsGridPane.setOpacity(0);
        this.currentValuesStackPane.getChildren().remove(0, 1);
        this.currentValuesStackPane.getChildren().setAll(this.systemSettingsGridPane, this.systemReadingsGridPane);
    }
    
    @FXML
    private void handleSystemSettingsSelect(ActionEvent event) throws SerialPortException, UnsupportedEncodingException  {
        this.currentValuesButton.setStyle("-fx-font-weight:bold");
        this.dataLoggerButton.setStyle("-fx-font-weight:normal");
        this.newSettingsButton.setStyle("-fx-font-weight:normal");        
        this.systemReadingsButton.setStyle("-fx-font-weight:normal");
        this.systemSettingsButton.setStyle("-fx-font-weight:bold");
        
        //Set each borderPane to proper opacity, then remove and reload in the proper order
        //Optimize in the future    
        this.systemReadingsGridPane.setOpacity(0);
        this.systemSettingsGridPane.setOpacity(1);
        this.currentValuesStackPane.getChildren().remove(0, 1);
        this.currentValuesStackPane.getChildren().setAll(this.systemReadingsGridPane, this.systemSettingsGridPane);
    }    
    
    @FXML
    private void handleDataDaySelect(ActionEvent event) throws SerialPortException, UnsupportedEncodingException  {
        this.barChartScale="day";
        this.dataDayButton.setStyle("-fx-font-weight:bold");
        this.dataMonthButton.setStyle("-fx-font-weight:normal");
        this.batteryVoltageButton.setStyle("-fx-font-weight:bold");
        this.ampHoursButton.setStyle("-fx-font-weight:normal");
        this.pVVoltageButton.setStyle("-fx-font-weight:normal");
        this.systemCurrentsButton.setStyle("-fx-font-weight:normal");
        this.morningSOCButton.setStyle("-fx-font-weight:normal");
        this.temperatureButton.setStyle("-fx-font-weight:normal");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;
            this.batteryVoltages.setTitle("Battery Voltages");        
            this.yAxis.setLabel("V");
            if (this.batteryVoltages.getData().size()==1) this.batteryVoltages.getData().add(this.displayedSeries2);
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][2]));
                            data.setXValue(dayData[i][0]);
                        }else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][2]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("MAX");                  
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][3]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][3]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;
                    }
                    series.setName("MIN");
                }else if(seriesCount>1)     {
                    System.out.println("Too many series!");
                }
                seriesCount++;
            }
        }));
        tl.setCycleCount(1);
        tl.play(); 
    }
    
    @FXML
    private void handleDataMonthSelect(ActionEvent event) throws SerialPortException, UnsupportedEncodingException  {
        this.barChartScale="month";
        this.dataDayButton.setStyle("-fx-font-weight:bold");
        this.dataMonthButton.setStyle("-fx-font-weight:normal");
        this.batteryVoltageButton.setStyle("-fx-font-weight:bold");
        this.ampHoursButton.setStyle("-fx-font-weight:normal");
        this.pVVoltageButton.setStyle("-fx-font-weight:normal");
        this.systemCurrentsButton.setStyle("-fx-font-weight:normal");
        this.morningSOCButton.setStyle("-fx-font-weight:normal");
        this.temperatureButton.setStyle("-fx-font-weight:normal");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;
            this.batteryVoltages.setTitle("Battery Voltages");        
            this.yAxis.setLabel("V");
            if (this.batteryVoltages.getData().size()==1) this.batteryVoltages.getData().add(this.displayedSeries2);
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][2]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][2]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("MAX");                  
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][3]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][3]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;
                    }
                    series.setName("MIN");
                }else if(seriesCount>1)     {
                    System.out.println("Too many series!");
                }
                seriesCount++;
            }
        }));
        tl.setCycleCount(1);
        tl.play(); 
    }
    
    @FXML
    private void handleBatteryVoltageSelect(ActionEvent event) throws InterruptedException {        
        this.batteryVoltageButton.setStyle("-fx-font-weight:bold");
        this.ampHoursButton.setStyle("-fx-font-weight:normal");
        this.pVVoltageButton.setStyle("-fx-font-weight:normal");
        this.systemCurrentsButton.setStyle("-fx-font-weight:normal");
        this.morningSOCButton.setStyle("-fx-font-weight:normal");
        this.temperatureButton.setStyle("-fx-font-weight:normal");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;
            this.batteryVoltages.setTitle("Battery Voltages");        
            this.yAxis.setLabel("V");
            if (this.batteryVoltages.getData().size()==1) this.batteryVoltages.getData().add(this.displayedSeries2);
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][2]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][2]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("MAX");                  
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][3]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][3]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;
                    }
                    series.setName("MIN");
                }else if(seriesCount>1)     {
                    System.out.println("Too many series!");
                }
                seriesCount++;
            }
        }));
        tl.setCycleCount(1);
        tl.play();     
    }
    
    @FXML
    private void handleAmpHourSelect(ActionEvent event) throws InterruptedException {
        this.batteryVoltageButton.setStyle("-fx-font-weight:normal");
        this.ampHoursButton.setStyle("-fx-font-weight:bold");
        this.pVVoltageButton.setStyle("-fx-font-weight:normal");
        this.systemCurrentsButton.setStyle("-fx-font-weight:normal");
        this.morningSOCButton.setStyle("-fx-font-weight:normal");
        this.temperatureButton.setStyle("-fx-font-weight:normal");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;
            this.batteryVoltages.setTitle("Amp Hours In and Out");        
            this.yAxis.setLabel("Ah");
            if (this.batteryVoltages.getData().size()==1) this.batteryVoltages.getData().add(this.displayedSeries2);
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][4]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][4]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("Charged");
                    
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][5]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][5]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;
                    }
                    series.setName("Discharged");
                }else if(seriesCount>1) System.out.println("Too many series!");
                seriesCount++;
            }
        }));
        tl.setCycleCount(1);
        tl.play();       
    }
    
    @FXML
    private void handlePVVoltageSelect(ActionEvent event) throws InterruptedException {
        this.batteryVoltageButton.setStyle("-fx-font-weight:normal");
        this.ampHoursButton.setStyle("-fx-font-weight:normal");
        this.pVVoltageButton.setStyle("-fx-font-weight:bold");
        this.systemCurrentsButton.setStyle("-fx-font-weight:normal");
        this.morningSOCButton.setStyle("-fx-font-weight:normal");
        this.temperatureButton.setStyle("-fx-font-weight:normal");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;
            this.batteryVoltages.setTitle("Solar Array Voltages");        
            this.yAxis.setLabel("V");
            if (this.batteryVoltages.getData().size()==1) this.batteryVoltages.getData().add(this.displayedSeries2);
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][6]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][6]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("MAX");                  
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][7]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][7]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;
                    }
                    series.setName("MIN");
                }else if(seriesCount>1)     {
                    System.out.println("Too many series!");
                }
                seriesCount++;
            }
        }));
        tl.setCycleCount(1);
        tl.play();
    }
    
    @FXML
    private void handleSystemCurrentsSelect(ActionEvent event) throws InterruptedException {
        this.batteryVoltageButton.setStyle("-fx-font-weight:normal");
        this.ampHoursButton.setStyle("-fx-font-weight:normal");
        this.pVVoltageButton.setStyle("-fx-font-weight:normal");
        this.systemCurrentsButton.setStyle("-fx-font-weight:bold");
        this.morningSOCButton.setStyle("-fx-font-weight:normal");
        this.temperatureButton.setStyle("-fx-font-weight:normal");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;
            this.batteryVoltages.setTitle("Max Charge Current and Max Load Current");        
            this.yAxis.setLabel("A");
            if (this.batteryVoltages.getData().size()==1) this.batteryVoltages.getData().add(this.displayedSeries2);
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][8]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][8]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("Charge");                  
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][9]));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][9]));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;
                    }
                    series.setName("Load");
                }else if(seriesCount>1)     {
                    System.out.println("Too many series!");
                }
                seriesCount++;
            }
        }));
        tl.setCycleCount(1);
        tl.play();
    }
    
    @FXML
    private void handleSOCSelect(ActionEvent event) throws InterruptedException {
        this.batteryVoltageButton.setStyle("-fx-font-weight:normal");
        this.ampHoursButton.setStyle("-fx-font-weight:normal");
        this.pVVoltageButton.setStyle("-fx-font-weight:normal");
        this.systemCurrentsButton.setStyle("-fx-font-weight:normal");
        this.morningSOCButton.setStyle("-fx-font-weight:bold");
        this.temperatureButton.setStyle("-fx-font-weight:normal");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;            
            this.batteryVoltages.setTitle("State of Charge Percentage");        
            this.yAxis.setLabel("%");       
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {                        
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][10].replaceAll("%", "")));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][10].replaceAll("%", "")));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("SOC");                  
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        data.setYValue(0);
                        if(this.barChartScale.equals("day")){                            
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                }else if(seriesCount>1)     {
                    System.out.println("Too many series!");
                }
                seriesCount++;
            }
            this.batteryVoltages.getData().remove(this.displayedSeries2.getData());
        }));        
        tl.setCycleCount(1);
        tl.play();
    }
    
    @FXML
    private void handleTemperatureSelect(ActionEvent event) throws InterruptedException {
        this.batteryVoltageButton.setStyle("-fx-font-weight:normal");
        this.ampHoursButton.setStyle("-fx-font-weight:normal");
        this.pVVoltageButton.setStyle("-fx-font-weight:normal");
        this.systemCurrentsButton.setStyle("-fx-font-weight:normal");
        this.morningSOCButton.setStyle("-fx-font-weight:normal");
        this.temperatureButton.setStyle("-fx-font-weight:bold");
        final String[][] dayData = this.solidDecryptor.getDayDecoded();
        final String[][] monthData = this.solidDecryptor.getMonthDecoded();
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), (ActionEvent actionEvent) -> {
            int i=0;
            int seriesCount=0;
            this.batteryVoltages.setTitle("External Temperature Max and Min Degrees Celsius");        
            this.yAxis.setLabel("°C");
            if (this.batteryVoltages.getData().size()==1) this.batteryVoltages.getData().add(this.displayedSeries2);
            for (XYChart.Series<String, Number> series : this.batteryVoltages.getData()) {
                if(seriesCount==0) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][11].replaceAll("°C", "")));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][11].replaceAll("°C", "")));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;                        
                    }
                    series.setName("MAX");                    
                }else if(seriesCount==1) {
                    i=0;
                    for (XYChart.Data<String, Number> data : series.getData()) {
                        if(this.barChartScale.equals("day")){
                            data.setYValue(Float.parseFloat(dayData[i][12].replaceAll("°C", "")));
                            data.setXValue(dayData[i][0]);
                        }
                        else if(this.barChartScale.equals("month")){
                            data.setYValue(Float.parseFloat(monthData[i][12].replaceAll("°C", "")));
                            data.setXValue(monthData[i][0]);
                        }
                        i++;
                    }
                    series.setName("MIN");
                }else if(seriesCount>1)     {
                    System.out.println("Too many series!");
                }
                
                seriesCount++;
            }
            
        }));
        tl.setCycleCount(1);
        tl.play();
    }
    
    @FXML
    private void refreshCurrentValues(ActionEvent event) throws SerialPortException, UnsupportedEncodingException {
        
        this.dataMonthButton.setStyle("-fx-font-weight:normal");
        this.dataDayButton.setStyle("-fx-font-weight:bold");
        this.systemReadingsButton.setStyle("-fx-font-weight:bold");
        this.systemReadingsButton.setStyle("-fx-font-weight:normal");
        this.barChartScale = "day";
        float batteryVoltage;
        int stateOfCharge = 0;
        int chargeCurrent = 0;
        int loadCurrent = 0;
        int todaysEnergy = 0;
        int batteryChargingState = 0;
        byte batteryChargingStateByte = (byte) 0b00000000;
        String batteryChargingStateString;
        int loadState = 0;
        int temperature = 0;
        String menuState;
        byte menuStateByte = (byte) 0b111111111;
        String[][] dayData = new String[31][15];
        String[][] monthData = new String[24][15];
        
        this.solidDecryptor = new CXNsolidDataDecryptor();  
        this.solidDecryptor.decryptCurrentValues();
        this.solidDecryptor.decryptDataLogger();
        
        //Load the DataLogger Charts
        int i=0;
        int j;
        dayData = this.solidDecryptor.getDayDecoded();
        while ( dayData[0][0].isEmpty() && i < 100 )    {
            try {
                Thread.sleep(100);                 //Optimize this value, .5 secs for now
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            i++;
        }
        monthData = this.solidDecryptor.getMonthDecoded();
        while ( monthData[0][0].isEmpty() && i < 100 )    {
            try {
                Thread.sleep(100);                 //Optimize this value, .5 secs for now
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            i++;
        }
        for (i=0;i<31;i++)  {
           if (!dayData[i][0].equals("200-0-0")&&!dayData[i][0].equals("200-1-1"))    {
                this.batteryMaxSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][2])));
                this.batteryMaxSeries.setName("Max");
                System.out.println(dayData[i][0]+Float.parseFloat(dayData[i][2]));
                this.batteryMinSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][3])));
                this.batteryMinSeries.setName("Min");
                System.out.println(dayData[i][0]+ Float.parseFloat(dayData[i][3]));
                this.chargeAmpHoursSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][4])));
                this.chargeAmpHoursSeries.setName("Charging");
                System.out.println(dayData[i][0]+ Float.parseFloat(dayData[i][4]));
                this.loadAmpHoursSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][5])));
                this.loadAmpHoursSeries.setName("Discharging");
                this.pVMaxSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][6])));
                this.pVMaxSeries.setName("Max");
                this.pVMinSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][7])));
                this.pVMinSeries.setName("Min");
                this.maxLoadCurrentSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][8])));
                this.maxLoadCurrentSeries.setName("Discharge");
                this.maxChargeCurrentSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][9])));
                this.maxChargeCurrentSeries.setName("Charge");
                this.morningSOCSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][10].replace("%", ""))));
                this.morningSOCSeries.setName("State of Charge");
                this.maxExternalTempSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][11].replace("°C",""))));
                this.maxExternalTempSeries.setName("Max");
                this.minExternalTempSeries.getData().add(new XYChart.Data<>(dayData[i][0], Float.parseFloat(dayData[i][12].replace("°C",""))));
                this.minExternalTempSeries.setName("Min");
            }            
        }
        for (i=0;i<24;i++)  {
           if (!monthData[i][0].equals("200-0-0")&&!monthData[i][0].equals("200-1-1"))    {
                this.batteryMaxMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][2])));
                this.batteryMaxMonthSeries.setName("Max");
                System.out.println(monthData[i][0]+Float.parseFloat(monthData[i][2]));
                this.batteryMinMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][3])));
                this.batteryMinMonthSeries.setName("Min");
                System.out.println(monthData[i][0]+ Float.parseFloat(monthData[i][3]));
                this.chargeAmpHoursMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][4])));
                this.chargeAmpHoursMonthSeries.setName("Charging");
                System.out.println(monthData[i][0]+ Float.parseFloat(monthData[i][4]));
                this.loadAmpHoursMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][5])));
                this.loadAmpHoursMonthSeries.setName("Discharging");
                this.pVMaxMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][6])));
                this.pVMaxMonthSeries.setName("Max");
                this.pVMinMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][7])));
                this.pVMinMonthSeries.setName("Min");
                this.maxLoadCurrentMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][8])));
                this.maxLoadCurrentMonthSeries.setName("Discharge");
                this.maxChargeCurrentMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][9])));
                this.maxChargeCurrentMonthSeries.setName("Charge");
                this.morningSOCMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][10].replace("%", ""))));
                this.morningSOCMonthSeries.setName("State of Charge");
                this.maxExternalTempMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][11].replace("°C",""))));
                this.maxExternalTempMonthSeries.setName("Max");
                this.minExternalTempMonthSeries.getData().add(new XYChart.Data<>(monthData[i][0], Float.parseFloat(monthData[i][12].replace("°C",""))));
                this.minExternalTempMonthSeries.setName("Min");
            }
        }
        
        //Load the Current Values
        this.currentBatteryVoltage.setText(String.valueOf(this.solidDecryptor.getBatteryVoltage()));
        this.currentStateOfCharge.setText(String.valueOf(this.solidDecryptor.getSOCPercent()));
        this.currentChargeCurrent.setText(String.valueOf(this.solidDecryptor.getChargeCurrent()));
        this.currentLoadCurrent.setText(String.valueOf(this.solidDecryptor.getLoadCurrent()));
        int dayIndex = 0;
        String currentDay = dayData[0][0];
        String tempDayString;
        int tempDayInt;
        for (i=0;i<15;i++)  {
            tempDayString = dayData[i][0];
            tempDayInt=Integer.parseInt(tempDayString.replace("-", ""));
            if(tempDayInt>Integer.parseInt(currentDay.replace("-", "")))    {
                currentDay = dayData[i][0];     
                dayIndex=i;
            }            
        }
        if ((Integer.parseInt(dayData[dayIndex][14],2)&1024)==1024){
            this.currentTodaysEnergy.setText(String.valueOf(Float.parseFloat(dayData[dayIndex][4])*24));
        }else if ((Integer.parseInt(dayData[dayIndex][14],2)&2048)==2048){
            this.currentTodaysEnergy.setText(String.valueOf(Float.parseFloat(dayData[dayIndex][4])*48));   
        }else   {
            this.currentTodaysEnergy.setText(String.valueOf(Float.parseFloat(dayData[dayIndex][4])*12));
        }
        int chargeState = this.solidDecryptor.getChargeState();
        if ( (chargeState&1)==1 ) {
            this.currentBatteryChargingState.setText("BOOST");
        }else if ( (chargeState&2)==2 ) {
            this.currentBatteryChargingState.setText("EQUALIZE");
        }else if ( (chargeState&8)==8 ) {
            this.currentBatteryChargingState.setText("NIGHT");
        }else {
            this.currentBatteryChargingState.setText("FLOAT");
        }        
        this.currentLoadState.setText(String.valueOf(this.solidDecryptor.getLoadState()));
        this.currentTemperature.setText(String.valueOf(this.solidDecryptor.getExternalTemp()));
        
        //Load Charts
        this.displayedSeries1.setData(this.batteryMaxSeries.getData());
        this.displayedSeries2.setData(this.batteryMinSeries.getData());
        this.displayedSeries1.setName("MAX");
        this.displayedSeries2.setName("MIN");
        this.batteryVoltages.getData().addAll(this.displayedSeries1, this.displayedSeries2);
        this.batteryVoltages.setTitle("Battery Voltages");        
        this.yAxis.setLabel("Volts");
        this.yAxis.setAnimated(true);
        this.xAxis.setLabel("Date");
        this.xAxis.setAnimated(true);        
    } 

    @FXML
    private void loadSystemReadings()   {
        
    }
        
    @FXML
    private void handleCurrentValuesSelect(ActionEvent event) throws SerialPortException, UnsupportedEncodingException  {
        //Set selected button to bold
        this.currentValuesButton.setStyle("-fx-font-weight:bold");
        this.dataLoggerButton.setStyle("-fx-font-weight:normal");
        this.newSettingsButton.setStyle("-fx-font-weight:normal");        
        this.systemReadingsButton.setStyle("-fx-font-weight:bold");
        this.systemSettingsButton.setStyle("-fx-font-weight:normal");
        
        //Set each borderPane to proper opacity, then remove and reload in the proper order
        //Optimize in the future
        this.dataLoggerBorderPane.setOpacity(0);
        this.newSettingsBorderPane.setOpacity(0);
        this.currentValuesBorderPane.setOpacity(1);
        this.cxPageStackPane.getChildren().remove(0, 2);
        this.cxPageStackPane.getChildren().setAll(this.newSettingsBorderPane, this.dataLoggerBorderPane, this.currentValuesBorderPane);
    }
    
    
    @FXML
    private void handleDataLoggerSelect(ActionEvent event)   {        
        //Set Selected buttons to bold
        this.currentValuesButton.setStyle("-fx-font-weight:normal");
        this.dataLoggerButton.setStyle("-fx-font-weight:bold");
        this.newSettingsButton.setStyle("-fx-font-weight:normal");
        
        
        //Set each borderPane to proper opacity, then remove and reload in the proper order
        //Optimize in the future
        this.currentValuesBorderPane.setOpacity(0);
        this.newSettingsBorderPane.setOpacity(0);    
        this.dataLoggerBorderPane.setOpacity(1);
        this.cxPageStackPane.getChildren().remove(0, 2);
        this.cxPageStackPane.getChildren().setAll(this.currentValuesBorderPane, this.newSettingsBorderPane, this.dataLoggerBorderPane);
        //Set week chart as default
        int i=0;
        if( !this.barChartStackPane.getChildren().isEmpty() ) {
            this.barChartStackPane.getChildren().removeAll();
        }         
        this.barChartStackPane.getChildren().setAll(
                this.batteryVoltages);
    }
    
    @FXML
    private void handleNewSettingsSelect(ActionEvent event)   {
        //Set selected buttons to bold
        this.currentValuesButton.setStyle("-fx-font-weight:normal");
        this.dataLoggerButton.setStyle("-fx-font-weight:normal");
        this.newSettingsButton.setStyle("-fx-font-weight:bold");
        
        //Set each borderPane to proper opacity, then remove and reload in the proper order
        //Optimize in the future
        this.currentValuesBorderPane.setOpacity(0);
        this.newSettingsBorderPane.setOpacity(1);    
        this.dataLoggerBorderPane.setOpacity(0);
        this.cxPageStackPane.getChildren().remove(0, 2);
        this.cxPageStackPane.getChildren().setAll(this.currentValuesBorderPane, this.dataLoggerBorderPane, this.newSettingsBorderPane);
        
    }
    
    
    
}