import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Application {

    public static ArrayList<Section> basket = new ArrayList<>();

    /*
     * (non-Javadoc)
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        // declaring the main page pane wich is a border pane
        BorderPane borderPane = new BorderPane();
        // declaring the to bar of the main page wich is a HBox
        HBox hBoxTop = new HBox();
        // seting the alignment to center
        hBoxTop.setAlignment(Pos.TOP_RIGHT);
        // seting the padding
        hBoxTop.setPadding(new Insets(15, 15, 15, 15));
        // seting spacing between the combonents of the the HBox
        hBoxTop.setSpacing(100);
        hBoxTop.setBackground(Background.fill(Paint.valueOf("ffff")));

        Text titel = new Text("Add Sections to Basket");
        titel.setFont(Font.font(30));

        Button topRightButton = new Button("start with a saved schedule");
        topRightButton.setPadding(new Insets(10, 10, 10, 10));
        topRightButton.setFont(Font.font(15));

        hBoxTop.getChildren().addAll(titel, topRightButton);

        TableView<Section> tableView = new TableView<>();

        TableColumn<Section, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));

        TableColumn<Section, String> secColumn = new TableColumn<>("Section");
        secColumn.setCellValueFactory(new PropertyValueFactory<>("sec"));

        TableColumn<Section, String> daysColumn = new TableColumn<>("Days");
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("days"));

        TableColumn<Section, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Section, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Section, Button> addBottonColumn = new TableColumn<>("Adding botton");
        addBottonColumn.setCellValueFactory(new PropertyValueFactory<>("addButton"));

        TableColumn<Section, Button> removeButtonColumn = new TableColumn<>("RemovebButton");
        removeButtonColumn.setCellValueFactory(new PropertyValueFactory<>("removeButton"));

        tableView.setItems(Section.getSectionsObservableList());

        tableView.getColumns().add(courseColumn);
        tableView.getColumns().add(secColumn);
        tableView.getColumns().add(daysColumn);
        tableView.getColumns().add(timeColumn);
        tableView.getColumns().add(locationColumn);
        tableView.getColumns().add(addBottonColumn);
        tableView.getColumns().add(removeButtonColumn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPadding(new Insets(0, 10, 0, 10));
        tableView.setBackground(Background.fill(Paint.valueOf("ffff")));

        HBox hBoxBottom = new HBox();
        hBoxBottom.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxBottom.setBackground(Background.fill(Paint.valueOf("ffff")));
        hBoxBottom.setPadding(new Insets(15, 15, 15, 15));

        Button nextButton = new Button("next");
        nextButton.setPadding(new Insets(10, 10, 10, 10));
        nextButton.setFont(Font.font(15));

        hBoxBottom.getChildren().add(nextButton);

        borderPane.setTop(hBoxTop);
        borderPane.setBottom(hBoxBottom);
        borderPane.setCenter(tableView);

        Section.handlAddAndRemoveButtens();
        Scene scene = new Scene(borderPane, 900, 700);
        primaryStage.setTitle("ShowImage"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage\
        primaryStage.show();

        nextButton.setOnAction(e -> {
            try {
                primaryStage.setTitle("CourseOffering");
                primaryStage.setScene(courseOffering1.getSecondScene());
                primaryStage.show();
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
