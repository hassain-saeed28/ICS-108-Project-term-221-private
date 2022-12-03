import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) {

        // declaring the main page pane wich is a border pane 
        BorderPane borderPane = new BorderPane();
        // declaring the to bar of the main page wich is a HBox 
        HBox hBoxTop = new HBox();
        // seting the alignment to center 
        hBoxTop.setAlignment(Pos.TOP_RIGHT);
        // seting the padding 
        hBoxTop.setPadding(new Insets(15,15,15,15));
        // seting spacing between the combonents of the the HBox
        hBoxTop.setSpacing(100);

        Text titel = new Text("Add Sections to Basket");
        titel.setFont(Font.font(30));

        Button topRightButton =  new Button("start with a saved schedule");
        topRightButton.setPadding(new Insets(10, 10, 10, 10));
        topRightButton.setFont(Font.font(15));
        

        
        hBoxTop.getChildren().addAll(titel,topRightButton);


        // ListView listViewCourses = new ListView<>();

        HBox hBoxBottom = new HBox();



    

        borderPane.setTop(hBoxTop);
        borderPane.setBottom(hBoxBottom);
        // borderPane.setCenter(listViewCourses);

        Scene scene = new Scene(borderPane, 900, 700);   
        primaryStage.setTitle("ShowImage"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage\
        primaryStage.show();

        
    }

    public static void main(String[] args) {
        launch(args);
    }


    
}
