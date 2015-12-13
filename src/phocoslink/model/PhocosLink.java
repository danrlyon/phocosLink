/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phocoslink.model;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.net.URL;
/**
 *
 * @author Dell-N7110
 */
public class PhocosLink extends Application {
    
    //Variables for global use
    public static final String space = " ";
    public static final String exclamation = "!";
    public static String selectedPort = "NONE";
    public static String controllerType = "NONE";
    
    //FXML files and IDs for each screen
    public static String screen1ID = "main";
    public static String screen1File = "IntroPage.fxml";
    public static String screen2ID = "cxpage";
    public static String screen2File = "CXPage.fxml";
    public static String screen3ID = "cxnpage";
    public static String screen3File = "CXNPage.fxml";
    public static String screen4ID = "cxnsolidpage";
    public static String screen4File = "CXNsolidPage.fxml";
    public static String styleSheet ="StyleSheet_CXlink.css";
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        //Loads the 3 different screen options. Uncomment for more screens
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(PhocosLink.screen1ID, PhocosLink.screen1File);
        mainContainer.loadScreen(PhocosLink.screen2ID, PhocosLink.screen2File);
        mainContainer.loadScreen(PhocosLink.screen3ID, PhocosLink.screen3File);
        mainContainer.loadScreen(PhocosLink.screen4ID, PhocosLink.screen4File); 
        
        //Set the IntroPage
        mainContainer.setScreen(PhocosLink.screen1ID);        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        URL cssLocation = getClass().getResource(PhocosLink.styleSheet);
        scene.getStylesheets().add(cssLocation.toString());
        primaryStage.setTitle("CXlink");
        
        //Checks that logo is properly loaded, then loads it
        FileInputStream cxLinkLogo = null; 
        try {
            cxLinkLogo = new FileInputStream(System.getProperty("user.dir") + "/src/phocoslink/images/CXLINK-simple.png");
        }   catch (FileNotFoundException ex)    {
                System.out.println("FileNotFoundException caught");
        }            
        if(cxLinkLogo!=null)    primaryStage.getIcons().add(new Image(cxLinkLogo));
        
        //Set the Stage with the IntroPage
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
