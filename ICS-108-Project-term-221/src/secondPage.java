import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class secondPage extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        // for button 
        HBox hBoxbutton = new HBox();
        hBoxbutton.setAlignment(Pos.BOTTOM_LEFT);
        hBoxbutton.setPadding(new Insets(15,15,15,15));
        hBoxbutton.setSpacing(8);
        Button save = new Button("Save Schedule");
        save.setPadding(new Insets(10, 10, 10, 10));
        save.setFont(Font.font(15));
        hBoxbutton.getChildren().addAll(save);

        TableView table = new TableView<Section>();
        // sunday column 
        TableColumn<Course,Section> suncolumns = new TableColumn<Course,Section>("Sunday");
        suncolumns.setCellValueFactory(new PropertyValueFactory<Course,Section>("time_days"));
        // monday column 
        TableColumn<Course,Section> muncolumns = new TableColumn<Course,Section>("Monday");
        muncolumns.setCellValueFactory(new PropertyValueFactory<Course,Section>("time_days"));
        // tuesday column 
        TableColumn<Course,Section> tuecolumns = new TableColumn<Course,Section>("Tuesday");
        tuecolumns.setCellValueFactory(new PropertyValueFactory<Course,Section>("time_days"));
        // wenseday column
        TableColumn<Course,Section> wecolumns = new TableColumn<Course,Section>("Wenesday");
        wecolumns.setCellValueFactory(new PropertyValueFactory<Course,Section>("time_days"));
        // thersday column
        TableColumn<Course,Section> thrcolumns = new TableColumn<Course,Section>("Thersday");
        thrcolumns.setCellValueFactory(new PropertyValueFactory<Course,Section>("time_days"));

        table.getColumns().add(suncolumns);
        table.getColumns().add(muncolumns);
        table.getColumns().add(tuecolumns);
        table.getColumns().add(wecolumns);
        table.getColumns().add(thrcolumns);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        root.setCenter(table);

        Scene scene = new Scene(root, 500,450);
        primaryStage.setTitle("Course Offring ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
