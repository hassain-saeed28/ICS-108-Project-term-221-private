import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScheduleMaker extends Application {

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();
        HBox hBoxTop = new HBox();
        hBoxTop.setPadding(new Insets(15,12,15,12));
        hBoxTop.setAlignment(Pos.CENTER);
        
        Button button = new Button("start with a saved schedule");
        button.setPrefHeight(20);
        button.setPrefWidth(40);
        button.setAlignment(Pos.CENTER);

        Pane titelPane = new Pane();
        titelPane.getChildren().add(new Text(50, 27, "Add Sections to Basket"));
        // titelPane.getChildren().add(button);
        // hBoxTop.getChildren().add(new Text(100, 27, "Add Sections to Basket"));
        // // hBoxTop.setSpacing(40);
        // hBoxTop.getChildren().add(new Text(100, 100, "Add Sections to Basket"));
        // // hBoxTop.setSpacing(30);
        hBoxTop.getChildren().addAll(titelPane,button);
        HBox hBoxBottom = new HBox();
        Pane pane = new Pane();
        borderPane.setTop(hBoxTop);
        borderPane.setBottom(hBoxBottom);
        borderPane.setCenter(pane);

        Scene scene = new Scene(borderPane, 450, 100);   
        primaryStage.setTitle("ShowImage"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage\
        primaryStage.show();

        
    }

    public static void main(String[] args) {
        launch(args);
    }


    
}
