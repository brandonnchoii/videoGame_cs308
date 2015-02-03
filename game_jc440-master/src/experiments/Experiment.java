package experiments;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Experiment extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        btn.setText("Hello World!");
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                btn2.setText("Hello back at you!");
                root.getChildren().add(btn2);
            }
        });
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
        	
        	public void handle(ActionEvent event)
        	{
        		btn3.setText("This is the last button");
        		root.getChildren().add(btn3);
        	}
        });
        
        btn3.setOnAction(new EventHandler<ActionEvent>() {
        	
        	public void handle(ActionEvent event)
        	{
        		System.exit(1);
        	}
        });
        
        
        
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
